package com.ruoyi.web.controller.order;

import java.util.List;

import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.service.IFfwyAfterSaleService;
import com.ruoyi.system.service.IFfwyConsigneeAddrService;
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
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.service.IFfwyOrderDetailsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单详情Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "订单详情")
@RestController
@RequestMapping("/back/order")
public class FfwyOrderDetailsController extends BaseController
{
    @Autowired
    private IFfwyOrderDetailsService ffwyOrderDetailsService;
    @Autowired
    private IFfwyConsigneeAddrService iFfwyConsigneeAddrService;
    @Autowired
    private IFfwyAfterSaleService ffwyAfterSaleService;

    /**
     * 查询订单详情列表
     */
    @ApiOperation("查询订单详情列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyOrderDetails ffwyOrderDetails)
    {
        startPage();
        List<FfwyOrderDetails> list = ffwyOrderDetailsService.backOrderDetailsList(ffwyOrderDetails);
        return getDataTable(list);
    }
    @GetMapping("/shopAddr")
    public TableDataInfo getShopAddr(FfwyConsigneeAddr ffwyConsigneeAddr){
        startPage();

        List<FfwyConsigneeAddr> list = iFfwyConsigneeAddrService.selectShopAddrList(ffwyConsigneeAddr);
        return getDataTable(list);
    }

    /**
     * 导出订单详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:export')")
    @Log(title = "订单详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyOrderDetails ffwyOrderDetails)
    {
        List<FfwyOrderDetails> list = ffwyOrderDetailsService.selectFfwyOrderDetailsList(ffwyOrderDetails);
        ExcelUtil<FfwyOrderDetails> util = new ExcelUtil<FfwyOrderDetails>(FfwyOrderDetails.class);
        return util.exportExcel(list, "ffwy_order_details");
    }

    /**
     * 获取订单详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:query')")
    @GetMapping(value = "/{orderDetailsId}")
    public AjaxResult getInfo(@PathVariable("orderDetailsId") Long orderDetailsId)
    {
        return AjaxResult.success(ffwyOrderDetailsService.selectFfwyOrderDetailsById(orderDetailsId));
    }

    /**
     * 新增订单详情
     */
    @ApiOperation("新增订单详情")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:add')")
    @Log(title = "订单详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyOrderDetails ffwyOrderDetails)
    {
        return toAjax(ffwyOrderDetailsService.insertFfwyOrderDetails(ffwyOrderDetails));
    }

    /**
     * 修改订单详情
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:edit')")
    @Log(title = "订单详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyOrderDetails ffwyOrderDetails)
    {
        return toAjax(ffwyOrderDetailsService.updateFfwyOrderDetails(ffwyOrderDetails));
    }

    /**
     * 发货
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:edit')")
//    @Log(title = "订单详情", businessType = BusinessType.UPDATE)
    @PutMapping("/shipments")
    public AjaxResult shipments(@RequestBody FfwyOrderDetails ffwyOrderDetails)
    {

        return toAjax(ffwyOrderDetailsService.shipments(ffwyOrderDetails));
    }
    /**
     * 同意退款
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:edit')")
//    @Log(title = "订单详情", businessType = BusinessType.UPDATE)
    @PutMapping("/agree")
    public AjaxResult agree(@RequestBody FfwyOrderDetails ffwyOrderDetails)
    {
        int agree = ffwyOrderDetailsService.agree(ffwyOrderDetails);
        if (agree==-2)
            return AjaxResult.error("退款失败");
        return toAjax(agree);
    }
    /**
     * 拒绝退款
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:edit')")
//    @Log(title = "订单详情", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    public AjaxResult reject(@RequestBody FfwyOrderDetails ffwyOrderDetails)
    {

        return toAjax(ffwyOrderDetailsService.reject(ffwyOrderDetails));
    }
    /**
     * 同意退货退款
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:edit')")
//    @Log(title = "订单详情", businessType = BusinessType.UPDATE)
    @PutMapping("/agreeAndSales")
    public AjaxResult agreeAndSales(@RequestBody FfwyOrderDetails ffwyOrderDetails)
    {
        int i = ffwyOrderDetailsService.agreeAndSales(ffwyOrderDetails);

        if (i==-2)
            return AjaxResult.error("退款失败");
        return toAjax(i);
    }

    /**
     * 删除订单详情
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:remove')")
//    @Log(title = "订单详情", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{orderDetailsIds}")
//    public AjaxResult remove(@PathVariable Long orderDetailsIds)
//    {
//        return toAjax(ffwyOrderDetailsService.deleteFfwyOrderDetailsByIds(orderDetailsIds));
//    }
    /**
     * 查看退款理由
     */
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_details:remove')")

    @GetMapping("/queryAfterSale/{orderId}")
    public AjaxResult queryAfterSale(@PathVariable Long orderId)
    {

        return AjaxResult.success(ffwyAfterSaleService.selectByOrderId(orderId));
    }

}
