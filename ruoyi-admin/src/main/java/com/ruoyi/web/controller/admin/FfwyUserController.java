package com.ruoyi.web.controller.admin;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商城用户")
@RestController
@RequestMapping("/back")
public class FfwyUserController extends BaseController {

    @Autowired
    private IFfwyUserService ffwyUserService;


    /**
     * 查询商城用户列表
     */
    @ApiOperation("获取用户列表")
//    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/users")
    public TableDataInfo list(FfwyUser ffwyUser)
    {
        startPage();
        //跟前端商量好  1:18岁一下 2:  18-30岁  3: 30-40 岁 4:40-50岁 5: 50岁以上
        List<FfwyUser> list = ffwyUserService.selectFfwyUserList(ffwyUser);
        return getDataTable(list);
    }

    /**
     * 新增商城用户
     */
    @ApiOperation("新增商城用户列表")
//    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "商城用户", businessType = BusinessType.INSERT)
    @PostMapping("/adduser")
    public AjaxResult add(@Validated @RequestBody FfwyUser ffwyUser)
    {
        System.out.println(ffwyUser);
        return toAjax(ffwyUserService.insertFfwyUser(ffwyUser));
    }

    /**
     * 修改商城用户
     */
    @ApiOperation("修改商城用户")
//    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "商城用户", businessType = BusinessType.UPDATE)
    @PostMapping("/updateuser")
    public AjaxResult edit(@Validated @RequestBody FfwyUser ffwyUser)
    {
        return toAjax(ffwyUserService.updateFfwyUser(ffwyUser));
    }

    /**
     * 删除商城用户
     */
    @ApiOperation("删除商城用户")
//    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "商城用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteUsers/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(ffwyUserService.deleteFfwyUserByIds(userIds));
    }
}
