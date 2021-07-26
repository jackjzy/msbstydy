package com.ruoyi.app.util.sms;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.jersey.Result;
import com.ruoyi.common.utils.sms.MessageConfig;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: SendSmsUtils
 * @Author: Zx
 * @Date: 2021/7/22 4:34 下午
 * @Version: v1.0
 */
public class SendSmsUtils {
    private static final   String   secretKey = MessageConfig.secretKey;

    private static final   String   secretId = MessageConfig.secretId;

    private static final   String  SdkAppId = MessageConfig.sdkAppId;

    private static final   String  SignName = MessageConfig.signName;

    private static final   String  Endpoint = "sms.tencentcloudapi.com";

    private static final   String  region   = "ap-guangzhou";


    public static Result  sendsms(String phone, String templateId, String code){
        if(StringUtils.isBlank(phone)) return Result.build(201,"PHONE IS NULL");
        if(StringUtils.isBlank(templateId)) return Result.build(201,"TEMPLATEID IS NULL");
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(secretId, secretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(Endpoint);
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, region, clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            //手动赋值 "+86"
            String [] phoneNUmber = new String[1];
            phoneNUmber[0] = "+86"+phone;
            //下发手机号码，采用 E.164 标准，格式为+[国家或地区码][手机号]，单次请求最多支持200个手机号且要求全为境内手机号或全为境外手机号。 例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号。
            req.setPhoneNumberSet(phoneNUmber);
            //短信 SdkAppId，在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
            req.setSmsSdkAppid(SdkAppId);
            //模板 ID，必须填写已审核通过的模板 ID。模板 ID 可登录 短信控制台 查看，若向境外手机号发送短信，仅支持使用国际/港澳台短信模板。
            req.setTemplateID(templateId);
            //签名 名称
            req.setSign(SignName);
            //模板参数

            if(StringUtils.isNotBlank(code)){
                String [] codeNumber = new String[1];
                codeNumber[0] = code;
                req.setTemplateParamSet(codeNumber);
            }
            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            JSONArray rest = JSONObject.parseObject(SendSmsResponse.toJsonString(resp)).getJSONArray("SendStatusSet");
            if("Ok".equals(rest.getJSONObject(0).get("Code")))return Result.ok();
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return Result.build(201,"发送短信错误!");
    }


    /*public static void main(String [] args) {




      String  secretKey = "tc9RUIsAHA3gHelhjc3HxOn1qlW5RjcH";
      String  secretId = "AKIDGKABwTCphzvcMqA2pPjmbQ0MdnhUuLXU";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(secretId, secretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String [] phone = new String[1];
            phone[0] = "+86"+"17634010196";
            //下发手机号码，采用 E.164 标准，格式为+[国家或地区码][手机号]，单次请求最多支持200个手机号且要求全为境内手机号或全为境外手机号。 例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号。
            req.setPhoneNumberSet(phone);
            //短信 SdkAppId，在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
            req.setSmsSdkAppid("1400520231");
            //模板 ID，必须填写已审核通过的模板 ID。模板 ID 可登录 短信控制台 查看，若向境外手机号发送短信，仅支持使用国际/港澳台短信模板。
            req.setTemplateID("1041632");
            //签名 名称
            req.setSign("北京嗅美科技有限公司");
            //模板参数
            String [] code = new String[1];
            code[0] = "123456";
            req.setTemplateParamSet(code);
            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }*/
}