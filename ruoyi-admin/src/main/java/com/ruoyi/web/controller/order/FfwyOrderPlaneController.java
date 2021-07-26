package com.ruoyi.web.controller.order;

import java.util.List;

import com.ruoyi.system.domain.order.FfwyOrderPlane;
import com.ruoyi.system.domain.vo.FfwyOrderPlaneVo;
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
import com.ruoyi.system.service.IFfwyOrderPlaneService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@RestController
@RequestMapping("/system/plane")
public class FfwyOrderPlaneController extends BaseController
{
    @Autowired
    private IFfwyOrderPlaneService ffwyOrderPlaneService;

    /**
     * 查询【请填写功能名称】列表
     */
   /* @PreAuthorize("@ss.hasPermi('system:plane:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyOrderPlane ffwyOrderPlane)
    {
        startPage();
        List<FfwyOrderPlaneVo> list = ffwyOrderPlaneService.selectFfwyOrderPlaneList(ffwyOrderPlane);
        return getDataTable(list);
    }*/


    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:plane:query')")
    @GetMapping(value = "/{planeId}")
    public AjaxResult getInfo(@PathVariable("planeId") Long planeId)
    {
        return AjaxResult.success(ffwyOrderPlaneService.selectFfwyOrderPlaneById(planeId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:plane:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyOrderPlane ffwyOrderPlane)
    {
        return toAjax(ffwyOrderPlaneService.insertFfwyOrderPlane(ffwyOrderPlane));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:plane:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyOrderPlane ffwyOrderPlane)
    {
        return toAjax(ffwyOrderPlaneService.updateFfwyOrderPlane(ffwyOrderPlane));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:plane:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planeIds}")
    public AjaxResult remove(@PathVariable Long[] planeIds)
    {
        return toAjax(ffwyOrderPlaneService.deleteFfwyOrderPlaneByIds(planeIds));
    }
}
