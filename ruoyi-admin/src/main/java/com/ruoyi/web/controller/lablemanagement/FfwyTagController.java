package com.ruoyi.web.controller.lablemanagement;

import java.util.List;

import com.ruoyi.system.domain.FfwyTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
import com.ruoyi.system.service.IFfwyTagService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "标签管理")
@RestController
@RequestMapping("/back/tags")
public class FfwyTagController extends BaseController
{
    @Autowired
    private IFfwyTagService ffwyTagService;

    /**
     * 查询所有标签
     */
    @ApiOperation("查看所有标签")
    //@PreAuthorize("@ss.hasPermi('system:tag:list')")
    @GetMapping("/all")
    public TableDataInfo list(FfwyTag ffwyTag)
    {
        startPage();
        List<FfwyTag> list = ffwyTagService.selectFfwyTagListSum(ffwyTag);
        return getDataTable(list);
    }

    /**
     * 新增标签
     */
    @ApiOperation("新增标签")
    //@ApiImplicitParam(name = "ffwyTag", value = "新增标签", dataType = "FfwyTag")
    @PostMapping("/inserttag")
    public AjaxResult addlable(@RequestBody FfwyTag ffwyTag)
    {
        return AjaxResult.success(ffwyTagService.insertFfwyTag(ffwyTag));
    }

    /**
     * 修改标签
     */
    @ApiOperation("修改标签")
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyTag ffwyTag)
    {
        return AjaxResult.success(ffwyTagService.updateFfwyTag(ffwyTag));
    }

    /**
     * 删除标签
     */
    @ApiOperation("删除标签")
    //@ApiImplicitParam(name = "tagId", value = "标签ID", required = true, dataType = "int", paramType = "path")
    @Log(title = "删除标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagId}")
    public AjaxResult remove(@PathVariable Long tagId)
    {
        int i = ffwyTagService.deleteFfwyTagById(tagId);
        if (i == 1){
            return AjaxResult.success();
        } else {
            return AjaxResult.error("标签正在使用");
        }

    }
}
