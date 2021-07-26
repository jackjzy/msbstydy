package com.ruoyi;

import com.alipay.api.AlipayClient;
import com.ruoyi.common.config.pay.AlipayAppConfig;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.vo.UserVo;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.service.IFfwyUserService;
import io.swagger.annotations.Api;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestUser {
    @Autowired
    private IFfwyUserService iFfwyUserService;
    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private AlipayAppConfig alipayAppConfig;

    @Test
    public void test1() throws InterruptedException {
        System.err.println(alipayAppConfig);
//        Thread.sleep(10000);
//        System.err.println("关注数："+i);
//        System.err.println("粉丝数："+i1);
    }
}
