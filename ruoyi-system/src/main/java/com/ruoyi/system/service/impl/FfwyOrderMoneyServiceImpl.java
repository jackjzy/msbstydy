package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.FfwyPayment;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.order.FfwyOrderStatus;
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.domain.vo.FfwyOrderVo;
import com.ruoyi.system.domain.vo.FfwyorderDetailsVo;
import com.ruoyi.system.domain.vo.PaymentVo;
import com.ruoyi.system.mapper.FfwyPaymentMapper;
import com.ruoyi.system.mapper.FfwySpecificationMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.order.*;
import com.ruoyi.system.service.IFfwyOrderMoneyService;
import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyOrderMoneyServiceImpl implements IFfwyOrderMoneyService {

    @Autowired
    private FfwyPaymentMapper ffwyPaymentMapper;

    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;

    @Autowired
    private FfwySpecificationMapper ffwySpecificationMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwyOrderMoneyMapper ffwyOrderMoneyMapper;

    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;

    /**
     * 查询订单
     *
     * @param orderId 订单ID
     * @return 订单
     */
    @Override
    public List<FfwyorderDetailsVo> selectFfwyOrderById(Long orderId) {
        FfwyorderDetailsVo ffwyorderDetailsVo = new FfwyorderDetailsVo();
        List<FfwyorderDetailsVo> detailist = new ArrayList();
        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
        ffwyOrderDetails.setOrderId(orderId);
        List<FfwyOrderDetails> ffwyOrderDetails1 = ffwyOrderDetailsMapper.selectFfwyOrderDetailsList(ffwyOrderDetails);
        FfwySpecification ffwySpecification = new FfwySpecification();
        FfwyOrder ffwyOrder = new FfwyOrder();
        for (FfwyOrderDetails orderDetails : ffwyOrderDetails1) {
            ffwyorderDetailsVo.setPrice(orderDetails.getPrice());
            ffwyorderDetailsVo.setNumber(orderDetails.getNumber());
            ffwyorderDetailsVo.setPriceSum(orderDetails.getPriceSum());
            ffwySpecification.setSpecificationId(orderDetails.getSpecificationId());
            ffwyOrder.setOrderId(orderDetails.getOrderId());
        }
        Long orderId1 = ffwyOrder.getOrderId();
        // FfwyOrder ffwyOrder1 = ffwyOrderMapper.selectFfwyOrderById(orderId1);
        FfwyUser ffwyUser = new FfwyUser();
        // ffwyUser.setUserId(ffwyOrder1.getUserId());
        List<FfwyUser> ffwyUsers = ffwyUserMapper.selectFfwyUserList(ffwyUser);
        for (FfwyUser user : ffwyUsers) {
            ffwyorderDetailsVo.setUsername(user.getUserName());
            ffwyorderDetailsVo.setPhonenumber(user.getPhonenumber());
        }
        FfwyPayment ffwyPayment = new FfwyPayment();
        //ffwyPayment.setPaymentId(ffwyOrder1.getPaymentId());
        List<FfwyPayment> ffwyPayments = ffwyPaymentMapper.selectFfwyPaymentList(ffwyPayment);
        for (FfwyPayment payment : ffwyPayments) {
            ffwyorderDetailsVo.setPatmenttype(payment.getPatmentType());
        }
        List<FfwySpecification> ffwySpecifications = ffwySpecificationMapper.selectFfwySpecificationList(ffwySpecification);
        for (FfwySpecification specification : ffwySpecifications) {
            ffwyorderDetailsVo.setParentid(specification.getParentid());
            ffwyorderDetailsVo.setSpecificationName(specification.getSpecificationName());
        }
        detailist.add(ffwyorderDetailsVo);
        return detailist;
    }

    /**
     * 查询所有支付记录
     *
     * @param ffwyOrderVo 订单
     * @return 订单
     */
    @Override
    public List<PaymentVo> selectFfwyOrderListBydetail(FfwyOrderVo ffwyOrderVo) {
        List<PaymentVo> ffwyOrders = null;
        String orderStatus = ffwyOrderVo.getOrderStatus();
        String paymentName = ffwyOrderVo.getPaymentName();
        Date beginTime = ffwyOrderVo.getBeginTime();
        Date endTime = ffwyOrderVo.getEndTime();
        String orderNumber = ffwyOrderVo.getOrderNumber();
        Decimal money = ffwyOrderVo.getMoney();
        String username = ffwyOrderVo.getUserName();
        String productName = ffwyOrderVo.getProductName();
        Date createTime = ffwyOrderVo.getCreateTime();
        Integer combomealId = ffwyOrderVo.getCombomealId();
        Date paymentTime = ffwyOrderVo.getPaymentTime();
        Date shipmentsTime = ffwyOrderVo.getShipmentsTime();
        Date finishTime = ffwyOrderVo.getFinishTime();
        String phone = ffwyOrderVo.getPhone();
        Long shopId = ffwyOrderVo.getShopId();
        Integer working = ffwyOrderVo.getWorking();
        String searchStr = ffwyOrderVo.getSearchStr();
        Integer statusId = ffwyOrderVo.getStatusId();
        Integer paymentId = ffwyOrderVo.getPaymentId();
        String figure = ffwyOrderVo.getFigure();
        BigDecimal priceSum = ffwyOrderVo.getPriceSum();
        if (paymentName != null || paymentTime != null || orderStatus != null || orderNumber != null || money != null || username != null || beginTime != null || endTime != null || productName != null || createTime != null || combomealId != null || paymentTime != null || shipmentsTime != null || finishTime != null || phone != null || shopId != null || working != null || searchStr != null || statusId != null || paymentId != null) {
            ffwyOrders = ffwyOrderMoneyMapper.selectFfwyOrderListBycond(ffwyOrderVo);
        } else if (figure==null&&priceSum == null && paymentName == null && orderStatus == null && orderNumber == null && money == null && username == null && beginTime == null && endTime == null && productName == null && createTime == null && combomealId == null && paymentTime == null && shipmentsTime == null && finishTime == null && phone == null && shopId == null && working == null && searchStr == null && statusId == null && paymentId == null) {
            ffwyOrders = ffwyOrderMoneyMapper.selectFfwyOrderListAll();
            return ffwyOrders;
        } else if (figure.equals("10万以上") && figure != null) {
            ffwyOrderVo.setFigure("100000");
            ffwyOrders = ffwyOrderMoneyMapper.selectFfwyOrderListBycond(ffwyOrderVo);
            return ffwyOrders;
        } else if (figure != null&&!figure.equals("")) {
            String value = figure.substring(0, figure.indexOf("-"));
            String v = figure.substring(figure.indexOf("-") + 1).trim();
            ffwyOrderVo.setFigure(value);
            ffwyOrderVo.setNumber(v);
            ffwyOrders = ffwyOrderMoneyMapper.selectFfwyOrderListBycond(ffwyOrderVo);
            return ffwyOrders;
        }

        return ffwyOrders;
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


}
