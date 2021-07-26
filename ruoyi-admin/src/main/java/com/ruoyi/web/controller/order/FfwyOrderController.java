package com.ruoyi.web.controller.order;

import java.util.List;

import com.ruoyi.system.domain.vo.OrderVo;
import com.ruoyi.system.domain.vo.Orders;
import com.ruoyi.system.domain.vo.ProductByUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.service.IFfwyOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/back/orders")
public class FfwyOrderController extends BaseController
{
    @Autowired
    private IFfwyOrderService ffwyOrderService;



    /**
     * 查询订单列表
     */
    @ApiOperation("查询订单列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order:list')")
    @GetMapping
    public TableDataInfo list(Orders orders)
    {
        startPage();
        List<OrderVo> list = ffwyOrderService.selectFfwyOrderList(orders);
        return getDataTable(list);
    }

    /**
     * 获取订单详细信息
     */
    @ApiOperation("获取订单详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order:query')")
    @GetMapping(value = "/{orderId}/{userId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId,@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(ffwyOrderService.selectFfwyOrderById(orderId,userId));
    }

    /**
     * 新增订单
     */
    @ApiOperation("新增订单")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyOrder ffwyOrder)
    {
        return toAjax(ffwyOrderService.insertFfwyOrder(ffwyOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyOrder ffwyOrder)
    {
        return toAjax(ffwyOrderService.updateFfwyOrder(ffwyOrder));
    }

    /**
     * 发货
     */
    @ApiOperation("发货")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping("/shipments")
    public AjaxResult shipments(@RequestParam Integer orderId , @RequestParam String  orderNumber)
    {
        return toAjax(ffwyOrderService.shipmentsFfwyOrder(orderId,orderNumber));
    }

    /**
     * 退款
     */
    @ApiOperation("退款")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping("/refund")
    public AjaxResult refund(@RequestParam Integer orderId ,@RequestParam  boolean  agreeToRefuse)
    {
        return toAjax(ffwyOrderService.refundFfwyOrder(orderId,agreeToRefuse));
    }

    /**
     * 取消订单
     */
    @ApiOperation("取消订单")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping("/cancel")
    public AjaxResult cancel(@RequestParam Integer orderId ,@RequestParam boolean  agreeToRefuse)
    {
        return toAjax(ffwyOrderService.cancelFfwyOrder(orderId));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(ffwyOrderService.deleteFfwyOrderByIds(orderIds));
    }
}
