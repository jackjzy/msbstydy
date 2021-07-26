package com.ruoyi.web.controller.shop;

import java.util.*;

import com.ruoyi.common.utils.cos.CosUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import com.ruoyi.system.service.IFfwyShopPhotoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 店铺图片Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "自营店铺图片")
@RestController
@RequestMapping("/back/ffwyShopPhoto")
public class FfwyShopPhotoController extends BaseController
{
    @Autowired
    private IFfwyShopPhotoService ffwyShopPhotoService;

    /**
     * 查询店铺图片列表
     */
    @ApiOperation("查询店铺图片列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyShopPhoto ffwyShopPhoto)
    {
        startPage();
        List<FfwyShopPhoto> list = ffwyShopPhotoService.selectFfwyShopPhotoList(ffwyShopPhoto);
        return getDataTable(list);
    }


    /**
     * 导出店铺图片列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:export')")
    @Log(title = "店铺图片", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyShopPhoto ffwyShopPhoto)
    {
        List<FfwyShopPhoto> list = ffwyShopPhotoService.selectFfwyShopPhotoList(ffwyShopPhoto);
        ExcelUtil<FfwyShopPhoto> util = new ExcelUtil<FfwyShopPhoto>(FfwyShopPhoto.class);
        return util.exportExcel(list, "ffwy_shop_photo");
    }

    /**
     * 获取店铺图片详细信息
     */
    @ApiOperation("获取店铺图片详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:query')")
    @GetMapping(value = "/{photoId}")
    public AjaxResult getInfo(@PathVariable("photoId") Long photoId)
    {
        return AjaxResult.success(ffwyShopPhotoService.selectFfwyShopPhotoById(photoId));
    }

    /**
     * 新增店铺图片
     */
    @ApiOperation("新增店铺图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:add')")
    @Log(title = "店铺图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestParam Long shopId,MultipartFile file)
    {


        Map<String, Object> map = ffwyShopPhotoService.insertFfwyShopPhoto(shopId, file);
        return AjaxResult.success(map);

    }

    @ApiOperation("批量上传图片")
    @PostMapping(value = "/addPhotos",headers = "content-type=multipart/form-data")
    public AjaxResult addPhotos(@RequestParam(name = "files") MultipartFile[] files,String title)
    {   List<FfwyShopPhoto> list =new ArrayList<>();



        if (files.length>0){

            for (int i=0;i<files.length;i++){
                /*
                * 上传腾讯云返回路径
                *
                * */
                String path=null;
                try {
                    path = CosUtil.CosUpload(files[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //创建新图片对象
                list.add(new FfwyShopPhoto(
                        (long) 5,
                        title,
                        path,
                        new Date().getTime(),
                        new Date(),
                        "1"));
            }
        }
        return toAjax(ffwyShopPhotoService.insertFfwyShopPhotos(list));
    }

    /**
     * 修改店铺图片
     */
    @ApiOperation("修改店铺图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:edit')")
    @Log(title = "店铺图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyShopPhoto ffwyShopPhoto)
    {
        return toAjax(ffwyShopPhotoService.updateFfwyShopPhoto(ffwyShopPhoto));
    }

    /**
     * 删除店铺图片
     */
    @ApiOperation("删除店铺图片")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:remove')")
    @Log(title = "店铺图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{photoId}")
    public AjaxResult remove(@PathVariable Long photoId)
    {

        return toAjax(ffwyShopPhotoService.deleteFfwyShopPhotoById(photoId));
    }
}
