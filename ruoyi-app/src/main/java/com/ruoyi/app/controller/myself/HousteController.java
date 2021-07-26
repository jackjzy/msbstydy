package com.ruoyi.app.controller.myself;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.domain.combomeal.FfwyCombomealComment;
import com.ruoyi.system.domain.combomeal.FfwyCombomealPhoto;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import com.ruoyi.system.domain.combomealorders.Materials;
import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhaseMsg;
import com.ruoyi.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/houste")
public class HousteController extends BaseController
{
    @Autowired
    private IFfwyOrderCombomealService iFfwyOrderCombomealService;
    @Autowired
    private IFfwyCombomealPhotoService iFfwyCombomealPhotoService;


    @Autowired
    private IFfwyMaterialService iFfwyMaterialService;

    @Autowired
    private IFfwyCombomealCommentService iFfwyCombomealCommentService;

    @Autowired
    private IFfwyPhaseService iFfwyPhaseService;

    @Autowired
    private IFfwyPhaseMsgService iFfwyPhaseMsgService;


    //查看毛坯房订单
    @GetMapping("/blank")
    public TableDataInfo blank(HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyOrderCombomeal ffwyOrderCombomeal =new FfwyOrderCombomeal();
        ffwyOrderCombomeal.setUserId(userId);
        ffwyOrderCombomeal.setOrderType("1");
        startPage();
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);


        return getDataTable(ffwyOrderCombomeals);
    }
    //查看简装房订单
    @GetMapping("/deluxe")
    public TableDataInfo deluxe(HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyOrderCombomeal ffwyOrderCombomeal =new FfwyOrderCombomeal();
        ffwyOrderCombomeal.setUserId(userId);
        ffwyOrderCombomeal.setOrderType("2");
        startPage();
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);


        return getDataTable(ffwyOrderCombomeals);
    }
    //查看量房订单
    @GetMapping("/plate")
    public TableDataInfo plate(HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyOrderCombomeal ffwyOrderCombomeal =new FfwyOrderCombomeal();
        ffwyOrderCombomeal.setOrderType("0");
        startPage();
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);


        return getDataTable(ffwyOrderCombomeals);
    }
    //查看订单量房订单详情信息
    @GetMapping("/myOrder")
    public AjaxResult myOrder(Long orderId){
        FfwyOrderCombomeal ffwyOrderCombomeal = iFfwyOrderCombomealService.selectFfwyOrderCombomealById(orderId);

        return AjaxResult.success(ffwyOrderCombomeal);
    }

    //查看毛配房订单及主题房订单
    @GetMapping("/myOutherOrder")
    public AjaxResult myOutherOrder(Long orderId){


        FfwyOrderCombomeal ffwyOrderCombomeal = iFfwyOrderCombomealService.selectFfwyOrderCombomealById(orderId);
        FfwyCombomealPhoto ffwyCombomealPhoto = new FfwyCombomealPhoto();
        ffwyCombomealPhoto.setPhotoType("0");
        ffwyCombomealPhoto.setCombomealId(ffwyOrderCombomeal.getCombomealId());
        //查询套餐轮播图
        List<FfwyCombomealPhoto> ffwyCombomealPhotos = iFfwyCombomealPhotoService.selectFfwyCombomealPhotoList(ffwyCombomealPhoto);
        FfwyMaterial ffwyMaterial=new FfwyMaterial();
        ffwyMaterial.setOrderCombomealId(ffwyOrderCombomeal.getOrderId());
        //查询当前订单的装修材料
        List<FfwyMaterial> ffwyMaterials = iFfwyMaterialService.selectFfwyMaterialList(ffwyMaterial);
        //查询当前订单信息
        FfwyPhase ffwyPhase=new FfwyPhase();
        ffwyPhase.setOrderId(ffwyOrderCombomeal.getOrderId());
        //查询当前订单的节点
        List<FfwyPhase> ffwyPhases = iFfwyPhaseService.selectFfwParent(ffwyPhase);
        //查询节点施工图片
        FfwyPhaseMsg ffwyPhaseMsg = new FfwyPhaseMsg();
        ffwyPhaseMsg.setOrderCombomealId(orderId);
        List<FfwyPhaseMsg> ffwyPhaseMsgs = iFfwyPhaseMsgService.selectFfwyPhaseMsgAndPhoto(ffwyPhaseMsg);
        Map<String,Object> map=new HashMap<>();
        map.put("ffwyCombomealPhotos",ffwyCombomealPhotos);
        map.put("Materials",ffwyMaterials);
        map.put("Phase",ffwyPhases);
        map.put("CombomealOrder",ffwyOrderCombomeal);
        map.put("ffwyPhaseMsg",ffwyPhaseMsgs);
        return AjaxResult.success(map);
    }

    //修改套餐子订单状态接口
    @PutMapping("/chageOrderStatus")
    public AjaxResult chageOrderStatus(Long phaseId,HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyPhase ffwyPhase = iFfwyPhaseService.selectFfwyPhaseById(phaseId);

        return AjaxResult.success(iFfwyPhaseService.updateFfwyPhases(ffwyPhase,userId,"0"));
    }

    //支付接口
    @PostMapping("/pay")
    public AjaxResult pay(@RequestBody FfwyPayRecord payRecord, HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyPhase ffwyPhase = iFfwyPhaseService.selectFfwyPhaseByOrder(payRecord.getOrderNumber());

        return AjaxResult.success(iFfwyPhaseService.updateFfwyPhases(ffwyPhase,userId,"0"));
    }

    //添加评价接口
    @PostMapping("/addComment")
    public AjaxResult addComment(@RequestParam String comment,
                                 @RequestParam Integer decorationAttitude,
                                 @RequestParam Integer serviceAttitude,
                                 @RequestParam Integer decorationQuality,
                                 @RequestParam Long combomealId,
                                HttpServletRequest request
            , MultipartFile[] files){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));

        FfwyCombomealComment ffwyCombomealComment = new FfwyCombomealComment();
        ffwyCombomealComment.setComment(comment);
        ffwyCombomealComment.setDecorationAttitude(decorationAttitude);
        ffwyCombomealComment.setDecorationQuality(decorationQuality);
        ffwyCombomealComment.setServiceAttitude(serviceAttitude);
        ffwyCombomealComment.setUserId(userId);
        ffwyCombomealComment.setCombomealId(combomealId);
        return AjaxResult.success(iFfwyCombomealCommentService.insertFfwyCombomealCommentAndPhoto(ffwyCombomealComment, files));
    }

    @GetMapping("/comment")
    public TableDataInfo getComment(HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyCombomealComment ffwyCombomealComment = new FfwyCombomealComment();
        ffwyCombomealComment.setUserId(userId);
        startPage();
        List<FfwyCombomealComment> list = iFfwyCombomealCommentService.selectFfwyCombomealCommentList(ffwyCombomealComment);
        return getDataTable(list);


    }
    //查看合同
    @GetMapping("/checkPact")
    public AjaxResult checkPack(Long orderId){



        return AjaxResult.success();
    }

}
