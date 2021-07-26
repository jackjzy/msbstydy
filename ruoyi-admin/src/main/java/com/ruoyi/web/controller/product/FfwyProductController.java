package com.ruoyi.web.controller.product;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品信息Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "商品信息")
@RestController
@RequestMapping("/back/products")
public class FfwyProductController extends BaseController
{
    @Autowired
    private IFfwyProductService ffwyProductService;

    /**
     * 查询商品信息列表
     */
    @ApiOperation("查询商品信息列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:list')")
    @GetMapping
    public TableDataInfo list(ProductTo ffwyProduct)
    {
        startPage();
        List<FfwyProduct> list = ffwyProductService.selectFfwyProductList(ffwyProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ProductTo ffwyProduct)
    {
        List<FfwyProduct> list = ffwyProductService.selectFfwyProductList(ffwyProduct);
        ExcelUtil<FfwyProduct> util = new ExcelUtil<FfwyProduct>(FfwyProduct.class);
        return util.exportExcel(list, "ffwy_product");
    }

    /**
     * 获取商品信息详细信息
     */
    @ApiOperation("获取商品信息详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return AjaxResult.success(ffwyProductService.selectFfwyProductById(productId));
    }

    /**
     * 新增商品信息
     */
    @ApiOperation("新增商品信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductTo productTo)
    {
        if(null != productTo.getProductId()){
            return AjaxResult.success(ffwyProductService.updataProductSku(productTo));
        }
        return AjaxResult.success(ffwyProductService.insertFfwyProduct(productTo));
    }

    /**
     * 修改商品,上架下架商品
     */
    @ApiOperation("修改商品信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(Long productId,String productStatus)
    {
        return toAjax(ffwyProductService.updateFfwyProduct(productId,productStatus));
    }


    /**
     * 删除商品信息
     */
    @ApiOperation("删除商品信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(ffwyProductService.deleteFfwyProductByIds(productIds));
    }
}
