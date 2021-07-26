package com.ruoyi.app.controller.protocol;

import java.util.List;

import com.ruoyi.system.domain.protocol.FfwyProtocolUser;
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

import com.ruoyi.system.service.IFfwyProtocolUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 记录用户是否同意过用户协议Controller
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
@RestController
@RequestMapping("/app/protocolUser")
public class FfwyProtocolUserController extends BaseController
{
    @Autowired
    private IFfwyProtocolUserService ffwyProtocolUserService;

    /**
     * 查询记录用户是否同意过用户协议列表
     */

    @GetMapping("/list")
    public TableDataInfo list(FfwyProtocolUser ffwyProtocolUser)
    {
        startPage();
        List<FfwyProtocolUser> list = ffwyProtocolUserService.selectFfwyProtocolUserList(ffwyProtocolUser);
        return getDataTable(list);
    }

    /**
     * 导出记录用户是否同意过用户协议列表
     */

    @GetMapping("/export")
    public AjaxResult export(FfwyProtocolUser ffwyProtocolUser)
    {
        List<FfwyProtocolUser> list = ffwyProtocolUserService.selectFfwyProtocolUserList(ffwyProtocolUser);
        ExcelUtil<FfwyProtocolUser> util = new ExcelUtil<FfwyProtocolUser>(FfwyProtocolUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取记录用户是否同意过用户协议详细信息
     */

    @GetMapping(value = "/{protocolUserId}")
    public AjaxResult getInfo(@PathVariable("protocolUserId") Integer protocolUserId)
    {
        return AjaxResult.success(ffwyProtocolUserService.selectFfwyProtocolUserById(protocolUserId));
    }

    /**
     * 新增记录用户是否同意过用户协议
     */

    @PostMapping
    public AjaxResult add(@RequestBody FfwyProtocolUser ffwyProtocolUser)
    {
        return toAjax(ffwyProtocolUserService.insertFfwyProtocolUser(ffwyProtocolUser));
    }

    /**
     * 修改记录用户是否同意过用户协议
     */


    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProtocolUser ffwyProtocolUser)
    {
        return toAjax(ffwyProtocolUserService.updateFfwyProtocolUser(ffwyProtocolUser));
    }

    /**
     * 删除记录用户是否同意过用户协议
     */

	@DeleteMapping("/{protocolUserIds}")
    public AjaxResult remove(@PathVariable Integer[] protocolUserIds)
    {
        return toAjax(ffwyProtocolUserService.deleteFfwyProtocolUserByIds(protocolUserIds));
    }
}
