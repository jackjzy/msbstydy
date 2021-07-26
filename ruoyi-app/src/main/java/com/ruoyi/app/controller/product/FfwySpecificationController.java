package com.ruoyi.app.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.service.IFfwySpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品规格Controller
 *
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "商品规格Controller")
@RestController
@RequestMapping("/app/ffwySpecification")
public class FfwySpecificationController extends BaseController {
    @Autowired
    private IFfwySpecificationService ffwySpecificationService;

    /**
     * 查询商品规格列表
     */
    @ApiOperation("查询商品规格列表")
    @GetMapping("/list")
    public AjaxResult list(FfwySpecification ffwySpecification) {
       return ffwySpecificationService.selectFfwySpecificationList(ffwySpecification);
    }



    /**
     * 获取商品规格详细信息
     */
    @ApiOperation("获取商品规格详细信息")
    @GetMapping(value = "/{specificationId}")
    public AjaxResult getInfo(@PathVariable("specificationId") Integer specificationId) {
        return AjaxResult.success(ffwySpecificationService.selectFfwySpecificationById(specificationId));
    }

    /**
     * 获取单个商品规格详细信息
     */
    @ApiOperation("获取单个商品规格详细信息")
    @GetMapping(value = "/listId")
    public AjaxResult listId(Long productId,  String value) {
        return ffwySpecificationService.selectFfwySpecificationListById(productId, value);
    }


}
