package com.ruoyi.back.controller.report;

import java.util.List;

import com.ruoyi.back.domain.FfwyReport;
import com.ruoyi.back.service.IFfwyReportService;
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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 举报管理Controller
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Api(tags = "举报管理")
@RestController
@RequestMapping("/back/report")
public class FfwyReportController extends BaseController
{
    @Autowired
    private IFfwyReportService ffwyReportService;

    /**
     * 查询举报管理列表
     */
   // @PreAuthorize("@ss.hasPermi('back:report:list')")
    @GetMapping("/list")
    @ApiOperation("举报列表")
    public TableDataInfo list(FfwyReport ffwyReport)
    {
        startPage();
        List<FfwyReport> list = ffwyReportService.selectFfwyReportList(ffwyReport);
        return getDataTable(list);
    }

    /**
     * 导出举报管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:report:export')")
    @Log(title = "举报管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyReport ffwyReport)
    {
        List<FfwyReport> list = ffwyReportService.selectFfwyReportList(ffwyReport);
        ExcelUtil<FfwyReport> util = new ExcelUtil<FfwyReport>(FfwyReport.class);
        return util.exportExcel(list, "report");
    }

    /**
     * 获取举报管理详细信息
     */
    //@PreAuthorize("@ss.hasPermi('back:report:query')")
    @GetMapping(value = "/{reportId}")
    @ApiOperation("举报详情信息")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return AjaxResult.success(ffwyReportService.selectFfwyReportById(reportId));
    }

    /**
     * 新增举报管理
     */
    @PreAuthorize("@ss.hasPermi('back:report:add')")
    @Log(title = "举报管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyReport ffwyReport)
    {
        return toAjax(ffwyReportService.insertFfwyReport(ffwyReport));
    }

    /**
     * 回复举报信息
     */

    @PutMapping
    public AjaxResult edit(@RequestBody FfwyReport ffwyReport)
    {
        return toAjax(ffwyReportService.reply(ffwyReport));
    }

    /**
     * 删除举报管理
     */
    @PreAuthorize("@ss.hasPermi('back:report:remove')")
    @Log(title = "举报管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(ffwyReportService.deleteFfwyReportByIds(reportIds));
    }
}
