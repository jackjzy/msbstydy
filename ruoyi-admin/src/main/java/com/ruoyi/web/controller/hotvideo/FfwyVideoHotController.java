package com.ruoyi.web.controller.hotvideo;

import java.util.List;

import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.domain.FfwyVideoHot;
import com.ruoyi.back.domain.FfwyVideoHots;
import com.ruoyi.back.service.IFfwyVideoHotService;
import com.ruoyi.back.service.IFfwyVideoHotsService;
import com.ruoyi.back.service.IFfwyVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@RestController
@RequestMapping("/back/hot")
public class FfwyVideoHotController extends BaseController
{
    @Autowired
    private IFfwyVideoHotService ffwyVideoHotService;

    @Autowired
    private IFfwyVideoHotsService iFfwyVideoHotsService;



    @Autowired
    private IFfwyVideoService iFfwyVideoService;

    /**
     * 查询【请填写功能名称】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:hot:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyVideoHot ffwyVideoHot)
    {
        startPage();
        List<FfwyVideoHot> list = ffwyVideoHotService.selectFfwyVideoHotList(ffwyVideoHot);
        return getDataTable(list);
    }

    @GetMapping("/videos")
    public TableDataInfo getVideos(){
        FfwyVideo ffwyVideo = new FfwyVideo();
        ffwyVideo.setVideoStatus("0");
        startPage();
        List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoList(ffwyVideo);
        return getDataTable(ffwyVideos);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:hot:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyVideoHot ffwyVideoHot)
    {
        List<FfwyVideoHot> list = ffwyVideoHotService.selectFfwyVideoHotList(ffwyVideoHot);
        ExcelUtil<FfwyVideoHot> util = new ExcelUtil<FfwyVideoHot>(FfwyVideoHot.class);
        return util.exportExcel(list, "hot");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:hot:query')")
    @GetMapping(value = "/{hotId}")
    public AjaxResult getInfo(@PathVariable("hotId") Integer hotId)
    {
        return AjaxResult.success(ffwyVideoHotService.selectFfwyVideoHotById(hotId));
    }

    /**
     * 新增【请填写功能名称】
     */
 //   @PreAuthorize("@ss.hasPermi('system:hot:add')")
    @Log(title = "【新增一级】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyVideoHot ffwyVideoHot)
    {
        System.err.println(ffwyVideoHot);
        return toAjax(ffwyVideoHotService.insertFfwyVideoHot(ffwyVideoHot));
    }
    @Log(title = "【新增二级】", businessType = BusinessType.INSERT)
    @PostMapping("/addHots")
    public AjaxResult addHots(@RequestBody FfwyVideoHots ffwyVideoHots){

        return toAjax(iFfwyVideoHotsService.insertFfwyVideoHots(ffwyVideoHots));
    }

    /**
     * 修改【请填写功能名称】
     */
   // @PreAuthorize("@ss.hasPermi('system:hot:edit')")
    @Log(title = "【修改一级排行榜】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyVideoHot ffwyVideoHot)
    {
        return toAjax(ffwyVideoHotService.updateFfwyVideoHot(ffwyVideoHot));
    }

    @Log(title = "【修改二级排行榜】", businessType = BusinessType.UPDATE)
    @PutMapping("/changeHots")
    public AjaxResult edit(@RequestBody FfwyVideoHots ffwyVideoHots)
    {
        return toAjax(iFfwyVideoHotsService.updateFfwyVideoHots(ffwyVideoHots));
    }

    /**
     * 删除【请填写功能名称】
     */
   // @PreAuthorize("@ss.hasPermi('system:hot:remove')")
    @Log(title = "【删除一级排行榜】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{hotIds}")
    public AjaxResult remove(@PathVariable Integer hotIds)
    {
        return toAjax(ffwyVideoHotService.deleteFfwyVideoHotById(hotIds));
    }

    @Log(title = "【删除二级排行榜】", businessType = BusinessType.DELETE)
    @DeleteMapping("/hots")
    public AjaxResult remove(@RequestParam Long videoHotId)
    {
        return toAjax(iFfwyVideoHotsService.deleteFfwyVideoHotsById(videoHotId));
    }
}
