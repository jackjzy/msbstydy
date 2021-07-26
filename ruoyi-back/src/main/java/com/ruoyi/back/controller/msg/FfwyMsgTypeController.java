package com.ruoyi.back.controller.msg;

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
import com.ruoyi.back.domain.FfwyMsgType;
import com.ruoyi.back.service.IFfwyMsgTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消息类型Controller
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
@RestController
@RequestMapping("/back/msgType")
public class FfwyMsgTypeController extends BaseController
{
    @Autowired
    private IFfwyMsgTypeService ffwyMsgTypeService;

    /**
     * 查询消息类型列表
     */
   // @PreAuthorize("@ss.hasPermi('back:back:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyMsgType ffwyMsgType)
    {
        startPage();
        List<FfwyMsgType> list = ffwyMsgTypeService.selectFfwyMsgTypeList(ffwyMsgType);
        return getDataTable(list);
    }

    /**
     * 导出消息类型列表
     */
    @PreAuthorize("@ss.hasPermi('back:back:export')")
    @Log(title = "消息类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyMsgType ffwyMsgType)
    {
        List<FfwyMsgType> list = ffwyMsgTypeService.selectFfwyMsgTypeList(ffwyMsgType);
        ExcelUtil<FfwyMsgType> util = new ExcelUtil<FfwyMsgType>(FfwyMsgType.class);
        return util.exportExcel(list, "back");
    }

    /**
     * 获取消息类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:back:query')")
    @GetMapping(value = "/{msgTypeId}")
    public AjaxResult getInfo(@PathVariable("msgTypeId") Long msgTypeId)
    {
        return AjaxResult.success(ffwyMsgTypeService.selectFfwyMsgTypeById(msgTypeId));
    }

    /**
     * 新增消息类型
     */
    @PreAuthorize("@ss.hasPermi('back:back:add')")
    @Log(title = "消息类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyMsgType ffwyMsgType)
    {
        return toAjax(ffwyMsgTypeService.insertFfwyMsgType(ffwyMsgType));
    }

    /**
     * 修改消息类型
     */
    @PreAuthorize("@ss.hasPermi('back:back:edit')")
    @Log(title = "消息类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyMsgType ffwyMsgType)
    {
        return toAjax(ffwyMsgTypeService.updateFfwyMsgType(ffwyMsgType));
    }

    /**
     * 删除消息类型
     */
    @PreAuthorize("@ss.hasPermi('back:back:remove')")
    @Log(title = "消息类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{msgTypeIds}")
    public AjaxResult remove(@PathVariable Long[] msgTypeIds)
    {
        return toAjax(ffwyMsgTypeService.deleteFfwyMsgTypeByIds(msgTypeIds));
    }
}
