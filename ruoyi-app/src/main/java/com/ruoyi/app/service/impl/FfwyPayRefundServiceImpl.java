package com.ruoyi.app.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ruoyi.app.util.unionPay.acp.demo.DemoBase;
import com.ruoyi.app.util.unionPay.acp.sdk.AcpService;
import com.ruoyi.app.util.unionPay.acp.sdk.LogUtil;
import com.ruoyi.app.util.unionPay.acp.sdk.SDKConfig;
import com.ruoyi.app.util.unionPay.acp.sdk.SDKConstants;
import com.ruoyi.common.config.pay.AlipayAppConfig;
import com.ruoyi.common.constant.OrderStatus;
import com.ruoyi.common.enums.PayType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.FormatUtils;

import com.ruoyi.system.domain.FfwyPayRefund;
import com.ruoyi.system.domain.aftersale.FfwyAfterSale;

import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.mapper.FfwyPayRecordMapper;
import com.ruoyi.system.mapper.FfwyPayRefundMapper;
import com.ruoyi.system.mapper.aftersale.FfwyAfterSaleMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;

import com.ruoyi.system.service.IFfwyPayRefundService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.ruoyi.app.util.unionPay.acp.demo.BackRcvResponse.getAllRequestParam;


/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-07
 */
@Service
@Slf4j
public class FfwyPayRefundServiceImpl implements IFfwyPayRefundService {
    @Autowired
    private FfwyPayRefundMapper ffwyPayRefundMapper;

    @Autowired
    private FfwyPayRecordMapper ffwyPayRecordMapper;

    @Autowired
    private IFfwyPayRefundService ffwyPayRefundService;

    @Autowired
    private FfwyAfterSaleMapper ffwyAfterSaleMapper;
    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;

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
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyPayRefund selectFfwyPayRefundById(Long id) {
        return ffwyPayRefundMapper.selectFfwyPayRefundById(id);
    }

    @Override
    public FfwyPayRefund selectFfwyPayRefundByOrderId(Long id) {
        return null;
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyPayRefund> selectFfwyPayRefundList(FfwyPayRefund ffwyPayRefund) {
        return ffwyPayRefundMapper.selectFfwyPayRefundList(ffwyPayRefund);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyPayRefund(FfwyPayRefund ffwyPayRefund) {
        ffwyPayRefund.setCreateTime(DateUtils.getNowDate());
        return ffwyPayRefundMapper.insertFfwyPayRefund(ffwyPayRefund);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyPayRefund(FfwyPayRefund ffwyPayRefund) {
        return ffwyPayRefundMapper.updateFfwyPayRefund(ffwyPayRefund);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPayRefundByIds(Long[] ids) {
        return ffwyPayRefundMapper.deleteFfwyPayRefundByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPayRefundById(Long id) {
        return ffwyPayRefundMapper.deleteFfwyPayRefundById(id);
    }

    /**
     * 调取银联支付的退款接口
     *
     * @param orderId           原订单id
     * @param orderRefundNumber 生成的退款订单id
     * @param payType           交易类型 银联或者支付包 微信
     * @param userId            确认退款的用户
     * @param reFundUser        需要退款的用户
     * @param money             退款金额
     * @param orderType         支付订单类型
     * @return
     */
    @Override
    public boolean refundUnionPay(String orderId, String orderRefundNumber, String payType, Long userId, Long reFundUser, String money,Integer orderType) {

        //加载classpath下的acp_sdk.properties文件内容   -*******注意 每次都需要读取
        SDKConfig.getConfig().loadPropertiesFromSrc();

        String origQryId = ffwyPayRecordMapper.selectFfwyPayRecordByOrderNumber(orderId).getCellBackQueryId();



        Map<String, String> data = new HashMap<String, String>();

        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        data.put("version", DemoBase.version);               //版本号
        data.put("encoding", DemoBase.encoding);             //字符集编码 可以使用UTF-8,GBK两种方式
        data.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
        data.put("txnType", "04");                           //交易类型 04-退货
        data.put("txnSubType", "00");                        //交易子类型  默认00
        data.put("bizType", "000201");                       //业务类型
        data.put("channelType", "08");                       //渠道类型，07-PC，08-手机

        /***商户接入参数***/
        data.put("merId", SDKConfig.getConfig().getMerId());                //商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
        data.put("accessType", "0");                         //接入类型，商户接入固定填0，不需修改
        data.put("orderId", orderRefundNumber);          //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则，重新产生，不同于原消费
        data.put("txnTime", DemoBase.getCurrentTime());      //订单发送时间，格式为yyyyMMddHHmmss，必须取当前时间，否则会报txnTime无效
        data.put("currencyCode", "156");                     //交易币种（境内商户一般是156 人民币）
        data.put("txnAmt", money);                          //****退货金额，单位分，不要带小数点。退货金额小于等于原消费金额，当小于的时候可以多次退货至退货累计金额等于原消费金额
        data.put("backUrl", SDKConfig.getConfig().getRefundBackUrl());               //后台通知地址，后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 退货交易 商户通知,其他说明同消费交易的后台通知

        /***要调通交易以下字段必须修改***/
        data.put("origQryId", origQryId);      //****原消费交易返回的的queryId，可以从消费交易后台通知接口中或者交易状态查询接口中获取

        //区分支付订单类型
        data.put("reqReserved",orderType.toString());

        //组装退款表数据
        FfwyPayRefund ffwyPayRefund = new FfwyPayRefund();
        ffwyPayRefund.setPayType(payType);
        ffwyPayRefund.setOrderId(orderId);
        ffwyPayRefund.setReqData(JSONObject.toJSONString(data));
        ffwyPayRefund.setCreateTime(new Date());
        ffwyPayRefund.setUserId(userId.intValue());
        ffwyPayRefund.setRefundUserId(reFundUser.intValue());
        ffwyPayRefund.setRefundMoney(new BigDecimal(money));
        ffwyPayRefund.setStatus(1);
        ffwyPayRefund.setReFundOrderId(orderRefundNumber);
        ffwyPayRefundMapper.insertFfwyPayRefund(ffwyPayRefund);

        // 请求方保留域，
        // 透传字段，查询、通知、对账文件中均会原样出现，如有需要请启用并修改自己希望透传的数据。
        // 出现部分特殊字符时可能影响解析，请按下面建议的方式填写：
        // 1. 如果能确定内容不会出现&={}[]"'等符号时，可以直接填写数据，建议的方法如下。
//		data.put("reqReserved", "透传信息1|透传信息2|透传信息3");
        // 2. 内容可能出现&={}[]"'符号时：
        // 1) 如果需要对账文件里能显示，可将字符替换成全角＆＝｛｝【】“‘字符（自己写代码，此处不演示）；
        // 2) 如果对账文件没有显示要求，可做一下base64（如下）。
        //    注意控制数据长度，实际传输的数据长度不能超过1024位。
        //    查询、通知等接口解析时使用new String(Base64.decodeBase64(reqReserved), DemoBase.encoding);解base64后再对数据做后续解析。
//		data.put("reqReserved", Base64.encodeBase64String("任意格式的信息都可以".toString().getBytes(DemoBase.encoding)));

        /**请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文------------->**/
        Map<String, String> reqData = AcpService.sign(data, DemoBase.encoding);        //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String url = SDKConfig.getConfig().getBackRequestUrl();                                    //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
        Map<String, String> rspData = AcpService.post(reqData, url, DemoBase.encoding);//这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

        /**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
        //应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
        if (!rspData.isEmpty()) {
            if (AcpService.validate(rspData, DemoBase.encoding)) {
                LogUtil.writeLog("验证签名成功");
                String respCode = rspData.get("respCode");
                if (("00").equals(respCode)) {

                    //请求成功更新返回数据 只是获取返回数据,但不更新状态 等待回调
                    ffwyPayRefund.setResData(JSONObject.toJSONString(rspData));
                    ffwyPayRefundMapper.updateFfwyPayRefund(ffwyPayRefund);
                    return true;
                    //交易已受理(不代表交易已成功），等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。
                    //TODO
                } else if (("03").equals(respCode) ||
                        ("04").equals(respCode) ||
                        ("05").equals(respCode)) {
                    //后续需发起交易状态查询交易确定交易状态
                    //TODO
                } else {
                    //其他应答码为失败请排查原因
                    //TODO
                }
            } else {
                LogUtil.writeErrorLog("验证签名失败");
                //TODO 检查验证签名失败的原因
            }
        } else {
            //未返回正确的http状态
            LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
        }
        String reqMessage = DemoBase.genHtmlResult(reqData);
        String rspMessage = DemoBase.genHtmlResult(rspData);
        System.err.println("请求报文:<br/>" + reqMessage + "<br/>" + "应答报文:</br>" + rspMessage + "");
        return false;
    }

    /**
     * 调取银联支付的退款接口
     *
     * @param orderNo           原订单id
     * @param outRequestNo 生成的退款订单id
     * @param refundFee             退款金额
     * @return
     */
    @Override
    public boolean refunAliPay(String orderNo, Integer refundFee,String outRequestNo) {
        log.info("支付宝退款 {}");
       // String outRequestNo = orderNo +System.currentTimeMillis();

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

            log.error("支付宝退款失败{}",e.getLocalizedMessage());

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

    @Override
    public boolean refun(FfwyOrderDetails orderDetails) {
        //售后信息
        FfwyAfterSale ffwyAfterSale = ffwyAfterSaleMapper.selectFfwyAfterSaleByOrderId(orderDetails.getOrderDetailsId());
        //订单信息
        FfwyOrder ffwyOrder = ffwyOrderMapper.selectFfwyOrderById(orderDetails.getOrderId(), null);
        //支付方式
        Integer paymentId = ffwyOrder.getPaymentId();
        //商品单价
        BigDecimal price = orderDetails.getPrice();
        //需要退款的总金额
        BigDecimal prices = price.multiply(new BigDecimal(orderDetails.getNumber())).multiply(new BigDecimal(100)).setScale(0);
        //需要退款的订单号
        String orderNumber = ffwyOrder.getOrderNumber();
        //退款单号
        String refunOrderNumber = ffwyAfterSale.getRefundOrderNumber();



        switch (paymentId){

            case 1:
                return true;
            case 2:

                //this.refunAliPay(orderNumber,Integer.valueOf(prices.toString()),refunOrderNumber);
                //测试环境退款金额为1分
                return this.refunAliPay(orderNumber,//原订单号
                        1,//退款金额
                        refunOrderNumber);//退款订单号
            case 3:

                return  this.refundUnionPay(orderNumber,//原订单号
                        refunOrderNumber,//退款订单号
                        "3",//支付类型银联
                        orderDetails.getUserId(),//退款的商户
                        ffwyAfterSale.getUserId(),//退款的用户
                        prices.toString(),//退款金额
                        PayType.PAY_PRODUCT.getCode());//退款订单类型

            case 4:
                return true;
            default:return false;
        }


    }

    @Override
    public void callBackRefund(HttpServletRequest req, HttpServletResponse resp)  {

        LogUtil.writeLog("退款回调接收后台通知开始");
        //加载classpath下的acp_sdk.properties文件内容
        SDKConfig.getConfig().loadPropertiesFromSrc();

        String encoding = req.getParameter(SDKConstants.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);
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
            /**
             * 注意 这里是获取的退款订单号  不是主订单号
             */
            String refundOrderId =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode = valideData.get("respCode");

            FfwyPayRefund ffwyPayRefund = ffwyPayRefundMapper.selectFfwyPayRefundByRefundOrderId(refundOrderId);
            ffwyPayRefund.setCallBackData(JSONObject.toJSONString(valideData));
            ffwyPayRefund.setCallBackDate(new Date());
            ffwyPayRefund.setCallBackQuerid(valideData.get("queryId"));
            ffwyPayRefund.setStatus(2);
            ffwyPayRefundMapper.updateFfwyPayRefund(ffwyPayRefund);

            ffwyPayRefundService.ffchageOrderRefund(ffwyPayRefund.getOrderId(),reqParam.get("reqReserved"));

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
     * 退款更改订单状态
     * @param orderId    原订单id
     * @param reqReserved 支付订单类型
     */
    @Override
    public void ffchageOrderRefund(String orderId, String reqReserved) {
        /**
         *  需要自己写逻辑 更改状态  退款成功
         */
        //修改支付状态
//        FfwyPayRecord ffwyPayRecord = ffwyPayRecordMapper.selectFfwyPayRecordByOrderNumber(orderId);
//        ffwyPayRecord.setPayStatus(PayType.PAYMENT_SUCCESSFUL.getCode());
//        ffwyPayRecordMapper.updateFfwyPayRecord(ffwyPayRecord);
//        if (PayType.PAY_PHASE.getCode().equals(type)){
//            //根据订单号查询当前订单 并修改当前订单状态
//            FfwyPhase ffwyPhase = iFfwyPhaseService.selectFfwyPhaseByOrder(orderId);
//            if (!ObjectUtil.isEmpty(ffwyPhase))
//                iFfwyPhaseService.updateFfwyPhases(ffwyPhase, ffwyPayRecord.getUserId());
//            System.err.println("毛配房节点");
//        } else if (PayType.PAY_PRODUCT.getCode().equals(type)){
//            System.err.println("商品订单");
//            FfwyOrder ffwyOrder = new FfwyOrder();
//            ffwyOrder.setOrderId(Long.valueOf(orderId));
//            List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderList(ffwyOrder);
//            if (ffwyOrders.size()>0){
//                ffwyOrder.setStatusId(OrderStatus.AITING_DELIVER);
//                ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
//                FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
//                ffwyOrderDetails.setOrderId(Long.valueOf(orderId));
//                ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(ffwyOrderDetails);
//            }
//
//        } else if (PayType.PAY_COMBOMEAL.getCode().equals(type)){
//            System.err.println("量房");
//
//        } else if (PayType.PAY_THEME_HOST.getCode().equals(type)){
//            //根据订单id查询简装房套餐
//            FfwyOrderCombomeal ffwyOrderCombomeal = iFfwyOrderCombomealService.selectFfwyOrderCombomealByOrderNumber(orderId);
//            //修改订单状态为等待上门
//            ffwyOrderCombomeal.setOrderStatus("2");
//            iFfwyOrderCombomealService.updateFfwyOrderCombomeal(ffwyOrderCombomeal);
//            System.err.println("简装房");
//        }
        //先去查询退款订单
        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
        ffwyOrderDetails.setOrderStatus(OrderStatus.WAITING_REFUNDED);
        ffwyOrderDetails.setOrderId(Long.valueOf(orderId));
        List<FfwyOrderDetails> orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsList(ffwyOrderDetails);
        //再去查询退款的总数
        List<FfwyOrderDetails> orderDetails1 = ffwyOrderDetailsMapper.selectFfwyOrderById(Long.valueOf(orderId));
        if (orderDetails.size()!=orderDetails1.size()){
            orderDetails.forEach(order->{
                order.setOrderStatus(OrderStatus.REFUNDSUCCESS);
                ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(order);
            });
            //如果查询订单的数量跟总数一样修改订单表的状态。
        }else if (orderDetails.size()==orderDetails1.size()){
            orderDetails.forEach(order->{
                order.setOrderStatus(OrderStatus.REFUNDSUCCESS);
                ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(order);
            });
            FfwyOrder ffwyOrder = new FfwyOrder();
            ffwyOrder.setOrderId(Long.valueOf(orderId));
            ffwyOrder.setStatusId(OrderStatus.REFUNDSUCCESS);
            ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
        }


    }
}
