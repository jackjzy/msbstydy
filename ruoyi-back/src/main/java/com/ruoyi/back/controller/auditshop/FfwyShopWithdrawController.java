package com.ruoyi.back.controller.auditshop;

import com.ruoyi.back.domain.FfwyShopWithdraw;
import com.ruoyi.back.service.IFfwyShopWithdrawService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.service.IFfwyShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提现申请管理Controller
 *
 * @author Yapeng Guo
 * @date 2021-05-25
 */
@Api(tags = "店铺提现申请")
@RestController
@RequestMapping("/back/withdraw")
public class FfwyShopWithdrawController extends BaseController
{
    @Autowired
    private IFfwyShopWithdrawService ffwyShopWithdrawService;
    @Autowired
    private IFfwyShopService iFfwyShopService;

    /**
     * 查询提现申请列表
     */
//    @PreAuthorize("@ss.hasPermi('back:withdraw:list')")
    @ApiOperation("查询提现申请列表")
    @GetMapping("/list")
    public TableDataInfo list(FfwyShopWithdraw ffwyShopWithdraw)
    {
        startPage();
        List<FfwyShopWithdraw> list = ffwyShopWithdrawService.selectFfwyShopWithdrawList(ffwyShopWithdraw);
        return getDataTable(list);
    }
    @GetMapping("/balance/{shopId}")
    public AjaxResult getBalance(@PathVariable("shopId") Long shopId){
        FfwyShop ffwyShop = iFfwyShopService.selectFfwyShopById(shopId);
        return AjaxResult.success(ffwyShop);
    }

    /**
     * 导出提现申请列表
     */
//    @PreAuthorize("@ss.hasPermi('back:withdraw:export')")
//    @ApiOperation("导出提现申请")
    @Log(title = "提现申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyShopWithdraw ffwyShopWithdraw)
    {
        List<FfwyShopWithdraw> list = ffwyShopWithdrawService.selectFfwyShopWithdrawList(ffwyShopWithdraw);
        ExcelUtil<FfwyShopWithdraw> util = new ExcelUtil<FfwyShopWithdraw>(FfwyShopWithdraw.class);
        return util.exportExcel(list, "withdraw");
    }

    /**
     * 获取提现申请详细信息
     */
//    @PreAuthorize("@ss.hasPermi('back:withdraw:query')")
    @ApiOperation("获取提现申请详细信息")
    @GetMapping(value = "/{withdrawId}")
    public AjaxResult getInfo(@PathVariable("withdrawId") Long withdrawId)
    {
        return AjaxResult.success(ffwyShopWithdrawService.selectFfwyShopWithdrawById(withdrawId));
    }

    /**
     * 新增提现申请
     */
//    @PreAuthorize("@ss.hasPermi('back:withdraw:add')")
    @ApiOperation("新增提现申请")
    @Log(title = "提现申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyShopWithdraw ffwyShopWithdraw)
    {
        BigDecimal withdrawNum = ffwyShopWithdraw.getWithdrawNum();
        System.err.println(withdrawNum);
        return toAjax(ffwyShopWithdrawService.insertFfwyShopWithdraw(ffwyShopWithdraw));
    }

    /**
     * 修改提现申请
     */
//    @PreAuthorize("@ss.hasPermi('back:withdraw:edit')")
    @ApiOperation("修改提现申请")
    @Log(title = "提现申请", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateBanance",headers = "content-type=multipart/form-data")
    public AjaxResult edit(@RequestParam Long withdrawId,
                           @RequestParam String applyStatus,
                           @RequestParam Long shopId,
                           @RequestParam BigDecimal withdrawNum,
                           @RequestParam("file") MultipartFile file)
    {


        return toAjax(ffwyShopWithdrawService.updateFfwyShopWithdraw(
                withdrawId,applyStatus,shopId,withdrawNum,file));
//        return null;
    }

    @ApiOperation("批量删除提现申请")
    @Log(title = "拒绝提现申请", businessType = BusinessType.DELETE)
    @PutMapping ("/reject")
    public AjaxResult reject(@RequestBody FfwyShopWithdraw ffwyShopWithdraw)
    {
        System.err.println(ffwyShopWithdraw);
        return toAjax(ffwyShopWithdrawService.rejectShopWithdraw(ffwyShopWithdraw));
    }

    /**
     * 批量删除提现申请
     */
//    @PreAuthorize("@ss.hasPermi('back:withdraw:remove')")
    @ApiOperation("批量删除提现申请")
    @Log(title = "提现申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{withdrawIds}")
    public AjaxResult removeMany(@PathVariable Long[] withdrawIds)
    {
        return toAjax(ffwyShopWithdrawService.deleteFfwyShopWithdrawByIds(withdrawIds));
    }


    /**
     * 删除提现申请
     */
//    @PreAuthorize("@ss.hasPermi('back:withdraw:remove')")
    @ApiOperation("删除提现申请")
    @Log(title = "提现申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteById/{withdrawId}")
    public AjaxResult remove(@PathVariable Long withdrawId)
    {
        return toAjax(ffwyShopWithdrawService.deleteFfwyShopWithdrawById(withdrawId));
    }





}
