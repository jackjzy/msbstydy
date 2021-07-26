package com.ruoyi.app.controller.videolike;

import com.ruoyi.back.constant.Comment;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BizCodeEnum;
import com.ruoyi.common.utils.jwt.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api("视频")
@RestController
@RequestMapping("/video/like")
public class VideoLikeController extends BaseController {
    @Autowired
    private IFfwyVideoService iFfwyVideoService;

    @Autowired
    private StringRedisTemplate redisTemplate;




    //点赞
    @ApiOperation("点赞")
    @GetMapping("/givelike")
    public AjaxResult like(Long videoId,HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        long sum = iFfwyVideoService.videoAddLike(videoId, id);

        return toAjax(sum==(long) 3);
    }

    //取消点赞
    @ApiOperation("取消点赞")
    @GetMapping("/cancellike")
    public AjaxResult noLike(Long videoId,HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        long l = iFfwyVideoService.videoAddLike(videoId, id);


        return toAjax(l==(long) 4);
    }

    @DeleteMapping
    //刪除視頻
    public AjaxResult deleteVideo(Long videoId){

        return toAjax(iFfwyVideoService.deleteFfwyVideoById(videoId));
    }

    @PutMapping("/private")
    //修改視頻為私密視頻
    public AjaxResult privateVideo(Long videoId){
        FfwyVideo ffwyVideo = iFfwyVideoService.selectFfwyVideoById(videoId);
        ffwyVideo.setVideoStatus("2");
        return toAjax(iFfwyVideoService.updateFfwyVideo(ffwyVideo));
    }

    //修改視頻為公開視頻
    @PutMapping("/public")
    public AjaxResult publicVideo(Long videoId){
        FfwyVideo ffwyVideo = iFfwyVideoService.selectFfwyVideoById(videoId);
        ffwyVideo.setVideoStatus("0");
        return toAjax(iFfwyVideoService.updateFfwyVideo(ffwyVideo));
    }
}
