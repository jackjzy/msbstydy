package com.ruoyi.app.config;

import com.ruoyi.app.intercepts.JWTintercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptsCofig implements WebMvcConfigurer {
    @Autowired
    private JWTintercepts jwTintercepts;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwTintercepts)
                .addPathPatterns("/app/individual")
                .addPathPatterns("/app/addr")//拦截的路径，进行token验证
                .addPathPatterns("/app/seeting")
                .excludePathPatterns("/*.html")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/app/captcha")
                .excludePathPatterns("/app/users/register")
                .excludePathPatterns("/app/login/**")
                .excludePathPatterns("/profile/**")
                .excludePathPatterns("/common/download**")
                .excludePathPatterns("/common/download/resource**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/*/api-docs")
                .excludePathPatterns("/druid/**")
                .excludePathPatterns("/unionPay/**")
                .excludePathPatterns("/dev-api/**");//放行接口
    }
}
