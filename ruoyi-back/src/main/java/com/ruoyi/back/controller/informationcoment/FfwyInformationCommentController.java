package com.ruoyi.back.controller.informationcoment;

import java.util.Date;
import java.util.List;

import com.ruoyi.back.domain.FfwyInformationComment;
import com.ruoyi.back.domain.queryentity.QueryInformationComment;
import com.ruoyi.back.service.IFfwyInformationCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-19
 */
@Api(tags = "咨询评论管理")
@RestController
@RequestMapping("/back/ifmtcomment")
public class FfwyInformationCommentController extends BaseController
{
    @Autowired
    private IFfwyInformationCommentService ffwyInformationCommentService;
    private final Logger log=LoggerFactory.getLogger(getClass());

    /**
     * 查询【请填写功能名称】列表
     */
    //@PreAuthorize("@ss.hasPermi('system:comment:list')")
    @ApiOperation("搜索筛选评论")
    @GetMapping("/list")
    public TableDataInfo list(QueryInformationComment ffwyInformationComment)
    {
        logger.info("接收的对象",ffwyInformationComment);
        startPage();
        List<FfwyInformationComment> list = ffwyInformationCommentService.selectFfwyInformationCommentList(ffwyInformationComment);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(QueryInformationComment ffwyInformationComment)
    {
        List<FfwyInformationComment> list = ffwyInformationCommentService.selectFfwyInformationCommentList(ffwyInformationComment);
        ExcelUtil<FfwyInformationComment> util = new ExcelUtil<FfwyInformationComment>(FfwyInformationComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取【查询某条评论】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{informationCommentId}")
    public AjaxResult getInfo(@PathVariable("informationCommentId") Long informationCommentId)
    {
        return AjaxResult.success(ffwyInformationCommentService.selectFfwyInformationCommentById(informationCommentId));
    }
    /**
     * 获取【查询某条评论】详细信息
     */
    @ApiOperation("回复评论")
    @PutMapping("/reply")
    public AjaxResult addReply(@RequestBody FfwyInformationComment ffwyInformationComment)
    {
       log.info("接收的回复为：",ffwyInformationComment.getReply());
       log.info("接收的id位",ffwyInformationComment.getInformationCommentId());
        ffwyInformationComment.setReplyTime(new Date());
        return AjaxResult.success(ffwyInformationCommentService.updateFfwyInformationComment(ffwyInformationComment));
    }


    /**
     * 删除并封禁用户【请填写功能名称】
     */
    @ApiOperation("删除并封禁用户")

    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(Long ffwyInformationCommentId,Long userId)
    {
        return toAjax(ffwyInformationCommentService.deleteComentChageUser(ffwyInformationCommentId, userId));
    }

    @ApiOperation("删除评论")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{informationCommentId}")
    public AjaxResult edits(@PathVariable Long informationCommentId)
    {

        //return  null;
        return toAjax(ffwyInformationCommentService.deleteComentChageUser(informationCommentId, null));
    }


}
