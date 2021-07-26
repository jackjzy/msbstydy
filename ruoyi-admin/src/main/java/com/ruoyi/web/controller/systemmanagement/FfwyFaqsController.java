package com.ruoyi.web.controller.systemmanagement;

import java.util.List;

import com.ruoyi.system.domain.vo.FaqsVo;
import com.ruoyi.system.domain.vo.FfwyFaqsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.system.domain.FfwyFaqs;
import com.ruoyi.system.service.IFfwyFaqsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "常见问题")
@RestController
@RequestMapping("/back/faqs")
public class FfwyFaqsController extends BaseController
{
    @Autowired
    private IFfwyFaqsService ffwyFaqsService;

    /**
     * 查询常见问题
     */
    @ApiOperation("查询常见问题")
    //@PreAuthorize("@ss.hasPermi('system:faqs:list')")
    @GetMapping("/findByfaqs")
    public TableDataInfo list(FfwyFaqs ffwyFaqs)
    {
        startPage();
        List<FfwyFaqs> list = ffwyFaqsService.selectFfwyFaqsAndReplyList(ffwyFaqs);
        return getDataTable(list);
    }



    /**
     * 新增常见问题
     */
    @ApiOperation("添加常见问题")
    //@PreAuthorize("@ss.hasPermi('system:faqs:add')")
    @Log(title = "添加常见问题", businessType = BusinessType.INSERT)
    @PostMapping("/addfaqs")
    public AjaxResult addfaqs(@RequestBody FaqsVo faqsVo)
    {

        return toAjax(ffwyFaqsService.insertFfwyFaqs(faqsVo));
    }

    /**
     * 修改常见问题
     */
    @ApiOperation("修改常见问题")
    //@PreAuthorize("@ss.hasPermi('system:faqs:edit')")
    @Log(title = "修改常见问题", businessType = BusinessType.UPDATE)
    @PutMapping("/updatefaqs")
    public AjaxResult updatefaqs(@RequestBody FfwyFaqsVo ffwyFaqsVo)
    {

        return toAjax(ffwyFaqsService.updateFfwyFaqs(ffwyFaqsVo));
    }

    /**
     * 删除常见问题
     */
    @ApiOperation("删除常见问题")
    //@PreAuthorize("@ss.hasPermi('system:faqs:remove')")
    @Log(title = "删除常见问题", businessType = BusinessType.DELETE)
	@DeleteMapping("{faqId}")
    public AjaxResult deletefaqsid(@PathVariable Integer faqId)
    {
        return toAjax(ffwyFaqsService.deleteFfwyFaqsById(faqId));
    }
}
