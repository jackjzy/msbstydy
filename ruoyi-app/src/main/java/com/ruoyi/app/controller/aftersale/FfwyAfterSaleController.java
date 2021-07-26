package com.ruoyi.app.controller.aftersale;

import java.util.*;

import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.aftersale.*;
import com.ruoyi.system.domain.combomeal.FfwyCombomealPhoto;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.vo.FfwyAftersaletypeVo;
import com.ruoyi.system.domain.vo.FfwyorderdespeVo;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 售后Controller
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
@Api(tags ="售后")
@RestController
@RequestMapping("/app/sale")
public class FfwyAfterSaleController extends BaseController
{
    @Autowired
    private IFfwyAfterSaleService ffwyAfterSaleService;

    @Autowired
    private IFfwyOrderDetailsService ffwyOrderDetailsService;

    @Autowired
    private IFfwyAfterCauseService ffwyAfterCauseService;

    @Autowired
    private IFfwyAfterTypeService ffwyAfterTypeService;

    @Autowired
    private IFfwyAfterAddrService ffwyAfterAddrService;

    @Autowired
    private IFfwyAfterPhotoService ffwyAfterPhotoService;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;


    /**
     * 查询退货退款列表
     */
    @ApiOperation("查询退货退款列表")
    //@PreAuthorize("@ss.hasPermi('system:sale:list')")
    @GetMapping("/list")
    public TableDataInfo list(HttpServletRequest request)
    {
        startPage();
        Long userId = JWTUtils.getId(request.getHeader("token"));
        List<FfwyorderdespeVo> list = ffwyOrderDetailsService.selectFfwyorderdetailsByseales(userId);
        return getDataTable(list);
    }

    /**
     * 查询退货退款详情
     */
    @ApiOperation("查询售后详情")
    //@PreAuthorize("@ss.hasPermi('system:sale:list')")
    @GetMapping("/findBydetails/{afterSaleid}")
    public AjaxResult findBydetails(@PathVariable Integer afterSaleid)
    {

        Map map = ffwyAfterSaleService.selectFfwyOrderDetailsByorderDetailsId(afterSaleid);
        return AjaxResult.success(map);
    }

    /**
     * 查询退货原因
     */
    @ApiOperation("查询退货原因")
    //@PreAuthorize("@ss.hasPermi('system:sale:list')")
    @GetMapping("/findBycause")
    public TableDataInfo findBycause()
    {
        startPage();
        List<FfwyAfterCause> list = ffwyAfterCauseService.selectFfwyAfterCauseLists();
        return getDataTable(list);
    }

    /**
     * 查询退货类型
     */
    @ApiOperation("查询退货类型")
    //@PreAuthorize("@ss.hasPermi('system:sale:list')")
    @GetMapping("/findBytype")
    public TableDataInfo findBytype()
    {
        startPage();
        List<FfwyAfterType> list = ffwyAfterTypeService.selectFfwyAfterTypeLists();
        return getDataTable(list);
    }

    /**
     * 查询退货物流信息
     */
    @ApiOperation("查询退货物流信息")
    //@PreAuthorize("@ss.hasPermi('system:sale:list')")
    @GetMapping("/findByaddr")
    public TableDataInfo findByaddr(@RequestBody FfwyAfterAddr ffwyAfterAddr)
    {
        startPage();
        List<FfwyAfterAddr> list = ffwyAfterAddrService.selectFfwyAfterAddrList(ffwyAfterAddr);
        return getDataTable(list);
    }

    /**
     * 新增退货物流
     */
    @ApiOperation("新增退货物流")
    //@PreAuthorize("@ss.hasPermi('system:sale:add')")
    //@Log(title = "售后", businessType = BusinessType.INSERT)
    @PostMapping("/insertaddr")
    public AjaxResult insertaddr(@RequestBody FfwyAfterAddr ffwyAfterAddr)
    {
        return toAjax(ffwyAfterAddrService.insertFfwyAfterAddr(ffwyAfterAddr));
    }



    /**
     * 获取售后详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sale:query')")
    @GetMapping(value = "/{afterSaleid}")
    public AjaxResult getInfo(@PathVariable("afterSaleid") Long afterSaleid)
    {
        return AjaxResult.success(ffwyAfterSaleService.selectFfwyAfterSaleById(afterSaleid));
    }

    @ApiOperation("批量上传售后凭证图片")
    @PostMapping("/addafterPhotos")
    public Map<String,Object> addafterPhotos(@RequestParam MultipartFile[] files)
    {
        Map<String,Object> map=new HashMap<>(1);
        List<String> path= new ArrayList<>();
        if (files!=null && files.length>0){
            for (int i=0;i<files.length;i++){

                String path1 = null;
                try {
                    path1 = CosUtil.CosUpload(files[i]);
                    path.add(prefixUrl+path1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        map.put("data",path);
        return map;
    }


    /**
     * 新增售后
     */
    @ApiOperation("新增售后")
    //@PreAuthorize("@ss.hasPermi('system:sale:add')")
   // @Log(title = "售后", businessType = BusinessType.INSERT)
    @PostMapping("/insertaftersale")
    public AjaxResult add(HttpServletRequest request,@RequestBody FfwyAfterSale ffwyAfterSale)
    {
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        ffwyAfterSale.setUserId(userId);
        ffwyAfterSaleService.insertFfwyAfterSale(ffwyAfterSale);
        Long afterSaleid = ffwyAfterSale.getAfterSaleid();

        return AjaxResult.success(afterSaleid);
    }

    /**
     * 添加快递信息
     */
    @ApiOperation("添加快递信息")
    @PostMapping("/addkd")
    public AjaxResult addkd(@RequestBody FfwyAfterSale ffwyAfterSale)
    {
        return toAjax(ffwyAfterSaleService.updateFfwyAfterSale(ffwyAfterSale));
    }




    /**
     * 取消售后
     */
    @ApiOperation("取消售后")
    @GetMapping("/updateafter/{orderDetailsId}")
    public AjaxResult edit(@PathVariable Integer orderDetailsId)
    {
        return toAjax(ffwyAfterSaleService.updateFfwyAfterSaleBysuatus(orderDetailsId));
    }

    /**
     * 删除售后
     */
    @Log(title = "删除售后", businessType = BusinessType.DELETE)
	@DeleteMapping("/deletesh")
    public AjaxResult remove( Long afterSaleid)
    {
        return toAjax(ffwyAfterSaleService.deleteFfwyAfterSaleById(afterSaleid));
    }
}
