package com.ruoyi.app.controller.myself;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ruoyi.back.domain.FfwyAudit;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyAuditService;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.service.IFfwyConsigneeAddrService;
import com.ruoyi.system.service.IFfwyProductService;
import com.ruoyi.system.service.IFfwyShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "成为商家")
@RestController
@RequestMapping("/app/chage")
public class FfwyAuditAppController extends BaseController {

    @Autowired
    private IFfwyAuditService ffwyAuditService;

    /**
     * 在线申请信息
     */
    @ApiOperation("在线申请")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_consignee_addr:add')")
    @Log(title = "在线申请信息", businessType = BusinessType.INSERT)
    @PostMapping("/shop")
    public AjaxResult add(@RequestBody FfwyAudit ffwyAudit,HttpServletRequest request) {


        System.err.println(ffwyAudit);


        ffwyAudit.setUserId(JWTUtils.getId(request.getHeader(JWTUtils.TOKRN)));

        //System.err.println(ffwyAudit);
//        for (MultipartFile file : files) {
//            System.err.println("name:"+file.getOriginalFilename());
//            System.err.println("是否有值："+!file.isEmpty());
//        }
//        FfwyAudit ffwyAudit = ffwyAuditService.selectFfwyAuditById((long) 1);
//        ffwyAudit.setUserId((long) 95);
        Map<String, Object> dataMap = ffwyAuditService.insertFfwyAudit(ffwyAudit);
        if ("1".equals(dataMap.get("code"))) {
            return AjaxResult.success(dataMap.get("data"));
        } else {
            return AjaxResult.error((String) dataMap.get("resmsg"));
        }
        //return AjaxResult.success();
    }

    /**
     * 查询申请信息
     */
    @ApiOperation("查询申请信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:query')")
    @GetMapping(value = "getInfo")
    public AjaxResult getInfo(HttpServletRequest request) {
        Long id = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return AjaxResult.success(ffwyAuditService.selectFfwyAuditByUserId(id));
    }


}
