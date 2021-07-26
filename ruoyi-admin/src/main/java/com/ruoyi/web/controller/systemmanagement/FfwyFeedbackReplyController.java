package com.ruoyi.web.controller.systemmanagement;

import java.util.List;

import com.ruoyi.system.domain.Reply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.FfwyFeedbackReply;
import com.ruoyi.system.service.IFfwyFeedbackReplyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "回复接口")
@RestController
@RequestMapping("/back/reply")
public class FfwyFeedbackReplyController extends BaseController
{
    @Autowired
    private IFfwyFeedbackReplyService ffwyFeedbackReplyService;


    /**
     * 回复接口
     */
    @ApiOperation("回复接口")
    //@ApiImplicitParam(name = "reply", value = "回复接口", dataType = "Reply")
    @Log(title = "回复接口", businessType = BusinessType.INSERT)
    @PostMapping("/addreply")
    public AjaxResult addreply(Reply reply)
    {
        return toAjax(ffwyFeedbackReplyService.insertFfwyFeedbackReply(reply));
    }

}
