package com.ruoyi.common.utils.sms;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/11
 **/
@Component
@Getter
public class MessageConfig {
    public static String secretKey;
    public static String secretId;
    public static String sdkAppId;
    public static String signName;



    @Value("${cos.secretKey}")
    public void setSecretKey(String secretKey) {
        MessageConfig.secretKey = secretKey;
    }

    @Value("${cos.secretId}")
    public void setSecretId(String secretId) {
        MessageConfig.secretId = secretId;
    }

    @Value("${message.sdkAppId}")
    public void setSmsSign(String sdkAppId) {
        MessageConfig.sdkAppId = sdkAppId;
    }

    @Value("${message.signName}")
    public void setSignName(String signName) {
        MessageConfig.signName = signName;
    }
}
