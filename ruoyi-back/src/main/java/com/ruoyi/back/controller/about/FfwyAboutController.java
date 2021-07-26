package com.ruoyi.back.controller.about;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.hutool.core.io.file.FileReader;
import com.ruoyi.back.domain.FfwyAbout;
import com.ruoyi.back.service.IFfwyAboutService;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.IFfwyProtocolService;
import org.apache.poi.hwpf.extractor.WordExtractor;

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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
@RestController
@RequestMapping("/back/about")
public class FfwyAboutController extends BaseController
{
    @Autowired
    private IFfwyAboutService ffwyAboutService;

    @Autowired
    private IFfwyProtocolService iFfwyProtocolService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:about:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyAbout ffwyAbout)
    {
        startPage();
        List<FfwyAbout> list = ffwyAboutService.selectFfwyAboutList(ffwyAbout);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:about:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyAbout ffwyAbout)
    {
        List<FfwyAbout> list = ffwyAboutService.selectFfwyAboutList(ffwyAbout);
        ExcelUtil<FfwyAbout> util = new ExcelUtil<FfwyAbout>(FfwyAbout.class);
        return util.exportExcel(list, "about");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:about:query')")
    @GetMapping(value = "/{appId}")
    public AjaxResult getInfo(@PathVariable("appId") Long appId)
    {
        return AjaxResult.success(ffwyAboutService.selectFfwyAboutById(appId));
    }

    @GetMapping("/aboutAs")
    public AjaxResult getAbout()
    {
        return AjaxResult.success(ffwyAboutService.selectFfwyAboutById((long) 1));
    }
    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:about:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FfwyAbout ffwyAbout)
    {
        return toAjax(ffwyAboutService.insertFfwyAbout(ffwyAbout));
    }

    /**
     * 修改【修改关于我们】
     */
    //@PreAuthorize("@ss.hasPermi('system:about:edit')")
    @Log(title = "【修改关于我们】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FfwyAbout ffwyAbout)
    {

        return toAjax(ffwyAboutService.updateFfwyAbout(ffwyAbout));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:about:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appIds}")
    public AjaxResult remove(@PathVariable Long[] appIds)
    {
        return toAjax(ffwyAboutService.deleteFfwyAboutByIds(appIds));
    }



    @PostMapping("/uploadss")
    //使用MultipartFile 接收文件流
    public void importWord(MultipartFile file) throws IOException {
        iFfwyProtocolService.chageProtocol(file);

    }

}
