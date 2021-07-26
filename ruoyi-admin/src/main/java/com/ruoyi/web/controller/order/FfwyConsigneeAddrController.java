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
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.service.IFfwyConsigneeAddrService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收货地址Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags="收货")
@RestController
@RequestMapping("/back/ffwy_consignee_addr")
public class FfwyConsigneeAddrController extends BaseController
{
    @Autowired
    private IFfwyConsigneeAddrService ffwyConsigneeAddrService;

    /**
     * 查询收货地址列表
     */
    @ApiOperation("查询收货地址列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyConsigneeAddr ffwyConsigneeAddr)
    {
        startPage();
        List<FfwyConsigneeAddr> list = ffwyConsigneeAddrService.selectFfwyConsigneeAddrList(ffwyConsigneeAddr);
        return getDataTable(list);
    }

    /**
     * 导出收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:export')")
    @Log(title = "收货地址", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyConsigneeAddr ffwyConsigneeAddr)
    {
        List<FfwyConsigneeAddr> list = ffwyConsigneeAddrService.selectFfwyConsigneeAddrList(ffwyConsigneeAddr);
        ExcelUtil<FfwyConsigneeAddr> util = new ExcelUtil<FfwyConsigneeAddr>(FfwyConsigneeAddr.class);
        return util.exportExcel(list, "ffwy_consignee_addr");
    }

    /**
     * 获取收货地址详细信息
     */
    @ApiOperation("获取收货地址详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:query')")
    @GetMapping(value = "/{consigneeAddrid}")
    public AjaxResult getInfo(@PathVariable("consigneeAddrid") Long consigneeAddrid)
    {
        return AjaxResult.success(ffwyConsigneeAddrService.selectFfwyConsigneeAddrById(consigneeAddrid));
    }

    /**
     * 新增收货地址
     */
    @ApiOperation("新增收货地址")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:add')")
    @Log(title = "收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyConsigneeAddr ffwyConsigneeAddr)
    {
        return toAjax(ffwyConsigneeAddrService.insertFfwyConsigneeAddrs(ffwyConsigneeAddr));
    }

    /**
     * 修改收货地址
     */
    @ApiOperation("修改收货地址")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:edit')")
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyConsigneeAddr ffwyConsigneeAddr)
    {
        return toAjax(ffwyConsigneeAddrService.updateFfwyConsigneeAddr(ffwyConsigneeAddr));
    }

    /**
     * 删除收货地址
     */
    @ApiOperation("删除收货地址")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:remove')")
    @Log(title = "收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consigneeAddrids}")
    public AjaxResult remove(@PathVariable Long[] consigneeAddrids)
    {
        return toAjax(ffwyConsigneeAddrService.deleteFfwyConsigneeAddrByIds(consigneeAddrids));
    }
}
