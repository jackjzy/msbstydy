package com.ruoyi.common.enums;

import javax.management.relation.RoleNotFoundException;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/4/26
 *
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *      002：短信验证码频率太高
 *  11: 商品
 *  12: 订单
 *  13: 购物车
 *  14: 物流
 *  15：用户
 *
 *
 **/
public enum BizCodeEnum {

    LIKE_EXCEPTION(9000,"不能重复点赞"),
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    VAILD_EXCEPTION(10001,"参数格式校验失败"),
    TO_MANY_REQUEST(10002,"请求流量过大，请稍后再试"),
    SMS_CODE_EXCEPTION(10002,"验证码获取频率太高，请稍后再试"),
    CODE_EXCEPTION(10002,"验证码错误"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架异常"),
    USER_EXIST_EXCEPTION(15001,"存在相同的用户"),
    USER_CAN_NOT_ACTICE(15001,"用户处于冻结状态，请联系管理员"),
    PHONE_EXIST_EXCEPTION(15002,"存在相同的手机号"),
    NO_PHONE_EXIST_EXCEPTION(15002,"手机号未注册"),
    PHONE_PASSWORD_EXCEPTION(15002,"手机号或者密码错误"),
    NO_STOCK_EXCEPTION(21000,"商品库存不足"),
    LOGINACCT_PASSWORD_EXCEPTION(15003,"账号或密码错误"),
    TOKEN_SIGN_EXCEPTION(16001,"无效签名！"),
    TOKEN_EXPIRED_EXCEPTION(16002,"token过期"),
    ALGORITHM_MISMATCH_EXCEPTION(16003,"算法不一致"),
    TOEKN_EXCTPTION(16004,"token无效"),
    ADDR_NOT_FOUNT(17001,"您还没有收货地址哦"),
    UPLOAD_ERROR(18001,"上传失败"),
    PUSH_ERROR(19001,"消息发送失败"),
    ROLE_USER_ERROR(20001,"没有切换角色权限"),
    ROLE_WORKER_ERROR(20002,"没有切换商铺权限"),
    ROLE_SHOP_ERROR(20003,"没有切换工长权限"),
    ROLE_ERROR(20004,"切换角色错误"),
            ;

    private Integer code;

    private String message;

    BizCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
