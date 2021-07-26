package com.ruoyi.web.controller.combomeal;

import java.io.IOException;
import java.util.*;

import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.FfwyWorkplan;
import com.ruoyi.system.domain.combomeal.FfwyCombomealComment;
import com.ruoyi.system.domain.combomeal.FfwyCombomealPhoto;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomealPlan;
import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import com.ruoyi.system.domain.order.FfwyOrderClient;
import com.ruoyi.system.domain.order.FfwyOrderPlane;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "套餐项目管理")
@RestController
@RequestMapping("/back/combomeals")
public class FfwyCombomealprojectController extends BaseController
{
    @Autowired
    private IFfwyCombomealService ffwyCombomealService;

    @Autowired
    private IFfwyOrderMoneyService ffwyOrderMoneyService;

    @Autowired
    private IFfwyOrderCombomealService ffwyOrderCombomealService;

    @Autowired
    private IFfwyPhaseService ffwyPhaseService;

    @Autowired
    private IFfwyOrderClientService ffwyOrderClientService;

    @Autowired
    private IFfwyWorkplanService ffwyWorkplanService;

    @Autowired
    private IFfwyMyhoustsService ffwyMyhoustsService;

    @Autowired
    private IFfwyOrderPlaneService ffwyOrderPlaneService;

    @Autowired
    private IFfwyCombomealCommentService ffwyCombomealCommentService;

    @Autowired
    private IFfwyCombomealPhotoService ffwyCombomealPhotoService;

    @Autowired
    private IFfwyMaterialService iFfwyMaterialService;

    @Autowired
    IFfwyOrderCombomealPlanService iFfwyOrderCombomealPlanService;



    @ApiOperation("查询所有订单PC")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_order:list')")
    @GetMapping("/listPC")
    public TableDataInfo listPC(FfwyOrderCombomealVo ffwyOrderCombomealVo)
    {
        startPage();
        List<FfwyOrderCombomeal> list = ffwyOrderCombomealService.selectFfwyOrderCombomealListPC(ffwyOrderCombomealVo);
        return getDataTable(list);
    }
    @GetMapping("/queryOne/{orderId}")
    public AjaxResult queone(@PathVariable Long orderId){
        Map<String,Object> map =new HashMap<>();
        FfwyOrderCombomeal ffwyOrderCombomeal = ffwyOrderCombomealService.selectFfwyOrderCombomealById(orderId);
        FfwyMaterial ffwyMaterial = new FfwyMaterial();
        FfwyProductSku sku = new FfwyProductSku();
        ffwyMaterial.setOrderCombomealId(orderId);
        //查询软装列表
        ffwyMaterial.setTagId(2);
        List<FfwyMaterial> soft = iFfwyMaterialService.selectProductlList(ffwyMaterial);
        //查询硬装列表
        ffwyMaterial.setTagId(1);
        List<FfwyMaterial> commodity = iFfwyMaterialService.selectProductlList(ffwyMaterial);

        //智能家居列表
        ffwyMaterial.setTagId(3);
        List<FfwyMaterial> smart = iFfwyMaterialService.selectProductlList(ffwyMaterial);

        //查询生活用品列表
        ffwyMaterial.setTagId(5);
        List<FfwyMaterial> wiring = iFfwyMaterialService.selectProductlList(ffwyMaterial);

        //查询家电产品列表
        ffwyMaterial.setTagId(4);
        List<FfwyMaterial> householdAppliances = iFfwyMaterialService.selectProductlList(ffwyMaterial);
        //查询空间布置列表
        ffwyMaterial.setTagId(6);
        List<FfwyMaterial> spatialArranement = iFfwyMaterialService.selectProductlList(ffwyMaterial);
        map.put("ffwyOrderCombomeal",ffwyOrderCombomeal);
        map.put("soft",soft);//软装
        map.put("commodity",commodity);//硬装
        map.put("smart",smart);//智能家居
        map.put("wiring",wiring);//生活日用
        map.put("householdAppliances",householdAppliances);//家电产品
        map.put("spatialArranement",spatialArranement);//空间布置
        return AjaxResult.success(map);
    }

    //量房结果
    @GetMapping("/checkOrder")
    public AjaxResult orderOne(Long orderId){
        FfwyPhase ffwyPhase = new FfwyPhase();
        FfwyMaterial ffwyMaterial = new FfwyMaterial();
        FfwyOrderCombomeal ffwyOrderCombomeal = ffwyOrderCombomealService.selectFfwyOrderCombomealById(orderId);
        ffwyMaterial.setOrderCombomealId(ffwyOrderCombomeal.getOrderId());
        ffwyPhase.setOrderId(ffwyOrderCombomeal.getOrderId());
        //查询当前订单的节点
        List<FfwyPhase> ffwyPhases = ffwyPhaseService.selectFfwParent(ffwyPhase);
        //查询当前订单的装修材料
        List<FfwyMaterial> ffwyMaterials = iFfwyMaterialService.selectFfwyMaterialList(ffwyMaterial);

        FfwyOrderCombomealPlan ffwyOrderCombomealPlan = new FfwyOrderCombomealPlan();
        ffwyOrderCombomealPlan.setOrderId(orderId);
        List<FfwyOrderCombomealPlan> combomealPlans = iFfwyOrderCombomealPlanService.selectFfwyOrderCombomealPlanList(ffwyOrderCombomealPlan);
        Map<String,Object> map=new HashMap<>();
        map.put("CombomealOrder",ffwyOrderCombomeal);
        map.put("Materials",ffwyMaterials);
        map.put("Phase",ffwyPhases);
        map.put("photos",combomealPlans);

        return AjaxResult.success(map);
    }

    @ApiOperation("查看量房订单")
    // @PreAuthorize("@ss.hasPermi('system:myhousts:list')")
    @GetMapping
    public TableDataInfo findBylimit()
    {
        startPage();
        List<FfwyOrderCombomeal> list = ffwyOrderCombomealService.selectFfwyOrderCombomealByorderType();
        return getDataTable(list);
    }


    @ApiOperation("分配工长PC")
    //@PreAuthorize("@ss.hasPermi('system:workplan:add')")
    @Log(title = "【分配工长PC】", businessType = BusinessType.INSERT)
    @PostMapping("/allocationPC")
    public AjaxResult addPC(@RequestBody FfwyOrderCombomeal ffwyOrderCombomeal)
    {

      //  System.err.println(ffwyOrderCombomeal);
        return toAjax(ffwyOrderCombomealService.allocationWorker(ffwyOrderCombomeal));
//        return null;
    }

    @PostMapping("test/upload")
    public AjaxResult testUplad(MultipartFile file) throws IOException {
        //String s = CosUtil.CosUpload(file);
        String s1 = CosUtil.CosUploadtest(file);
        return AjaxResult.success(s1);
    }


    /**
     * 创建订单
     */
    @ApiOperation("创建订单")
    //@PreAuthorize("@ss.hasPermi('system:client:add')")
    //@Log(title = "【创建订单】", businessType = BusinessType.INSERT)
    @PostMapping("/insertorder")
    public AjaxResult add(@RequestParam Long orderId,
                          @RequestParam String designerName,
                          @RequestParam("photo") MultipartFile photo,
                          @RequestParam("phtotos") List<MultipartFile> phtotos,
                          @RequestParam("phaseStr")String phaseStr,
                          @RequestParam("doc") MultipartFile doc
    )
    {
        System.err.println("orderid:"+orderId);
        System.err.println("designerName:"+designerName);
        System.err.println("设计师图片:"+!photo.isEmpty());
        System.err.println("设计师图片名称:"+photo.getOriginalFilename());
        System.err.println("设计风格图："+!phtotos.isEmpty());
        System.err.println("比例："+phaseStr);
        System.err.println("合同"+!doc.isEmpty());
        System.err.println(phtotos.size());
        phtotos.forEach(file -> {
            String originalFilename = file.getOriginalFilename();
            System.err.println(originalFilename);
        });
        ffwyOrderCombomealService.createOrder(orderId, designerName, photo, phtotos,phaseStr,doc);
        return null;
    }


    @ApiOperation("添加设计图")
    @PostMapping("/addDesignerPhotos")
    public AjaxResult addDesignerPhotos(@RequestParam Long orderId,@RequestParam("files") List<MultipartFile> files){


        return toAjax(ffwyOrderCombomealService.addDesignerPhotos(orderId, files));

        //return null;
    }
    /**
     * 订单详情
     */
    @ApiOperation("查询最终订单")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/findorder")
    public TableDataInfo findAllCombomeall(FfwyOrderPlane ffwyOrderPlane)
    {
        startPage();
        List<FfwyOrderPlaneVo> list = ffwyOrderPlaneService.selectFfwyOrderPlaneList(ffwyOrderPlane);
        return getDataTable(list);
    }


    /**
     * 删除该阶段
     */
    @ApiOperation("删除该阶段")
    //@PreAuthorize("@ss.hasPermi('system:phase:remove')")
    @Log(title = "【删除该阶段】", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteByphaseid/{phaseIds}")
    public AjaxResult removeid(@PathVariable Long[] phaseIds)
    {
        return toAjax(ffwyPhaseService.deleteFfwyPhaseByIds(phaseIds));
    }

    /**
     * 查询评论
     */
    @ApiOperation("查询评论")
    //@PreAuthorize("@ss.hasPermi('system:comment:list')")
    @GetMapping("/lookcomment")
    public TableDataInfo list(FfwyCombomealComment ffwyCombomealComment)
    {
        startPage();
        List list = ffwyCombomealCommentService.selectFfwyCombomealCommentList(ffwyCombomealComment);
        return getDataTable(list);
    }

    /**
     * 删除评论
     */
    @ApiOperation("删除评论")
    //@PreAuthorize("@ss.hasPermi('system:comment:remove')")
    @Log(title = "【删除评论】", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteBycommenid/{commentIds}")
    public AjaxResult deletecomment(@PathVariable Long[] commentIds)
    {
        return toAjax(ffwyCombomealCommentService.deleteFfwyCombomealCommentByIds(commentIds));
    }

    /**
     * 查询套餐图片列表
     */
    @ApiOperation("查询套餐图片列表")
    //@PreAuthorize("@ss.hasPermi('system:photo:list')")
    @GetMapping("/comblist")
    public TableDataInfo list(FfwyCombomealPhoto ffwyCombomealPhoto)
    {
        startPage();
        List<FfwyCombomealPhoto> list = ffwyCombomealPhotoService.selectFfwyCombomealPhotoList(ffwyCombomealPhoto);
        return getDataTable(list);
    }

    /**
     * 获取【图片】详细信息
     */
    @ApiOperation("获取图片详细信息")
    //@PreAuthorize("@ss.hasPermi('system:photo:query')")
    @GetMapping("comb/{photoId}")
    public AjaxResult getInfo(@PathVariable("photoId") Long photoId)
    {
        return AjaxResult.success(ffwyCombomealPhotoService.selectFfwyCombomealPhotoById(photoId));
    }

    /**
     * 新增套餐图片
     */
    @ApiOperation("新增套餐图片")
    //@PreAuthorize("@ss.hasPermi('system:photo:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/insertcomb")
    public AjaxResult add(@RequestBody FfwyCombomealPhoto ffwyCombomealPhoto)
    {
        return toAjax(ffwyCombomealPhotoService.insertFfwyCombomealPhoto(ffwyCombomealPhoto));
    }

    @ApiOperation("添加套餐轮播图")
    @PostMapping(value = "/addcombPhotos",headers = "content-type=multipart/form-data")
    public AjaxResult addPhotos(MultipartFile[] files, String title)
    {   List<FfwyCombomealPhoto> list =new ArrayList<>();
        if (files!=null){
            for (int i=0;i<files.length;i++){
                /*
                 * 上传腾讯云返回路径
                 *
                 * */
                String path=null;
                try {
                    path = CosUtil.CosUpload(files[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //创建新图片对象
                list.add(new FfwyCombomealPhoto(
                        (long) 5,
                        title,
                        path,
                        new Date().getTime(),
                        new Date(),
                        "1"));
            }
        }
        return toAjax(ffwyCombomealPhotoService.insertFfwyCombomealPhotos(list));
    }

    /**
     * 修改套餐图片
     */
    @ApiOperation("修改套餐图片")
    //@PreAuthorize("@ss.hasPermi('system:photo:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/updatecomb")
    public AjaxResult edit(@RequestBody FfwyCombomealPhoto ffwyCombomealPhoto)
    {
        return toAjax(ffwyCombomealPhotoService.updateFfwyCombomealPhoto(ffwyCombomealPhoto));
    }

    /**
     * 删除套餐图片
     */
    @ApiOperation("删除套餐图片")
    // @PreAuthorize("@ss.hasPermi('system:photo:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/comb/{photoIds}")
    public AjaxResult remove(@PathVariable Long[] photoIds)
    {
        return toAjax(ffwyCombomealPhotoService.deleteFfwyCombomealPhotoByIds(photoIds));
    }
}
