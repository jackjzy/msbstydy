package com.ruoyi.web.controller.shop;

import java.util.List;

import io.swagger.annotations.Api;
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
import com.ruoyi.system.domain.shop.FfwyShopType;
import com.ruoyi.system.service.IFfwyShopTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店铺类型Controller
 * 
 * @author ruoyi
 * @date 2021-04-20
 */
@Api(tags = "店铺类型")
@RestController
@RequestMapping("/back/ffwy_shop_type")
public class FfwyShopTypeController extends BaseController
{
    @Autowired
    private IFfwyShopTypeService ffwyShopTypeService;

    /**
     * 查询店铺类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_type:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyShopType ffwyShopType)
    {
        startPage();
        List<FfwyShopType> list = ffwyShopTypeService.selectFfwyShopTypeList(ffwyShopType);
        return getDataTable(list);
    }

    /**
     * 导出店铺类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_type:export')")
    @Log(title = "店铺类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyShopType ffwyShopType)
    {
        List<FfwyShopType> list = ffwyShopTypeService.selectFfwyShopTypeList(ffwyShopType);
        ExcelUtil<FfwyShopType> util = new ExcelUtil<FfwyShopType>(FfwyShopType.class);
        return util.exportExcel(list, "ffwy_shop_type");
    }

    /**
     * 获取店铺类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_type:query')")
    @GetMapping(value = "/{shopType}")
    public AjaxResult getInfo(@PathVariable("shopType") Integer shopType)
    {
        return AjaxResult.success(ffwyShopTypeService.selectFfwyShopTypeById(shopType));
    }

    /**
     * 新增店铺类型
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_type:add')")
    @Log(title = "店铺类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyShopType ffwyShopType)
    {
        return toAjax(ffwyShopTypeService.insertFfwyShopType(ffwyShopType));
    }

    /**
     * 修改店铺类型
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_type:edit')")
    @Log(title = "店铺类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyShopType ffwyShopType)
    {
        return toAjax(ffwyShopTypeService.updateFfwyShopType(ffwyShopType));
    }

    /**
     * 删除店铺类型
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_type:remove')")
    @Log(title = "店铺类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shopTypes}")
    public AjaxResult remove(@PathVariable Integer[] shopTypes)
    {
        return toAjax(ffwyShopTypeService.deleteFfwyShopTypeByIds(shopTypes));
    }
}
