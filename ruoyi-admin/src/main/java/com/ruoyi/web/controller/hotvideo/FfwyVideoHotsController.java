package com.ruoyi.web.controller.hotvideo;

import java.util.List;

import com.ruoyi.back.domain.FfwyVideoHots;
import com.ruoyi.back.service.IFfwyVideoHotsService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@RestController
@RequestMapping("/system/hots")
public class FfwyVideoHotsController extends BaseController
{
    @Autowired
    private IFfwyVideoHotsService ffwyVideoHotsService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:hots:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyVideoHots ffwyVideoHots)
    {
        startPage();
        List<FfwyVideoHots> list = ffwyVideoHotsService.selectFfwyVideoHotsList(ffwyVideoHots);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:hots:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyVideoHots ffwyVideoHots)
    {
        List<FfwyVideoHots> list = ffwyVideoHotsService.selectFfwyVideoHotsList(ffwyVideoHots);
        ExcelUtil<FfwyVideoHots> util = new ExcelUtil<FfwyVideoHots>(FfwyVideoHots.class);
        return util.exportExcel(list, "hots");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hots:query')")
    @GetMapping(value = "/{videoHotId}")
    public AjaxResult getInfo(@PathVariable("videoHotId") Long videoHotId)
    {
        return AjaxResult.success(ffwyVideoHotsService.selectFfwyVideoHotsById(videoHotId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:hots:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyVideoHots ffwyVideoHots)
    {
        return toAjax(ffwyVideoHotsService.insertFfwyVideoHots(ffwyVideoHots));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:hots:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyVideoHots ffwyVideoHots)
    {
        return toAjax(ffwyVideoHotsService.updateFfwyVideoHots(ffwyVideoHots));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:hots:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{videoHotIds}")
    public AjaxResult remove(@PathVariable Long[] videoHotIds)
    {
        return toAjax(ffwyVideoHotsService.deleteFfwyVideoHotsByIds(videoHotIds));
    }
}
