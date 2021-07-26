package com.ruoyi.common.utils.jg;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class JiGuangPushUtil {
    //两个参数分别填写你申请的masterSecret和appKey
    private static JPushClient jPushClient=new JPushClient("41fcf37caf51542102c3b174","09593950fb8535898f4f2c5d");

    /**
     * 通知推送
     * 备注：推送方式不为空时，推送的值也不能为空；推送方式为空时，推送值不做要求
     * @param type 推送方式：1、“tag”标签推送，2、“alias”别名推送
     * @param value 推送的标签或别名值
     * @param alert 推送的内容
     */
    public static void pushNotice(String type,String value,String alert){
        Builder builder= PushPayload.newBuilder();
        builder.setPlatform(Platform.all());//设置接受的平台，all为所有平台，包括安卓、ios、和微软的
        //设置如果用户不在线、离线消息保存的时间
        Options options=Options.sendno();
        options.setTimeToLive(86400l);    //设置为86400为保存一天，如果不设置默认也是保存一天
        builder.setOptions(options);
        //设置推送方式
        if(type.equals("alias")){
            builder.setAudience(Audience.alias(value));//根据别名推送
        }else if(type.equals("tag")){
            builder.setAudience(Audience.tag(value));//根据标签推送
        }else{
            builder.setAudience(Audience.all());//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
        }
        Map<String,String> m =new HashMap<>();
        m.put("title","我是title");
        m.put("alert","我是内容 标签456"+System.currentTimeMillis());
        //设置为采用通知的方式发送消息

        builder.setNotification(Notification.android(m.get("alert"),m.get("title"),m));
        PushPayload pushPayload=builder.build();
        
        try{
            //进行推送，实际推送就在这一步
            PushResult pushResult=jPushClient.sendPush(pushPayload);
        }catch(Exception e){
            e.printStackTrace();
        }

    }



    /**
     * 通知推送
     * 备注：推送方式不为空时，推送的值也不能为空；推送方式为空时，推送值不做要求
     * @param type 推送方式：1、“tag”标签推送，2、“alias”别名推送
     * @param value 推送的标签或别名值
     * @param alert 推送的内容
     * @param title 推送的标题
     */
    public static void pushAndroid(String type,String value,String title,String alert){
        Builder builder= PushPayload.newBuilder();
        builder.setPlatform(Platform.all());//设置接受的平台，all为所有平台，包括安卓、ios、和微软的
        //设置如果用户不在线、离线消息保存的时间
        Options options=Options.sendno();
        options.setTimeToLive(86400l);    //设置为86400为保存一天，如果不设置默认也是保存一天
        builder.setOptions(options);
        //设置推送方式
        if(type.equals("alias")){
            builder.setAudience(Audience.alias(value));//根据别名推送
        }else if(type.equals("tag")){
            builder.setAudience(Audience.tag(value));//根据标签推送
        }else{
            builder.setAudience(Audience.all());//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
        }
        Map<String,String> m =new HashMap<>();
        m.put("title",title);
        m.put("alert",alert);
        //设置为采用通知的方式发送消息
        builder.setNotification(Notification.android(m.get("alert"),m.get("title"),m));
        PushPayload pushPayload=builder.build();

        try{
            //进行推送，实际推送就在这一步
            PushResult pushResult=jPushClient.sendPush(pushPayload);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // 极光推送>>ios
    public static void jpushIOS(Map<String, String> parm) {
        PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
                .setAudience(Audience.all())// 所有用户
                .setNotification(
                        Notification.newBuilder()
                                .addPlatformNotification(IosNotification.newBuilder().setAlert(parm.get("msg"))
                                        .setBadge(+1).setSound("happy")// 这里是设置提示音(更多可以去官网看看)
                                        .addExtras(parm).build())
                                .build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())// 自定义信息
                .build();

        try {
            PushResult pu = jPushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }



    //设置别名
    public static void setAlias(String deviceTagAlias, String alias) {
        HashSet<String> tagsToAdd = new HashSet<>();
        HashSet<String> tagsToRemove = new HashSet<>();
        try {
            jPushClient.updateDeviceTagAlias(deviceTagAlias, alias, tagsToAdd, tagsToRemove);
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //删除别名
    public static void delAlias(String username) {
        try {
            jPushClient.deleteAlias(username,null);
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }






    /**
     * 自定义消息推送
     * 备注：推送方式不为空时，推送的值也不能为空；推送方式为空时，推送值不做要求
     * @param type 推送方式：1、“tag”标签推送，2、“alias”别名推送
     * @param value 推送的标签或别名值
     * @param alert 推送的内容
     */
    public static void pushMsg(String type, String value,String alert){
        Builder builder= PushPayload.newBuilder();
        builder.setPlatform(Platform.all());//设置接受的平台
        if(type.equals("alias")){
            builder.setAudience(Audience.alias(value));//别名推送
        }else if(type.equals("tag")){
            builder.setAudience(Audience.tag(value));//标签推送
        }else{
            builder.setAudience(Audience.all());//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
        }

        Message.Builder newBuilder=Message.newBuilder();

        newBuilder.setTitle("测试标题");
        newBuilder.setMsgContent("12345");//消息内容
        Message message=newBuilder.build();
        builder.setMessage(message);
        PushPayload pushPayload=builder.build();
        try{
            PushResult pushResult=jPushClient.sendPush(pushPayload);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws APIConnectionException, APIRequestException {
        //给标签为kefu的用户进行消息推送

            JiGuangPushUtil.pushNotice("tag","456","ce");

    }
}
