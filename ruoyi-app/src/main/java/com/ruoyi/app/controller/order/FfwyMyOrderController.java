package com.ruoyi.app.controller.order;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.service.IFfwyOrderDetailsService;
import com.ruoyi.system.service.IFfwyOrderService;
import com.ruoyi.system.service.IFfwyProductSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.util.List;

/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "我的订单")
@RestController
@RequestMapping("/app/myorders")
public class FfwyMyOrderController extends BaseController {
    @Autowired
    private IFfwyOrderService ffwyOrderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IFfwyProductSkuService ffwyProductSkuService;
    @Autowired
    private IFfwyOrderDetailsService ffwyOrderDetailsService;

    //我的订单
    @ApiOperation("我的订单统计")
    @GetMapping("/statistics")
    public AjaxResult statistics(FfwyOrderDetails order) {
        List<FfwyOrderDetails> ordersList = ffwyOrderDetailsService.selectFfwyOrderStatusList(order);
        return AjaxResult.success(ordersList);
    }

    //我的订单
    @ApiOperation("全部订单")
    @GetMapping("/allList")
    public TableDataInfo allList(FfwyOrder order) {
        startPage();
        List<FfwyOrder> ordersList = ffwyOrderService.selectFfwyOrderallList(order);
        return getDataTable(ordersList);
    }

    //我的订单取消 确认收货
    @ApiOperation("我的订单取消")
    @GetMapping("/updateState")
    public AjaxResult updateState(FfwyOrderDetails order) {
        int i = ffwyOrderDetailsService.updateFfwyOrderDetails(order);
        return AjaxResult.success(i);
    }

    //我的订单取消  订单状态 订单id  查看
    @ApiOperation("待付款订单详情")
    @GetMapping("/unpaidOrders")
    public AjaxResult unpaidOrders(FfwyOrderDetails order) {
        return ffwyOrderDetailsService.selectFfwyOrderUnpaidOrdersList(order);

    }

    //待发货订单详情
    @ApiOperation("发布评价查询")
    @GetMapping("/publishEvaluate")
    public AjaxResult publishEvaluate(FfwyOrderDetails ffwyOrderDetails) {
        return ffwyOrderDetailsService.selectFfwyOrderDetails(ffwyOrderDetails);
    }

//    @ApiOperation("发布评价查询")
//    @GetMapping("/publishEvaluate")
//    public AjaxResult publishEvaluate(FfwyOrderDetails order) {
//        return ffwyOrderDetailsService.publishEvaluate(order);
//    }

    /**
     * 删除订单详情
     */
    @PostMapping("/orderDetailsIds")
    public AjaxResult remove(@RequestBody FfwyOrder ffwyOrder)
    {
        return toAjax(ffwyOrderDetailsService.deleteFfwyOrderDetailsByIds(ffwyOrder));
    }

}
