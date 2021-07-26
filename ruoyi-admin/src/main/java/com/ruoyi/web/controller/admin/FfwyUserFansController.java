package com.ruoyi.web.controller.admin;

import java.util.List;

import com.ruoyi.system.domain.admin.FfwyUserFans;
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
import com.ruoyi.system.service.IFfwyUserFansService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 粉丝Controller
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@RestController
@RequestMapping("/system/fans")
public class FfwyUserFansController extends BaseController
{
    @Autowired
    private IFfwyUserFansService ffwyUserFansService;

    /**
     * 查询粉丝列表
     */
    @PreAuthorize("@ss.hasPermi('system:fans:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyUserFans ffwyUserFans)
    {
        startPage();
        List<FfwyUserFans> list = ffwyUserFansService.selectFfwyUserFansList(ffwyUserFans);
        return getDataTable(list);
    }

    /**
     * 导出粉丝列表
     */
    @PreAuthorize("@ss.hasPermi('system:fans:export')")
    @Log(title = "粉丝", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyUserFans ffwyUserFans)
    {
        List<FfwyUserFans> list = ffwyUserFansService.selectFfwyUserFansList(ffwyUserFans);
        ExcelUtil<FfwyUserFans> util = new ExcelUtil<FfwyUserFans>(FfwyUserFans.class);
        return util.exportExcel(list, "fans");
    }

    /**
     * 获取粉丝详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:fans:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(ffwyUserFansService.selectFfwyUserFansById(userId));
    }

    /**
     * 新增粉丝
     */
    @PreAuthorize("@ss.hasPermi('system:fans:add')")
    @Log(title = "粉丝", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyUserFans ffwyUserFans)
    {
        return toAjax(ffwyUserFansService.insertFfwyUserFans(ffwyUserFans));
    }

    /**
     * 修改粉丝
     */
    @PreAuthorize("@ss.hasPermi('system:fans:edit')")
    @Log(title = "粉丝", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyUserFans ffwyUserFans)
    {
        return toAjax(ffwyUserFansService.updateFfwyUserFans(ffwyUserFans));
    }

    /**
     * 删除粉丝
     */
    @PreAuthorize("@ss.hasPermi('system:fans:remove')")
    @Log(title = "粉丝", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(ffwyUserFansService.deleteFfwyUserFansByIds(userIds));
    }
}
