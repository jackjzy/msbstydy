package com.ruoyi.web.controller.myhoust;

import java.util.List;

import com.ruoyi.system.domain.myhoust.FfwyMyhousts;
import com.ruoyi.system.domain.order.FfwyOrderClient;
import com.ruoyi.system.domain.vo.FfwyWorkplanVo;
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
import com.ruoyi.system.service.IFfwyMyhoustsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
@Api("订单详情")
@RestController
@RequestMapping("/back/myhousts")
public class FfwyMyhoustsController extends BaseController
{
    @Autowired
    private IFfwyMyhoustsService ffwyMyhoustsService;

    /**
     * 查询【请填写功能名称】列表
     */
    @ApiOperation("详情")
    //@PreAuthorize("@ss.hasPermi('system:myhousts:list')")
    @GetMapping("/list")
    public TableDataInfo list(FfwyOrderClient ffwyOrderClient)
    {
        startPage();
        List<FfwyWorkplanVo> list = ffwyMyhoustsService.selectFfwyMyhoustsList(ffwyOrderClient);
        return getDataTable(list);
    }

}
