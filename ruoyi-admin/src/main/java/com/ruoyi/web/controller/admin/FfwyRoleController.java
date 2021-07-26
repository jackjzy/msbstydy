package com.ruoyi.web.controller.admin;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.admin.FfwyRole;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/back")
public class FfwyRoleController extends BaseController
{
    @Autowired
    private IFfwyRoleService ffwyRoleService;

    /**
     * 查询角色信息列表
     */
    @ApiOperation("查询角色信息列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_role:list')")
    @GetMapping("/roles")
    public TableDataInfo list(FfwyRole ffwyRole)
    {
        startPage();
        List<FfwyRole> list = ffwyRoleService.selectFfwyRoleList(ffwyRole);
        return getDataTable(list);
    }

    /**
     * 导出角色信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_role:export')")
    @Log(title = "角色信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyRole ffwyRole)
    {
        List<FfwyRole> list = ffwyRoleService.selectFfwyRoleList(ffwyRole);
        ExcelUtil<FfwyRole> util = new ExcelUtil<FfwyRole>(FfwyRole.class);
        return util.exportExcel(list, "ffwy_role");
    }

    /**
     * 获取角色信息详细信息
     */
    @ApiOperation("获取角色信息详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_role:query')")
    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId)
    {
        return AjaxResult.success(ffwyRoleService.selectFfwyRoleById(roleId));
    }

    /**
     * 新增角色信息
     */
    @ApiOperation("新增角色信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_role:add')")
    @Log(title = "角色信息", businessType = BusinessType.INSERT)
    @PostMapping("/roleAdd")
    public AjaxResult add(@RequestBody String roleName)
    {
        return toAjax(ffwyRoleService.insertFfwyRole(roleName));
    }

    /**
     * 修改角色信息
     */
    @ApiOperation("修改角色信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_role:edit')")
    @Log(title = "角色信息", businessType = BusinessType.UPDATE)
    @PutMapping("/roleupdate")
    public AjaxResult edit(@RequestBody Long roleId,@RequestBody String roleName)
    {
        return toAjax(ffwyRoleService.updateFfwyRole(new FfwyRole(roleId,roleName)));
    }

    /**
     * 删除角色信息
     */
    @ApiOperation("删除角色信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_role:remove')")
    @Log(title = "角色信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/roledelete/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds)
    {
        return toAjax(ffwyRoleService.deleteFfwyRoleByIds(roleIds));
    }
}
