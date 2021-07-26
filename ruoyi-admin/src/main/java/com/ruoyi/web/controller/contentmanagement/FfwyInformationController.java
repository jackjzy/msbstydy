package com.ruoyi.web.controller.contentmanagement;

import java.util.List;

import com.ruoyi.system.domain.FfwyInformation;
import com.ruoyi.system.domain.vo.FfwyTagVo;
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
import com.ruoyi.system.service.IFfwyInformationService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api("内容管理")
@RestController
@RequestMapping("/back/informations")
public class FfwyInformationController extends BaseController
{
    @Autowired
    private IFfwyInformationService ffwyInformationService;

    /**
     * 查询所有
     */
    @ApiOperation("查询所有内容")
    //@PreAuthorize("@ss.hasPermi('system:information:findAll')")
    @GetMapping("/findAll")
    public TableDataInfo findAll(FfwyInformation ffwyInformation)
    {

        startPage();
        List list = ffwyInformationService.selectFfwyInformationList(ffwyInformation);
        return getDataTable(list);
    }

   /* *//**
     * 导出【请填写功能名称】列表
     *//*
    @PreAuthorize("@ss.hasPermi('system:information:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyInformation ffwyInformation)
    {
        List<FfwyInformation> list = ffwyInformationService.selectFfwyInformationList(ffwyInformation);
        ExcelUtil<FfwyInformation> util = new ExcelUtil<FfwyInformation>(FfwyInformation.class);
        return util.exportExcel(list, "information");
    }
*/

    /**
     * 新增咨询
     */
    @ApiOperation("新增咨询")
    //@PreAuthorize("@ss.hasPermi('system:information:add')")
    @Log(title = "新增咨询", businessType = BusinessType.INSERT)
    @PostMapping("/addinfomation")
    public AjaxResult addinfomation(@RequestBody FfwyInformation ffwyInformation)
    {
        return toAjax(ffwyInformationService.addFfwyInformation(ffwyInformation));
    }

    /**
     * 新增【请填写功能名称】
     */
    @ApiOperation("为咨询添加标签")
    //@PreAuthorize("@ss.hasPermi('system:information:add')")
    @Log(title = "为咨询添加标签", businessType = BusinessType.INSERT)
    @PostMapping("/addtag")
    public AjaxResult addtag(@RequestBody FfwyTagVo ffwyTagVo)
    {
        return toAjax(ffwyInformationService.insertFfwyInformation(ffwyTagVo));
    }

    /**
     * 修改内容
     */
    @ApiOperation("修改咨询")
   // @PreAuthorize("@ss.hasPermi('system:information:edit')")
    @Log(title = "修改咨询", businessType = BusinessType.UPDATE)
    @PutMapping("/updateinformation")
    public AjaxResult edit(@RequestBody FfwyInformation ffwyInformation)
    {
        return toAjax(ffwyInformationService.updateFfwyInformation(ffwyInformation));
    }

    /**
     * 根据id删除内容
     */
    @ApiOperation("删除咨询")
    //@PreAuthorize("@ss.hasPermi('system:information:remove')")
    @Log(title = "删除咨询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{informationId}")
    public AjaxResult remove(@PathVariable Long informationId)
    {
        return toAjax(ffwyInformationService.deleteFfwyInformationById(informationId));
    }
}
