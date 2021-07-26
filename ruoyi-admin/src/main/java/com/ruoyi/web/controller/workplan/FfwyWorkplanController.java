package com.ruoyi.web.controller.workplan;

import java.util.List;

import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomeal.FfwyCombomealPhoto;
import com.ruoyi.system.domain.myhoust.FfwyMyhousts;
import com.ruoyi.system.domain.order.FfwyOrderClient;
import com.ruoyi.system.domain.order.FfwyOrderPlane;
import com.ruoyi.system.domain.to.AllocationWokers;
import com.ruoyi.system.domain.vo.OrderClientVo;
import com.ruoyi.system.service.*;
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
import com.ruoyi.system.domain.FfwyWorkplan;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2021-04-25
 */
@Api(tags = "工长管理")
@RestController
@RequestMapping("/back/workplan")
public class FfwyWorkplanController extends BaseController {
    @Autowired
    private IFfwyWorkplanService ffwyWorkplanService;

    @Autowired
    private IFfwyMyhoustsService ffwyMyhoustsService;

    @Autowired
    private IFfwyOrderClientService ffwyOrderClientService;

    @Autowired
    private IFfwyUserService ffwyUserService;


    /**
     * 查询工长列表
     */
    @ApiOperation("查询工长")
    //@PreAuthorize("@ss.hasPermi('system:workplan:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyUser ffwyUser) {
        System.err.println(ffwyUser);
        startPage();
        List list = ffwyUserService.selectFfwyUserListByusertyep(ffwyUser);
        return getDataTable(list);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @ApiOperation("查看量房结果")
    //@PreAuthorize("@ss.hasPermi('system:myhousts:list')")
    @GetMapping("/findBymyhoust")
    public TableDataInfo list(FfwyOrderClient ffwyOrderClient) {
        startPage();
        List list = ffwyMyhoustsService.selectFfwyMyhoustsList(ffwyOrderClient);
        return getDataTable(list);
    }

    @ApiOperation("订单搜索")
    // @PreAuthorize("@ss.hasPermi('system:myhousts:list')")
    @GetMapping("/findBylimit")
    public TableDataInfo findBylimit(FfwyOrderClient ffwyOrderClient) {
        startPage();
        List<OrderClientVo> list = ffwyOrderClientService.selectFfwyOrderClientList(ffwyOrderClient);
        return getDataTable(list);
    }


    /**
     * 指派项目
     */
    @ApiOperation("指派项目")
    //@PreAuthorize("@ss.hasPermi('system:workplan:add')")
    @Log(title = "【指派项目】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyWorkplan ffwyWorkplan) {
        return toAjax(ffwyWorkplanService.insertFfwyWorkplan(ffwyWorkplan));
    }

    /**
     * 新增工长
     */
    @ApiOperation("新增工长")
    //@PreAuthorize("@ss.hasPermi('system:workplan:add')")
    @Log(title = "【新增工长】", businessType = BusinessType.INSERT)
    @PostMapping("/addsupervisor")
    public AjaxResult insrtsupervisor(@RequestBody FfwyUser ffwyUser) {

        return toAjax(ffwyUserService.insertFfwyUserBytype(ffwyUser));
    }

    @PutMapping("/allocationWokers")
    public AjaxResult addProects(@RequestBody AllocationWokers allocationWokers){

        return toAjax(ffwyWorkplanService.allocations(allocationWokers));
    }

    /**
     * 修改工长
     */
    @ApiOperation("修改工长")
    //@PreAuthorize("@ss.hasPermi('system:photo:edit')")
    //@Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/updausertype")
    public AjaxResult edit(@RequestBody FfwyUser ffwyUser)
    {
        return toAjax(ffwyUserService.updateFfwyUser(ffwyUser));
    }


    /**
     * 删除工长
     */
    @ApiOperation("删除工长")
    //@PreAuthorize("@ss.hasPermi('system:workplan:remove')")
    @Log(title = "【删除工长】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userId}")
    public AjaxResult remove(@PathVariable(value = "userId") Long userId) {
        return toAjax(ffwyUserService.deleteFfwyUserById(userId));
    }
}
