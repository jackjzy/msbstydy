package com.ruoyi.app.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwyProductDesc;
import com.ruoyi.system.service.IFfwyProductDescService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "商品详情")
@RestController
@RequestMapping("/app/desc")
public class FfwyProductDescController extends BaseController
{
    @Autowired
    private IFfwyProductDescService ffwyProductDescService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FfwyProductDesc ffwyProductDesc)
    {
        startPage();
        List<FfwyProductDesc> list = ffwyProductDescService.selectFfwyProductDescList(ffwyProductDesc);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:desc:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductDesc ffwyProductDesc)
    {
        List<FfwyProductDesc> list = ffwyProductDescService.selectFfwyProductDescList(ffwyProductDesc);
        ExcelUtil<FfwyProductDesc> util = new ExcelUtil<FfwyProductDesc>(FfwyProductDesc.class);
        return util.exportExcel(list, "desc");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:desc:query')")
    @GetMapping(value = "/{descId}")
    public AjaxResult getInfo(@PathVariable("descId") Long descId)
    {
        return AjaxResult.success(ffwyProductDescService.selectFfwyProductDescById(descId));
    }



    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:desc:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{descIds}")
    public AjaxResult remove(@PathVariable Long[] descIds)
    {
        return toAjax(ffwyProductDescService.deleteFfwyProductDescByIds(descIds));
    }
}
