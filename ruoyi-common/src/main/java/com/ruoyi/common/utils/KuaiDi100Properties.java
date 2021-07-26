package com.ruoyi.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云 oss 配置类
 * <P> 阿里云oss控制台： https://oss.console.aliyun.com/overview <P/>
 * <P> 继承  CommandLineRunner  是让该配置类在项目启动成功后再执行，确保 ${isAliOss} 参数的正常读取 </P>
 * @author wangsong
 * @date 2020/12/11 0011 17:10
 * @return
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Configuration
@ConfigurationProperties(prefix = "kuaidu.kuaidi100")
@Data
@Slf4j
public class KuaiDi100Properties {

    private String key;

    private String customer;


//    public void println() {
//        // 打印加载信息
//        log.info(ConsoleColors.YELLOW_BRIGHT +
//                "\r\n" +
//                "|---     快递 100 配置   ---| \r\n" +
//                "|  key: {} \r\n" +
//                "|  customer: {} \r\n" +
//                "| ----------------------------------|"
//                + ConsoleColors.RESET, key, customer);
//    }
}

