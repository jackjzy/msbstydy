package com.ruoyi.app.bean;

import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanFactoryApp {

    @Bean("getUser")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//设置多例
    public FfwyUser getUser(){

        return new FfwyUser();
    }

    @Bean("getvideo")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//设置多例
    public FfwyVideo getvideo(){

        return new FfwyVideo();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//设置多例
    public FfwyOrderCombomeal getOrderCombomeal(){

        return new FfwyOrderCombomeal();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//设置多例
    public FfwyPhase getPhase(){

        return new FfwyPhase();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//设置多例
    public FfwyMaterial getMaterial(){

        return new FfwyMaterial();
    }
}
