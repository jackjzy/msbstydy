package com.ruoyi.web.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwyProductCategory;
import com.ruoyi.system.service.IFfwyProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类别Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "商品分类")
@RestController
@RequestMapping("/back/categorys")
public class FfwyProductCategoryController extends BaseController
{
    @Autowired
    private IFfwyProductCategoryService ffwyProductCategoryService;

    /**
     * 查询商品类别列表
     */
    @ApiOperation("查询商品类别列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_category:list')")
    @GetMapping
    public TableDataInfo list(FfwyProductCategory ffwyProductCategory)
    {
        startPage();
        List<FfwyProductCategory> list = ffwyProductCategoryService.selectFfwyProductCategoryList(ffwyProductCategory);
        return getDataTable(list);
    }

    /**
     * 导出商品类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_category:export')")
    @Log(title = "商品类别", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductCategory ffwyProductCategory)
    {
        List<FfwyProductCategory> list = ffwyProductCategoryService.selectFfwyProductCategoryList(ffwyProductCategory);
        ExcelUtil<FfwyProductCategory> util = new ExcelUtil<FfwyProductCategory>(FfwyProductCategory.class);
        return util.exportExcel(list, "ffwy_product_category");
    }

    /**
     * 获取商品类别详细信息
     */
    @ApiOperation("获取商品类别详细信息")
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(ffwyProductCategoryService.selectFfwyProductCategoryById(categoryId));
    }

    /**
     * 新增商品类别
     */
    @ApiOperation("新增商品类别")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_category:add')")
    @Log(title = "商品类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyProductCategory ffwyProductCategory)
    {
        int i = ffwyProductCategoryService.insertFfwyProductCategory(ffwyProductCategory);
        if (i == 3){
            return AjaxResult.error("一级分类条数不可多于10条");
        }
        return toAjax(i);
    }

    /**
     * 编辑商品类别
     */
    @ApiOperation("编辑商品类别")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_category:edit')")
    @Log(title = "商品类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProductCategory ffwyProductCategory)
    {
        return toAjax(ffwyProductCategoryService.updateFfwyProductCategory(ffwyProductCategory));
    }

    /**
     * 删除商品类别
     */
    @ApiOperation("删除商品类别")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_category:remove')")
    @Log(title = "商品类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long categoryIds)
    {

        System.err.println(categoryIds);
        int i = ffwyProductCategoryService.deleteFfwyProductCategoryById(categoryIds);
        System.err.println(i);
        if (i == -1){
            return AjaxResult.error("请先删除分类下子类及商品");
        }
        if (i == -2){
            return AjaxResult.error("请先移除该分类下的商品");
        }
        return toAjax(1);
    }
}
