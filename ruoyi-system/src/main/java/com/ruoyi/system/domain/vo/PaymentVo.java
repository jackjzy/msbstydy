package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.order.FfwyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentVo extends FfwyOrder {

    private String userName;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNumber;

    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    private String patmenttype;

    /**
     * 实付金额
     */
    @Excel(name = "实付金额")
    private BigDecimal money;

    private String statusname;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用户信息
     */
    @Excel(name = "用户信息")
    private Long userId;

    /**
     * 收货地址id
     */
    @Excel(name = "收货地址id")
    private Long addrId;

    /**
     * 下单状态
     */
    @Excel(name = "下单状态")
    private Integer statusId;

    /**
     * 付款方式
     */
    @Excel(name = "付款方式")
    private Integer paymentId;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipmentsTime;

    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    /**
     * 用户电话
     */
    @Excel(name = "用户电话")
    private String phone;

    /**
     * 商城id
     */
    @Excel(name = "商城id")
    private Long shopId;

    /**
     * 套餐id
     */
    @Excel(name = "套餐id")
    private Integer combomealId;

    /**
     * 总工时
     */
    @Excel(name = "总工时")
    private Integer working;

    /**
     * 订单类型
     */
    @Excel(name = "订单类型")
    private String orderType;

    private Date bgepaymentTime;

    private Date actpaymentTime;

    private String searchStr;

    private Date endTime;

    private Integer figure;
    private BigDecimal priceSum;


}
