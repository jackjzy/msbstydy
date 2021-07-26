package com.ruoyi.app.controller.video;

import java.util.*;

import com.google.common.collect.Maps;
import com.ruoyi.back.domain.FfwyReport;
import com.ruoyi.back.domain.FfwyReportType;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyReportService;
import com.ruoyi.back.service.IFfwyReportTypeService;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.utils.JacksonUtil;
import com.ruoyi.common.utils.Signature;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.video.FfwyVideoComment;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2021-05-14
 */
@Api(tags = "详情")
@RestController
@RequestMapping("/app/video")
public class FfwyVideoCommentController extends BaseController {
    @Autowired
    private IFfwyVideoCommentService ffwyVideoCommentService;

    @Autowired
    private IFfwyUserService iFfwyUserService;

    @Autowired
    private IFfwyReportService ffwyReportService;

    @Autowired
    private IFfwyReportTypeService ffwyReportTypeService;

    @Autowired
    private IFfwyStoreVideoService ffwyStoreVideoService;

    @Autowired
    private IFfwyVideoService iFfwyVideoService;

    @Autowired
    private FfwySearchService ffwySearchService;

    @Autowired
    private IFfwyAppVideoService ffwyAppVideoService;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    /**
     * 查询【请填写功能名称】列表
     */
    @ApiOperation("查询举报类型")
    //@PreAuthorize("@ss.hasPermi('system:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyReportType ffwyReportType) {
        startPage();
        List<FfwyReportType> list = ffwyReportTypeService.selectFfwyReportTypeList(ffwyReportType);
        return getDataTable(list);
    }

    /**
     * 举报视频
     */
    @ApiOperation("举报视频")
    //@PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "【举报视频】", businessType = BusinessType.INSERT)
    @PostMapping("/report")
    public AjaxResult reportvideo(HttpServletRequest request,@RequestBody String body) {
        Integer videoId = JacksonUtil.parseInteger(body, "videoId");
        String reportContent = JacksonUtil.parseString(body, "reportContent");
        Integer typeId = JacksonUtil.parseInteger(body, "typeId");

        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return toAjax(ffwyReportService.insertFfwyReportVideo(id, videoId ,reportContent,typeId));
        //return null;
    }




    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyVideoComment ffwyVideoComment) {
        List<FfwyVideoComment> list = ffwyVideoCommentService.selectFfwyVideoCommentList(ffwyVideoComment);
        ExcelUtil<FfwyVideoComment> util = new ExcelUtil<FfwyVideoComment>(FfwyVideoComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{videoCommentId}")
    public AjaxResult getInfo(@PathVariable("videoCommentId") Long videoCommentId) {
        return AjaxResult.success(ffwyVideoCommentService.selectFfwyVideoCommentById(videoCommentId));
    }

    /**
     * 收藏视频
     */
    @ApiOperation("收藏视频")
    //@PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "【收藏视频】", businessType = BusinessType.INSERT)
    @GetMapping("/collect")
    public AjaxResult addcollect(Integer videoId, HttpServletRequest request) {
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        int i = ffwyStoreVideoService.insertFfwyStoreVideo(videoId, id);
        if (i== -1){
            return toAjax( ffwyStoreVideoService.deleteFfwyStoreVideo(videoId,id));
        }
        return toAjax(i);
    }

    /**
     * 删除收藏的视频
     */
    @ApiOperation("删除收藏的视频")
    //@PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "【删除收藏的视频】", businessType = BusinessType.INSERT)
    @DeleteMapping("/delcollect")
    public AjaxResult delcollect(Long storeVideoId) {

        return toAjax(ffwyStoreVideoService.deleteFfwyStoreVideoById(storeVideoId));
    }

    /**
     * 添加评论
     */
    @ApiOperation("添加评论")
    //@PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "【添加评论】", businessType = BusinessType.INSERT)
    @PostMapping("/comment")
    public AjaxResult add(@RequestBody FfwyVideoComment ffwyVideoComment, HttpServletRequest request) {
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        Long videoId = ffwyVideoComment.getVideoId();
        ffwyVideoCommentService.insertFfwyVideoComment(ffwyVideoComment, id);
        FfwyVideo ffwyVideo = iFfwyVideoService.selectFfwyVideoById(ffwyVideoComment.getVideoId());
        List<FfwyvideouserVo> ffwyvideouserVoList =  ffwyAppVideoService.selectFfwyvideocountcommentsum(videoId);
        FfwyUser ffwyUser = iFfwyUserService.selectFfwyUserById(id);
        for (FfwyvideouserVo ffwyvideouserVo : ffwyvideouserVoList) {
            ffwyvideouserVo.setVideoId(videoId.intValue());
            ffwyvideouserVo.setUserId(id);
            ffwyvideouserVo.setVideoComment(ffwyVideoComment.getVideoComment());
            ffwyvideouserVo.setPhoto(prefixUrl+ffwyUser.getPhoto());
            ffwyvideouserVo.setUserName(ffwyUser.getUserName());
            ffwyvideouserVo.setCreateTime(new Date());
        }

        return AjaxResult.success(ffwyvideouserVoList);
    }

    @GetMapping("/videoOne/{videoId}")
    public AjaxResult queryOne(@PathVariable("videoId") Long videoId, HttpServletRequest request) {
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return AjaxResult.success(iFfwyVideoService.selectFfwyVideoByOne(videoId, userId));

    }

    //评论列表
    @GetMapping("/videoComment")
    public TableDataInfo getComment(Long videoId) {

        FfwyVideoComment ffwyVideoComment = new FfwyVideoComment();
        ffwyVideoComment.setVideoId(videoId);
        startPage();
        List<FfwyVideoComment> ffwyVideoComments = ffwyVideoCommentService.selectFfwyVideoCommentList(ffwyVideoComment);
        for (FfwyVideoComment videoComment : ffwyVideoComments) {

            videoComment.setPhoto(prefixUrl+videoComment.getPhoto());
        }
        return getDataTable(ffwyVideoComments);
    }

    /**
     * @RequestBody String body
     * //        Long userId = Long.parseLong(JacksonUtil.parseString(body, "userId"));
     * //        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
     * @param request
     * @return
     */
    @ApiOperation("关注")
    @PostMapping("/attention")
    public AjaxResult addlikeperson(@RequestBody String body, HttpServletRequest request) {

        String userId = JacksonUtil.parseString(body, "userId");
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        Map<String,Integer> re = new HashMap<String,Integer>();
        re.put("status",iFfwyUserService.insertFfwyUserFnas( Long.valueOf(userId),id));
        return AjaxResult.success(re);
    }

    @ApiOperation("取消关注")
    @PostMapping("/deletefans")
    public AjaxResult deleteFans(@RequestBody  String body, HttpServletRequest request) {
        String userId = JacksonUtil.parseString(body, "userId");
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return toAjax(iFfwyUserService.deleteFfwyUserByFans(Long.valueOf(userId), id));
    }


    //点赞
    @ApiOperation("点赞")
    @GetMapping("/like")
    public AjaxResult givelike(Long videoId,HttpServletRequest request){
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        long sum = iFfwyVideoService.videoAddLike(videoId, id);
        FfwyVideo ffwyVideo = iFfwyVideoService.selectFfwyVideoById(videoId);

        List<FfwyvideouserVo> ffwyvideouserVoList = ffwyAppVideoService.selectFfwyvideolikesum(videoId);

        if (sum == 1) {
            return AjaxResult.success(ffwyvideouserVoList);
        } else {
//            return toAjax(sum >= (long) 1);
            return AjaxResult.success(ffwyvideouserVoList);
        }

    }

    //取消点赞
    @ApiOperation("取消点赞")
    @GetMapping("/deletelike")
    public AjaxResult deletelike(Long videoId, HttpServletRequest request) {
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        long l = iFfwyVideoService.videoRemoveLike(videoId, id);
        return toAjax(l == (long) 4);
    }

    //保存视频
    @ApiOperation("保存视频")
    @GetMapping("/upload/{videoId}")
    public AjaxResult uploadvideo(@PathVariable Long videoId, HttpServletRequest request) {
        //请求头获取jwt,解密jwt获取用户id
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return AjaxResult.success(iFfwyVideoService.selectFfwyvideoByphth(videoId, id));
    }

    //保存视频
    @ApiOperation("获取签名")
    @GetMapping("/unload")
    public AjaxResult unloadvideo() {

        String secretId = "AKIDGKABwTCphzvcMqA2pPjmbQ0MdnhUuLXU";
        String secretKey = "tc9RUIsAHA3gHelhjc3HxOn1qlW5RjcH";

        Signature sign = new Signature();
        sign.setSecretId(secretId);
        sign.setSecretKey(secretKey);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2);

        try {
            String signature = sign.getUploadSignature();
            Map<String, String> map = Maps.newHashMap();
            map.put("signature", signature);
            // return JsonResult.succeed(map);
            return AjaxResult.success("签名获取成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            // return JsonResult.fail("获取签名失败");
            return AjaxResult.success("签名获取失败", null);
        }
    }

    @ApiOperation("获取返回来的视频信息")
    @PostMapping("/insertvideo")
    public AjaxResult unloadvideo(@RequestBody FfwyVideo ffwyvideo, HttpServletRequest request) {
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        int i = iFfwyVideoService.insertFfwyVideoanduserid(ffwyvideo, id);
        ffwySearchService.importAllvideo(ffwyvideo.getVideoId());
        return toAjax(i);
    }

    @ApiOperation("图片上传腾讯云方法")
    @PostMapping(value = "/addPhotos",headers = "content-type=multipart/form-data")
    public String addPhotos( MultipartFile file)
    {
        String path=null;
        if (file!=null){
            /*
             * 上传腾讯云返回路径
             *
             * */

            try {
                String path1 = CosUtil.CosUpload(file);
                path = prefixUrl+path1;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return path;
    }
}
