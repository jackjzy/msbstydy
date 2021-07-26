package com.ruoyi.web.controller.moneymanagement;

import java.util.List;

import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.vo.FfwyShopVo;
import com.ruoyi.system.domain.vo.ShopVo;
import com.ruoyi.system.service.IFfwyShopMoneyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店铺信息Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "商铺分成管理")
@RestController
@RequestMapping("/back/querydeals")
public class FfwyShopMoneyController extends BaseController
{
    @Autowired
    private IFfwyShopMoneyService ffwyShopMoneyService;

    /**
     * 查询店铺信息
     */
    @ApiOperation("查询店铺信息")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_shop:list')")
    @GetMapping("/findAll")
    public TableDataInfo findAll(FfwyShopVo ffwyShopVo)
    {
        startPage();
        List<ShopVo> list = ffwyShopMoneyService.selectFfwyShopListBydetail(ffwyShopVo);
        return getDataTable(list);
    }



    /**
     * 修改分成比例
     */
    @ApiOperation("修改分成比例")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_shop:edit')")
    @Log(title = "店铺信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updateratio")
    public AjaxResult edit(@RequestBody FfwyShop ffwyShop)
    {
        return toAjax(ffwyShopMoneyService.updateFfwyShop(ffwyShop));
    }

}
