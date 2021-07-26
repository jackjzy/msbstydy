package com.ruoyi.web.controller.product;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwyProductPhoto;
import com.ruoyi.system.service.IFfwyProductPhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品图片Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "商品图片")
@RestController
@RequestMapping("/back/product_photo")
public class FfwyProductPhotoController extends BaseController
{
    @Autowired
    private IFfwyProductPhotoService ffwyProductPhotoService;

    /**
     * 查询商品图片列表
     */
    @ApiOperation("查询商品图片列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_photo:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyProductPhoto ffwyProductPhoto)
    {
        startPage();
        List<FfwyProductPhoto> list = ffwyProductPhotoService.selectFfwyProductPhotoList(ffwyProductPhoto);
        return getDataTable(list);
    }

    /**
     * 导出商品图片列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_photo:export')")
    @Log(title = "商品图片", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductPhoto ffwyProductPhoto)
    {
        List<FfwyProductPhoto> list = ffwyProductPhotoService.selectFfwyProductPhotoList(ffwyProductPhoto);
        ExcelUtil<FfwyProductPhoto> util = new ExcelUtil<FfwyProductPhoto>(FfwyProductPhoto.class);
        return util.exportExcel(list, "ffwy_product_photo");
    }

    /**
     * 获取商品图片详细信息
     */
    @ApiOperation("获取商品图片详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_photo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ffwyProductPhotoService.selectFfwyProductPhotoById(id));
    }

    /**
     * 新增商品图片
     */
    @ApiOperation("新增商品图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_photo:add')")
    @Log(title = "商品图片", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addPhotos")
    public AjaxResult add(@RequestBody @NotNull FfwyProductPhoto ffwyProductPhoto)
    {
        int i = ffwyProductPhotoService.insertFfwyProductPhoto(ffwyProductPhoto,ffwyProductPhoto.getProductId());
        if (i == 3){
            AjaxResult.error("每件商品所展示图片最多不超过4张，请先下架之前上传的图片");
        }
        return toAjax(i);
    }
    /**
     * 上传图片
     */
    @ApiOperation("上传图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_photo:add')")
    @Log(title = "商品图片", businessType = BusinessType.INSERT)
//    @PostMapping(value = "/uploadPhoto",headers = "content-type=multipart/form-data")
    @PostMapping(value = "/uploadPhoto")
    public AjaxResult uploadPhoto(@RequestParam(required = false) MultipartFile file)
    {
        System.err.println(file);

        String s = ffwyProductPhotoService.uploadPhotos(file);
        if (StringUtils.isEmpty(s)){
            return AjaxResult.error("上传失败");
        }
        return AjaxResult.success(s);
    }

    /**
     * 上架、下架
     */
    @ApiOperation("修改商品图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_photo:edit')")
    @Log(title = "商品图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestParam Long id, @RequestParam String photoStatus)
    {
        return toAjax(ffwyProductPhotoService.updateFfwyProductPhoto(id,photoStatus));
    }

    /**
     * 删除商品图片
     */
    @ApiOperation("删除商品图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_photo:remove')")
    @Log(title = "商品图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ffwyProductPhotoService.deleteFfwyProductPhotoByIds(ids));
    }
}
