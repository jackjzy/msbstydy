package com.ruoyi.back.controller.video;

import java.util.List;

import com.ruoyi.common.utils.jwt.JWTUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 视频Controller
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@RestController
@RequestMapping("/back/video")
public class FfwyVideoController extends BaseController
{
    @Autowired
    private IFfwyVideoService ffwyVideoService;

    /**
     * 查询视频列表
     */
    @PreAuthorize("@ss.hasPermi('back:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyVideo ffwyVideo)
    {
        startPage();
        List<FfwyVideo> list = ffwyVideoService.selectFfwyVideoList(ffwyVideo);
        return getDataTable(list);
    }

    /**
     * 导出视频列表
     */
    @PreAuthorize("@ss.hasPermi('back:video:export')")
    @Log(title = "视频", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyVideo ffwyVideo)
    {
        List<FfwyVideo> list = ffwyVideoService.selectFfwyVideoList(ffwyVideo);
        ExcelUtil<FfwyVideo> util = new ExcelUtil<FfwyVideo>(FfwyVideo.class);
        return util.exportExcel(list, "video");
    }

    /**
     * 获取视频详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:video:query')")
    @GetMapping(value = "/{videoId}")
    public AjaxResult getInfo(@PathVariable("videoId") Long videoId, HttpServletRequest request)
    {   Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return AjaxResult.success(ffwyVideoService.selectFfwyVideoByOne(videoId,userId));
    }

    /**
     * 新增视频
     */
    @PreAuthorize("@ss.hasPermi('back:video:add')")
    @Log(title = "视频", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyVideo ffwyVideo)
    {
        return toAjax(ffwyVideoService.insertFfwyVideo(ffwyVideo));
    }

    /**
     * 修改视频
     */
    @PreAuthorize("@ss.hasPermi('back:video:edit')")
    @Log(title = "视频", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyVideo ffwyVideo)
    {
        return toAjax(ffwyVideoService.updateFfwyVideo(ffwyVideo));
    }

    /**
     * 删除视频
     */
    @PreAuthorize("@ss.hasPermi('back:video:remove')")
    @Log(title = "视频", businessType = BusinessType.DELETE)
	@DeleteMapping("/{videoIds}")
    public AjaxResult remove(@PathVariable Long[] videoIds)
    {
        return toAjax(ffwyVideoService.deleteFfwyVideoByIds(videoIds));
    }
}
