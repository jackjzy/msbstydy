package com.ruoyi.app.controller.auth;

import com.ruoyi.app.util.em.HXUtil;
import com.ruoyi.common.utils.sms.SendSmsUtils;
import com.ruoyi.common.utils.sms.Template;
import com.ruoyi.common.constant.AuthServerConstant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BizCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/4/26
 **/
@Api(tags = "发送短信")
@Controller
@RequestMapping("/app")
@Slf4j
public class ValidateCodeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private HXUtil hxUtil;

    @ApiOperation("发送短信")
    @ResponseBody
    @GetMapping(value = "/captcha")
    public AjaxResult sendCode(@RequestParam("phone") String phone){
        //1、接口防刷
        String redisCode = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)) {
            //活动存入redis的时间，用当前时间减去存入redis的时间，判断用户手机号是否在60s内发送验证码
            long currentTime = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - currentTime < 60000) {
                //60s内不能再发
                log.info(AjaxResult.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMessage()).toString());
                return AjaxResult.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMessage());
            }
        }

        //2、验证码的再次效验 redis.存key-phone,value-code
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String codeNum = String.valueOf(code);
        String redisStorage = codeNum + "_" + System.currentTimeMillis();

        //存入redis，防止同一个手机号在60秒内再次发送验证码
        stringRedisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX+phone,
                redisStorage,20, TimeUnit.MINUTES);
        // 发送短信
        SendSmsUtils.sendsms(phone, Template.CODE.getCode(), codeNum);
        return AjaxResult.success(codeNum);
    }

    @ApiOperation("环信测试接口")
    @ResponseBody
    @GetMapping(value = "/add")
    public AjaxResult sendCode8(@RequestParam("phone") String phone){
        String user = phone;
        hxUtil.addUser(user,"123456",null);

//        Boolean user1 = HXUtil.getUser(user);
        return AjaxResult.success();
    }
}
