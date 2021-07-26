package com.ruoyi.web.controller.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.to.ProductSkuTo;
import com.ruoyi.system.domain.vo.ProductVo;
import com.ruoyi.system.domain.vo.SpeVO;
import com.ruoyi.system.domain.vo.SpecificationSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.expression.spel.ast.NullLiteral;
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
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.service.IFfwySpecificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品规格Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "商品规格")
@RestController
@RequestMapping("/back/ffwySpecification")
public class FfwySpecificationController extends BaseController
{
    @Autowired
    private IFfwySpecificationService ffwySpecificationService;


    /**
     * 查询商品规格以及商品sku列表
     */
    @ApiOperation("查询商品规格列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_specification:list')")
//    @GetMapping(value = "/{speId}")
//    public AjaxResult lists(@PathVariable("speId") Integer speId)
    @GetMapping(value = "/{productId}")
    public AjaxResult lists(@PathVariable("productId") Long productId)
    {
        return AjaxResult.success(ffwySpecificationService.selectSpeVoList(productId));
    }

    /**
     * 新增商品sku或者更改
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_sku:add')")
    @Log(title = "商品sku", businessType = BusinessType.INSERT)
    @PostMapping("/productSku")
    @ApiOperation("新增商品sku或者更改")
    public AjaxResult add(@RequestBody ProductSkuTo productSku)
    {
        return ffwySpecificationService.insertOrpdataProductSku(productSku);
    }

    /**
     * 新增商品规格
     */
    @ApiOperation("新增商品规格")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_specification:add')")
    @Log(title = "商品规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpeVO speVO)
    {
        return toAjax(ffwySpecificationService.insertOrUpdataSpecification(speVO));
    }

    /**
     * 修改商品规格
     */
    @ApiOperation("修改商品规格")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_specification:edit')")
    @Log(title = "商品规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwySpecification ffwySpecification)
    {
        return toAjax(ffwySpecificationService.updateFfwySpecification(ffwySpecification));
    }


    /**
     * 后台修改时专用接口  查询商品规格以及商品sku列表
     */
    @ApiOperation("后台修改时专用接口-查询商品规格列表")
    @GetMapping(value = "admin/{productId}")
    public AjaxResult adminLists(@PathVariable("productId") Long productId)
    {
        if(0 == productId || null == productId) return AjaxResult.error(201,"PRODUCTID IS NULL");
        return AjaxResult.success(ffwySpecificationService.selectAdminSpeVoList(productId));
    }

    /**
     * 删除商品规格
     */
    @ApiOperation("删除商品规格")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_specification:remove')")
    @Log(title = "商品规格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{specificationIds}")
    public AjaxResult remove(@PathVariable Integer[] specificationIds)
    {
        return toAjax(ffwySpecificationService.deleteFfwySpecificationByIds(specificationIds));
    }
}
