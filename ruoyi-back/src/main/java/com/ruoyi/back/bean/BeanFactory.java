package com.ruoyi.back.bean;

import com.ruoyi.system.domain.shop.FfwyShop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {
    @Bean(name = "getShop")
    public FfwyShop getShop(){
        FfwyShop ffwyShop=new FfwyShop();
        ffwyShop.setShopName("小白店铺");
        ffwyShop.setShopLogo("店铺logo");
        ffwyShop.setShopStatus(1);//设置默认状态为冻结
        return ffwyShop;
    }
}
