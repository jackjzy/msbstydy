package com.ruoyi.common.utils.ucpaasSms;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.ucpaasSms.client.AbsRestClient;
import com.ruoyi.common.utils.ucpaasSms.client.JsonReqClient;

public class SmsUtil {
    static AbsRestClient InstantiationRestAPI() {
        return new JsonReqClient();
    }

    public static void SendSms(String sid, String token, String appid, String templateid, String param, String mobile, String uid){
        try {
            String result=InstantiationRestAPI().sendSms(sid, token, appid, templateid, param, mobile,null);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂时使用 ,申请替换
     * @param phone 手机号
     * @param code 验证码
     * @return
     */

    public static Boolean  temSms(String phone, String code){

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appid","31621");
            jsonObject.put("to",phone);
            jsonObject.put("project","yhwV44");
            JSONObject tem = new JSONObject();
            tem.put("code",code);
            tem.put("time","5分钟");
            jsonObject.put("vars",tem.toJSONString());
            jsonObject.put("signature","98b70553c9e70aa89b224f1b325c1ba6");
            String s = HttpUtil.httpPost("https://api.mysubmail.com/message/xsend.json",jsonObject);
            if("success".equals(JSONObject.parseObject(s).get("status")))return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.err.println(temSms("17671850175", "666666"));
    }

}
