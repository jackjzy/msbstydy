package com.ruoyi.back.controller.auditstatus;

import java.util.List;
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
import com.ruoyi.back.domain.FfwyAuditStatus;
import com.ruoyi.back.service.IFfwyAuditStatusService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审核商户状态Controller
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@RestController
@RequestMapping("/back/audit/status")
public class FfwyAuditStatusController extends BaseController
{
    @Autowired
    private IFfwyAuditStatusService ffwyAuditStatusService;

    /**
     * 查询审核商户状态列表
     */
   // @PreAuthorize("@ss.hasPermi('system:status:list')")
    @GetMapping()
    public TableDataInfo list(FfwyAuditStatus ffwyAuditStatus)
    {
        startPage();
        List<FfwyAuditStatus> list = ffwyAuditStatusService.selectFfwyAuditStatusList(ffwyAuditStatus);
        return getDataTable(list);
    }


}
