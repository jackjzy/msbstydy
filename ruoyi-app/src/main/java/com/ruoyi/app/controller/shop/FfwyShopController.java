package com.ruoyi.app.controller.shop;

import java.util.List;

import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.shop.FfwyShop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IFfwyShopService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店铺Controller
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "店铺")
@RestController
@RequestMapping("/app/shops")
public class FfwyShopController extends BaseController {
    @Autowired
    private IFfwyShopService ffwyShopService;

    /**
     * 查询店铺列表
     * 名称,评分，收藏数
     */
    @ApiOperation("查询店铺列表")
    @GetMapping("/list")
    public TableDataInfo appList(FfwyShop ffwyShop) {
        startPage();
        List<FfwyShop> list = ffwyShopService.selectFfwyShopAppList(ffwyShop);
        return getDataTable(list);
    }

    /**
     * 查询店铺商品列表
     */
    @ApiOperation("查询店铺商品列表")
    @GetMapping("/productList")
    public TableDataInfo productList(FfwyProduct ffwyProduct) {
        startPage();
        List<FfwyProduct> list = ffwyShopService.selectFfwyproductList(ffwyProduct);
        return getDataTable(list);
    }


    /**
     * 获取店铺详细信息
     */
    @ApiOperation("获取店铺详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:query')")
    @GetMapping("listId")
    public AjaxResult getInfo(FfwyShop ffwyShop) {
        List<FfwyShop> list = ffwyShopService.selectFfwyShopIdList(ffwyShop);
        return AjaxResult.success(list);
    }

    //新增店铺收藏
    @ApiOperation("新增店铺收藏")
    @PostMapping("/collect")
    public AjaxResult collect(@RequestBody FfwyShop ffwyShop) {
        return ffwyShopService.insertStroeShop(ffwyShop);
    }

    //删除店铺收藏
    @ApiOperation("删除店铺收藏")
    @PostMapping("/removeRollect")
    public AjaxResult removeRollect(@RequestBody FfwyShop ffwyShop) {
        return ffwyShopService.deleteStroeShop(ffwyShop);
    }


}
