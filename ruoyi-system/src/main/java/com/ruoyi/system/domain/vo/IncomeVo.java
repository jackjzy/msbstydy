package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeVo {
    /**
     * 收入或者支出
     */
    private String incomePay;
    /**
     * 金额
     */
    private String money;
    /**
     * 交易方式
     */
    private String payment;
    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 订单号
     */
    private String orderNumber;
}
