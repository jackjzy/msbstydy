package com.ruoyi.web.controller.area;

import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.FfwyArea;
import com.ruoyi.system.domain.combomeal.FfwyCombomeal;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.service.IFfwyAreaService;
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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 省市县Controller
 * 
 * @author ruoyi
 * @date 2021-06-30
 */
@Api(tags = "省市县数据")
@RestController
@RequestMapping("/area/area")
public class FfwyAreaController extends BaseController
{
    @Autowired
    private IFfwyAreaService ffwyAreaService;

    /**
     * 查询省市县列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FfwyArea ffwyArea)
    {
        startPage();
        List<FfwyArea> list = ffwyAreaService.selectFfwyAreaList(ffwyArea);
        return getDataTable(list);
    }

    /**
     * 导出省市县列表
     */
    @Log(title = "省市县", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FfwyArea ffwyArea)
    {
        List<FfwyArea> list = ffwyAreaService.selectFfwyAreaList(ffwyArea);
        ExcelUtil<FfwyArea> util = new ExcelUtil<FfwyArea>(FfwyArea.class);
        return util.exportExcel(list, "area");
    }

    /**
     * 获取省市县详细信息
     */
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") String code)
    {



        return AjaxResult.success(ffwyAreaService.selectFfwyAreaById(code));
    }
    /**
     * 获取下一级内容
     * @param ffwyAreaList
     * @param code
     * @return
     */
    public List<FfwyArea> getChildren(List<FfwyArea> ffwyAreaList,String code){
        List<FfwyArea>  collect = ffwyAreaList.stream().filter(ffwyArea -> ffwyArea.getPerentCode().equals(code)).collect(Collectors.toList());
        collect.stream().forEach(ffwyAreaThree -> ffwyAreaThree.setFfwyAreaList(ffwyAreaList.stream().filter
                (ffwyArea -> ffwyArea.getPerentCode().equals(ffwyAreaThree.getCode())).collect(Collectors.toList())));
        return collect;
    }

}
