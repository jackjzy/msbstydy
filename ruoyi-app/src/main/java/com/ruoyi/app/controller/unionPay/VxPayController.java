package com.ruoyi.app.controller.unionPay;

import com.alibaba.fastjson.JSON;

import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.model.OrderQueryModel;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import com.ruoyi.common.config.pay.WxPayProperties;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.pay.VxPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: demo
 * @Date: 2020/9/23 15:51
 */
@RestController
@Slf4j
@RequestMapping("/wxPay")
public class VxPayController {
    @Autowired
    private WxPayProperties wxPayProperties;

    /**
     * 微信APP支付
     *
     * @param attach   订单描述：比如会员卡充值、商品名称
     * @param totalFee 支付金额
     * @return 微信支付返回
     */
    @GetMapping(value = "/appPay")
    @ResponseBody
    public Map<String, String> appPay(HttpServletRequest request, @RequestParam("totalFee") BigDecimal totalFee, @RequestParam("attach") String attach) {
        String ip = IpUtils.getIpAddr(request);
        if (StringUtils.isEmpty(ip)) {
            ip = "127.0.0.1";
        }
        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayProperties.getAppId())
                .mch_id(wxPayProperties.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body(attach)
                .attach(attach)
                .out_trade_no(WxPayKit.generateStr())
                .total_fee(String.valueOf(totalFee.multiply(new BigDecimal(100)).intValue()))
                .spbill_create_ip(ip)
                .notify_url(wxPayProperties.getNotifyUrl())
                .trade_type(TradeType.APP.getTradeType())
                .build()
                .createSign(wxPayProperties.getMchKey(), SignType.MD5);

        String xmlResult = WxPayApi.pushOrder(false, params);

        log.info(xmlResult);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

        String returnCode = result.get("return_code");
        String returnMsg = result.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
            log.info("错误信息:"+returnMsg);
        }
        String resultCode = result.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
            log.info("错误信息:"+returnMsg);
        }
        // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
        String prepayId = result.get("prepay_id");

        Map<String, String> packageParams = WxPayKit.appPrepayIdCreateSign(wxPayProperties.getAppId(), wxPayProperties.getMchId(), prepayId,
                wxPayProperties.getMchKey(), SignType.MD5);

        String jsonStr = JSON.toJSONString(packageParams);
        log.info("返回apk的参数:" + jsonStr);
        return packageParams;
    }

    /**
     * 微信回调接口
     */
    @PostMapping("/wxPayNotice")
    public String parseOrderNotifyResult(@RequestBody String xmlData) throws ParseException {
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("微信回调开始了");
        //这里我只进行了部分回调信息的保存，更多参数请查看微信支付官方api:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7&index=8
        Map<String, String> resultMap = WxPayKit.xmlToMap(xmlData);
        log.info("resultMap:" + resultMap);
        /*----------------------分割线，下面的是业务代码，根据自己业务进行修改--------------------------*/
        //订单成功业务处理
        if ("SUCCESS".equals(resultMap.get("result_code"))) {
//            // 根据resultMap处理订单业务
//            TblAutoOrderDat tblAutoOrderDat = new TblAutoOrderDat();
//            //订单描述，比如：会员卡充值，商品信息
//            tblAutoOrderDat.setAttach(resultMap.get("attach"));
//            //交易类型 比如：JSAPI、NATIVE、APP
//            tblAutoOrderDat.setTradeType(resultMap.get("trade_type"));
//            //商户订单号 商家生成，传给微信返回的
//            tblAutoOrderDat.setOrderId(resultMap.get("out_trade_no"));
//            //订单金额 订单总金额，单位为分，我这里转换成元了
//            tblAutoOrderDat.setTotalFee(new BigDecimal(resultMap.get("total_fee")).divide(new BigDecimal(100)));
//            //微信支付订单号
//            tblAutoOrderDat.setTransactionId(resultMap.get("transaction_id"));
//            //支付完成时间
//            String newDate = resultMap.get("time_end");
//            tblAutoOrderDat.setPayTime(sdf.parse(newDate.substring(0, 4) + "-" + newDate.substring(4, 6) + "-" + newDate.substring(6, 8) + " " + newDate.substring(8, 10) + ":" + newDate.substring(10, 12) + ":" + newDate.substring(12, 14)));
            //业务实现，把订单信息保存到数据库
            //tblAutoOrderDatService.add(tblAutoOrderDat);
        }
        /*----------------------分割线，业务结束--------------------------*/

        map.put("return_code", "SUCCESS");
        map.put("return_msg", "OK");
        return VxPayUtil.getMapToXml(map);
    }

    /**
     * 根据微信订单号查询订单状态
     *
     * @param transactionId 微信订单号
     * @return SUCCESS/FAIL
     */
    @GetMapping("/getOrderStates/{transactionId}")
    public String getOrderStates(@PathVariable("transactionId") String transactionId) {
        Map<String, String> params = OrderQueryModel
                .builder()
                .appid(wxPayProperties.getAppId())
                .mch_id(wxPayProperties.getMchId())
                .transaction_id(transactionId)
                .nonce_str(WxPayKit.generateStr())
                .build()
                .createSign(wxPayProperties.getMchKey(), SignType.MD5);
        String xml = WxPayApi.orderQuery(false, params);
        Map<String, String> resultMap = WxPayKit.xmlToMap(xml);
        log.info("resultMap:" + resultMap);
        return resultMap.get("result_code");
    }
}

