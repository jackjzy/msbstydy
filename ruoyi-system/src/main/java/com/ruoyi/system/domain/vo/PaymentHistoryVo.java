package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentHistoryVo {
    /**
     * 实付金额
     */
    @Excel(name = "实付金额")
    private BigDecimal money;

    private String paymentName;

    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createOrderTime;

    private String patmentType;

    /**
     * 用户信息
     */
    @Excel(name = "用户信息")
    private Long userId;

    /**
     * 下单状态
     */
    @Excel(name = "下单状态")
    private Integer statusId;

    /** 订单状态  0:量房款待付，1:量房款已付, 2:等待上门、3装修中、4表示已完成 ,5:已关闭*/
    @Excel(name = "订单状态")
    private String orderStatus;

}
