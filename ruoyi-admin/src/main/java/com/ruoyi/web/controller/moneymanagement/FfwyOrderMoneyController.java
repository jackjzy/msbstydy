package com.ruoyi.web.controller.moneymanagement;

import java.util.List;

import com.ruoyi.system.domain.FfwyPayment;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderStatus;
import com.ruoyi.system.domain.vo.FfwyOrderVo;
import com.ruoyi.system.service.IFfwyOrderMoneyService;
import com.ruoyi.system.service.IFfwyOrderService;
import com.ruoyi.system.service.IFfwyOrderStatusService;
import com.ruoyi.system.service.IFfwyPaymentService;
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
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.vo.PaymentVo;
/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "财务管理")
@RestController
@RequestMapping("/back/deals")
public class FfwyOrderMoneyController extends BaseController
{
    @Autowired
    private IFfwyOrderMoneyService ffwyOrderMoneyService;

    @Autowired
    private IFfwyPaymentService ffwyPaymentService;

    @Autowired
    private IFfwyOrderStatusService ffwyOrderStatusService;

    /**
     * 查询订单列表
     */
    @ApiOperation("查询所有支付记录")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_order:list')")
    @GetMapping("/list")
    public TableDataInfo findBypayment(FfwyOrderVo ffwyOrderVo)
    {
        startPage();
        List<PaymentVo> list= ffwyOrderMoneyService.selectFfwyOrderListBydetail(ffwyOrderVo);
        return getDataTable(list);
    }


    /**
     * 查询订单列表
     */
    @ApiOperation("查询所有支付方式")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_order:list')")
    @GetMapping("/findBytype")
    public TableDataInfo findBytype(FfwyPayment ffwyPayment)
    {
        startPage();
        List<FfwyPayment> list= ffwyPaymentService.selectFfwyPaymentList(ffwyPayment);
        return getDataTable(list);
    }

    /**
     * 查询订单列表
     */
    @ApiOperation("查询所有订单状态")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_order:list')")
    @GetMapping("/findBystatus")
    public TableDataInfo findBystatus(FfwyOrderStatus ffwyOrderStatus)
    {
        startPage();
        List<FfwyOrderStatus> list= ffwyOrderStatusService.selectFfwyOrderStatusList(ffwyOrderStatus);
        return getDataTable(list);
    }

    /**
     * 获取订单详细信息
     */
    @ApiOperation("查看订单详情")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_order:query')")
    @GetMapping(value = "/{orderId}")
    public TableDataInfo getInfo(@PathVariable("orderId") Long orderId)
    {
        startPage();
        List list = ffwyOrderMoneyService.selectFfwyOrderById(orderId);
        return getDataTable(list);
    }

}
