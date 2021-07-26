package com.ruoyi.web.controller.order;

import java.util.List;
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
import com.ruoyi.system.domain.order.FfwyIncome;
import com.ruoyi.system.service.IFfwyIncomeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收入明细Controller
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@RestController
@RequestMapping("/system/ffwy_income")
public class FfwyIncomeController extends BaseController
{
    @Autowired
    private IFfwyIncomeService ffwyIncomeService;

    /**
     * 查询收入明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_income:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyIncome ffwyIncome)
    {
        startPage();
        List<FfwyIncome> list = ffwyIncomeService.selectFfwyIncomeList(ffwyIncome);
        return getDataTable(list);
    }

    /**
     * 导出收入明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_income:export')")
    @Log(title = "收入明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyIncome ffwyIncome)
    {
        List<FfwyIncome> list = ffwyIncomeService.selectFfwyIncomeList(ffwyIncome);
        ExcelUtil<FfwyIncome> util = new ExcelUtil<FfwyIncome>(FfwyIncome.class);
        return util.exportExcel(list, "ffwy_income");
    }

    /**
     * 获取收入明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_income:query')")
    @GetMapping(value = "/{incomeId}")
    public AjaxResult getInfo(@PathVariable("incomeId") Long incomeId)
    {
        return AjaxResult.success(ffwyIncomeService.selectFfwyIncomeById(incomeId));
    }

    /**
     * 新增收入明细
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_income:add')")
    @Log(title = "收入明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyIncome ffwyIncome)
    {
        return toAjax(ffwyIncomeService.insertFfwyIncome(ffwyIncome));
    }

    /**
     * 修改收入明细
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_income:edit')")
    @Log(title = "收入明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyIncome ffwyIncome)
    {
        return toAjax(ffwyIncomeService.updateFfwyIncome(ffwyIncome));
    }

    /**
     * 删除收入明细
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_income:remove')")
    @Log(title = "收入明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{incomeIds}")
    public AjaxResult remove(@PathVariable Long[] incomeIds)
    {
        return toAjax(ffwyIncomeService.deleteFfwyIncomeByIds(incomeIds));
    }
}
