package com.ruoyi.app.controller.video;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;
import com.ruoyi.common.utils.Signature;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.admin.FfwyUserFans;
import com.ruoyi.system.mapper.admin.FfwyUserFansMapper;
import com.ruoyi.system.service.FfwySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruoyi.back.constant.Comment;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(tags = "详情")
@RestController
@RequestMapping("/app/elseuser")
public class FfwyUserhomepage extends BaseController{

    @Autowired
    private IFfwyUserService iFfwyUserService;

    @Autowired
    private IFfwyVideoService iFfwyVideoService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    private FfwyUserFansMapper ffwyUserFansMapper;



    //个人信息和我的视频
    @ApiOperation("个人信息和我的视频")
    @GetMapping("/personandmyvideo")
    public AjaxResult personandmyvideo(HttpServletRequest request,Long id){

        Map<String,Object> map =new HashMap<>();
        FfwyUser user = iFfwyUserService.selectFfwyUserById(id);
        //查询该用户的点赞数
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String s = ops.get(Comment.USER_LIKE + id);
        if (StringUtils.isNotEmpty(s)){
            Long userLikeNumber = Long.valueOf(s);

            user.setLikes(userLikeNumber);
        } else {
            user.setLikes(0L);
        }

        //查询用户的粉丝数
        int i = iFfwyUserService.selectFfwyUserByFansCount(id);
        user.setFans((long) i);
        //查询用户的关注数
        int i1 = iFfwyUserService.selectFfwyUserByAttentionCount(id);
        user.setAttention((long) i1);


        //添加关注3种状态
        //查询列表我关注
//        0:未关注  1:已关注  2:相互关注
        user.setType(0L);
        FfwyUserFans ffwyUserFans = new FfwyUserFans();
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        ffwyUserFans.setUserId(id);
        ffwyUserFans.setFansId(userId);
        List<FfwyUserFans> ffwyUserFans1 = ffwyUserFansMapper.selectFfwyUserFansList(ffwyUserFans);
        if (ffwyUserFans1.size() > 0) {
//           map.put("type", 1);
            user.setType(1L);
            FfwyUserFans userFans = new FfwyUserFans();
            userFans.setUserId(userId);
            userFans.setFansId(id);
            //查询相互关注
            List<FfwyUserFans> fans = ffwyUserFansMapper.selectFfwyUserFansList(userFans);
            if (fans.size() > 0) {
//                map.put("type", 2);
                user.setType(2L);
            }
        }
        map.put("user",user);//个人信息


//        //私有视频数量
//        int privateVideoCount = iFfwyVideoService.privateVideoCount(id);
//        map.put("privateVideoCount",privateVideoCount);
        return AjaxResult.success(map);

    }
    //查看我私有的视频
    @ApiOperation("我的私有视频")
    @GetMapping("/privatediveo")
    public TableDataInfo privatediveo(HttpServletRequest request){
        //请求头获取jwt,解密jwt获取用户id
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        //Long id= Long.valueOf(1);
        FfwyVideo ffwyVideo=new FfwyVideo();
        ffwyVideo.setUserId(id);
        ffwyVideo.setVideoStatus("2");
        List<FfwyVideo> list = iFfwyVideoService.selectFfwyVideoList(ffwyVideo);
        return getDataTable(list);
    }


    //查看我喜欢的视频
//    @ApiOperation("我喜欢的视频")
//    @GetMapping("/getVideolike")
//    public TableDataInfo getVideolike(HttpServletRequest request){
//        //请求头获取jwt,解密jwt获取用户id
//        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
//        startPage();
//        //Long id= Long.valueOf(1);
//        List<FfwyVideo> list = iFfwyVideoService.selectFfwyVideoByLike(id);
//
//        return getDataTable(list);
//
//    }
    //查看个人信息
    @ApiOperation("个人信息")
    @GetMapping("/getpersonaldetail")
    public AjaxResult getpersonaldetail(@RequestParam Long userId)
    {
        return AjaxResult.success(iFfwyUserService.selectFfwyUserById(userId));
    }

}
