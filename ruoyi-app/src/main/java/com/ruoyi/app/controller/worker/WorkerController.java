package com.ruoyi.app.controller.worker;

import com.ruoyi.common.config.MyRabbitConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomeal.FfwyCombomealPhoto;
import com.ruoyi.system.domain.combomealorders.*;
import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhaseMsg;
import com.ruoyi.system.service.*;
import io.jsonwebtoken.Jwt;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/worker")
public class WorkerController extends BaseController {

    @Autowired
    IFfwyOrderCombomealService iFfwyOrderCombomealService;
    @Autowired
    private IFfwyCombomealPhotoService iFfwyCombomealPhotoService;
    @Autowired
    IFfwyUserService iFfwyUserService;

    @Autowired
    IFfwyMaterialService iFfwyMaterialService;

    @Autowired
    IFfwyPhaseService iFfwyPhaseService;

    @Autowired
    IFfwyPhaseMsgService iFfwyPhaseMsgService;

    @Autowired
    IFfwyOrderCombomealPlanService iFfwyOrderCombomealPlanService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/msg")
    public AjaxResult mqMsg( String msg, Integer time){
        //rabbitTemplate.convertAndSend("X","XA","发送的消息："+12313);
//        System.err.println(msg);
//        System.err.println(time);
        CorrelationData correlationData=new CorrelationData("1");
        rabbitTemplate.convertAndSend(MyRabbitConfig.DELAYED_EXCHANGE_NAME,MyRabbitConfig.DELAYED_ROUTING_KEY,msg, message -> {
            message.getMessageProperties().setDelay(time);
            return message;
        },correlationData);
        return AjaxResult.success();
    }

    //量房订单列表
    @GetMapping("/plate")
    public TableDataInfo plate(HttpServletRequest request){
         Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyOrderCombomeal ffwyOrderCombomeal = new FfwyOrderCombomeal();
        ffwyOrderCombomeal.setWorkerId(userId);
         ffwyOrderCombomeal.setOrderType("0");
        startPage();
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);


        return getDataTable(ffwyOrderCombomeals);
    }
    //毛配房订单列表
    @GetMapping("/blank")
    public TableDataInfo blank(HttpServletRequest request){
        FfwyOrderCombomeal ffwyOrderCombomeal = new FfwyOrderCombomeal();
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        ffwyOrderCombomeal.setWorkerId(userId);
        ffwyOrderCombomeal.setOrderType("1");
        startPage();
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);
        return getDataTable(ffwyOrderCombomeals);

    }

    //简装房订单列表
    @GetMapping("/deluxe")
    public TableDataInfo deluxe(HttpServletRequest request){
        FfwyOrderCombomeal ffwyOrderCombomeal = new FfwyOrderCombomeal();
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        ffwyOrderCombomeal.setWorkerId(userId);
        ffwyOrderCombomeal.setOrderType("2");
        startPage();
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);
        return getDataTable(ffwyOrderCombomeals);
    }

    //订单搜索结果
   @GetMapping("/search")
    public TableDataInfo searchOrder(String searchStr, HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyOrderCombomeal ffwyOrderCombomeal = new FfwyOrderCombomeal();
        ffwyOrderCombomeal.setWorkerId(userId);
        ffwyOrderCombomeal.setSearchStr(searchStr);
        List<FfwyOrderCombomeal> orderCombomeals=new ArrayList<>();
       startPage();
       List<FfwyOrderCombomeal> list = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);
//       if (null!=list){
//           list.forEach(ffwyOrderCombomeal1 -> {
//               if ("0".equals(ffwyOrderCombomeal1.getOrderType())){
//                   FfwyOrderCombomeal combomeal = new FfwyOrderCombomeal();
//                   combomeal.setOrderId(ffwyOrderCombomeal1.getOrderId());
//                   combomeal.setCombomealCover(ffwyOrderCombomeal1.getCombomealCover());
//                   combomeal.setCombomealName(ffwyOrderCombomeal1.getCombomealName());
//                   combomeal.setOrderAddr(ffwyOrderCombomeal1.getOrderAddr());
//                   combomeal.setOrderPhone(ffwyOrderCombomeal1.getOrderPhone());
//                   combomeal.setOrderStatus(ffwyOrderCombomeal1.getOrderStatus());
//                   orderCombomeals.add(combomeal);
//
//               }
//               orderCombomeals.add(ffwyOrderCombomeal1);
//           });
//
//       }



      // return AjaxResult.success(orderCombomeals);

       return getDataTable(list);
    }

    //添加量房订单信息
    @PostMapping("/updateOrder")
    public AjaxResult updateOrder(@RequestBody Materials materials){

        return toAjax(iFfwyOrderCombomealService.insertFfwyOrder( materials));
    }



    //查看订单详情
    @GetMapping("/checkOrder")
    public AjaxResult orderOne(Long orderId){
        FfwyPhase ffwyPhase = new FfwyPhase();
        FfwyMaterial ffwyMaterial = new FfwyMaterial();
        FfwyOrderCombomeal ffwyOrderCombomeal = iFfwyOrderCombomealService.selectFfwyOrderCombomealById(orderId);
        FfwyCombomealPhoto ffwyCombomealPhoto = new FfwyCombomealPhoto();
        ffwyCombomealPhoto.setPhotoType("0");
        ffwyCombomealPhoto.setCombomealId(ffwyOrderCombomeal.getCombomealId());
        //查询套餐轮播图
        List<FfwyCombomealPhoto> ffwyCombomealPhotos = iFfwyCombomealPhotoService.selectFfwyCombomealPhotoList(ffwyCombomealPhoto);

        ffwyMaterial.setOrderCombomealId(ffwyOrderCombomeal.getOrderId());
        ffwyPhase.setOrderId(ffwyOrderCombomeal.getOrderId());
        //查询当前订单的节点
        List<FfwyPhase> ffwyPhases = iFfwyPhaseService.selectFfwParent(ffwyPhase);
        //查询当前订单的装修材料
        List<FfwyMaterial> ffwyMaterials = iFfwyMaterialService.selectFfwyMaterialList(ffwyMaterial);
        FfwyPhaseMsg ffwyPhaseMsg = new FfwyPhaseMsg();
        ffwyPhaseMsg.setOrderCombomealId(orderId);
        List<FfwyPhaseMsg> ffwyPhaseMsgs = iFfwyPhaseMsgService.selectFfwyPhaseMsgAndPhoto(ffwyPhaseMsg);
        Map<String,Object> map=new HashMap<>();
        map.put("ffwyCombomealPhotos",ffwyCombomealPhotos);
        map.put("CombomealOrder",ffwyOrderCombomeal);
        map.put("Materials",ffwyMaterials);
        map.put("Phase",ffwyPhases);
        map.put("FfwyPhaseMsgs",ffwyPhaseMsgs);

        return AjaxResult.success(map);
    }

    //修改子订单状态
    @PutMapping("/updateStatus")
    public AjaxResult updateStatus(Long phaseId,HttpServletRequest request){
        System.err.println(phaseId);
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyPhase ffwyPhase = iFfwyPhaseService.selectFfwyPhaseById(phaseId);
        ffwyPhase.setPhaseId(phaseId);
        return AjaxResult.success(iFfwyPhaseService.updateFfwyPhases(ffwyPhase,userId,"1"));
    }

    //上传节点图片和节点信息
    @PostMapping("/uploadPhase")
    public AjaxResult uploadPhase(FfwyPhaseMsg ffwyPhaseMsg, MultipartFile[] files){


        return AjaxResult.success(iFfwyPhaseMsgService.insertFfwyPhaseMsgAndPhoto(ffwyPhaseMsg,files));
    }

}
