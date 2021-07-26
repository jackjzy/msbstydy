package com.ruoyi.web.controller.product;

import java.util.List;

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
import com.ruoyi.system.domain.product.FfwyProductCommentPhoto;
import com.ruoyi.system.service.IFfwyProductCommentPhotoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品评论图片Controller
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Api(tags = "商品评论图片")
@RestController
@RequestMapping("/back/ffwy_product_comment_photo")
public class FfwyProductCommentPhotoController extends BaseController
{
    @Autowired
    private IFfwyProductCommentPhotoService ffwyProductCommentPhotoService;

    /**
     * 查询商品评论图片列表
     */
    @ApiOperation("查询商品评论图片列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment_photo:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyProductCommentPhoto ffwyProductCommentPhoto)
    {
        startPage();
        List<FfwyProductCommentPhoto> list = ffwyProductCommentPhotoService.selectFfwyProductCommentPhotoList(ffwyProductCommentPhoto);
        return getDataTable(list);
    }

    /**
     * 导出商品评论图片列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment_photo:export')")
    @Log(title = "商品评论图片", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductCommentPhoto ffwyProductCommentPhoto)
    {
        List<FfwyProductCommentPhoto> list = ffwyProductCommentPhotoService.selectFfwyProductCommentPhotoList(ffwyProductCommentPhoto);
        ExcelUtil<FfwyProductCommentPhoto> util = new ExcelUtil<FfwyProductCommentPhoto>(FfwyProductCommentPhoto.class);
        return util.exportExcel(list, "ffwy_product_comment_photo");
    }

    /**
     * 获取商品评论图片详细信息
     */
    @ApiOperation("获取商品评论图片详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment_photo:query')")
    @GetMapping(value = "/{productCommentPhotoId}")
    public AjaxResult getInfo(@PathVariable("productCommentPhotoId") Long productCommentPhotoId)
    {
        System.err.println(productCommentPhotoId);
        return AjaxResult.success(ffwyProductCommentPhotoService.selectFfwyProductCommentPhotoById(productCommentPhotoId));
    }

    /**
     * 新增商品评论图片
     */
    @ApiOperation("新增商品评论图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment_photo:add')")
    @Log(title = "商品评论图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyProductCommentPhoto ffwyProductCommentPhoto)
    {
        return toAjax(ffwyProductCommentPhotoService.insertFfwyProductCommentPhoto(ffwyProductCommentPhoto));
    }

    /**
     * 修改商品评论图片
     */
    @ApiOperation("修改商品评论图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment_photo:edit')")
    @Log(title = "商品评论图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProductCommentPhoto ffwyProductCommentPhoto)
    {
        return toAjax(ffwyProductCommentPhotoService.updateFfwyProductCommentPhoto(ffwyProductCommentPhoto));
    }

    /**
     * 删除商品评论图片
     */
    @ApiOperation("删除商品评论图片")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment_photo:remove')")
    @Log(title = "商品评论图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productCommentPhotoIds}")
    public AjaxResult remove(@PathVariable Long[] productCommentPhotoIds)
    {
        return toAjax(ffwyProductCommentPhotoService.deleteFfwyProductCommentPhotoByIds(productCommentPhotoIds));
    }
}
