package com.ruoyi.app.util.em;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.jersey.JerseyClientUtil;
import com.ruoyi.common.utils.jersey.Result;
import com.ruoyi.common.utils.redis.RedisCacheHelper;
import com.ruoyi.common.utils.redis.RedisKeyEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/6/25
 **/
@Component
public class HXUtil {


    @Value("${EN.clientId}")
    private String CLIENT_ID;

    @Value("${EN.clientSecret}")
    private String CLIENT_SECRET;

    @Value("${EN.appKey}")
    private String APP_KEY;

    @Value("${EN.orgName}")
    private String ORG_NAME;

    @Value("${EN.appName}")
    private String APP_NAME;

    @Value("${EN.apiUrl}")
    private String API_URL;

    @Value("${EN.endNum}")
    private Integer END_NUM;


    //用户管理 -注册单个用户 获取单个用户 删除单个用户
    public String UserMethods() { return String.format("/%s/%s/users",ORG_NAME,APP_NAME); }

    //好友 -添加好友  移除好友 获取好友列表
    public String contactUser(){ return String.format("/%s/%s/users/%s/contacts/users/%s",ORG_NAME,APP_NAME,"%s","%s"); }

    //黑名单 -添加黑名单  移除黑名单 获取黑名单
    public String blockUser(){ return String.format("/%s/%s/users/%s/blocks/users/%s",ORG_NAME,APP_NAME,"%s","%s"); }



    @Autowired
    private RedisCacheHelper redisCacheHelper;


    /**
     * 赋值TOKEN
     */
    public boolean setToken(){
        //👉🏻组装请求路径
        String url ="/"+ORG_NAME+"/"+APP_NAME+"/token";

        //👉🏻组装请求参数
        JSONObject rst_Body = new JSONObject();
        rst_Body.put("grant_type","client_credentials");
        rst_Body.put("client_id",CLIENT_ID);
        rst_Body.put("client_secret",CLIENT_SECRET);

        //👉🏻组装请求头
        JSONObject headers = new JSONObject();
        headers.put("Content-Type","application/json");

        //👉🏻获得返回信息
        Result result = JerseyClientUtil.postMethod(API_URL, url, JSONObject.toJSONString(rst_Body), headers);

        if(null==result)return false;

        //👉🏻获取 ACCESS_TOKEN
        String access_token = JSONObject.parseObject(result.getData().toString()).getString("access_token");

        //👉🏻放入redis
        return redisCacheHelper.set(RedisKeyEnum.EN_TOKEN.getKey(), access_token);
    }
    /**
     * 获取TOKEN
     * 无法通过接口判断该TOKEN是否有效
     */
    public String getToken(){

        //👉🏻查看redis是否有TOKEN
        Object o = redisCacheHelper.get(RedisKeyEnum.EN_TOKEN.getKey());

        if(o!=null) return o.toString();

        setToken();//更新token

        return redisCacheHelper.get(RedisKeyEnum.EN_TOKEN.getKey()).toString();
    }


    /**
     *  注册单个用户
     * @param phone 环信 ID ;也就是 IM 用户名的唯一登录账号，长度不可超过64个字符长度
     * @param password  登录密码，长度不可超过64个字符长度
     * @param nickname  昵称（可选），在 iOS Apns 推送时会使用的昵称（仅在推送通知栏内显示的昵称），并不是用户个人信息的昵称，环信是不保存用户昵称，头像等个人信息的，需要自己服务器保存并与给自己用户注册的IM用户名绑定，长度不可超过100个字符
     * @return
     */
    public String addUser(String phone, String password,String nickname) {
        try {
            //👉🏻组装请求参数
            JSONObject rst_Body = new JSONObject();
            rst_Body.put("username",phone);
            rst_Body.put("password",password);
            if(StringUtils.isNotEmpty(nickname)) rst_Body.put("nickname",nickname);

            //👉🏻组装请求头
            JSONObject headers = new JSONObject();
            headers.put("Content-Type","application/json");
            headers.put("Authorization","Bearer"+getToken());

            //👉🏻获得返回信息
            Result result = JerseyClientUtil.postMethod(API_URL, UserMethods(), JSONObject.toJSONString(rst_Body), headers);
            if(400==result.getCode())throw new RuntimeException("用户已存在、用户名或密码为空、用户名不合法");
            while (401==result.getCode()){
                setToken();;
                this.addUser(phone,password,nickname);
            }
            System.err.println(result);

            return result.getData().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  根据用户名获得用户信息
     * @param phone
     * @return
     */
    public Result getUser(String phone) {
        if(StringUtils.isEmpty(phone))return Result.build(201,"PHONE IS NULL");
        try {
            //👉🏻组装URL
            String path = UserMethods()+"/"+phone;

            //👉🏻组装请求头
            JSONObject headers = new JSONObject();
            headers.put("Content-Type","application/json");
            headers.put("Authorization","Bearer "+getToken());

            //👉🏻获得返回信息
            Result result = JerseyClientUtil.getMethodOnIm(API_URL, path,headers);

            //👉🏻判断token是否时效 重试3次

            /*while (result.getCode()!=0 & i<3){
                setToken();i++;
                this.finAll(phone);
            }*/
            //获得具体信息
            String entities = JSONObject.parseObject(result.getData().toString()).getJSONArray("entities").get(0).toString();

            return Result.ok(entities);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加好友
     * @param phone 原用户
     * @param firendPhone 要添加的好友
     * @param start 开始次数 默认传 NULL
     * @return
     */
    public Result addFirend(String phone,String firendPhone ,String start) {
        int startNum =0;
        if(StringUtils.isEmpty(phone))return Result.build(201,"PHONE IS NULL");
        if(StringUtils.isNotBlank(start))startNum=Integer.parseInt(start);

        try {
            //👉🏻组装URL
            String path = String.format(contactUser(),phone,firendPhone);

            //👉🏻组装请求头
            JSONObject headers = new JSONObject();
            headers.put("Content-Type","application/json");
            headers.put("Authorization","Bearer "+getToken());

            //👉🏻获得返回信息
            Result result = JerseyClientUtil.postMethod(API_URL, path,headers);

            if(404==result.getCode())return result;//表示此IM用户或被添加的好友不存在

            //👉🏻判断token是否时效 目前重试3次
            if(401==result.getCode() & startNum<=END_NUM){
                while (result.getCode()==401 ){
                    setToken();startNum++;
                    this.addFirend(phone,firendPhone,String.valueOf(startNum));
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    /**
     * 修改密码
     * @param username
     * @param password
     * @return
     */
    public  Boolean updateUser(String username, String password) {
        try {

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String format = String.format("/%s/%s/users/%s/contacts/users", "1", "2", "%s");
        System.err.println(format.format(format, "1"));

    }

}
