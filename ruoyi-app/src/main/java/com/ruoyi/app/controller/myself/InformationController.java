package com.ruoyi.app.controller.myself;


import com.ruoyi.app.config.JwtTokenUtil;
import com.ruoyi.back.constant.Comment;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BizCodeEnum;
import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyRole;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.vo.UserVo;
import com.ruoyi.system.service.IFfwyUserService;
import com.vdurmont.emoji.EmojiParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.*;

@Api("个人信息")
@RestController
@RequestMapping("/app/individual")
public class InformationController extends BaseController {
    @Autowired
    private IFfwyUserService iFfwyUserService;


    @Autowired
    private IFfwyVideoService iFfwyVideoService;



    @Autowired
    RedisTemplate<String,String> redisTemplate;

    //个人信息和我的视频
    @ApiOperation("个人信息和我的视频")
    @GetMapping
    public AjaxResult getinfomation(HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader("token"));
        long userLikeNumber=0;
        //Long id= Long.valueOf(1);
        Map<String,Object> map =new HashMap<>();
        FfwyUser user = iFfwyUserService.selectFfwyUserById(id);
        String s1 = EmojiParser.parseToUnicode(user.getUserName());
        user.setUserName(s1);
        //查询该用户的点赞数
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String s = ops.get(Comment.USER_LIKE + id);
        if (s!=null){
            userLikeNumber = Long.valueOf(s);
        }


        user.setLikes(userLikeNumber);
        //查询用户的粉丝数
        int i = iFfwyUserService.selectFfwyUserByFansCount(id);
        user.setFans((long) i);
        //查询用户的关注数
        int i1 = iFfwyUserService.selectFfwyUserByAttentionCount(id);
        user.setAttention((long) i1);



        map.put("user",user);//个人信息
        List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoByUserId(id);
        map.put("video",ffwyVideos);//我的视频
        //我的视频数量
        Long videoCount=null==ffwyVideos?0:(long)ffwyVideos.size();
        map.put("videoCount",videoCount);
        //我喜欢的视频数量
        int loveVideoCount = iFfwyVideoService.loveVideoCount(id);
        map.put("loveVideoCount",loveVideoCount);
        //私有视频数量
        int privateVideoCount = iFfwyVideoService.privateVideoCount(id);
        map.put("privateVideoCount",privateVideoCount);
        return AjaxResult.success(map);

    }

    @GetMapping("/getToken")
    public String getToken(){
        FfwyUser user = iFfwyUserService.selectFfwyUserById((long) 4);
        String appToke = JwtTokenUtil.getAppToke(user);
        return appToke;
    }
    //查看我私有的视频
    @ApiOperation("我的视频")
    @GetMapping("/myVideo")
    public TableDataInfo getVideoByself(HttpServletRequest request,Long userId){
        if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
            //请求头获取jwt,解密jwt获取用户id
            Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));

            //Long id= Long.valueOf(1);

            startPage();
            List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoByUserId(id);

            return getDataTable(ffwyVideos);
        } else {
            //根据视频用户的Id查数据
            startPage();
            List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoByUserId(userId);

            return getDataTable(ffwyVideos);
        }
    }
    //查看我私有的视频
    @ApiOperation("我的私有视频")
    @GetMapping("/secret")
    public TableDataInfo getVideoByLike(HttpServletRequest request){
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
    @ApiOperation("我喜欢的视频")
    @GetMapping("/like")
    public TableDataInfo getVideoByPrivate(Long pageNum,Long pageSize,HttpServletRequest request){
        //请求头获取jwt,解密jwt获取用户id
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));

        //Long id= Long.valueOf(1);

        List<FfwyVideo> list = iFfwyVideoService.selectFfwyVideoByLike(id,pageNum,pageSize);

        return getDataTable(list);

    }
    //查看个人信息
    @ApiOperation("个人信息")
    @GetMapping("/myself")
    public AjaxResult getInfo(HttpServletRequest request) {
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return AjaxResult.success(iFfwyUserService.selectFfwyUserById(id));
    }
    //修改头像
    @ApiOperation("修改图像")
    @PostMapping("/photo")
    public AjaxResult updatePhoto(MultipartFile file, HttpServletRequest request){

        System.err.println(file.isEmpty());

        FfwyUser ffwyUser = new FfwyUser();
        //请求头获取jwt,解密jwt获取用户id
        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        /*
        * 上传图片，并修改图片路径
        * */
        FfwyUser user = iFfwyUserService.selectFfwyUserById(ffwyUser.getUserId());
        //获取原来的图片的路径
        String path = user.getPhoto();
        String photo=null;
        try {
            //上传图片返回的路径
            photo=CosUtil.CosUpload(file);
            //删除原来的图片
            //if (null!=path)
            //CosUtil.deletePhoto(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ffwyUser.setPhoto(photo);
        System.err.println(ffwyUser);
        int i = iFfwyUserService.updateFfwyUserOther(ffwyUser);

        //修改图片的路径
        return AjaxResult.success(CosCofig.getPrefixUrl()+photo);
    }

    //更改用户名
    @ApiOperation("修改用户")
    @PutMapping("/update/username")
    public AjaxResult updateUserName(String userName, HttpServletRequest request){
        //请求头获取jwt,解密jwt获取用户id
        FfwyUser ffwyUser = new FfwyUser();
        ffwyUser.setUserName(userName);
        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(ffwyUser));
    }
    //更改性别
    @ApiOperation("修改性别")
    @PutMapping("/update/sex")
    public AjaxResult updateSex(String sex, HttpServletRequest request){
        FfwyUser ffwyUser = new FfwyUser();
        //请求头获取jwt,解密jwt获取用户id
        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        ffwyUser.setSex(sex);

        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(ffwyUser));
    }
    //更改生日
    @ApiOperation("修改生日")
    @PutMapping("/update/birthday")
    public AjaxResult updateBirthday(Date birthday, HttpServletRequest request){
        FfwyUser ffwyUser = new FfwyUser();
        //请求头获取jwt,解密jwt获取用户id
        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        ffwyUser.setBirthday(birthday);

        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(ffwyUser));
    }
    //更改地址
    @ApiOperation("修改所在城市")
    @PutMapping("/update/addr")
    public AjaxResult updateAddr(String addr, HttpServletRequest request){
        FfwyUser ffwyUser = new FfwyUser();
        ffwyUser.setCity(addr);
        //请求头获取jwt,解密jwt获取用户id
        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(ffwyUser));
    }
    //更改用户简介
    @ApiOperation("修改简介")
    @PutMapping("/update/intro")
    public AjaxResult updateIntro(String intro, HttpServletRequest request){
        FfwyUser ffwyUser = new FfwyUser();
        ffwyUser.setIntro(intro);
        //请求头获取jwt,解密jwt获取用户id
        ffwyUser.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));
        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(ffwyUser));
    }
    @ApiOperation("切换角色")
    @PutMapping("/update/role")
    public AjaxResult updaterole(Long roleId, HttpServletRequest request){
        //请求头获取jwt,解密jwt获取用户id
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyUser user = iFfwyUserService.selectFfwyUserById(id);
        //判断用户用户类型 0表示为普通用户 1表示工长 2表示商家
        if ("0".equals(user.getUserType())){
            if (!"0".equals(roleId.toString()))
                //用户类型为用户则不能切换
                return AjaxResult.error(BizCodeEnum.ROLE_USER_ERROR.getCode(),BizCodeEnum.ROLE_USER_ERROR.getMessage());

        }else if ("1".equals(user.getUserType())){
            if ("2".equals(roleId.toString()))
                //用户类型为工长不能切换为商家
                return AjaxResult.error(BizCodeEnum.ROLE_WORKER_ERROR.getCode(),BizCodeEnum.ROLE_WORKER_ERROR.getMessage());

        }else if ("2".equals(user.getUserType())){
            if ("1".equals(roleId.toString()))
                //用户类型为商家不能切换为工长
                return AjaxResult.error(BizCodeEnum.ROLE_SHOP_ERROR.getCode(),BizCodeEnum.ROLE_SHOP_ERROR.getMessage());

        }else {
            //为其他类型则不能切换
            return AjaxResult.error(BizCodeEnum.ROLE_ERROR.getCode(),BizCodeEnum.ROLE_ERROR.getMessage());

        }
        //修改用户当前角色
        FfwyRole ffwyRole = new FfwyRole();
        ffwyRole.setRoleId(roleId);
        user.setFfwyRole(ffwyRole);
        /*
       * 切换用户角色
       *
       * */

        return AjaxResult.success(iFfwyUserService.updateFfwyUserOther(user));
    }


    @ApiOperation("关注列表")
    @GetMapping("/attention")
    public TableDataInfo attention(HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        List<UserVo> list = iFfwyUserService.selectFfwyUserByAttention(id);

        return getDataTable(list);
    }

    @ApiOperation("粉丝列表")
    @GetMapping("/fans")
    public TableDataInfo fans(HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        List<UserVo> list = iFfwyUserService.selectFfwyUserByFans(id);
        return getDataTable(list);
    }

    @ApiOperation("添加粉丝")
    @PostMapping("/addfans")
    public AjaxResult addFans(@RequestParam Long userId,HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        int i = iFfwyUserService.insertFfwyUserFnas(id, userId);
        if (i<0) return AjaxResult.error(i,"不能重复添加");

        return toAjax(i);
    }

    @ApiOperation("回关")
    @PostMapping("/backFollow")
    public AjaxResult backFollow(@RequestParam Long userId,HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        int i = iFfwyUserService.insertFfwyUserFnas(userId, id);
        if (i<0) return AjaxResult.error(i,"不能重复添加");

        return toAjax(i);
    }
    @ApiOperation("删除粉丝/取消关注")
    @DeleteMapping("/removefans")
    public AjaxResult removeFans(Long userId,HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));

        return toAjax(iFfwyUserService.deleteFfwyUserByFans(userId,id));
    }
    @ApiOperation("草稿箱")
    @GetMapping("/draftbox")
    public TableDataInfo draftbox(HttpServletRequest request){
        FfwyVideo ffwyVideo = new FfwyVideo();
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        ffwyVideo.setUserId(userId);
        ffwyVideo.setVideoStatus("1");
        startPage();
        List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoList(ffwyVideo);
        return getDataTable(ffwyVideos);
    }

    @ApiOperation("批量刪除視頻")
    @DeleteMapping("/deleteVideo/{videoIds}")
    public AjaxResult deleteVideo(@PathVariable Long[] videoIds){
        return toAjax(iFfwyVideoService.deleteFfwyVideoByIds(videoIds));
    }

}
