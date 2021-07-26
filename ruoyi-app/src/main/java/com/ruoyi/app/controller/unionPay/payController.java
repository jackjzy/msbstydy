package com.ruoyi.app.controller.unionPay;

import com.ruoyi.app.service.FfwyPayService;
import com.ruoyi.common.constant.AuthServerConstant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.JacksonUtil;
import com.ruoyi.system.service.IFfwyOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Api(tags = "唤起支付")
@Controller
public class payController {

    @Autowired
    private IFfwyOrderService ffwyOrderService;

    @Autowired
    private FfwyPayService ffwyPayService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;




    /**
     * @param type    0:支付商品订单   1 支付量房订单 2 支付毛培房子节点 3 支付主题房
     * @param orderId 订单号
     * @param payType 1:微信支付  2:支付宝支付     3:银联支付      4:苹果支付
     * @return
     */
    @ApiOperation("唤醒支付第一步")
    @RequestMapping(value = "app/paymoeny", method = RequestMethod.POST)
    @ResponseBody
    public synchronized AjaxResult  paymoeny(@RequestBody String body) {
        Integer type = JacksonUtil.parseInteger(body, "type");
        String orderId = JacksonUtil.parseString(body, "orderId");
        Integer payType = JacksonUtil.parseInteger(body, "payType");
        if (type==null)return AjaxResult.error("商品类型不能为空");
        if(orderId==null)return AjaxResult.error("订单不能为空");
        if(payType==null)return AjaxResult.error("支付类型不能为空");



        SetOperations<String, String> set = redisTemplate.opsForSet();
        Boolean member = set.isMember(AuthServerConstant.ORDER_CHACK + orderId, "1");
        if (!member){
            set.add(AuthServerConstant.ORDER_CHACK+orderId,"1");
            //设置5s过期时间 防止重复提交表单
            redisTemplate.expire(AuthServerConstant.ORDER_CHACK+orderId,5, TimeUnit.SECONDS);
            String tn = ffwyPayService.unionpayGetTN(type, Long.parseLong(orderId), payType);


            return AjaxResult.success("操作成功",tn);
        }

        return AjaxResult.error("不能重复提交表单");

    }

    /**

     * @param orderId   订单号
     * @param type 支付类型

     */
    @ApiOperation("获取订单状态")
    @ResponseBody
    @GetMapping("app/paymoenyOrder")
    public AjaxResult paymoenyOrder(Long orderId,String type ) {
        return ffwyOrderService.selectFfwyOrderType(orderId,type);
    }

}
