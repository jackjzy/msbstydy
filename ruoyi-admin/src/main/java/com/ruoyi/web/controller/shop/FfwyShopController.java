package com.ruoyi.web.controller.shop;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import com.ruoyi.system.service.IFfwyShopPhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IFfwyShopService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 店铺Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "店铺")
@RestController
@RequestMapping("/back/shops")
public class FfwyShopController extends BaseController
{
    @Autowired
    private IFfwyShopService ffwyShopService;

    @Autowired
    private IFfwyShopPhotoService iFfwyShopPhotoService;

    /**
     * 查询店铺列表
     */
    @ApiOperation("查询店铺列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:list')")
    @GetMapping
    public TableDataInfo list(FfwyShop ffwyShop)
    {
        startPage();
        List<FfwyShop> list = ffwyShopService.selectFfwyShopList(ffwyShop);
        return getDataTable(list);
    }

    /**
     * 导出店铺列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:export')")
    @Log(title = "店铺", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyShop ffwyShop)
    {
        List<FfwyShop> list = ffwyShopService.selectFfwyShopList(ffwyShop);
        ExcelUtil<FfwyShop> util = new ExcelUtil<FfwyShop>(FfwyShop.class);
        return util.exportExcel(list, "ffwy_shop");
    }

    /**
     * 获取店铺详细信息
     */
    @ApiOperation("获取店铺详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        FfwyShop ffwyShop = ffwyShopService.selectFfwyByUserId(userId);
        FfwyShopPhoto ffwyShopPhoto = new FfwyShopPhoto();
        ffwyShopPhoto.setShopId(ffwyShop.getShopId());
        List<FfwyShopPhoto> list = iFfwyShopPhotoService.selectFfwyShopPhotoList(ffwyShopPhoto);
        Map<String,Object> map=new HashMap<>();
        map.put("shop",ffwyShop);
        map.put("shopPhoto",list);
        return AjaxResult.success(map);
    }

    /**
     * 新增店铺
     */
    @ApiOperation("新增店铺")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:add')")
    @Log(title = "店铺", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyShop ffwyShop)
    {

        return toAjax(ffwyShopService.insertFfwyShop(ffwyShop));
    }

    /**
     * 修改店铺
     */
    @ApiOperation("修改店铺")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:edit')")
    @Log(title = "店铺", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyShop ffwyShop)
    {

        return toAjax(ffwyShopService.updateFfwyShop(ffwyShop));
    }

    /**
     * 删除店铺
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:remove')")
//    @Log(title = "店铺", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shopIds}")
    public AjaxResult remove(@PathVariable Long[] shopIds)
    {
        return toAjax(ffwyShopService.deleteFfwyShopByIds(shopIds));
    }

    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file,Long shopId)
    {
        return AjaxResult.success(ffwyShopService.updateFfwyShopLogo(file,shopId));
    }

    @ApiOperation("平台分成展示")
    @GetMapping("/viewShare")
    public AjaxResult viewShare()
    {
        return AjaxResult.success(ffwyShopService.viewShare());
    }

    @ApiOperation("平台分成修改")
    @PostMapping("/modifyShare")
    public AjaxResult modifyShare(@RequestParam("terraceRatio") String terraceRatio,
                                @RequestParam("shopRetio") String shopRetio)
    {
        BigDecimal terrace = new BigDecimal(terraceRatio);
        BigDecimal shop = new BigDecimal(shopRetio);

        return AjaxResult.success(ffwyShopService.modifyShare(terrace,shop));
    }

    @ApiOperation("店铺禁用")
    @PostMapping("/modifyDisable")
    public AjaxResult modifyDisable(@RequestParam("shopId") Long shopId,
                                  @RequestParam("disableTime") Date disableTime)
    {
        return AjaxResult.success(ffwyShopService.disableShop(shopId,disableTime));
    }

}
