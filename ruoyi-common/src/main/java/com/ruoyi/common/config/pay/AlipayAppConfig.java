package com.ruoyi.common.config.pay;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author fuchangshuai
 * @date 2019/3/6 10:02 AM
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay.config")
public class AlipayAppConfig {
    private String url;
    private String appId;
    private String privateKey;
    private String pubKey;
    private String charset;
    private  String signType;
    // 异步回调地址
    private String notifyUrl;
    private String vipNotifyUrl;
    // 返回格式
    private String format;
}