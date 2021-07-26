package com.ruoyi.web.controller.shop;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.service.IFfwyConsigneeAddrService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/back/shopAddr")
public class FfwyShopAddrController extends BaseController {
    @Autowired
    private IFfwyConsigneeAddrService iFfwyConsigneeAddrService;

    @PostMapping("/delete")
    public AjaxResult deleteShopAddr(@RequestBody FfwyConsigneeAddr ffwyConsigneeAddr){
        System.out.println(ffwyConsigneeAddr);

        return AjaxResult.success(iFfwyConsigneeAddrService.deleteShopAddrById(ffwyConsigneeAddr));
    }

    @PostMapping
    public AjaxResult addShopAddr(@RequestBody FfwyConsigneeAddr ffwyConsigneeAddr){

        System.err.println(ffwyConsigneeAddr);
        return AjaxResult.success(iFfwyConsigneeAddrService.insertShopAddr(ffwyConsigneeAddr));
    }
}
