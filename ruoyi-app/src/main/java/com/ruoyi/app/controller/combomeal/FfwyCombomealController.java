package com.ruoyi.app.controller.combomeal;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.config.JwtTokenUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.to.CombomealGoodsTo;
import com.ruoyi.system.domain.to.CombomealTo;
import com.ruoyi.system.service.IFfwyCombomealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/17
 **/
@Api(tags = "商品套餐操作")
@Slf4j
@RestController
@RequestMapping("/app")
public class FfwyCombomealController extends BaseController {

    @Autowired
    private IFfwyCombomealService ffwyCombomealService;

    @ApiOperation("查询套餐分类下 风格列表")
    @GetMapping("/combomealStyleList")
    public AjaxResult getCombomealStyleList(Long categoryId,@NonNull Integer currPage)
    {
        return AjaxResult.success(ffwyCombomealService.getCombomealStyleList(categoryId,currPage));
    }

    /**
     * 套餐详情
     */
    @ApiOperation("套餐详情展示")
    @Log(title = "套餐详情", businessType = BusinessType.EXPORT)
    @GetMapping("/combomealDetails")
    public AjaxResult getCombomealDetails(Long combomealId,HttpServletRequest request)
    {
        Long userId = JwtTokenUtil.getUserId(request.getHeader(JWTUtils.TOKRN));
        if (null == userId){
            log.error("令牌不合法{}：",request.getHeader(JWTUtils.TOKRN));
            return AjaxResult.error("令牌不合法!");
        }
        return ffwyCombomealService.getCombomealDetails(combomealId,userId);
    }

    /**
     * 装修预估价格   （弃用）
     */
    @ApiOperation("装修预估价格")
    @Log(title = "装修预估价格", businessType = BusinessType.EXPORT)
    @PostMapping("/estimatedPrice")
    public AjaxResult getEstimatedPrice(@RequestBody @Validated CombomealTo combomealTo)
    {
        return AjaxResult.success(ffwyCombomealService.getEstimatedPrice(combomealTo));
    }

    /**
     * 装修商品添加
     */
    @ApiOperation("装修商品添加")
    @Log(title = "装修商品添加", businessType = BusinessType.EXPORT)
    @PostMapping("/combomaealGoodsAdd")
    public AjaxResult combomaealGoodsAdd(@RequestBody @Validated CombomealGoodsTo combomealGoodsTo,HttpServletRequest request)
    {
        Long userId = JwtTokenUtil.getUserId(request.getHeader(JWTUtils.TOKRN));
        if (null == userId){
            log.error("令牌不合法{}：",request.getHeader(JWTUtils.TOKRN));
            return AjaxResult.error("令牌不合法!");
        }
        combomealGoodsTo.setUserId(userId);
        return ffwyCombomealService.combomaealGoodsAdd(combomealGoodsTo);
    }

    /**
     * 生成订单
     */
    @ApiOperation("生成订单")
    @Log(title = "生成订单", businessType = BusinessType.EXPORT)
    @PostMapping("/combomaealOrder")
    public AjaxResult generateCombomaealOrder(@RequestBody @Validated CombomealTo combomealTo, HttpServletRequest request)
    {
        String msg = "下单失败，";
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        if (userId == null){
            return AjaxResult.error(msg+"请先登录");
        }
        combomealTo.setUserId(userId.toString());
        return AjaxResult.success(ffwyCombomealService.generateCombomaealOrder(combomealTo));

    }
}
