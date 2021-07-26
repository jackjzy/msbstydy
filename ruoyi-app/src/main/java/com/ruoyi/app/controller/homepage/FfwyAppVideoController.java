package com.ruoyi.app.controller.homepage;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.search.FfwyVideoSearch;
import com.ruoyi.system.domain.video.FfwyVideoHotVo;
import com.ruoyi.system.domain.video.FfwyVideoHotsVo;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;
import com.ruoyi.system.mapper.search.FfwysearchMapper;
import com.ruoyi.system.mapper.video.FfwyAppVideoMapper;
import com.ruoyi.system.service.FfwySearchService;
import com.ruoyi.system.service.IFfwyAppVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.service.IFfwyCombomealService;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-05-10
 */
@Api(tags = "首页")
@RestController
@RequestMapping("/app/homepage")
public class FfwyAppVideoController extends BaseController
{
    @Autowired
    private IFfwyCombomealService ffwyCombomealService;

    @Autowired
    private IFfwyAppVideoService ffwyAppVideoService;

    @Resource
    private FfwyAppVideoMapper ffwyAppVideoMapper;
    /**
     * 查询热度榜分类
     */
    @ApiOperation("查询热度榜分类")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/findBylist")
    public TableDataInfo findBylist()
    {
        List<FfwyVideoHotVo> list = ffwyCombomealService.selectFfwyCombomealBylists();
        return getDataTable(list);
    }

    /**
     * 查询热度榜分类详情
     */
    @ApiOperation("查询热度榜分类详情")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/findBylists")
    public TableDataInfo findBylists( Long hotId)
    {
        List<FfwyVideoHotsVo> list = ffwyCombomealService.selectFfwyCombomealfindBylists(hotId);
        return getDataTable(list);
    }

    /**
     * 热门
     */
    @ApiOperation("推荐")
    @GetMapping("/hotVido")
    public TableDataInfo hotVido(HttpServletRequest request)
    {
        if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))){
            Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
            List<FfwyvideouserVo> ffwyvideouserVos1 = ffwyAppVideoMapper.selectNotLove(userId);
            startPage();
            List<FfwyvideouserVo> list = ffwyAppVideoService.selectFfwyvideoByhotVido(userId,ffwyvideouserVos1);
            return getDataTable(list);
        }else {

            startPage();
            List<FfwyvideouserVo> list = ffwyAppVideoService.selectFfwyvideohotVido();
            return getDataTable(list);
        }

    }

    /**
     *
     */
    @ApiOperation("用户关注的视频")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/likeperson")
    public TableDataInfo likeperson(HttpServletRequest request)
    {
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        List<FfwyVideoSearch> list = ffwyAppVideoService.selectFfwyvideoByhotlikeperson(userId);
        return getDataTable(list);
    }


    /**
     *
     */
    @ApiOperation("点赞数量 ")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/likesum")
    public TableDataInfo likesum(Long videoId) {
        List<FfwyvideouserVo> list = ffwyAppVideoService.selectFfwyvideolikesum(videoId);
        return  getDataTable(list);
    }


    /**
     *
     */
    @ApiOperation("评论数量 ")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/countcommentsum")
    public TableDataInfo countcommentsum(Long videoId) {
        List<FfwyvideouserVo> list = ffwyAppVideoService.selectFfwyvideocountcommentsum(videoId);
        return  getDataTable(list);
    }

    /**
     *
     */
    @ApiOperation("视频转发")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/forward")
    public AjaxResult videoForward(HttpServletRequest request, Long videoId) {
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        int i = ffwyAppVideoService.insertVideoForward(userId, videoId);
        return  toAjax(i);
    }


}
