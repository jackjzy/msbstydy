package com.ruoyi.back.controller.report;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.back.domain.FfwyReportType;
import com.ruoyi.back.service.IFfwyReportTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 举报管理Controller
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Api(tags = "举报类型")
@RestController
@RequestMapping("/back/reportType")
public class FfwyReportTypeController extends BaseController
{
    @Autowired
    private IFfwyReportTypeService ffwyReportTypeService;

    /**
     * 查询举报管理列表
     */
    @ApiOperation("举报类型列表")
    //@PreAuthorize("@ss.hasPermi('back:reportType:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyReportType ffwyReportType)
    {
        startPage();
        List<FfwyReportType> list = ffwyReportTypeService.selectFfwyReportTypeList(ffwyReportType);
        return getDataTable(list);
    }

    /**
     * 导出举报管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:reportType:export')")
    @Log(title = "举报管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyReportType ffwyReportType)
    {
        List<FfwyReportType> list = ffwyReportTypeService.selectFfwyReportTypeList(ffwyReportType);
        ExcelUtil<FfwyReportType> util = new ExcelUtil<FfwyReportType>(FfwyReportType.class);
        return util.exportExcel(list, "reportType");
    }

    /**
     * 获取举报管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:reportType:query')")
    @GetMapping(value = "/{reportTypeId}")
    public AjaxResult getInfo(@PathVariable("reportTypeId") Long reportTypeId)
    {
        return AjaxResult.success(ffwyReportTypeService.selectFfwyReportTypeById(reportTypeId));
    }

    /**
     * 新增举报管理
     */
    @PreAuthorize("@ss.hasPermi('back:reportType:add')")
    @Log(title = "举报管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyReportType ffwyReportType)
    {
        return toAjax(ffwyReportTypeService.insertFfwyReportType(ffwyReportType));
    }

    /**
     * 修改举报管理
     */
    @PreAuthorize("@ss.hasPermi('back:reportType:edit')")
    @Log(title = "举报管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyReportType ffwyReportType)
    {
        return toAjax(ffwyReportTypeService.updateFfwyReportType(ffwyReportType));
    }

    /**
     * 删除举报管理
     */
    @PreAuthorize("@ss.hasPermi('back:reportType:remove')")
    @Log(title = "举报管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportTypeIds}")
    public AjaxResult remove(@PathVariable Long[] reportTypeIds)
    {
        return toAjax(ffwyReportTypeService.deleteFfwyReportTypeByIds(reportTypeIds));
    }
}
