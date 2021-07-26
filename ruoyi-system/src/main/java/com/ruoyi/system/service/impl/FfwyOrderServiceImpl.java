package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.OrderStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.OrderComStatusEnum;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.FfwyPayment;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.cart.FfwyCartInfo;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.order.FfwyOrderStatus;
import com.ruoyi.system.domain.product.FfwyProductCategory;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.FfwyPaymentHistoryMapper;
import com.ruoyi.system.mapper.FfwyPaymentMapper;
import com.ruoyi.system.mapper.FfwySpecificationMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.cart.FfwyCartInfoMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.order.FfwyOrderStatusMapper;
import com.ruoyi.system.mapper.product.FfwyProductCategoryMapper;
import com.ruoyi.system.mapper.product.FfwyProductCommentMapper;
import com.ruoyi.system.mapper.product.FfwyProductSkuMapper;
import com.ruoyi.system.service.IFfwyProductSkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hpsf.Decimal;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.service.IFfwyOrderService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Slf4j
@Service
public class FfwyOrderServiceImpl implements IFfwyOrderService {
    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;

    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;

    @Autowired
    private FfwyProductSkuMapper ffwyProductSkuMapper;

    @Autowired
    private FfwyOrderStatusMapper ffwyOrderStatusMapper;

    @Autowired
    private FfwyPaymentMapper ffwyPaymentMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwySpecificationMapper ffwySpecificationMapper;
    @Autowired
    private FfwyOrderStatusMapper orderStatusMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IFfwyProductSkuService ifwyProductSkuService;
    @Autowired
    private FfwyProductCommentMapper ffwyProductCommentMapper;
    @Autowired
    private FfwyProductCategoryMapper ffwyProductCategoryMapper;
    @Autowired
    private FfwyPaymentHistoryMapper ffwyPaymentHistoryMapper;
    @Autowired
    private FfwyCartInfoMapper ffwyCartInfoMapper;
    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    /**
     * 查询订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    @Override
    public FfwyOrder selectFfwyOrderById(Long orderId, Long shopId) {
        return ffwyOrderMapper.selectFfwyOrderById(orderId, shopId);
    }

    @Override
    public OrderVo selectFfwyOrderByOrderNumber(Long shopId, String orderNumber) {

        FfwyOrder ffwyOrder = ffwyOrderMapper.selectFfwyOrderByOrderNumber(shopId, orderNumber);
        if (null == ffwyOrder) {
            return null;
        }
        OrderVo orderVo = new OrderVo();
        assembleOrderVos(orderVo, ffwyOrder);
        return orderVo;
    }

    /**
     * 查询订单列表
     *
     * @param orders 订单
     * @return 订单
     */
    @Override
    public List<OrderVo> selectFfwyOrderList(Orders orders) {
        List<OrderVo> orderVos = new ArrayList<>();

        //1. 根据订单id查询
        if (orders.getOrderId() != null) {
            OrderVo orderVo = new OrderVo();
            assembleOrderVos(orderVo, this.selectFfwyOrderById(orders.getUserId(), null));
            orderVos.add(orderVo);
            return orderVos;
        }

        List<FfwyOrder> list = ffwyOrderMapper.selectFfwyOrderList(new FfwyOrder(orders.getSearchField(), orders.getOrderStatus(), orders.getBeginPrice(), orders.getEndPrice(), orders.getBeginCreateTime(), orders.getEndCreateTime(), orders.getShopId()));
        boolean sign;
        if (orders.getCategoryLevel() != null) {
            for (int i = 0; i < list.size(); i++) {
                sign = false;
                List<FfwyOrderDetails> ffwyOrderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsList(new FfwyOrderDetails(list.get(i).getOrderId()));
                for (FfwyOrderDetails ffwyOrderDetail : ffwyOrderDetails) {
                    if (orders.getCategoryLevel().equals(ffwyOrderDetail.getCategoryLevel())) {
                        sign = true;
                        break;
                    }
                }
                if (!sign) {
                    list.remove(i);
                    i--;
                }
            }

        }

        list.forEach(forder -> {
            OrderVo orderVo = new OrderVo();
            assembleOrderVos(orderVo, forder);
            orderVos.add(orderVo);
        });

        return orderVos;
    }

    /**
     * 新增订单
     *
     * @param ffwyOrder 订单
     * @return 结果
     */
    @Override
    public int insertFfwyOrder(FfwyOrder ffwyOrder) {
        ffwyOrder.setCreateTime(DateUtils.getNowDate());
        return ffwyOrderMapper.insertFfwyOrder(ffwyOrder);
    }

    @Override
    public int shipmentsFfwyOrder(Integer orderId, String ffwyOrder) {
        return ffwyOrderMapper.updateFfwyOrder(new FfwyOrder(Long.valueOf(orderId), OrderStatus.WAITING_DELIVERY, null));
    }

    @Override
    public int refundFfwyOrder(Integer orderId, boolean agreeToRefuse) {
        if (agreeToRefuse) {
            return ffwyOrderMapper.updateFfwyOrder(new FfwyOrder(Long.valueOf(orderId), OrderStatus.WAITING_REFUNDED, null));
        }
        return 0;
    }

    @Override
    public int cancelFfwyOrder(Integer orderId) {
        return ffwyOrderMapper.updateFfwyOrder(new FfwyOrder(Long.valueOf(orderId), OrderStatus.COMPLETED, null));
    }

    /**
     * 修改订单
     *
     * @param ffwyOrder 订单
     * @return 结果
     */
    @Override
    public int updateFfwyOrder(FfwyOrder ffwyOrder) {
        return ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
    }

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderByIds(Long[] orderIds) {
        return ffwyOrderMapper.deleteFfwyOrderByIds(orderIds);
    }

    /**
     * 删除订单信息
     *
     * @param orderId 订单ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderById(Long orderId) {
        return ffwyOrderMapper.deleteFfwyOrderById(orderId);
    }


    private void assembleOrderVos(OrderVo orderVo, FfwyOrder ffwyOrder) {
        // 1. 组装订单属性
        orderVo.setOrderId(ffwyOrder.getOrderId());
        orderVo.setOrderNumber(ffwyOrder.getOrderNumber());
        orderVo.setOrderStatus(ffwyOrderStatusMapper.selectFfwyOrderStatusById(Long.valueOf(ffwyOrder.getStatusId())));
        orderVo.setUserName(ffwyOrder.getUserName());
        orderVo.setPhone(ffwyOrder.getPhone());
        orderVo.setPriceSum(ffwyOrder.getMoney());
        orderVo.setPayment(ffwyPaymentMapper.selectpatmentTypeById(ffwyOrder.getPaymentId()));
        orderVo.setCreateTime(ffwyOrder.getCreateTime());
        orderVo.setOrderType(ffwyOrder.getOrderType());

        //2. 组装订单详情属性
        List<OrderDetailVo> detailVos = new ArrayList<>();
        List<FfwyOrderDetails> ffwyOrderDetailList = ffwyOrderDetailsMapper.selectFfwyOrderDetailsList(new FfwyOrderDetails(ffwyOrder.getOrderId(), null, null));
        ffwyOrderDetailList.forEach(detail -> {
            FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(detail.getProductSkuId());
            if (null == ffwyProductSku) {
                return;
            }
            detailVos.add(new OrderDetailVo(
                    ffwyProductSku.getSkuName(),
                    ffwyProductSku.getSkuId(),
                    ffwyProductSku.getSkuDefaultImg(),
                    ffwyProductSku.getCatalogId(),
                    detail.getPrice(),
                    detail.getNumber(),
                    ffwyProductSku.getStock(),
                    ffwyProductSku.getStockUnit(),
                    ffwyProductSku.getSaleCount(),
                    ffwyProductSku.getSkuSpec()
            ));
        });

        orderVo.setOrderDetails(detailVos);
    }

    @Transactional
    @Override
    public AjaxResult saveOrderInfo(FfwyOrder orderInfo) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
            //判断不同商家
            List<FfwyOrderDetails> detailList = orderInfo.getOrderDetailList();
            //拿到商家id
            Map<Long, List<Long>> map = new HashMap<>();
            for (FfwyOrderDetails num : detailList) {
                // map是否包含此key，若已经包含则添加一个新的数字到对应value集合中
                if (map.containsKey(num.getShopId())) {
                    map.get(num.getShopId()).add(num.getShopId());
                } else {
                    // map不包含此key，则重新创建一个新集合，并把这个数字添加进集合
                    // ，再把集合放到map中
                    List<Long> newList = new ArrayList<>();
                    newList.add(num.getShopId());
                    map.put(num.getShopId(), newList);
                }
            }
            //订单描述
            // 2.库存锁定，只要有异常，回滚订单数据
            //2 :订单详情表  商品清单

            WareSkuLockVo lockVo = new WareSkuLockVo();
            for (Long key : map.keySet()) {
                List<Long> longs = map.get(key);
                for (int i = 0; i < longs.size(); i++) {
                    if (i == 0) {
                        int sum = 0;
                        for (int j = 0; j < detailList.size(); j++) {
                            if (sum == 0) {
                                if (detailList.get(j).getShopId().equals(longs.get(i))) {
                                    List<FfwyOrder> materialList = new ArrayList<>();
                                    orderInfo.setStatusId(OrderStatus.WAITING_PAYMENT);
                                    //订单号  毫秒+随机数
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append(System.currentTimeMillis()).append(new Random().nextInt(1000));
                                    orderInfo.setOrderNumber(stringBuilder.toString());
                                    lockVo.setOrderSn(stringBuilder.toString());
                                    //生成订单时间
                                    orderInfo.setCreateTime(new Date());
                                    //过期时间  当前时间 追加 1天  明天
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(Calendar.MINUTE, 30);
                                    Date time = calendar.getTime();
                                    //过期时间
                                    orderInfo.setExpireTime(time);
                                    //进度
                                    orderInfo.setOrderType(OrderStatus.WAITING_PAYMENT.toString());
                                    //总金额
                                    BigDecimal totalAmount = new BigDecimal("0");
                                    for (int q = 0; q < detailList.size(); q++) {
                                        if (detailList.get(q).getShopId().equals(longs.get(i))) {
                                            totalAmount =
                                                    totalAmount.add(detailList.get(q).getPrice().multiply(new BigDecimal(detailList.get(q).getNumber())).add(detailList.get(q).getFreight()));
                                        }
                                    }
                                    orderInfo.setMoney(totalAmount);
                                    //用户的id
                                    orderInfo.setUserId(userId);
                                    //地址id
                                    orderInfo.setAddrId(orderInfo.getAddrId());
                                    //名字
                                    orderInfo.setUserName(detailList.get(j).getUserName());
                                    //手机号
                                    orderInfo.setPhone(detailList.get(j).getPhone());
                                    //运费
                                    orderInfo.setFreight(detailList.get(j).getFreight());
                                    //店铺id
                                    orderInfo.setShopId(detailList.get(j).getShopId());
                                    //商品名字
                                    //根据商品ID和规格名字去拿到skuid和图片
                                    FfwyProductSku ffwyProductSku = new FfwyProductSku();
                                    ffwyProductSku.setSkuId(detailList.get(j).getSkuId());
                                    List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyProductSkuList(ffwyProductSku);
                                    orderInfo.setProductName(ffwyProductSkus.get(0).getProductName());
                                    //单价
                                    orderInfo.setPrice(detailList.get(j).getPrice());
                                    //留言
                                    orderInfo.setMessage(orderInfo.getMessage());

                                    sum++;
                                    ffwyOrderMapper.insertFfwyOrder(orderInfo);
                                    materialList.add(orderInfo);
                                    lockVo.setProLocks(materialList);
                                    //给MQ发送消息
                                    log.info("lockVo:{}", materialList.size());
                                    rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", JSONObject.toJSON(lockVo));
                                    //删除购物车
                                    ffwyCartInfoMapper.deleteFfwyCartInfoSkuId(detailList.get(i).getSkuId());
                                }
                            }

                        }
                        //判断不同店铺id
                        FfwyOrderDetails orderDetail = new FfwyOrderDetails();
                        //订单号
                        orderDetail.setOrderNumber(orderInfo.getOrderNumber());
                        //外键
                        orderDetail.setOrderId(orderInfo.getOrderId());
                        //店铺id
                        orderDetail.setShopId(longs.get(i));
                        //用户名称
                        orderDetail.setUserName(orderInfo.getUserName());
                        //手机号
                        orderDetail.setPhone(orderInfo.getPhone());
                        for (int k = 0; k < detailList.size(); k++) {
                            if (detailList.get(k).getShopId().equals(longs.get(i))) {
                                //skuid
                                orderDetail.setProductSkuId(orderInfo.getSkuId());
                                //根据商品ID和规格名字去拿到skuid和图片
                                FfwyProductSku ffwyProductSku = new FfwyProductSku();
                                ffwyProductSku.setSkuId(detailList.get(k).getSkuId());
                                List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyProductSkuList(ffwyProductSku);
                                Long skuId = ffwyProductSkus.get(0).getSkuId();
                                String photo = ffwyProductSkus.get(0).getSkuDefaultImg();
                                //规格id
                                orderDetail.setProductSkuId(skuId);
                                //商品图片
                                orderDetail.setProductPhoto(photo);
                                //sku名字
                                orderDetail.setProductSkuName(ffwyProductSkus.get(0).getSkuName());
                                //规格名字
                                orderDetail.setProductSkuSpec(ffwyProductSkus.get(0).getSkuSpec());
                                //运费
                                orderDetail.setFreight(orderInfo.getFreight());
                                //时间
                                orderDetail.setCateateTime(DateUtils.getNowDate());
                                //收货地址
                                orderDetail.setAddrId(orderInfo.getAddrId());
                                //商品类目
                                Long catalogId = ffwyProductSkus.get(0).getCatalogId();
                                //商品id
                                orderDetail.setProductId(ffwyProductSkus.get(0).getProductId());
                                //数量
                                orderDetail.setNumber(detailList.get(k).getNumber());
                                //价格
                                orderDetail.setPrice(orderInfo.getPrice());
                                //小计
                                orderDetail.setPriceSum(orderInfo.getMoney());
                                //userId
                                orderDetail.setUserId(userId);
                                //订单状态
                                orderDetail.setOrderStatus(OrderStatus.WAITING_PAYMENT);
                                //创建时间
                                orderDetail.setCreateTime(new Date());
                                //商品id
                                orderDetail.setProductId(ffwyProductSkus.get(0).getProductId());
                                FfwyProductCategory ffwyProductCategory = ffwyProductCategoryMapper.selectFfwyProductCategoryById(catalogId);
                                if (ffwyProductCategory == null) {
                                    orderDetail.setCategoryName(null);
                                } else if (ffwyProductCategory.getCategoryName() != null) {
                                    orderDetail.setCategoryName(ffwyProductCategory.getCategoryName());
                                }
                                ffwyOrderDetailsMapper.insertFfwyOrderDetails(orderDetail);

                            }
                        }


                    }
                }
            }
            return AjaxResult.success("生成订单成功!", orderInfo);
        }

        return AjaxResult.error("没有token,生成订单失败");

    }


    //    @Override
//    public List<FfwyOrder> selectFfwyOrderStatusList(FfwyOrder order) {
//        return ffwyOrderMapper.selectFfwyOrderStatusList(order);
//
//    }
//
    @Override
    public List<FfwyOrder> selectFfwyOrderallList(FfwyOrder order) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        order.setUserId(userId);
        List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderListAll(order);
        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
        ffwyOrders.forEach(forder -> {
            ffwyOrderDetails.setUserId(userId);
            ffwyOrderDetails.setOrderId(forder.getOrderId());
            ffwyOrderDetails.setOrderStatus(order.getOrderStatus());
            ffwyOrderDetails.setOrderNumber(forder.getOrderNumber());
            List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderAllList(ffwyOrderDetails);
            orderDetails.forEach(orderDetails1 -> {
                orderDetails1.setProductPhoto(prefixUrl + orderDetails1.getProductPhoto());
                forder.setTrackIngNumber(orderDetails1.getTrackIngNumber());
            });
            forder.setOrderDetailList(orderDetails);
        });
        return ffwyOrders;

    }

    @Override
    public AjaxResult selectFfwyOrderList(FfwyOrder order) {
        order.setOrderId(order.getOrderId());
        order.setPaymentId(order.getPaymentId());
        List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderList(order);
        if (ffwyOrders.size()>0){
            return AjaxResult.success(ffwyOrders);
        }
        return AjaxResult.error("查找失败");
    }

    @Override
    public AjaxResult selectFfwyOrderType(Long orderId, String type) {
        String s = ffwyOrderMapper.selectFfwyOrderType(orderId);
        return AjaxResult.success("订单状态",s);
    }

    @Override
    public List<PaymentHistoryVo> selectFfwyOrderandCombomealList(Long userId) {
        List<PaymentHistoryVo> paymentHistoryVos = null;
        PaymentHistoryVo paymentHistoryVo = new PaymentHistoryVo();
        paymentHistoryVo.setUserId(userId);
        paymentHistoryVo.setOrderStatus("4");
        paymentHistoryVo.setStatusId(6);
        paymentHistoryVos = ffwyPaymentHistoryMapper.selectOrderpaymentAll(paymentHistoryVo);
        return paymentHistoryVos;
    }


    @Override
    public void combomaealUnlockStock(WareSkuLockVo lockVo) {
        log.info("准备商品关闭订单! {}", lockVo.getOrderSn());
        //查询订单
        FfwyOrder order =
                ffwyOrderMapper.selectFfwyOrderCombomaealUnlockStock(lockVo.getOrderSn());
        log.info("orderDetails:{}", order);

        //判断订单的状态
        log.info("orderDetails!null:{}", order);
        if (order.getStatusId().equals(OrderStatus.WAITING_PAYMENT)) {
            if (order != null) {
                log.info(" order" +
                        "Details.getOrderId():{}", order.getOrderId());
                FfwyOrderDetails orderDetails = new FfwyOrderDetails();
                orderDetails.setOrderStatus(OrderStatus.CLOSED);
                orderDetails.setOrderId(order.getOrderId());
                ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(orderDetails);
                FfwyOrder ffwyOrder = new FfwyOrder();
                ffwyOrder.setStatusId(OrderStatus.CLOSED);
                ffwyOrder.setOrderId(order.getOrderId());
                ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
            } else {
                rabbitTemplate.convertAndSend("order.release.order.queue.com", JSONObject.toJSON(lockVo));
            }
        }
    }

//    @Override
//    public AjaxResult publishEvaluate(FfwyOrder order) {
//        //获取真实用户的id
//        //随意在任何地方获取请求响应对象
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        //真实用户
//        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
//        if (userId != null) {
//            //判断是否有商品评价信息如果有就不添加
//            FfwyProductComment ffwyOrderCombomeal = new FfwyProductComment();
//            ffwyOrderCombomeal.setOrderId(order.getOrderId());
//            List<FfwyProductComment> ffwyProductComments = ffwyProductCommentMapper.selectFfwyProductCommentList(ffwyOrderCombomeal);
//            for (int i = 0; i < ffwyProductComments.size(); i++) {
//                if (ffwyProductComments.get(i).getOrderId() == null) {
//                    return AjaxResult.success("查询该商品无评价", ffwyOrderMapper.publishEvaluate(order));
//                }
//            }
//        }
//        return AjaxResult.error("查询该商品有评价");
//
//    }


}

