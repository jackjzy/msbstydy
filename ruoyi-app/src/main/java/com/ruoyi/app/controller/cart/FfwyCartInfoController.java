package com.ruoyi.app.controller.cart;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.cart.FfwyCartInfo;
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
import com.ruoyi.system.service.IFfwyCartInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 购物车 Controller
 *
 * @author ruoyi
 * @date 2021-05-14
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/app/cart")
public class FfwyCartInfoController extends BaseController {
    @Autowired
    private IFfwyCartInfoService ffwyCartInfoService;

    /**
     * 查询购物车 列表
     * userId
     */
    @ApiOperation("查询购物车")
    @GetMapping("/list")
    public AjaxResult list(FfwyCartInfo ffwyCartInfo) {
        return ffwyCartInfoService.selectFfwyCartInfoList(ffwyCartInfo);
    }


    /**
     * 获取购物车 详细信息
     */
    @ApiOperation("获取购物车详细信息")
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(ffwyCartInfoService.selectFfwyCartInfoById(id));
    }

    /**
     * 新增购物车
     * 默认是选中的
     */
    @ApiOperation("新增购物车")
    @PostMapping("/addproduct")
    public AjaxResult add(@RequestBody FfwyCartInfo ffwyCartInfo) {
        return ffwyCartInfoService.insertFfwyCartInfo(ffwyCartInfo);
    }

    /**
     * 修改购物车
     * <p>
     * 取消选择 0是不选中 1是选中   用户 商品   选中
     * 修改商品数量
     * 用户id  商品id  数量
     */
    @ApiOperation("修改购物车")
    @PostMapping("/checkCart")
    public AjaxResult edit(@RequestBody FfwyCartInfo ffwyCartInfo) {
        return ffwyCartInfoService.updateFfwyCartInfo(ffwyCartInfo);
    }

    /**
     * 删除购物车
     */
    @ApiOperation("删除")
    @DeleteMapping("deleteproduct")
    public AjaxResult remove(String ids) {
        return toAjax(ffwyCartInfoService.deleteFfwyCartInfoByIds(ids.toString()));
    }


    //3:根据用户ID
    // 查询购物车中已经选中的商品  设置给订单详情对象 准备出订单详情对象的集合 并回显页面
    //       order_detail  集合
    //4:商品总件数
    //5:商品总金额
    @ApiOperation("商品合并")
    @GetMapping("/getCartCheckedList")
    public AjaxResult getCartCheckedList(String id) {
        Map<String, Object> resultMap = ffwyCartInfoService.getCartCheckedList(id);
        return AjaxResult.success(resultMap);
    }
    //3:根据用户ID
    // 查询购物车中已经选中的商品  设置给订单详情对象 准备出订单详情对象的集合 并回显页面
    //       order_detail  集合
    //4:商品总件数
    //5:商品总金额
    @ApiOperation("商品合并")
    @GetMapping("/getCartCheckedSkuList")
    public AjaxResult getCartCheckedList(String skuId,int skuNum) {
        Map<String, Object> resultMap =
                ffwyCartInfoService.getCartCheckedSkuList(skuId,skuNum);
        return AjaxResult.success(resultMap);
    }

}
