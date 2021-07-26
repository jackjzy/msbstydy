package com.ruoyi.common.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class JWTUtils {
    //签名
    private static final  String SING="#131#@3Q3123##123";
    //存储id的key
    private static final  String USERID ="userId";
    //
    private static final  String USERNAME="userName";
    //过期时间
    private static final  int TIME = 60 * 24 * 24 * 180;

    private static final  String PHONE="phone";
    //从请求头获取token的key
    public static final  String TOKRN ="token";
    /*
    * 生成token heder.payload.sing
    * */

    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MONTH,7);//设置过期时间月

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime()).
                sign(Algorithm.HMAC256(SING));
        return token;
    }
    /*
    * 验证token合法性
    * */

    public static DecodedJWT verifyy(String token){
      return   JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        //return  null;
    }

    //获取用户id
    public static Long getId(String token){
        String s = JWT.require(Algorithm.HMAC256(SING))
                .build()
                .verify(token)
                .getClaim(USERID)
                .asString();
        return Long.valueOf(s);
    }


}
