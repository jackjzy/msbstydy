package com.ruoyi.web.controller.protocoll;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.protocol.FfwyProtocol;
import com.ruoyi.system.service.IFfwyProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
@RestController
@RequestMapping("/back/protocol")
public class ProtocolController extends BaseController
{
    @Autowired
    private IFfwyProtocolService ffwyProtocolService;

    /**
     * 查询【用户协议列表】列表
     */

    @GetMapping("/list")
    public TableDataInfo list(FfwyProtocol ffwyProtocol)
    {
        startPage();
        List<FfwyProtocol> list = ffwyProtocolService.selectFfwyProtocolList(ffwyProtocol);
        return getDataTable(list);
    }

    /**
     * 导出【用户协议列表】列表
     */
    @PreAuthorize("@ss.hasPermi('system:protocol:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyProtocol ffwyProtocol)
    {
        List<FfwyProtocol> list = ffwyProtocolService.selectFfwyProtocolList(ffwyProtocol);
        ExcelUtil<FfwyProtocol> util = new ExcelUtil<FfwyProtocol>(FfwyProtocol.class);
        return util.exportExcel(list, "protocol");
    }

    /**
     * 获取【用户协议列表】详细信息
     */

    @GetMapping(value = "/{protocolId}")
    public AjaxResult getInfo(@PathVariable("protocolId") Integer protocolId)
    {
        return AjaxResult.success(ffwyProtocolService.selectFfwyProtocolById(1));
    }

    /**
     * 新增【用户协议列表】
     */
//    @PreAuthorize("@ss.hasPermi('system:protocol:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyProtocol ffwyProtocol)
    {
        return toAjax(ffwyProtocolService.insertFfwyProtocol(ffwyProtocol));
    }

    /**
     * 修改【用户协议列表】
     */
//    @PreAuthorize("@ss.hasPermi('system:protocol:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyProtocol ffwyProtocol)
    {
        return toAjax(ffwyProtocolService.updateFfwyProtocol(ffwyProtocol));
    }

    /**
     * 删除【用户协议列表】
     */
//    @PreAuthorize("@ss.hasPermi('system:protocol:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{protocolIds}")
    public AjaxResult remove(@PathVariable Integer[] protocolIds)
    {
        return toAjax(ffwyProtocolService.deleteFfwyProtocolByIds(protocolIds));
    }
}
