package com.ruoyi.app.controller.myself;

import com.ruoyi.back.constant.Comment;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.service.IFfwyProductService;
import com.ruoyi.system.service.IFfwyShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "我的收藏")
@RestController
@RequestMapping("/app/store")
public class StoreController extends BaseController {
    @Autowired
    private IFfwyVideoService iFfwyVideoService;
    @Autowired
    private IFfwyShopService iFfwyShopService;
    @Autowired
    private IFfwyProductService  iFfwyProductService;
    @ApiOperation("收藏视频列表")
    @GetMapping("/video")
    private TableDataInfo video(HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoByStroe(userId);
        return getDataTable(ffwyVideos);
    }

    @ApiOperation("店铺收藏列表")
    @GetMapping("/shop")
    private TableDataInfo shop(HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        List<FfwyShop> ffwyShops = iFfwyShopService.selectStroeShop(userId);
        return getDataTable(ffwyShops);
    }

    @ApiOperation("商品收藏列表")
    @GetMapping("/product")
    private TableDataInfo product(HttpServletRequest request){
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        List<FfwyProduct> ffwyProducts = iFfwyProductService.selectStoreProductList(userId);

        return getDataTable(ffwyProducts);
    }



}
