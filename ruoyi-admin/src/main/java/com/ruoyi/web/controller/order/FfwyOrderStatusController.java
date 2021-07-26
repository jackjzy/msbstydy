package com.ruoyi.web.controller.order;

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
import com.ruoyi.system.domain.order.FfwyOrderStatus;
import com.ruoyi.system.service.IFfwyOrderStatusService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单状态Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "订单状态")
@RestController
@RequestMapping("/back/ffwy_order_status")
public class FfwyOrderStatusController extends BaseController
{
    @Autowired
    private IFfwyOrderStatusService ffwyOrderStatusService;

    /**
     * 查询订单状态列表
     */
    @ApiOperation("查询订单状态列表")
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_status:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyOrderStatus ffwyOrderStatus)
    {
        startPage();
        List<FfwyOrderStatus> list = ffwyOrderStatusService.selectFfwyOrderStatusList(ffwyOrderStatus);
        return getDataTable(list);
    }

    /**
     * 导出订单状态列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_status:export')")
    @Log(title = "订单状态", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyOrderStatus ffwyOrderStatus)
    {
        List<FfwyOrderStatus> list = ffwyOrderStatusService.selectFfwyOrderStatusList(ffwyOrderStatus);
        ExcelUtil<FfwyOrderStatus> util = new ExcelUtil<FfwyOrderStatus>(FfwyOrderStatus.class);
        return util.exportExcel(list, "ffwy_order_status");
    }

    /**
     * 获取订单状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_status:query')")
    @GetMapping(value = "/{statusId}")
    public AjaxResult getInfo(@PathVariable("statusId") Long statusId)
    {
        return AjaxResult.success(ffwyOrderStatusService.selectFfwyOrderStatusById(statusId));
    }

    /**
     * 新增订单状态
     */
    @ApiOperation("新增订单状态")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_order_status:add')")
    @Log(title = "订单状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyOrderStatus ffwyOrderStatus)
    {
        return toAjax(ffwyOrderStatusService.insertFfwyOrderStatus(ffwyOrderStatus));
    }

    /**
     * 修改订单状态
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_status:edit')")
    @Log(title = "订单状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyOrderStatus ffwyOrderStatus)
    {
        return toAjax(ffwyOrderStatusService.updateFfwyOrderStatus(ffwyOrderStatus));
    }

    /**
     * 删除订单状态
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_order_status:remove')")
    @Log(title = "订单状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{statusIds}")
    public AjaxResult remove(@PathVariable Long[] statusIds)
    {
        return toAjax(ffwyOrderStatusService.deleteFfwyOrderStatusByIds(statusIds));
    }
}
