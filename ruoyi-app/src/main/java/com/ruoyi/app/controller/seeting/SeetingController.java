package com.ruoyi.app.controller.seeting;

import com.ruoyi.back.service.IFfwyAboutService;
import com.ruoyi.common.constant.AuthServerConstant;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BizCodeEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.FfwyFaqs;
import com.ruoyi.system.domain.FfwyFeedback;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyFaqsService;
import com.ruoyi.system.service.IFfwyFeedbackService;
import com.ruoyi.system.service.IFfwyShopService;
import com.ruoyi.system.service.IFfwyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("设置接口")
@RestController
@RequestMapping("/app/seeting")
public class SeetingController extends BaseController {
    @Autowired
    private IFfwyFeedbackService iFfwyFeedbackService;
    @Autowired
    private IFfwyShopService iFfwyShopService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private IFfwyUserService iFfwyUserService;
    @Autowired
    private IFfwyFaqsService ffwyFaqsService;
    @Autowired
    private IFfwyAboutService ffwyAboutService;

;

    //反馈
    @ApiOperation("反馈信息")
    @PostMapping( value = "/feedback")
    public AjaxResult feedBack(@RequestBody FfwyFeedback feedback,
                               HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return toAjax(iFfwyFeedbackService.insertFfwyFeedback(feedback,userId));
    }

    //常见问题
    @ApiOperation("查询常见问题")
    //@PreAuthorize("@ss.hasPermi('system:faqs:list')")
    @GetMapping("/findByfaqs")
    public TableDataInfo list(FfwyFaqs ffwyFaqs)
    {
        startPage();
        List<FfwyFaqs> list = ffwyFaqsService.selectFfwyFaqsAndReplyList(ffwyFaqs);
        return getDataTable(list);
    }
    //联系我们
    @ApiOperation("联系我们")
    @GetMapping("/contactus")
    public AjaxResult contactus(){
        return AjaxResult.success(ffwyAboutService.selectFfwyAboutById((long) 1));
    }
    //关于我们
    @ApiOperation("关于我们")
    @GetMapping("/about")
    public AjaxResult aboutAs(){
        return AjaxResult.success(ffwyAboutService.selectFfwyAboutById((long) 1));
    }

    //关闭消息推送
    @GetMapping("/push")
    public AjaxResult closePush(){
        /*
        * 关闭消息推送
        *
        * */
        return null;
    }


    //设置手机号
    @ApiOperation("设置手机号")
    @GetMapping("/phone")
    public AjaxResult seetingPhone(String phone, String code, HttpServletRequest request){

        FfwyUser ffwyUser = new FfwyUser();
        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        //验证手机号是否为空
        if (!StringUtils.isEmpty(phone)){
            ffwyUser.setPhonenumber(phone);
            //验证手机号是否存在
            FfwyUser User = new FfwyUser();
            User.setPhonenumber(phone);
            List<FfwyUser> list = iFfwyUserService.selectFfwyUserList(User);
            if (null==list||list.size()==0){
                String s = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
                String redisCode=s.split("_")[0];
                //验证验证码是否过期
                if (!StringUtils.isEmpty(code)){
                    //判断验证码是否正确
                    if (redisCode.equals(code)){

                            //修改手机号
                        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(ffwyUser));

                    }else {

                        return AjaxResult.error(BizCodeEnum.CODE_EXCEPTION.getCode(),BizCodeEnum.CODE_EXCEPTION.getMessage());
                    }

                }else {
                        return AjaxResult.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMessage());
                }
            }else {

                return AjaxResult.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(),BizCodeEnum.PHONE_EXIST_EXCEPTION.getMessage());

            }

        }else {

            return AjaxResult.error(BizCodeEnum.PHONE_PASSWORD_EXCEPTION.getCode(),BizCodeEnum.PHONE_PASSWORD_EXCEPTION.getMessage());

        }


    }
}



