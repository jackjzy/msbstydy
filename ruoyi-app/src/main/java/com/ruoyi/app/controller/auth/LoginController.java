package com.ruoyi.app.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.config.JwtTokenUtil;
import com.ruoyi.app.util.em.HXUtil;
import com.ruoyi.common.constant.AuthServerConstant;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.Platform;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BizCodeEnum;
import com.ruoyi.common.utils.JacksonUtil;
import com.ruoyi.common.utils.jersey.Result;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/4/25
 **/
@Api(tags = "登陆相关操作")
@Slf4j
@RestController
@RequestMapping("/app")
public class LoginController extends BaseController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IFfwyUserService iFfwyUserService;

    @Resource
    private HXUtil hxUtil;

    @ApiOperation("平台用户协议")
    @ResponseBody
    @GetMapping(value = "/getAgreement")
    public String getAgreement(){
        return Platform.getAgreement();
    }


//    @IgnoreAuth
    @ApiOperation("注册")
    @PostMapping("/users/register")
    public AjaxResult simpleReg(@RequestBody String body) {
        String phone = JacksonUtil.parseString(body, "phone");
        String code = JacksonUtil.parseString(body, "code");
        String password = JacksonUtil.parseString(body, "password");
        if (org.apache.commons.lang3.StringUtils.isEmpty(phone)){
            phone = getValue(body, "phone");
            password = getValue(body, "password");
            code = getValue(body, "code");
        }

        //1、判断手机是否注册
        FfwyUser ffwyUser = iFfwyUserService.selectFfwyUserByPhonenumber(phone,null);
        if (null != ffwyUser){
            log.info("注册   手机已注册：{}",AjaxResult.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.PHONE_EXIST_EXCEPTION.getMessage()));
            return AjaxResult.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.PHONE_EXIST_EXCEPTION.getMessage());
        }

        //2、效验验证码
        boolean b = authCode(phone, password, code, 0l,AuthServerConstant.REGISTER,null);
        if (b){
            log.info("注册   注册成功");
            return AjaxResult.success("注册成功");
        }

        log.info("注册   效验验证码失败：{}",AjaxResult.error(BizCodeEnum.CODE_EXCEPTION.getCode(),BizCodeEnum.CODE_EXCEPTION.getMessage()));
        return AjaxResult.error(BizCodeEnum.CODE_EXCEPTION.getCode(),BizCodeEnum.CODE_EXCEPTION.getMessage());

    }

    @ApiOperation("更改密码")
    @PostMapping("/users/alterpassword")
    public AjaxResult alterPassword(@RequestBody String body) {
        String phone = JacksonUtil.parseString(body, "phone");
        String code = JacksonUtil.parseString(body, "code");
        String password = JacksonUtil.parseString(body, "password");
        if (org.apache.commons.lang3.StringUtils.isEmpty(phone)){
            phone = getValue(body, "phone");
            password = getValue(body, "password");
            code = getValue(body, "code");
        }
        //1、判断手机号是否有效
        FfwyUser ffwyUser = iFfwyUserService.selectFfwyUserByPhonenumber(phone,null);
        if (null == ffwyUser){
            log.info("更改密码 手机号无效：{}",AjaxResult.error(BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getMessage()));
            return AjaxResult.error(BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getMessage());
        }

        //2、效验验证码
        boolean b = authCode(phone, password, code, ffwyUser.getUserId(), AuthServerConstant.ALTERPASSWORD,ffwyUser.getEmId());

        if (b){
            //成功
            log.info("更改密码 修改成功");
            return AjaxResult.success("修改成功");
        }
        log.info("更改密码 效验验证码失败：{}",AjaxResult.error(BizCodeEnum.CODE_EXCEPTION.getCode(),BizCodeEnum.CODE_EXCEPTION.getMessage()));
        return AjaxResult.error(BizCodeEnum.CODE_EXCEPTION.getCode(),BizCodeEnum.CODE_EXCEPTION.getMessage());

    }
    private String getValue(String body,String key){
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(body)){
            body = body.indexOf("\"")==0?body.substring(1, body.length()-1):body;
            String[] split = body.split("&");
            for (String s : split) {
                String[] split1 = s.split("=");
                if (split1[0].equals(key)){
                    return split1[1];
                }
            }
        }
        return null;
}

    @ApiOperation("密码登陆")
    @PostMapping("/login/password")
    public AjaxResult loginPassword(@RequestBody String body) {
        System.err.println(body);
        String phone = JacksonUtil.parseString(body, "phone");
        String password = JacksonUtil.parseString(body, "password");

        if (org.apache.commons.lang3.StringUtils.isEmpty(phone)){
            phone = getValue(body, "phone");
            password = getValue(body, "password");
        }
        AjaxResult ajax = AjaxResult.success();
        FfwyUser login = iFfwyUserService.login(phone, password);
        if (null == login){
            log.info(AjaxResult.error(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMessage()).toString());
            return AjaxResult.error(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMessage());
        }
        String appToke = JwtTokenUtil.getAppToke(login);

        ajax.put(Constants.TOKEN,appToke);
        ajax.put(Constants.USER,login);
        login.setToken(appToke);
        iFfwyUserService.updateFfwyUserOther(login);
        log.info("账号密码登陆成功！");
        return ajax;
    }

    @ApiOperation("短信验证登陆")
    @PostMapping("/login/phone")
    public AjaxResult loginPhone(@RequestBody String body) {
        String phone = JacksonUtil.parseString(body, "phone");
        String code = JacksonUtil.parseString(body, "code");
        if (org.apache.commons.lang3.StringUtils.isEmpty(phone)){
            phone = getValue(body, "phone");
            code = getValue(body, "code");
        }
        AjaxResult ajax = AjaxResult.success();

        FfwyUser login = iFfwyUserService.selectFfwyUserByPhonenumber(phone, null);
        if (null == login){
            log.info("短信验证登陆:{}",AjaxResult.error(BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getMessage()));
            return AjaxResult.error(BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.NO_PHONE_EXIST_EXCEPTION.getMessage());
        }

        //2、效验验证码
        boolean b = authCode(phone, null, code, null, AuthServerConstant.LOGINPHONE,null);
        if (!b){
            log.info("效验验证码:{}",AjaxResult.error(BizCodeEnum.CODE_EXCEPTION.getCode(),BizCodeEnum.CODE_EXCEPTION.getMessage()));
            return AjaxResult.error(BizCodeEnum.CODE_EXCEPTION.getCode(),BizCodeEnum.CODE_EXCEPTION.getMessage());
        }
        String appToke = JwtTokenUtil.getAppToke(login);
        ajax.put(Constants.TOKEN, appToke);
        ajax.put(Constants.USER,login);
        login.setToken(appToke);
        iFfwyUserService.updateFfwyUser(login);
        return ajax;
    }




    /**
     *   验证码效验
     * @param phone
     * @param password
     * @param code
     * @param type
     * @return
     */
    private boolean authCode(String phone,String password,String code,Long userId, String type,String emId){
        int register = 0;
        //效验验证码
        //获取存入Redis里的验证码
        String redisCode = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)) {
            //截取字符串
            if (code.equals(redisCode.split("_")[0])) {
                //删除验证码;令牌机制
                stringRedisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX+phone);

                //验证码通过，真正注册，调用注册接口进行注册
                FfwyUser ffwyUser = null;
                if (AuthServerConstant.REGISTER.equals(type)){ // 注册
                    ffwyUser = new FfwyUser(phone, password, "3", phone, "0");
                    // 注册环信
                    ffwyUser.setEmId(hxUtil.addUser(phone, password, null));
                    ffwyUser.setEmKey(password);
                    register = iFfwyUserService.insertFfwyUser(ffwyUser);
                }else if (AuthServerConstant.ALTERPASSWORD.equals(type)){   // 改密码
                    ffwyUser = new FfwyUser(userId, password);
                    // 修改环信密码
                    hxUtil.updateUser(emId,password);
                    ffwyUser.setEmKey(password);
                    register = iFfwyUserService.updateFfwyUser(ffwyUser);
                }else { // 短信登陆
                    register = 1;
                }

                if (register == 1) {
                    return true;
                }
            }
        }
            return false;

    }

    /**
     * 验证错误，返回页面
     * @param errors
     * @param attributes
     * @param str
     */
    private void regis(Map<String, String> errors,RedirectAttributes attributes,String str){
        errors.put("msg", str);
        attributes.addFlashAttribute("errors",errors);
    }

}
