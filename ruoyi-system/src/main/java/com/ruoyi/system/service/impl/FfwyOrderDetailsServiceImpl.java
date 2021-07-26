package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.constant.OrderStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.OrderStatusEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.aftersale.FfwyAfterStatus;
import com.ruoyi.system.domain.order.FfwyIncome;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.vo.FfwyorderDetailsVo;
import com.ruoyi.system.mapper.FfwyPayRefundMapper;
import com.ruoyi.system.mapper.FfwyPaymentMapper;
import com.ruoyi.system.mapper.aftersale.FfwyAfterSaleMapper;
import com.ruoyi.system.mapper.order.FfwyConsigneeAddrMapper;
import com.ruoyi.system.mapper.order.FfwyIncomeMapper;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.mapper.product.FfwyProductCommentMapper;
import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.vo.FfwyorderdespeVo;
import com.ruoyi.system.mapper.FfwyorderdespeMapper;
import com.ruoyi.system.service.IFfwyPayRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.service.IFfwyOrderDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单详情Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FfwyOrderDetailsServiceImpl implements IFfwyOrderDetailsService {
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;

    @Autowired
    private FfwyProductCommentMapper ffwyProductCommentMapper;
    @Autowired
    private FfwyorderdespeMapper ffwyorderdespeMapper;
    @Autowired
    private FfwyConsigneeAddrMapper ffwyConsigneeAddrMapper;
    @Autowired
    private FfwyPaymentMapper ffwyPaymentMapper;
    @Autowired
    private FfwyIncomeMapper ffwyIncomeMapper;
    @Autowired
    private FfwyAfterSaleMapper ffwyAfterSaleMapper;
    @Autowired
    private IFfwyPayRefundService iFfwyPayRefundService;
    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;
    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    /**
     * 查询订单详情
     *
     * @param orderDetailsId 订单详情ID
     * @return 订单详情
     */
    @Override
    public FfwyOrderDetails selectFfwyOrderDetailsById(Long orderDetailsId) {
        return ffwyOrderDetailsMapper.selectFfwyOrderDetailsById(orderDetailsId);
    }

    /**
     * 查询订单详情列表
     *
     * @param ffwyOrderDetails 订单详情
     * @return 订单详情
     */
    @Override
    public List<FfwyOrderDetails> selectFfwyOrderDetailsList(FfwyOrderDetails ffwyOrderDetails) {
        return ffwyOrderDetailsMapper.selectFfwyOrderDetailsList(ffwyOrderDetails);
    }

    @Override
    public List<FfwyOrderDetails> backOrderDetailsList(FfwyOrderDetails ffwyOrderDetails) {
        return ffwyOrderDetailsMapper.backOrderDetailsList(ffwyOrderDetails);
    }

    /**
     * 新增订单详情
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    @Override
    public int insertFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails) {
        return ffwyOrderDetailsMapper.insertFfwyOrderDetails(ffwyOrderDetails);
    }

    /**
     * 修改订单详情
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    @Override
    public int updateFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails) {
        if (ffwyOrderDetails.getOrderStatus() == 6) {
            List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderById(ffwyOrderDetails.getOrderDetailsId());
            orderDetails.forEach(ffwyOrderDetails1 -> {
                FfwyIncome ffwyIncome = new FfwyIncome();
                ffwyIncome.setOrderNumber(ffwyOrderDetails1.getOrderNumber());
                ffwyIncome.setPaymentId(Integer.valueOf(ffwyOrderDetails1.getPayId()));
                ffwyIncome.setMoney(ffwyOrderDetails1.getPriceSum());
                ffwyIncome.setOrderId(ffwyOrderDetails1.getOrderId());
                ffwyIncome.setUserId(ffwyOrderDetails1.getUserId());
                ffwyIncomeMapper.insertFfwyIncome(ffwyIncome);
            });
        } else if (ffwyOrderDetails.getOrderStatus() == 3) {
            Date date = DateUtil.date();
            //成交时间
            ffwyOrderDetails.setKnockdownTime(date);
            ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(ffwyOrderDetails);
        }
        if (ffwyOrderDetails.getOrderId() != null) {
            FfwyOrder ffwyOrder = new FfwyOrder();
            ffwyOrder.setOrderId(ffwyOrderDetails.getOrderId());
            ffwyOrder.setStatusId(ffwyOrderDetails.getOrderStatus());
            int i = ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
        }

        return ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(ffwyOrderDetails);
    }

    @Override
    public int shipments(FfwyOrderDetails ffwyOrderDetails) {
        //修改订单状态为待收货
        Integer Orderstatus = Integer.valueOf(OrderStatusEnum.RENOVATIONING.getCode());
        ffwyOrderDetails.setOrderStatus(Orderstatus);
        ffwyOrderDetails.setDeliverTime(DateUtils.getNowDate());
        return ffwyOrderDetailsMapper.updateFfwyOrderDetails(ffwyOrderDetails);
    }

    @Override
    public int reject(FfwyOrderDetails ffwyOrderDetails) {
        //售后状态为拒绝退款
        Integer Orderstatus = Integer.valueOf(OrderStatusEnum.ATTER_COMPLETE.getCode());
        //修改订单状态
        ffwyOrderDetails.setOrderStatus(Orderstatus);
        //修改和新增售后信息
        chageAfterSaleStatus(ffwyOrderDetails.getAfterSaleId(), Orderstatus);
        return ffwyOrderDetailsMapper.updateFfwyOrderDetails(ffwyOrderDetails);
    }

    @Override
    public int agree(FfwyOrderDetails ffwyOrderDetails) {
        /*正式环境下修改*/
        FfwyOrderDetails ffwyOrderDetails1 = selectFfwyOrderDetailsById(ffwyOrderDetails.getOrderDetailsId());
        boolean refun = iFfwyPayRefundService.refun(ffwyOrderDetails1);
        if (refun){
            //售后状态为退款成功
            Integer Orderstatus = Integer.valueOf(OrderStatusEnum.REFUND_SUCCESSFUL.getCode());
            //修改订单状态
            ffwyOrderDetails.setOrderStatus(Orderstatus);
            //修改和新增售后信息
            chageAfterSaleStatus(ffwyOrderDetails.getAfterSaleId(), Orderstatus);
            return ffwyOrderDetailsMapper.updateFfwyOrderDetails(ffwyOrderDetails);
        }

        return -2;

    }

    @Override
    public int agreeAndSales(FfwyOrderDetails ffwyOrderDetails) {


            //售后状态为退货中
            Integer Orderstatus = Integer.valueOf(OrderStatusEnum.RETURNING_GOODS.getCode());
            //修改订单状态
            ffwyOrderDetails.setOrderStatus(Orderstatus);
            //修改和新增售后信息
            chageAfterSaleStatus(ffwyOrderDetails.getAfterSaleId(), Orderstatus);
            return ffwyOrderDetailsMapper.updateFfwyOrderDetails(ffwyOrderDetails);




    }

    /**
     * 批量删除订单详情
     *
     * @param ffwyOrder 需要删除的订单详情ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderDetailsByIds(FfwyOrder ffwyOrder) {
        int i = ffwyOrderMapper.deleteFfwyOrderById(ffwyOrder.getOrderId());
        return ffwyOrderDetailsMapper.deleteFfwyOrderDetailsByIds(ffwyOrder.getOrderId());
    }

    /**
     * 删除订单详情信息
     *
     * @param orderDetailsId 订单详情ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderDetailsById(Long orderDetailsId) {
        return ffwyOrderDetailsMapper.deleteFfwyOrderDetailsById(orderDetailsId);
    }

    @Override
    public List<FfwyOrderDetails> selectFfwyOrderStatusList(FfwyOrderDetails order) {
        return ffwyOrderDetailsMapper.selectFfwyOrderStatusList(order);
    }

    @Override
    public List<FfwyOrderDetails> selectFfwyOrderAllList(FfwyOrderDetails order) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        order.setUserId(userId);
        List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderAllList(order);
//        int count = 0;
//        for (int i = 0; i < orderDetails.size(); i++) {
//            String orderNumber = orderDetails.get(i).getOrderNumber();
//            FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
//            ffwyOrderDetails.setUserId(userId);
//            ffwyOrderDetails.setOrderNumber(orderNumber);
//            List<FfwyOrderDetails> orderDetails1 = ffwyOrderDetailsMapper.selectFfwyOrderAllList(ffwyOrderDetails);
//
//            if (orderDetails1.get(0).getOrderNumber().equals(orderDetails.get(i).getOrderNumber())) {
//                if (count == 0) {
//                    count++;
//                    orderDetails.forEach(od -> {
//                        od.setDetails(orderDetails1);
//                    });
//                }
//            }
//        }
        return orderDetails;
    }

    @Override
    public AjaxResult selectFfwyOrderUnpaidOrdersList(FfwyOrderDetails order) {
        //剩余时间
        //买家留言
        //商品信息
        //商品总价
        //运费
        //需付款金额
        //订单编号
        //支付方式
        //创建时间
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        order.setUserId(userId);
        List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetails(order);
        if (orderDetails.size() > 0) {
            //待收货
            if (orderDetails.get(0).getOrderStatus().equals(OrderStatus.AITING_DELIVER) ||
                    orderDetails.get(0).getOrderStatus().equals(OrderStatus.WAITING_DELIVERY) ||
                    orderDetails.get(0).getOrderStatus().equals(OrderStatus.WAITING_REFUNDED) ||
                    orderDetails.get(0).getOrderStatus().equals(OrderStatus.COMPLETED) ||
                    orderDetails.get(0).getOrderStatus().equals(OrderStatus.CLOSED) ||
                    orderDetails.get(0).getOrderStatus().equals(OrderStatus.AITING_EVALUATED) ||
                    orderDetails.get(0).getOrderStatus().equals(OrderStatus.AUDIT)
            ) {
                List<FfwyOrderDetails> orderDetails1 = ffwyOrderDetailsMapper.selectFfwyOrderDetails(order);
                orderDetails1.forEach(ffwyOrderDetails -> {
                    ffwyOrderDetails.setProductPhoto(prefixUrl + ffwyOrderDetails.getProductPhoto());
                });
                order.setDetails(orderDetails1);
                order.setAddr(ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrById(order.getDetails().get(0).getAddrId()));
                FfwyOrder ffwyOrder = new FfwyOrder();
                ffwyOrder.setOrderId(order.getOrderId());
                List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderList(ffwyOrder);
                //订单类型
                if (order.getDetails().get(0).getPayId() == null) {
                    order.getDetails().get(0).setPayId(null);
                    order.setPatmentType(null);
                } else if (Integer.valueOf(order.getDetails().get(0).getPayId()) != null) {
                    order.setPatmentType(Integer.valueOf(order.getDetails().get(0).getPayId()));
                }
                if (order.getDetails().get(0).getOrderNumber() != null) {
                    order.setOrderNumber(order.getDetails().get(0).getOrderNumber());
                }
                if (order.getDetails().get(0).getCreateTime() != null) {
                    //创建时间
                    order.setCreateTime(order.getDetails().get(0).getCreateTime());
                }
                if (order.getDetails().get(0).getPaymentTime() != null) {
                    //支付时间
                    order.setPaymentTime(order.getDetails().get(0).getPaymentTime());
                }
                if (order.getDetails().get(0).getDeliverTime() != null) {
                    //发货时间
                    order.setDeliverTime(order.getDetails().get(0).getDeliverTime());
                }
                if (order.getDetails().get(0).getFreight() != null) {
                    //快递费
                    order.setFreight(order.getDetails().get(0).getFreight());
                }
                if (order.getDetails().get(0).getPriceSum() != null) {
                    //实际付款
                    order.setPriceSum(order.getDetails().get(0).getPriceSum());
                }
                if (order.getPriceSum() != null) {
                    //总价
                    BigDecimal subtract = order.getPriceSum().subtract(order.getFreight());
                    order.setPrice(subtract);
                }
                if (ffwyOrders.get(0).getMessage() != null) {
                    //买家留言
                    order.setMessage(ffwyOrders.get(0).getMessage());
                }
                if (order.getDetails().get(0).getTrackIngName() != null) {
                    //快递名称
                    order.setTrackIngName(order.getDetails().get(0).getTrackIngName());
                }
                if (order.getDetails().get(0).getKnockdownTime() != null) {
                    //成交时间
                    order.setKnockdownTime(order.getDetails().get(0).getKnockdownTime());
                }
                if (ffwyOrders.get(0).getStatusId() != null) {
                    //订单状态
                    order.setStatusId(ffwyOrders.get(0).getStatusId());
                }
                if (order.getDetails().get(0).getTrackIngNumber() != null) {
                    //快递单号
                    order.setTrackIngNumber(order.getDetails().get(0).getTrackIngNumber());
                }
                return AjaxResult.success("查询订单详情成功！", order);
                //用户的收货地址
            } else if (orderDetails.get(0).getOrderStatus().equals(OrderStatus.WAITING_PAYMENT)) {
//                if (order.getMessage() != null && order.getMessage().equals("")) {
//                    FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
//                    ffwyOrderDetails.setOrderDetailsId(orderDetails.get(0).getOrderDetailsId());
//                    ffwyOrderDetails.setMessage(order.getMessage());
//                    ffwyOrderDetailsMapper.updateFfwyOrderDetails(ffwyOrderDetails);
//                }
                order.setMessage(orderDetails.get(0).getMessage());
                order.setAddr(ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrById(orderDetails.get(0).getAddrId()));
                order.setFfwyOrderDetails(orderDetails.get(0));
                List<FfwyOrderDetails> orderDetails1 = ffwyOrderDetailsMapper.selectFfwyOrderDetails(order);
                orderDetails1.forEach(ffwyOrderDetails -> {
                    ffwyOrderDetails.setProductPhoto(prefixUrl + ffwyOrderDetails.getProductPhoto());
                });
                order.setDetails(orderDetails1);
                if (!order.getDetails().get(0).getFreight().equals("")) {
                    order.setFreight(order.getDetails().get(0).getFreight());
                }
                if (order.getDetails().get(0).getPrice() != null) {
                    Integer number = order.getDetails().get(0).getNumber();
                    BigDecimal bigDecimal =
                            new BigDecimal(Integer.parseInt(number.toString()));
                    order.setPrice(order.getDetails().get(0).getPrice().multiply(bigDecimal));
                }
                if (order.getDetails().get(0).getPriceSum() != null) {
                    order.setPriceSum(order.getDetails().get(0).getPriceSum());
                }
                if (order.getDetails().get(0).getOrderNumber() != null) {

                    order.setOrderNumber(order.getDetails().get(0).getOrderNumber());
                }
                if (order.getDetails().get(0).getCreateTime() != null) {
                    order.setCreateTime(order.getDetails().get(0).getCreateTime());
                }
                if (order.getDetails().get(0).getTrackIngName() != null) {
                    order.setTrackIngName(order.getDetails().get(0).getTrackIngName());
                }
                if (order.getDetails().get(0).getOrderStatus() != null) {
                    order.setStatusId(order.getDetails().get(0).getOrderStatus());
                }
                return AjaxResult.success("待付款订单详情查询成功", order);
            }
        }
        return AjaxResult.error("没有此查询订单详情");

    }

    @Override
    public AjaxResult publishEvaluate(FfwyOrderDetails order) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        if (userId != null) {
            //判断是否有商品评价信息如果有就不添加
            FfwyProductComment ffwyOrderCombomeal = new FfwyProductComment();
            ffwyOrderCombomeal.setUserId(userId);
            ffwyOrderCombomeal.setOrderId(order.getOrderId());
            List<FfwyProductComment> ffwyProductComments = ffwyProductCommentMapper.selectFfwyProductCommentList(ffwyOrderCombomeal);
            for (int i = 0; i < ffwyProductComments.size(); i++) {
                if (ffwyProductComments.get(i).getOrderId() == null) {
                    order.setOrderStatus(3);
                    List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectpublishEvaluate(order);
                    orderDetails.forEach(ffwyOrderDetails -> {
                        ffwyOrderDetails.setProductPhoto(prefixUrl + ffwyOrderDetails.getProductPhoto());
                    });
                    return AjaxResult.success("查询该商品无评价", orderDetails);
                }
            }
            if (ffwyProductComments.size() == 0) {
                order.setOrderStatus(3);
                List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectpublishEvaluate(order);
                orderDetails.forEach(ffwyOrderDetails -> {
                    ffwyOrderDetails.setProductPhoto(prefixUrl + ffwyOrderDetails.getProductPhoto());
                });
                return AjaxResult.success("查询该商品无评价", orderDetails);

            }
        }
        return AjaxResult.error("查询该商品有评价");

    }

    @Override
    public AjaxResult selectFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        ffwyOrderDetails.setUserId(userId);
        List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectpublishEvaluate(ffwyOrderDetails);
        orderDetails.forEach(ffwyOrderDetails1 -> {
            ffwyOrderDetails1.setProductPhoto(prefixUrl + ffwyOrderDetails1.getProductPhoto());
        });
        return AjaxResult.success("查询未评价！", orderDetails);
    }

    @Override
    public List<FfwyorderdespeVo> selectFfwyorderdetailsByseales(Long userId) {
        List<FfwyorderdespeVo> ffwyorderdespeVos = ffwyorderdespeMapper.selectFfwyorderdetailsByseales(userId);

        for (FfwyorderdespeVo ffwyorderdespeVo : ffwyorderdespeVos) {
            ffwyorderdespeVo.setProductPhoto(prefixUrl + ffwyorderdespeVo.getProductPhoto());
            List<FfwyOrderDetails> ffwyOrderDetailss = new ArrayList<>();
            String orderDetailsId = ffwyorderdespeVo.getOrderDetailsId();
            String[] split = orderDetailsId.split(",");
            BigDecimal zongj = new BigDecimal("0");
            for (String s : split) {
                FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                ffwyOrderDetails.setOrderDetailsId(Long.valueOf(s));
                List<FfwyOrderDetails> ffwyOrderDetails1 = ffwyOrderDetailsMapper.selectpublishEvaluate(ffwyOrderDetails);
                for (FfwyOrderDetails orderDetails : ffwyOrderDetails1) {
                    orderDetails.setProductPhoto(prefixUrl + orderDetails.getProductPhoto());
                    BigDecimal priceSum = orderDetails.getPriceSum();
                    zongj = zongj.add(priceSum);
                }
                ffwyOrderDetailss.addAll(ffwyOrderDetails1);
            }
            ffwyorderdespeVo.setFfwyOrderDetails(ffwyOrderDetailss);
            ffwyorderdespeVo.setZongj(zongj);

        }

        return ffwyorderdespeVos;
    }

    @Override
    public List<FfwyAfterSale> selectFfwyOrderDetailsByorderDetailsId(Integer orderDetailsId) {
        return ffwyOrderDetailsMapper.selectFfwyOrderDetailsByorderDetailsId(orderDetailsId);
    }

    private void chageAfterSaleStatus(Long saleId, Integer Orderstatus) {

        //新增售后状态
        FfwyAfterStatus status = new FfwyAfterStatus();
        status.setSaleId(saleId);
        status.setAuditStatusId(Orderstatus);
        status.setCreateTime(new Date());
        ffwyAfterSaleMapper.insertFfwyAfterStatus(status);
        //修改售后信息
        FfwyAfterSale ffwyAfterSale = new FfwyAfterSale();
        ffwyAfterSale.setAfterSaleid(saleId);
        ffwyAfterSale.setAfterStatus(Orderstatus.toString());
        ffwyAfterSale.setUpdateTime(DateUtils.getNowDate());
        ffwyAfterSaleMapper.updateFfwyAfterSale(ffwyAfterSale);
    }
}
