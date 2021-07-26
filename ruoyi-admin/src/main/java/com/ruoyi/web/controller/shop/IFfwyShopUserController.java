package com.ruoyi.web.controller.shop;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.order.FfwyIncome;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.vo.IncomeVo;
import com.ruoyi.system.domain.vo.ProductByUserVo;
import com.ruoyi.system.service.IFfwyShopService;
import com.ruoyi.system.service.IFfwyShopUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 赵字豪
 * @Title: 店铺管理（商户权限）
 * @date 2021/4/20
 */
@Api(tags = "店铺管理（商户权限）")
@RestController
@RequestMapping("/back/shopUser")
public class IFfwyShopUserController extends BaseController {

    @Autowired
    private IFfwyShopUserService iFfwyShopUserService;
    @Autowired
    private IFfwyShopService iFfwyShopService;



    /**
     * 统计用户订单量
     */
    @ApiOperation("统计用户订单量")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:list')")
    @GetMapping("/statistics/{shopId}")
    public AjaxResult list(@PathVariable Long shopId)
    { Integer userId=null;
        FfwyShop ffwyShop = iFfwyShopService.selectFfwyShopById(shopId);
        if (null!=ffwyShop){
            Long id = ffwyShop.getShopId();
            if (null!=id)
             userId = Integer.valueOf(id.toString());
        }


        return AjaxResult.success(iFfwyShopUserService.statisticsOrder(userId));
    }


    /**
     * 七天内营收
     */
    @ApiOperation("七天内营收")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:list')")
    @GetMapping("/flowingWater")
    public AjaxResult flowingWater(ProductByUserVo productByUserVo)
    {
        return AjaxResult.success(iFfwyShopUserService.flowingWater(productByUserVo));
    }

    /**
     * 收支明细
     */
    @ApiOperation("收入流水")
    @PostMapping("/detailed")
    public TableDataInfo detailed(@RequestBody FfwyIncome ffwyIncome)
    {

        startPage();
        List<FfwyIncome> ffwyIncomes = iFfwyShopUserService.detailedByshop(ffwyIncome);
        return getDataTable(ffwyIncomes);

    }

    /**
     * 流水详情
     */
    @ApiOperation("流水详情")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product:list')")
    @GetMapping("/flowingWaterDetails")
    public AjaxResult flowingWaterDetails(ProductByUserVo productByUserVo)
    {
        return AjaxResult.success(iFfwyShopUserService.flowingWaterDetails(productByUserVo));
    }
}
