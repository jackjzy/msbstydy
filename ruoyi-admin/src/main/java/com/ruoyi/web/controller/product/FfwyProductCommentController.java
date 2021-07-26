package com.ruoyi.web.controller.product;

import java.util.List;

import com.ruoyi.system.domain.vo.ProductByUserVo;
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
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.service.IFfwyProductCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品评论Controller
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Api(tags = "商品评论")
@RestController
@RequestMapping("/back/productComment")
public class FfwyProductCommentController extends BaseController
{
    @Autowired
    private IFfwyProductCommentService ffwyProductCommentService;

    /**
     * 查询商品评论列表
     */
    @ApiOperation("查询商品评论列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody FfwyProductComment ffwyProductComment)
    {
        startPage();
        List<FfwyProductComment> list = ffwyProductCommentService.selectShopComment(ffwyProductComment);
        return getDataTable(list);
    }

    /**
     * 获取商品评论详
     */
    @ApiOperation("获取商品")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:query')")
    @GetMapping(value = "/products")
    public TableDataInfo getInfo(ProductByUserVo productByUserVo)
    {
        startPage();
        List<FfwyProductComment> list = ffwyProductCommentService.selectUserId(productByUserVo.getUserId(), productByUserVo.getShopId(), productByUserVo.getProductId(),
                productByUserVo.getCombomealId());
        return getDataTable(list);
    }

    /**
     * 导出商品评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:export')")
    @Log(title = "商品评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductComment ffwyProductComment)
    {
        List<FfwyProductComment> list = ffwyProductCommentService.selectFfwyProductCommentList(ffwyProductComment);
        ExcelUtil<FfwyProductComment> util = new ExcelUtil<FfwyProductComment>(FfwyProductComment.class);
        return util.exportExcel(list, "ffwy_product_comment");
    }

    /**
     * 获取商品评论详细信息
     */
    @ApiOperation("获取商品评论详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:query')")
    @GetMapping(value = "/{productCommentId}")
    public AjaxResult getUser(@PathVariable("productCommentId") Long productCommentId)
    {
        System.err.println(productCommentId);
        return AjaxResult.success(ffwyProductCommentService.selectFfwyProductCommentById(productCommentId));
    }


//    /**
//     * 新增商品评论
//     */
//    @ApiOperation("新增商品评论")
////    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:add')")
//    @Log(title = "商品评论", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody FfwyProductComment ffwyProductComment)
//    {
//        return ffwyProductCommentService.insertFfwyProductComment(ffwyProductComment);
//    }

    /**
     * 修改商品评论
     */
    @ApiOperation("修改商品评论")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:edit')")
    @Log(title = "商品评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProductComment ffwyProductComment)
    {
        return toAjax(ffwyProductCommentService.revert(ffwyProductComment));
    }

    /**
     * 删除商品评论
     */
    @ApiOperation("删除商品评论")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:remove')")
    @Log(title = "商品评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productCommentIds}")
    public AjaxResult remove(@PathVariable Long[] productCommentIds)
    {
        return toAjax(ffwyProductCommentService.deleteFfwyProductCommentByIds(productCommentIds));
    }
}
