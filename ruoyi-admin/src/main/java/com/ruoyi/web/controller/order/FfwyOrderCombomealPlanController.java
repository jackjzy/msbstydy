package com.ruoyi.web.controller.order;

import java.util.List;

import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomealPlan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IFfwyOrderCombomealPlanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 套餐设计图Controller
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
@RestController
@RequestMapping("/back/plan")
public class FfwyOrderCombomealPlanController extends BaseController
{
    @Autowired
    private IFfwyOrderCombomealPlanService ffwyOrderCombomealPlanService;

    /**
     * 查询套餐设计图列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyOrderCombomealPlan ffwyOrderCombomealPlan)
    {
        startPage();
        List<FfwyOrderCombomealPlan> list = ffwyOrderCombomealPlanService.selectFfwyOrderCombomealPlanList(ffwyOrderCombomealPlan);
        return getDataTable(list);
    }

    /**
     * 导出套餐设计图列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:export')")
    @Log(title = "套餐设计图", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyOrderCombomealPlan ffwyOrderCombomealPlan)
    {
        List<FfwyOrderCombomealPlan> list = ffwyOrderCombomealPlanService.selectFfwyOrderCombomealPlanList(ffwyOrderCombomealPlan);
        ExcelUtil<FfwyOrderCombomealPlan> util = new ExcelUtil<FfwyOrderCombomealPlan>(FfwyOrderCombomealPlan.class);
        return util.exportExcel(list, "plan");
    }

    /**
     * 获取套餐设计图详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:plan:query')")
    @GetMapping(value = "/{photoId}")
    public AjaxResult getInfo(@PathVariable("photoId") Long photoId)
    {
        return AjaxResult.success(ffwyOrderCombomealPlanService.selectFfwyOrderCombomealPlanById(photoId));
    }

    /**
     * 新增套餐设计图
     */
    //@PreAuthorize("@ss.hasPermi('system:plan:add')")
    @Log(title = "套餐设计图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestParam Long orderId, MultipartFile file)
    {
        return toAjax(ffwyOrderCombomealPlanService.insertFfwyOrderCombomealPlan(orderId, file));
    }

    /**
     * 修改套餐设计图
     */
    @PreAuthorize("@ss.hasPermi('system:plan:edit')")
    @Log(title = "套餐设计图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyOrderCombomealPlan ffwyOrderCombomealPlan)
    {
        return toAjax(ffwyOrderCombomealPlanService.updateFfwyOrderCombomealPlan(ffwyOrderCombomealPlan));
    }

    /**
     * 删除套餐设计图
     */
    @PreAuthorize("@ss.hasPermi('system:plan:remove')")
    @Log(title = "套餐设计图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{photoIds}")
    public AjaxResult remove(@PathVariable Long[] photoIds)
    {
        return toAjax(ffwyOrderCombomealPlanService.deleteFfwyOrderCombomealPlanByIds(photoIds));
    }
}
