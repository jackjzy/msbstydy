package com.ruoyi.common.config.pay;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data

public class WxPayProperties {
    /**
     * appid.
     */
    @Value("${wxpay.appId}")
    private String appId;

    /**
     * 微信支付商户号.
     */
    @Value("${wxpay.mchId}")
    private String mchId;

    /**
     * 微信支付商户密钥.
     */
    @Value("${wxpay.mchKey}")
    private String mchKey;

    /**
     * 异步回调地址
     */
    @Value("${wxpay.notifyUrl}")
    private String notifyUrl;

}
