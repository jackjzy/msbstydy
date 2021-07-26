package com.ruoyi.common.constant;

import org.omg.CORBA.CODESET_INCOMPATIBLE;

/**
 * 订单状态
 */
public class OrderStatus {

    /**
     * 待付款
     */
    public static final Integer WAITING_PAYMENT =1;

    /**
     *待发货
     */
    public static final Integer AITING_DELIVER = 2;

    /**
     * 待评价
     */
    public static final Integer AITING_EVALUATED = 3;

    /**
     *待收货
     */
    public static final Integer WAITING_DELIVERY = 4;

    /**
     * 待退款
     */
    public static final Integer WAITING_REFUNDED = 5;

    /**
     * 已完成
     */
    public static final Integer COMPLETED = 6;

    /**
     * 已关闭
     */
    public static final Integer CLOSED = 7;

    /**
     * 申请退款
     */
    public static final Integer AUDIT = 8;
    /**
     * 退款成功
     */
    public static final Integer REFUNDSUCCESS= 11;




}
