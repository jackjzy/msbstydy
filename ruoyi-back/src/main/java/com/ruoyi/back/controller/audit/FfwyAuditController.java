package com.ruoyi.back.controller.audit;

import java.util.Date;
import java.util.List;

import com.ruoyi.back.domain.queryentity.QueryAudti;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.jg.JiGuangPushUtil;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.common.utils.ucpaasSms.SmsUtil;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.service.IFfwyShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.back.domain.FfwyAudit;
import com.ruoyi.back.service.IFfwyAuditService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审核Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "审核管理")
@RestController
@RequestMapping("/back/audit")
public class FfwyAuditController extends BaseController
{
    @Autowired
    private IFfwyAuditService ffwyAuditService;
    @Autowired
    private IFfwyShopService iFfwyShopService;
    @Autowired
    FfwyShop ffwyShop;
    //驳回状态
    private final int AUDIT_STATUS_REJECT= 3;
    //通过状态
    private final int AUDIT_STATUS_PASS=2;

    /**
     * 查询审核列表
     */
   // @PreAuthorize("@ss.hasPermi('audit:audit:list')")
    @ApiOperation("审核列表")
    @GetMapping("/list")
    public TableDataInfo list(QueryAudti ffwyAudit)
    {

        startPage();
        List<FfwyAudit> list = ffwyAuditService.faindAllAuditList(ffwyAudit);
        return getDataTable(list);
    }

    /**
     * 导出审核列表
     */
    @PreAuthorize("@ss.hasPermi('audit:audit:export')")
    @Log(title = "审核", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyAudit ffwyAudit)
    {
        List<FfwyAudit> list = ffwyAuditService.selectFfwyAuditList(ffwyAudit);
        ExcelUtil<FfwyAudit> util = new ExcelUtil<FfwyAudit>(FfwyAudit.class);
        return util.exportExcel(list, "audit");
    }





    /**
     * 修改审核
     */
    @ApiOperation("审核能否成为店铺")
   // @PreAuthorize("@ss.hasPermi('audit:audit:edit')")
    @Log(title = "审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyAudit ffwyAudit)
    {

        ffwyAuditService.auditByYes(ffwyAudit);
        return toAjax(ffwyAuditService.updateFfwyAudit(ffwyAudit));
    }


}
