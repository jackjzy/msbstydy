package com.ruoyi.web.controller.product;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.product.FfwyProductDesc;
import com.ruoyi.system.service.IFfwyProductDescService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "商品详情")
@RestController
@RequestMapping("/back/desc")
public class FfwyProductDescController extends BaseController
{
    @Autowired
    private IFfwyProductDescService ffwyProductDescService;

    /**
     * 查询商品详情列表
     */
    @ApiOperation("查询商品详情列表")
//    @PreAuthorize("@ss.hasPermi('system:desc:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyProductDesc ffwyProductDesc)
    {
        startPage();
        List<FfwyProductDesc> list = ffwyProductDescService.selectFfwyProductDescList(ffwyProductDesc);
        return getDataTable(list);
    }

    /**
     * 导出商品详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:desc:export')")
    @Log(title = "商品详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductDesc ffwyProductDesc)
    {
        List<FfwyProductDesc> list = ffwyProductDescService.selectFfwyProductDescList(ffwyProductDesc);
        ExcelUtil<FfwyProductDesc> util = new ExcelUtil<FfwyProductDesc>(FfwyProductDesc.class);
        return util.exportExcel(list, "desc");
    }

    /**
     * 获取商品详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:desc:query')")
    @GetMapping(value = "/{descId}")
    public AjaxResult getInfo(@PathVariable("descId") Long descId)
    {
        return AjaxResult.success(ffwyProductDescService.selectFfwyProductDescById(descId));
    }

    /**
     * 新增商品详情
     */
    @ApiOperation("新增商品详情")
//    @PreAuthorize("@ss.hasPermi('system:desc:add')")
    @Log(title = "商品详情", businessType = BusinessType.INSERT)
//    @PostMapping(consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @PostMapping(value = "/upload",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public AjaxResult add(@RequestParam MultipartFile[] files, @RequestParam(required = false)String desc, @RequestParam @NotNull Long productId)
    {
//        return toAjax("ffwyProductDescService.insertFfwyProductDescList(files,desc,productId)");
        return null;
    }

    /**
     * 上架/下架
     */
    @ApiOperation("上架/下架")
//    @PreAuthorize("@ss.hasPermi('system:desc:edit')")
    @Log(title = "商品详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestParam Long descId, @RequestParam Boolean descStatus)
    {
        return toAjax(ffwyProductDescService.updateFfwyProductDesc(descId,descStatus));
    }

    /**
     * 删除商品详情
     */
    @ApiOperation("删除商品详情")
//    @PreAuthorize("@ss.hasPermi('system:desc:remove')")
    @Log(title = "商品详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{descIds}")
    public AjaxResult remove(@PathVariable Long[] descIds)
    {
        return toAjax(ffwyProductDescService.deleteFfwyProductDescByIds(descIds));
    }
}
