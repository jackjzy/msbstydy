package com.ruoyi.common.enums;

/**
 * 套餐订单状态
 */
public enum OrderComStatusEnum {

    CREATE_NEW("0","量房款待付"),
    PAID("1","量房款已付"),
    WFTD("2","等待上门量房"),
    RENOVATIONING("3","装修中"),
    COMPLETED("4","已完成"),
    CLOSED("5","已关闭")
    ;

    private String code;
    private String msg;

    OrderComStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
