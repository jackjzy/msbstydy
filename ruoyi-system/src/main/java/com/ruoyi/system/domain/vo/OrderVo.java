package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class OrderVo  {

    private Long orderId;

    /**
     * 订单号
     */
    private String orderNumber;

    /** 订单商品详情 */
    private List<OrderDetailVo> orderDetails;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 订单状态 */
    private String orderStatus;

    /** 订单类型 */
    private String orderType;

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal priceSum;

    private String userName;

    private String phone;
    /**
     * 支付方式
     */
    private String payment;


}
