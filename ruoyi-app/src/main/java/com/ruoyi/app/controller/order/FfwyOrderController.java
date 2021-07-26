package com.ruoyi.app.controller.order;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.PayType;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.vo.OrderVo;
import com.ruoyi.system.domain.vo.Orders;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.product.FfwyProductSkuMapper;
import com.ruoyi.system.service.IFfwyOrderDetailsService;
import com.ruoyi.system.service.IFfwyOrderService;
import com.ruoyi.system.service.IFfwyProductSkuService;
import com.ruoyi.system.service.impl.FfwyOrderDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/app/orders")
public class FfwyOrderController extends BaseController {
    @Autowired
    private IFfwyOrderService ffwyOrderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IFfwyProductSkuService ffwyProductSkuService;
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;
    @Autowired
    private FfwyProductSkuMapper ffwyProductSkuMapper;


    //生成结算号
    @ApiOperation("生成结算号")
    @GetMapping("/auth/trade")
    public AjaxResult trade(HttpServletRequest request) {
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        String tradeNo = UUID.randomUUID().toString();
        //保存结算号的Key
        StringBuilder key = new StringBuilder("user:");
        key.append(userId).append(":tradeNo");
        //保存此tradeNo 到缓存中
        redisTemplate.opsForValue().set(key.toString(), tradeNo);
        return AjaxResult.success(tradeNo);
    }


    //提交订单
    @ApiOperation("提交订单")
    @PostMapping("/auth/submitOrder")
    public AjaxResult submitOrder(@RequestBody FfwyOrder orderInfo, HttpServletRequest request) {
//        //1:防止二次提交
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
////        //保存结算号的Key
        StringBuilder key = new StringBuilder("user:");
        key.append(userId).append(":tradeNo");
        String tradeNo1 = (String) redisTemplate.opsForValue().get(key.toString());
        if (StringUtils.isEmpty(tradeNo1)) {
            return AjaxResult.error("不要重复提交订单");
        } else {
            if (!tradeNo1.equalsIgnoreCase(orderInfo.getTradeNo())) {
                return AjaxResult.error("不要重复提交订单");
            }
        }
        List<FfwyOrderDetails> orderDetailList = orderInfo.getOrderDetailList();
        for (FfwyOrderDetails orderDetail : orderDetailList) {
            //每一款商品都验证库存
            FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(orderDetail.getSkuId());
            //已用库存
            FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
            ffwyOrderDetails.setSkuId(orderDetail.getSkuId());
            FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
            //剩余库存
            if (orderDetails == null) {
                long num = ffwyProductSku.getStock();
                if (num == 0) {
                    //无货
                    return AjaxResult.error("库存不足", num);
                }
            } else {
                if (orderDetails.getNum() == null) {
                    long num = ffwyProductSku.getStock();
                    if (num==0){
                        //无货
                        return AjaxResult.error("库存不足", num);
                    }
                } else {
                    long num = ffwyProductSku.getStock() - orderDetails.getNum();
                    Integer number = orderDetail.getNumber();
                    if (num < number) {
                        //无货
                        return AjaxResult.error("库存不足", orderDetail.getProductSkuName());
                    }
                }
            }
        }
        //代码到此处的时候 证明结算号是正确的 防止 二次提交  应该在此处删除结算号
        //  redisTemplate.delete(key.toString());
        //2：保存订单 返回值 订单ID
        orderInfo.setUserId(userId);
        AjaxResult ajaxResult = ffwyOrderService.saveOrderInfo(orderInfo);
        ajaxResult.put("type", PayType.PAY_PRODUCT.getCode());
        return ajaxResult;
    }

}
