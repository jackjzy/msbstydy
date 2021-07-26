package com.ruoyi.web.controller.systemmanagement;

import java.util.List;

import com.ruoyi.system.domain.FfwyFeedbackReply;
import com.ruoyi.system.domain.Reply;
import com.ruoyi.system.domain.vo.FeedbckVo;
import com.ruoyi.system.service.IFfwyFeedbackReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
import com.ruoyi.system.domain.FfwyFeedback;
import com.ruoyi.system.service.IFfwyFeedbackService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "系统管理")
@RestController
@RequestMapping("/back/feedbacks")
public class FfwyFeedbackController extends BaseController
{
    @Autowired
    private IFfwyFeedbackService ffwyFeedbackService;

    @Autowired
    private IFfwyFeedbackReplyService iFfwyFeedbackReplyService;

    /**
     * 查询【请填写功能名称】列表
     */
    @ApiOperation("查询所有")
    //@PreAuthorize("@ss.hasPermi('system:feedback:list')")
    @GetMapping("/findAll")
    public TableDataInfo findAll(FfwyFeedback ffwyFeedback)
    {
        startPage();
        List<FfwyFeedback> list = ffwyFeedbackService.selectFfwyFeedback(ffwyFeedback);
        return getDataTable(list);

    }


    @ApiOperation("回复")
    //@PreAuthorize("@ss.hasPermi('system:feedback:list')")
    @PostMapping()
    public AjaxResult reply(@RequestBody Reply reply)
    {
        System.out.println(reply);
        return toAjax( iFfwyFeedbackReplyService.insertFfwyFeedbackReply(reply));

    }
    /**
     * 删除接口
     */
    @ApiOperation("删除接口")
   // @ApiImplicitParam(name = "feedbackId", value = "反馈ID", required = true, dataType = "int", paramType = "path")
    //@PreAuthorize("@ss.hasPermi('system:feedback:remove')")
    @Log(title = "删除接口", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackId}")
    public AjaxResult deleteByid(@PathVariable Integer feedbackId)
    {
        return toAjax(ffwyFeedbackService.deleteFfwyFeedbackById(feedbackId));
    }
}
