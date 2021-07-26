package com.ruoyi;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestJwt {
    String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjEwNTkyMjAsInVzZXJpZCI6IjEifQ.NiG_HQaOdKjTil7bwmlbcx8w6CqhACVLKhD2xV04UAU";
    @Autowired
    IFfwyUserService userService;

    @Test
    public void test1(){
        FfwyUser user = userService.selectFfwyUserById((long) 4);
        Long id = JWTUtils.getId(token);
        System.err.println(id);

    }
}
