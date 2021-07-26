package com.ruoyi.app.controller.unionPay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.ruoyi.common.config.pay.AlipayAppConfig;
import com.ruoyi.common.config.pay.AlipayNotifyParam;
import com.ruoyi.app.service.FfwyPayService;
import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.service.IFfwyPayRecordService;
import com.ruoyi.system.service.IFfwyPayRefundService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

@RequestMapping("/app/callBack")
@Controller
public class CellbackController {
    @Autowired
    private FfwyPayService ffwyPayService;
    @Autowired
    private FfwyOrderMapper  ffwyOrderMapper;

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Autowired
    private IFfwyPayRecordService ffwyPayRecordService;

    @Autowired
    private AlipayAppConfig alipayAppConfig;

    @Autowired
    private IFfwyPayRefundService iFfwyPayRefundService;







    @ApiOperation("银联回调")
    @RequestMapping("/unionPay")
    @ResponseBody
    public void callBack(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ffwyPayService.cellBack(req, resp);

    }


    @ApiOperation("支付宝回调")
    @RequestMapping(value = "/aliPay", method = RequestMethod.POST)
    @ResponseBody
    public String callback(HttpServletRequest request) {
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JSON.toJSONString(params);
        log.info("支付宝回调，{}", paramsJson);
        try {
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayAppConfig.getPubKey(),
                    alipayAppConfig.getCharset(), alipayAppConfig.getSignType());
            log.info("signVerified={}", signVerified);
            if (signVerified) {
                log.info("支付宝回调签名认证成功");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                check(params);
                // 另起线程处理业务
                log.info("另起线程处理业务");
                executorService.execute(() -> {
                    AlipayNotifyParam param = buildAlipayNotifyParam(params);
                    String tradeStatus = param.getTradeStatus();
                    // 支付成功
                    if (tradeStatus.equals("TRADE_SUCCESS")) {
                        log.info("支付成功");
                        //TODO 用户处理自己逻辑;
                        ffwyPayService.chageOrder(params.get("out_trade_no"),params.get("passback_params"));
                    } else {
                        log.info("支付失败");
                        //TODO 用户处理自己逻辑
                    }
                });
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                return "success";
            } else {
                log.info("支付宝回调签名认证失败, paramsJson:{}", paramsJson);
                return "failure";
            }
        } catch (AlipayApiException e) {
            log.info("支付宝回调发生异常{}", e.getLocalizedMessage());
            return "failure";
        }
    }

    @ApiOperation("银联退款回调")
    @RequestMapping(value = "/callBackRefund", method = RequestMethod.POST)
    @ResponseBody
    public void callBackRefund(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        iFfwyPayRefundService.callBackRefund(req, resp);

    }
    private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
        String json = JSON.toJSONString(params);
        return JSON.parseObject(json, AlipayNotifyParam.class);
    }


    // 将request中的参数转换成Map
    private Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<>();
        //1.从支付宝回调的request域中取值
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            retMap.put(name, valueStr);
        }
        return retMap;
    }

    private void check(Map<String, String> params) throws AlipayApiException {
        String outTradeNo = params.get("out_trade_no");
        System.err.println(outTradeNo);
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        FfwyPayRecord ffwyPayRecord = ffwyPayRecordService.selectFfwyPayRecordByOrderNumber(outTradeNo);
        if (ffwyPayRecord == null) {
            throw new AlipayApiException("out_trade_no错误");
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//        Integer totalAmount = FormatUtils.yuan2Fen(new BigDecimal(params.get("total_amount")));
//        log.info("totalAmount{}", totalAmount);
//        if (!totalAmount.equals(ffwyPayRecord.getPayMoney())) {
//            throw new AlipayApiException("error total_amount");
//        }

        // 3、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(alipayAppConfig.getAppId())) {
            throw new AlipayApiException("app_id不一致");
        }
    }


}
