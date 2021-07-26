package com.ruoyi.back.controller.msg;

import java.util.List;

import com.ruoyi.back.domain.queryentity.QueryMsg;
import com.ruoyi.common.enums.BizCodeEnum;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
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
import com.ruoyi.back.domain.FfwyMsg;
import com.ruoyi.back.service.IFfwyMsgService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.management.Query;

/**
 * 消息管理Controller
 * 
 * @author wemem
 * @date 2021-04-23
 */
@Api(tags = "消息管理")
@RestController
@RequestMapping("/back/msgs")
public class FfwyMsgController extends BaseController
{
    @Autowired
    private IFfwyMsgService ffwyMsgService;

    @Autowired

    private IFfwyUserService iFfwyUserService;

    /**
     * 查询消息管理列表
     */
   // @PreAuthorize("@ss.hasPermi('back:msg:list')")
    @ApiOperation("消息列表")
    @GetMapping("/list")
    public TableDataInfo list(QueryMsg ffwyMsg)
    {
        startPage();
        List<FfwyMsg> list = ffwyMsgService.selectFfwyMsgList(ffwyMsg);
        return getDataTable(list);
    }

    /**
     * 查询所有用户列表
     */
    // @PreAuthorize("@ss.hasPermi('back:msg:list')")
    @ApiOperation("消息列表")
    @GetMapping("/user")
    public TableDataInfo userList(QueryMsg ffwyMsg)
    {
        startPage();
        FfwyUser user = new FfwyUser();
        user.setUserType("0");
        List<FfwyUser> list = iFfwyUserService.selectFfwyUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出消息管理列表
     */
   // @PreAuthorize("@ss.hasPermi('back:msg:export')")
    @Log(title = "消息管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(QueryMsg ffwyMsg)
    {
        List<FfwyMsg> list = ffwyMsgService.selectFfwyMsgList(ffwyMsg);
        ExcelUtil<FfwyMsg> util = new ExcelUtil<FfwyMsg>(FfwyMsg.class);
        return util.exportExcel(list, "msg");
    }

    /**
     * 获取消息管理详细信息
     */
 //   @PreAuthorize("@ss.hasPermi('back:msg:query')")
    @GetMapping(value = "/{msgId}")
    public AjaxResult getInfo(@PathVariable("msgId") Long msgId)
    {
        return AjaxResult.success(ffwyMsgService.selectFfwyMsgById(msgId));
    }

    /**
     * 新增消息管理
     */
   // @PreAuthorize("@ss.hasPermi('back:msg:add')")
    @ApiOperation("添加消息")
    @Log(title = "消息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyMsg ffwyMsg)
    {
        System.err.println(ffwyMsg);
        Integer msgTypeId = ffwyMsg.getFfwyMsgType().getMsgTypeId();
        switch(msgTypeId){
            case 1 :
                /*给商家发送消息 */
                break;
            case 2 :
                /*给所有用户发送消息*/
                break;
            case 3 :
                /*给单个用户发送消息*/
                break;
            default:
                return AjaxResult.error(BizCodeEnum.PUSH_ERROR.getCode(),BizCodeEnum.PUSH_ERROR.getMessage());
        }
        return toAjax(ffwyMsgService.insertFfwyMsg(ffwyMsg));
       // return null;
    }

    /**
     * 修改消息管理
     */
    //@PreAuthorize("@ss.hasPermi('back:msg:edit')")
    @Log(title = "消息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyMsg ffwyMsg)
    {
        return toAjax(ffwyMsgService.updateFfwyMsg(ffwyMsg));
    }

    /**
     * 删除消息管理
     */
   // @PreAuthorize("@ss.hasPermi('back:msg:remove')")
    @Log(title = "消息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{msgIds}")
    public AjaxResult remove(@PathVariable Long[] msgIds)
    {
        return toAjax(ffwyMsgService.deleteFfwyMsgByIds(msgIds));
    }
}
