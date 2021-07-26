package com.ruoyi.common.enums;

import lombok.Getter;

@Getter
public enum PayType {
    PAY_PRODUCT(0,"支付商品订单"),

    PAY_COMBOMEAL(1,"支付量房订单"),

    PAY_PHASE(2,"支付毛培房子节点"),

    PAY_THEME_HOST(3,"支付主题房"),
    TO_BE_PAID(0,"待支付"),

    PAYMENT_SUCCESSFUL(1,"支付成功"),

    PAYMENT_FAILED(2,"支付失败");


    private Integer code;
    private String type;

    PayType(Integer code,String type){
        this.code = code;
        this.type = type;
    }
}
