package com.ruoyi.app.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.service.IFfwyProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品skuController
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@RestController
@RequestMapping("/back/ffwy_product_sku")
public class FfwyProductSkuController extends BaseController
{
    @Autowired
    private IFfwyProductSkuService ffwyProductSkuService;

    /**
     * 查询商品sku列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyProductSku ffwyProductSku)
    {
        startPage();
        List<FfwyProductSku> list = ffwyProductSkuService.selectFfwyProductSkuList(ffwyProductSku);
        return getDataTable(list);
    }

    /**
     * 导出商品sku列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_sku:export')")
    @Log(title = "商品sku", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductSku ffwyProductSku)
    {
        List<FfwyProductSku> list = ffwyProductSkuService.selectFfwyProductSkuList(ffwyProductSku);
        ExcelUtil<FfwyProductSku> util = new ExcelUtil<FfwyProductSku>(FfwyProductSku.class);
        return util.exportExcel(list, "ffwy_product_sku");
    }

    /**
     * 获取商品sku详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_sku:query')")
    @GetMapping(value = "/{skuId}")
    public AjaxResult getInfo(@PathVariable("skuId") Long skuId)
    {
        return AjaxResult.success(ffwyProductSkuService.selectFfwyProductSkuById(skuId));
    }

    /**
     * 新增商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_sku:add')")
    @Log(title = "商品sku", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyProductSku ffwyProductSku)
    {
        return toAjax(ffwyProductSkuService.insertFfwyProductSku(ffwyProductSku));
    }

    /**
     * 修改商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_sku:edit')")
    @Log(title = "商品sku", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProductSku ffwyProductSku)
    {
        return toAjax(ffwyProductSkuService.updateFfwyProductSku(ffwyProductSku));
    }

    /**
     * 删除商品sku
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_sku:remove')")
    @Log(title = "商品sku", businessType = BusinessType.DELETE)
	@DeleteMapping("/{skuIds}")
    public AjaxResult remove(@PathVariable Long[] skuIds)
    {
        return toAjax(ffwyProductSkuService.deleteFfwyProductSkuByIds(skuIds));
    }
}
