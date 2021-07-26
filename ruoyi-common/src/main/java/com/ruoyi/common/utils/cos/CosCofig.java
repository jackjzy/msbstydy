package com.ruoyi.common.utils.cos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CosCofig {
    private static String secretId;
    private static String secretKey;
    private static  String bucketName;
    private static String prefixUrl;

    public static String getPrefixUrl() {
        return prefixUrl;
    }
    @Value("${cos.prefixUrl}")
    public  void setPrefixUrl(String prefixUrl) {
        CosCofig.prefixUrl = prefixUrl;
    }

    public static String getSecretKey() {
        return secretKey;
    }
    @Value("${cos.secretKey}")
    public  void setSecretKey(String secretKey) {
        CosCofig.secretKey = secretKey;
    }

    public static String getBucketName() {
        return bucketName;
    }
    @Value("${cos.bucketName}")
    public  void setBucketName(String bucketName) {
        CosCofig.bucketName = bucketName;
    }

    @Value("${cos.secretId}")
    public void setSecretId(String secretId) {
        CosCofig.secretId = secretId;
    }

    public static String getSecretId() {
        return secretId;
    }

}
