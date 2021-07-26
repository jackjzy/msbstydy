package com.ruoyi.app.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.to.ProductTo;
import com.ruoyi.system.service.IFfwyProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息Controller
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "商品信息")
@RestController
@RequestMapping("/app/product")
public class FfwyProductController extends BaseController {
    @Autowired
    private IFfwyProductService ffwyProductService;

    /**
     * 查询商品信息列表
     */
    @ApiOperation("根据分类查询商品列表")
    @GetMapping("/queryall")
    public TableDataInfo selectClassifyList(FfwyProduct ffwyProduct) {
        startPage();
        List<FfwyProduct> list = ffwyProductService.selectClassifyList(ffwyProduct);
        return getDataTable(list);
    }

    /**
     * 查询商品信息列表
     */
    @ApiOperation("查询单个商品信息列表")
    @GetMapping("/queryone")
    public TableDataInfo listId(FfwyProduct ffwyProduct) {
        startPage();
        List<FfwyProduct> list = ffwyProductService.selectFfwyProductListId(ffwyProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductTo ffwyProduct) {
        List<FfwyProduct> list = ffwyProductService.selectFfwyProductList(ffwyProduct);
        ExcelUtil<FfwyProduct> util = new ExcelUtil<FfwyProduct>(FfwyProduct.class);
        return util.exportExcel(list, "ffwy_product");
    }

    /**
     * 获取商品信息详细信息
     */
    @ApiOperation("获取商品信息详细信息")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId) {
        return AjaxResult.success(ffwyProductService.selectFfwyProductById(productId));
    }

    /**
     * 获取商品信息详细信息
     */
    @ApiOperation("商品收藏添加")
    @PostMapping(value = "/productCollect",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult productCollect(@RequestBody FfwyProduct ffwyProduct) {
        return ffwyProductService.insertStroeProduct(ffwyProduct);
    }

    /**
     * 获取商品信息详细信息
     */
    @ApiOperation("商品收藏删除")
    @PostMapping(value = "/productRemoveRollect",produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult productRemoveRollect(@RequestBody FfwyProduct ffwyProduct) {
        return ffwyProductService.deleteStroeProduct(ffwyProduct);
    }
}
