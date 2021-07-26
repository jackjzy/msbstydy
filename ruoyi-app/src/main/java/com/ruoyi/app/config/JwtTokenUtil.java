package com.ruoyi.app.config;

import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/4/26
 **/
public class JwtTokenUtil {



    public static String getAppToke(FfwyUser user){
        Map<String,String> map =new HashMap<>();
        //存储用户id
        map.put("userId",user.getUserId().toString());
        //存储用户名
        map.put("userName",user.getUserName());
        //存储用户手机号
        map.put("phone",user.getPhonenumber());

        return JWTUtils.getToken(map);
    }

    public static Long getUserId(String token){
        Long userId = null;
        try {
            userId = JWTUtils.getId(token);
        }catch (Exception e){

        }
        return userId;
    }
}
