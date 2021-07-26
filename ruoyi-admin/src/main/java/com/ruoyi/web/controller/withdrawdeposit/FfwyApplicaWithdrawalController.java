package com.ruoyi.web.controller.withdrawdeposit;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.vo.ProductByUserVo;
import com.ruoyi.system.domain.withdrawdeposit.FfwyApplicaWithdrawal;
import com.ruoyi.system.service.IFfwyApplicaWithdrawalService;
import com.ruoyi.system.service.IFfwyProductCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品评论Controller
 * 
 * @author ruoyi
 * @date 2021-05-20
 */
@Api(tags = "提现管理")
@RestController
@RequestMapping("/back/withdrawdeposit")
public class FfwyApplicaWithdrawalController extends BaseController
{

    @Autowired
    private IFfwyApplicaWithdrawalService ffwyApplicaWithdrawalService;

    @Autowired
    private IFfwyProductCommentService ffwyProductCommentService;

    /**
     * 新增商品评论
     */
    @ApiOperation("申请提现")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:add')")
    @Log(title = "申请提现", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FfwyApplicaWithdrawal ffwyApplicaWithdrawal)
    {
        return toAjax(ffwyApplicaWithdrawalService.insertFfwyApplicaWithdrawalt(ffwyApplicaWithdrawal));
    }

    /**
     * 修改商品评论
     */
    @ApiOperation("修改商品评论")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_product_comment:edit')")
    @Log(title = "商品评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProductComment ffwyProductComment)
    {
        return toAjax(ffwyProductCommentService.updateFfwyProductComment(ffwyProductComment));
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
