package com.ruoyi.web.controller.phase;

import java.util.List;



import com.ruoyi.system.domain.combomealorders.FfwyPhase;

import com.ruoyi.system.service.IFfwyPhaseService;
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
 * @date 2021-04-26
 */
@RestController
@RequestMapping("/system/phase")
public class FfwyPhaseController extends BaseController
{
    @Autowired
    private IFfwyPhaseService ffwyPhaseService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:phase:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyPhase ffwyPhase)
    {
        startPage();
        List<FfwyPhase> list = ffwyPhaseService.selectFfwyPhaseList(ffwyPhase);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:phase:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyPhase ffwyPhase)
    {
        List<FfwyPhase> list = ffwyPhaseService.selectFfwyPhaseList(ffwyPhase);
        ExcelUtil<FfwyPhase> util = new ExcelUtil<FfwyPhase>(FfwyPhase.class);
        return util.exportExcel(list, "phase");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:phase:query')")
    @GetMapping(value = "/{phaseId}")
    public AjaxResult getInfo(@PathVariable("phaseId") Long phaseId)
    {
        return AjaxResult.success(ffwyPhaseService.selectFfwyPhaseById(phaseId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:phase:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyPhase ffwyPhase)
    {
        return toAjax(ffwyPhaseService.insertFfwyPhase(ffwyPhase));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:phase:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyPhase ffwyPhase)
    {
        return toAjax(ffwyPhaseService.updateFfwyPhase(ffwyPhase));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:phase:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{phaseIds}")
    public AjaxResult remove(@PathVariable Long[] phaseIds)
    {
        return toAjax(ffwyPhaseService.deleteFfwyPhaseByIds(phaseIds));
    }
}
