package com.ruoyi.back.controller.auditshop;

import com.ruoyi.back.constant.AudtiStatus;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.jg.JiGuangPushUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.system.domain.querys.QueryShop;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.service.IFfwyShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 店铺Controller
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "店铺审核管理")
@RestController
@RequestMapping("/back/auditShop")
public class FfwyAuditShopController extends BaseController
{
    @Autowired
    private IFfwyShopService ffwyShopService;

    /**
     * 查询店铺列表
     */
    @ApiOperation("查询店铺审核列表")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:list')")
    @GetMapping("/list")
    public TableDataInfo list(QueryShop ffwyShop)
    {
        startPage();
        List<FfwyShop> list = ffwyShopService.selectByShopAdudtiStatus(ffwyShop);
        return getDataTable(list);
    }

    /**
     * 导出店铺列表
     */
    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:export')")
    @Log(title = "店铺", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyShop ffwyShop)
    {
        List<FfwyShop> list = ffwyShopService.selectFfwyShopList(ffwyShop);
        ExcelUtil<FfwyShop> util = new ExcelUtil<FfwyShop>(FfwyShop.class);
        return util.exportExcel(list, "ffwy_shop");
    }

    /**
     * 获取店铺详细信息
     */
    @ApiOperation("获取店铺详细信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:query')")
    @GetMapping(value = "/{shopId}")
    public AjaxResult getInfo(@PathVariable("shopId") Long shopId)
    {
        return AjaxResult.success(ffwyShopService.selectFfwyShopById(shopId));
    }


    /**
     * 修改店铺
     */
    @ApiOperation("店铺审核")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop:edit')")
    @Log(title = "店铺", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyShop ffwyShop)
    {
        String hash = Md5Utils.hash(ffwyShop.getShopId().toString());
        if (ffwyShop.getFfwyAuditStatuss().getAuditStatusId()== AudtiStatus.AUDIT_STATUS_PASS){
            /*
            * 发送通知：发送店铺通过审核通知，店主可登录店铺管理后台
            *
            * */

            JiGuangPushUtil.pushAndroid("alias",hash,"审核成功","您的商品成功通过审核，可在app上查看");
            ffwyShop.setPassTime(new Date());

        }else if (ffwyShop.getFfwyAuditStatuss().getAuditStatusId()== AudtiStatus.AUDIT_STATUS_PASS){

            /*
             * 发送通知：发送店铺通过审核通知，被拒绝的原因
             *
             * */
            JiGuangPushUtil.pushAndroid("alias",hash,"审核失败",ffwyShop.getReject());
            logger.info(ffwyShop.getReject());
        }else {
            try{
                new RuntimeException("审核状态错误");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ffwyShop.setUpdateTime(new Date());
        return toAjax(ffwyShopService.updateFfwyShop(ffwyShop));
    }
}
