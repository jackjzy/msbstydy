package com.ruoyi.app.controller.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.vo.ProductByUserVo;
import com.ruoyi.system.service.IFfwyProductCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品评论Controller
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Api(tags = "商品评论")
@RestController
@RequestMapping("/app/productComment")
public class FfwyProductCommentController extends BaseController {
    @Autowired
    private IFfwyProductCommentService ffwyProductCommentService;

    /**
     * 查询商品评论列表
     */
    @ApiOperation("查询商品评论列表")
    @GetMapping("/list")
    public TableDataInfo list(FfwyProductComment ffwyProductComment) {
        startPage();
        List<FfwyProductComment> list = ffwyProductCommentService.selectFfwyProductCommentAllList(ffwyProductComment);
        return getDataTable(list);
    }

    /**
     * 获取商品评论详
     */
    @ApiOperation("获取商品")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:query')")
    @GetMapping(value = "/products")
    public TableDataInfo getInfo(ProductByUserVo productByUserVo) {
        startPage();
        List<FfwyProductComment> list = ffwyProductCommentService.selectUserId(productByUserVo.getUserId(), productByUserVo.getShopId(), productByUserVo.getProductId(), productByUserVo.getCombomealId());
        return getDataTable(list);
    }

    /**
     * 导出商品评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:export')")
    @Log(title = "商品评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProductComment ffwyProductComment) {
        List<FfwyProductComment> list = ffwyProductCommentService.selectFfwyProductCommentList(ffwyProductComment);
        ExcelUtil<FfwyProductComment> util = new ExcelUtil<FfwyProductComment>(FfwyProductComment.class);
        return util.exportExcel(list, "ffwy_product_comment");
    }

    /**
     * 获取商品评论详细信息
     */
    @ApiOperation("获取商品评论详细信息")
    @GetMapping(value = "/{productCommentId}")
    public AjaxResult getUser(@PathVariable("productCommentId") Long productCommentId) {
        return AjaxResult.success(ffwyProductCommentService.selectFfwyProductCommentById(productCommentId));
    }


    /**
     * 新增商品评论
     */
    @ApiOperation("新增商品评论")
    @PostMapping("add")
    public AjaxResult add(@RequestBody JSONObject jsonObject) {
        FfwyProductComment comment = new FfwyProductComment();
        System.err.println(jsonObject.getJSONArray("comments").toJSONString());
        List<FfwyProductComment> ffwyProductComments = JSONObject.parseArray(jsonObject.getJSONArray("comments").toJSONString(), FfwyProductComment.class);
        System.err.println(ffwyProductComments);
        comment.setComments(ffwyProductComments);
        return ffwyProductCommentService.insertFfwyProductComment(comment);
    }

    /**
     * 修改商品评论
     */
    @ApiOperation("修改商品评论")
    @Log(title = "商品评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProductComment ffwyProductComment) {
        return toAjax(ffwyProductCommentService.updateFfwyProductComment(ffwyProductComment));
    }

    /**
     * 删除商品评论
     */
    @ApiOperation("删除商品评论")
    @Log(title = "商品评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productCommentIds}")
    public AjaxResult remove(@PathVariable Long[] productCommentIds) {
        return toAjax(ffwyProductCommentService.deleteFfwyProductCommentByIds(productCommentIds));
    }

    /**
     * 查询商品评论列表
     */
    @ApiOperation("查询商品评论列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:list')")
    @GetMapping("/listUser")
    public TableDataInfo listUser(FfwyProductComment ffwyProductComment) {
        startPage();
        List<FfwyProductComment> list = ffwyProductCommentService.selectShopComment(ffwyProductComment);
        return getDataTable(list);
    }

}
