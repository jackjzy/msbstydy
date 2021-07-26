package com.ruoyi.app.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.FfwyPayRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FfwyPayService {
    /**
     * 支付套餐子节点订单
     *
     * @param pay 支付信息
     * @return tn
     */
    public FfwyPayRecord payByPhaseOrder(long orderId,Integer payType);
    /**
     * 支付商品订单
     *
     * @param pay 支付信息
     * @return tn
     */
    public FfwyPayRecord payByProductOrder(long orderId,Integer payType);
    /**
     * 支付量房订单
     *
     * @param pay 支付信息
     * @return tn
     */
    public FfwyPayRecord payBycombomealOrder(long orderId,Integer payType);

    /**
     * 支付主题房订单
     *
     * @param pay 支付信息
     * @return tn
     */
    public FfwyPayRecord payBythemeHoustOrder(long orderId,Integer payType);


    public void cellBack(HttpServletRequest req, HttpServletResponse resp) ;

    String unionpayGetTN(Integer type, long parseLong,Integer payType);

    public void chageOrder(String orderNumber, String type);
}
