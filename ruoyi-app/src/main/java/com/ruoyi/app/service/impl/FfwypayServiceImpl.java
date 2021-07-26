package com.ruoyi.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.service.AlipayService;
import com.ruoyi.app.service.FfwyPayService;
import com.ruoyi.app.util.unionPay.acp.demo.BackRcvResponse;
import com.ruoyi.app.util.unionPay.acp.demo.DemoBase;
import com.ruoyi.app.util.unionPay.acp.demo.EncryptCerUpdateQuery;
import com.ruoyi.app.util.unionPay.acp.sdk.AcpService;
import com.ruoyi.app.util.unionPay.acp.sdk.LogUtil;
import com.ruoyi.app.util.unionPay.acp.sdk.SDKConstants;
import com.ruoyi.common.constant.OrderStatus;
import com.ruoyi.common.enums.OrderComStatusEnum;
import com.ruoyi.common.enums.PayType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.mapper.FfwyPayRecordMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
@Slf4j
@Service
public class FfwypayServiceImpl implements FfwyPayService {

    @Autowired
    private FfwyPayRecordMapper ffwyPayRecordMapper;
    @Autowired
    private IFfwyPhaseService iFfwyPhaseService;
    @Autowired
    private IFfwyOrderDetailsService iFfwyOrderDetailsService;
    @Autowired
    private IFfwyOrderCombomealService iFfwyOrderCombomealService;
    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;
    @Autowired
    private IFfwyCombomealService iFfwyCombomealService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private IFfwyUserService iFfwyUserService;

    /**
     * 支付套餐子节点订单
     *
     * @param orderId 订单号
     * @param payType 支付方式
     * @return 支付信息
     */
    @Override
    public FfwyPayRecord payByPhaseOrder(long orderId,Integer payType) {
        FfwyPayRecord ffwyPayRecord = new FfwyPayRecord();

        return chageOrAddPay(ffwyPayRecord, PayType.PAY_PHASE.getCode());
    }

    /**
     * 支付商品订单
     *
     * @param orderId 订单号
     * @param payType 支付方式
     * @return 支付信息
     */
    @Override
    public FfwyPayRecord payByProductOrder(long orderId,Integer payType) {
        //根据订单号查询相关支付信息
        FfwyOrder ffwyOrder = ffwyOrderMapper.selectFfwyOrderById(orderId, null);
        FfwyPayRecord ffwyPayRecord = new FfwyPayRecord();
        if (ffwyOrder != null) {
            ffwyPayRecord.setPayTyep(payType.toString());
            ffwyPayRecord.setOrderNumber(ffwyOrder.getOrderNumber());
            //元转分

            BigDecimal multiply = ffwyOrder.getMoney().multiply(new BigDecimal(100)).setScale(0);
            ffwyPayRecord.setPayMoney(multiply.toString());
            ffwyPayRecord.setProductMsg("商品支付");
            ffwyPayRecord.setPayOrderType(PayType.PAY_PRODUCT.getCode().toString());
            ffwyPayRecord.setCteateOrderTime(ffwyOrder.getCreateTime());
            //ffwyPayRecord.setProductMsg(ffwyOrder.getProductName());
        }

        return chageOrAddPay(ffwyPayRecord, PayType.PAY_PRODUCT.getCode());
    }

    /**
     * 支付量房订单
     *
     * @param orderId 订单号
     * @param payType 支付方式
     * @return 支付信息
     */
    @Override
    public FfwyPayRecord payBycombomealOrder(long orderId,Integer payType) {

        FfwyPayRecord ffwyPayRecord = new FfwyPayRecord();

        return chageOrAddPay(ffwyPayRecord, PayType.PAY_COMBOMEAL.getCode());
    }

    /**
     * 支付主题房订单
     *
     * @param orderId 订单号
     * @param payType 支付方式
     * @return 支付信息
     */
    @Override
    public FfwyPayRecord payBythemeHoustOrder(long orderId,Integer payType) {
        FfwyPayRecord ffwyPayRecord = new FfwyPayRecord();
        return chageOrAddPay(ffwyPayRecord, PayType.PAY_THEME_HOST.getCode());
    }

    //银联回调时修改支付状态
    @Override
    public void cellBack(HttpServletRequest req, HttpServletResponse resp) {
        LogUtil.writeLog("BackRcvResponse接收后台通知开始");
        String encoding = req.getParameter(SDKConstants.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = BackRcvResponse.getAllRequestParam(req);
        LogUtil.printRequestLog(reqParam);
        Map<String, String> valideData = null;

        if (null != reqParam && !reqParam.isEmpty()) {

            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();

            valideData = new HashMap<String, String>(reqParam.size());

            while (it.hasNext()) {

                Map.Entry<String, String> e = it.next();

                String key = (String) e.getKey();

                String value = (String) e.getValue();

                valideData.put(key, value);

            }

        }

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            LogUtil.writeLog("验证签名结果[失败].");
            //验签失败，需解决验签问题

        } else {
            LogUtil.writeLog("验证签名结果[成功].");
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderId = valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode = valideData.get("respCode");
            //判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
            try {


                if (EncryptCerUpdateQuery.queryOrder(req, resp)) {

                    FfwyPayRecord ffwyPayRecord = ffwyPayRecordMapper.selectFfwyPayRecordByOrderNumber(orderId);
                    ffwyPayRecord.setCellBackData(JSONObject.toJSONString(valideData));
                    ffwyPayRecord.setCellBackQueryId(valideData.get("queryId"));
                    ffwyPayRecordMapper.updateFfwyPayRecord(ffwyPayRecord);

                    chageOrder(orderId, reqParam.get("reqReserved"));
                }
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LogUtil.writeLog("BackRcvResponse接收后台通知结束");
        //返回给银联服务器http 200  状态码
        try {
            resp.getWriter().print("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *
     * @param orderId 订单号
     * @param payType 支付方式
     * @param type 支付类型
     * @return tn
     */
    @Override
    public String unionpayGetTN(Integer type, long orderId,Integer payType) {
        String txnTime = DemoBase.getCurrentTime();
        System.err.println("orderId===========" + orderId);
        System.err.println("txnTime===========" + txnTime);

        String TN = null;
        //套餐节点
        if (type == PayType.PAY_PHASE.getCode()) {
            TN = this.payByPhaseOrder(orderId,payType).getTn();
            //商品
        } else if (type == PayType.PAY_PRODUCT.getCode()) {
            TN = this.payByProductOrder(orderId,payType).getTn();
            //支付量房订单
        } else if (type == PayType.PAY_COMBOMEAL.getCode()) {
            TN = this.payBycombomealOrder(orderId,payType).getTn();
            //支付主题房
        } else if (type == PayType.PAY_THEME_HOST.getCode()) {
            TN = this.payBycombomealOrder(orderId,payType).getTn();
        }

        return TN;
    }




    /**
     * 新增或修改支付记录
     *
     * @param pay  支付信息
     * @param code 支付订单的类型
     * @return 支付信息
     */
    private FfwyPayRecord chageOrAddPay(FfwyPayRecord pay, Integer code) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        pay.setUserId(userId);


        FfwyPayRecord ffwyPayRecord = null;
        //查询支付记录判断是否支付过
        FfwyPayRecord ffwyPayRecord1 = ffwyPayRecordMapper.selectFfwyPayRecordByOrderNumber(pay.getOrderNumber());
        //支付过则修改订单时间未支付则创建新的支付记录
        if (ObjectUtil.isNotEmpty(ffwyPayRecord1)) {
            //支付
            ffwyPayRecord1.setPayTyep(pay.getPayTyep());
            ffwyPayRecord = setPay(ffwyPayRecord1);
            //支成功修改支付信息
            ffwyPayRecord.setCreateTime(DateUtils.getNowDate());
            ffwyPayRecordMapper.updateFfwyPayRecord(ffwyPayRecord);
        } else {
            //第一次发起支付
            pay.setCreateTime(DateUtils.getNowDate());
            pay.setPayOrderType(code.toString());
            //支付
            ffwyPayRecord = setPay(pay);
            //新增支付信息
            ffwyPayRecord.setPayOrderType(code.toString());

            ffwyPayRecordMapper.insertFfwyPayRecord(ffwyPayRecord);
        }
        //返回支付信息
        System.err.println("tn"+ffwyPayRecord.getTn());
        return ffwyPayRecord;
    }

    //发起支付请求
    private FfwyPayRecord setPay(FfwyPayRecord pay) {

        String payTyep = pay.getPayTyep();
        if (!StringUtils.isEmpty(payTyep)){
            Integer type = Integer.valueOf(payTyep);
            switch (type){
                case 1://微信支付
                    return null;
                case 2://支付宝支付
                    return alipayService.tradeAppPay(pay);
                case 3://银联支付
                    return unionPay(pay);
                default: return null;

            }

        }

        return null;
    }


    //银联支付
    private FfwyPayRecord unionPay(FfwyPayRecord pay){
        //支付时间

        String TxnTime = new SimpleDateFormat("yyyyMMddHHmmss").format(pay.getCreateTime());
        //发起支付

        Map<String, Object> map = EncryptCerUpdateQuery.payMoney(pay.getOrderNumber(), pay.getPayMoney(), TxnTime, pay.getPayOrderType());

        //支付完成录入支付信息
        Map<String, String> reqData = (Map<String, String>) map.get("reqData");
        Map<String, String> rspData = (Map<String, String>) map.get("rspData");
        Map<String, String> contentData = (Map<String, String>) map.get("contentData");
        //设置支付方式
        pay.setPayTyep("3");
        //设置支付状态为待支付
        pay.setPayStatus(PayType.TO_BE_PAID.getCode().toString());
        //存储支付相关的数据
        pay.setContentData(JSONObject.toJSONString(contentData));
        pay.setRequestData(JSONObject.toJSONString(reqData));
        pay.setResponseData(JSONObject.toJSONString(rspData));
        //支付需要
        pay.setTn(EncryptCerUpdateQuery.getTn(rspData, reqData));


        return pay;

    }

    @Override
    //修改订单状态
    public void chageOrder(String orderNumber, String type) {
        Integer itype = Integer.valueOf(type);

        //修改支付状态
        FfwyPayRecord ffwyPayRecord = ffwyPayRecordMapper.selectFfwyPayRecordByOrderNumber(orderNumber);
        ffwyPayRecord.setPayStatus(PayType.PAYMENT_SUCCESSFUL.getCode().toString());
        ffwyPayRecordMapper.updateFfwyPayRecord(ffwyPayRecord);
        if (PayType.PAY_PHASE.getCode()==itype) {
            //根据订单号查询当前订单 并修改当前订单状态
            FfwyPhase ffwyPhase = iFfwyPhaseService.selectFfwyPhaseByOrder(orderNumber);
            if (!ObjectUtil.isEmpty(ffwyPhase))
                iFfwyPhaseService.updateFfwyPhases(ffwyPhase, ffwyPayRecord.getUserId(), "0");
            log.info("毛配房节点");
        } else if (PayType.PAY_PRODUCT.getCode()==itype) {

            log.info("商品订单");
            FfwyUser user = new FfwyUser();

            FfwyOrder ffwyOrder = new FfwyOrder();
            ffwyOrder.setOrderNumber(orderNumber);
            List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderList(ffwyOrder);
            if (ffwyOrders.size() > 0) {
                for (int i = 0; i < ffwyOrders.size(); i++) {
                    //根据订单查询当前用户id
                    if (i==0){
                        FfwyOrder order = ffwyOrders.get(i);
                        Long userId = order.getUserId();
                        user= iFfwyUserService.selectFfwyUserById(userId);
                        user.setUserName(user.getUserName());
                    }
                    ffwyOrder.setStatusId(OrderStatus.AITING_DELIVER);
                    ffwyOrder.setOrderId(ffwyOrders.get(i).getOrderId());
                    ffwyOrder.setPaymentId(Integer.valueOf(ffwyPayRecord.getPayTyep()));
                    ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
                    FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                    //给每个订单详情添加用户名好手机号
                    if (ObjectUtil.isNotEmpty(user)){
                        ffwyOrderDetails.setUserName(user.getUserName());
                        ffwyOrderDetails.setPhone(user.getPhonenumber());
                    }
                    ffwyOrderDetails.setOrderNumber(orderNumber);
                    ffwyOrderDetails.setOrderId(ffwyOrders.get(i).getOrderId());
                    ffwyOrderDetails.setOrderStatus(OrderStatus.AITING_DELIVER);
                    ffwyOrderDetails.setPayId(ffwyPayRecord.getPayTyep());
                    ffwyOrderDetails.setPaymentTime(new Date());
                    ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(ffwyOrderDetails);
                }

            }

        } else if (PayType.PAY_COMBOMEAL.getCode()==itype) {
            log.info("量房");
            iFfwyCombomealService.combomaealStatusUpdata(Long.valueOf(orderNumber), OrderComStatusEnum.PAID.getCode());

        } else if (PayType.PAY_THEME_HOST.getCode()==itype) {
            //根据订单id查询简装房套餐
            FfwyOrderCombomeal ffwyOrderCombomeal = iFfwyOrderCombomealService.selectFfwyOrderCombomealByOrderNumber(orderNumber);
            //修改订单状态为等待上门
            ffwyOrderCombomeal.setOrderStatus("2");
            iFfwyOrderCombomealService.updateFfwyOrderCombomeal(ffwyOrderCombomeal);
            log.info("精装房");
        }

    }
}
