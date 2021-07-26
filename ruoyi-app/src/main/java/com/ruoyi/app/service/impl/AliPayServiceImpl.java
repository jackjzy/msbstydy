package com.ruoyi.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.config.pay.AlipayAppConfig;
import com.ruoyi.app.service.AlipayService;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

import com.ruoyi.common.utils.FormatUtils;
import com.ruoyi.system.domain.FfwyPayRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@Slf4j
public class AliPayServiceImpl implements AlipayService {

    @Autowired
    private AlipayAppConfig alipayAppConfig;
    private AlipayClient alipayClient;


    @PostConstruct
    public void init() {
        alipayClient = new DefaultAlipayClient(alipayAppConfig.getUrl(),
                alipayAppConfig.getAppId(),
                alipayAppConfig.getPrivateKey(),
                alipayAppConfig.getFormat(),
                alipayAppConfig.getCharset(),
                alipayAppConfig.getPubKey(),
                alipayAppConfig.getSignType());
    }

    @Override
    public FfwyPayRecord tradeAppPay(FfwyPayRecord pay) {
        log.info("支付宝app支付");
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content）
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("app支付");
        System.err.println(pay.getProductMsg());
        model.setSubject("测试商品");//商品名称
        String orderNumber = pay.getOrderNumber();
        model.setOutTradeNo(orderNumber);//支付订单号

        String payMoney = pay.getPayMoney();
        model.setTotalAmount(FormatUtils.fenToYuan(1));//支付金额
        model.setPassbackParams(pay.getPayOrderType());
        request.setBizModel(model);
        request.setNotifyUrl(alipayAppConfig.getNotifyUrl());//回调地址
        AlipayTradeAppPayResponse response = null;
        try {
            response = alipayClient.sdkExecute(request);

        } catch (AlipayApiException e) {
            log.error("支付宝支付失败{}", e.getErrMsg());

        }
        if (response.isSuccess()) {
            log.info("调用成功" + response.getBody());
            String body = response.getBody();
            pay.setResponseData(JSONObject.toJSONString(response));
            pay.setRequestData(JSONObject.toJSONString(request));
            pay.setTn(body);

        } else {
            log.error("调用失败" + response.getBody());
            pay.setRequestData(JSONObject.toJSONString(request));
            pay.setResponseData("支付异常");
        }
        return pay;
    }

    @Override
    public Boolean tradeRefund(String orderNo, Integer refundFee) {
        log.info("支付宝退款 {}");
        String outRequestNo = orderNo +System.currentTimeMillis();

        AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(orderNo);//订单号
        model.setRefundAmount(FormatUtils.fenToYuan(refundFee));//退款金额;
        model.setRefundReason("正常退款");
        model.setOutRequestNo(outRequestNo);//退款单号
        refundRequest.setBizModel(model);
        AlipayTradeRefundResponse response;
        try {
            response = alipayClient.execute(refundRequest);
        } catch (AlipayApiException e) {
            log.error("支付宝退款失败{}", e.getLocalizedMessage());
            return false;
        }
        if (response != null && response.getCode().equals("10000")) {
            log.info("调用成功" + response);
            return true;
        } else {
            log.error("调用失败" + response);
            return false;
        }

    }



}
