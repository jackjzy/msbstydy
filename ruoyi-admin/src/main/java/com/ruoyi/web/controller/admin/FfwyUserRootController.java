package com.ruoyi.web.controller.admin;

import java.util.List;

import com.ruoyi.system.domain.admin.FfwyUserRoot;
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
import com.ruoyi.system.service.IFfwyUserRootService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@RestController
@RequestMapping("/system/root")
public class FfwyUserRootController extends BaseController
{
    @Autowired
    private IFfwyUserRootService ffwyUserRootService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:root:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyUserRoot ffwyUserRoot)
    {
        startPage();
        List<FfwyUserRoot> list = ffwyUserRootService.selectFfwyUserRootList(ffwyUserRoot);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:root:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyUserRoot ffwyUserRoot)
    {
        List<FfwyUserRoot> list = ffwyUserRootService.selectFfwyUserRootList(ffwyUserRoot);
        ExcelUtil<FfwyUserRoot> util = new ExcelUtil<FfwyUserRoot>(FfwyUserRoot.class);
        return util.exportExcel(list, "root");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:root:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(ffwyUserRootService.selectFfwyUserRootById(userId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:root:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyUserRoot ffwyUserRoot)
    {
        return toAjax(ffwyUserRootService.insertFfwyUserRoot(ffwyUserRoot));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:root:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyUserRoot ffwyUserRoot)
    {
        return toAjax(ffwyUserRootService.updateFfwyUserRoot(ffwyUserRoot));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:root:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deleteusers}")
    public AjaxResult remove(@PathVariable Long[] deleteusers)
    {
        return toAjax(ffwyUserRootService.deleteFfwyUserRootByIds(deleteusers));
    }
}
