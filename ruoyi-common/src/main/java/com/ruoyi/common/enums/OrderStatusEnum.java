package com.ruoyi.common.enums;

/**
 * @author 郭晓康
 * @create 2021-05-24 上午11:40
 */
public enum OrderStatusEnum {


    TO_BE_PAID("1","待付款"),
    To_SEND_THE_GOODS("2","待发货"),
    WFTD("3","待评价"),
    RENOVATIONING("4","待收货"),
    REMAIN_TOBE_EVALUATED("5","待退款"),
    OFF_THE_STOCKS("6","已完成"),
    CLOSED("7","已关闭"),
    APPLY_FOR_AFTER_SALES("8","申请退款"),
    AFTER_COMPLETE("9","申请退货退款"),
    ATTER_COMPLETE("10","已拒绝"),
    REFUND_SUCCESSFUL("11","退款成功"),
    CLOSE_AFTER_SALES("12","取消/关闭售后"),
    RETURNING_GOODS("13","退货中");



    private String code;
    private String msg;

    OrderStatusEnum(String code, String msg) {
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
