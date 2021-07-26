package com.ruoyi.common.config.pay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VxPayConfig {
    @Bean("WxPayProperties")
    public WxPayProperties wxPayProperties(){
        return new WxPayProperties();
    }
}
