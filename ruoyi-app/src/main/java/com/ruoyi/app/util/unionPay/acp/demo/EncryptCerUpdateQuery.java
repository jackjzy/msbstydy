package com.ruoyi.app.util.unionPay.acp.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.util.unionPay.acp.sdk.AcpService;
import com.ruoyi.app.util.unionPay.acp.sdk.LogUtil;
import com.ruoyi.app.util.unionPay.acp.sdk.SDKConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ruoyi.app.util.unionPay.acp.demo.DemoBase.backUrl;

/**
 * 
 * 银联加密公钥更新查询(只适用于使用RSA证书加密的方式<即signMethod=01>，其他signMethod=11，12密钥加密用不到此交易)
 * 商户定期（1天1次）向银联全渠道系统发起获取加密公钥信息交易.
 * 本交易成功后会自动替换配置文件acp_sdk.properties文件中acpsdk.encryptCert.path指定的文件，可用不用手工替换
 * 声明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码，性能，规范性等方面的保障<br>
 */
public class EncryptCerUpdateQuery {
	public static Boolean queryOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String merId = req.getParameter("merId");
		String orderId = req.getParameter("orderId");
		String txnTime = req.getParameter("txnTime");
		LogUtil.writeLog("merId:"+merId);
		LogUtil.writeLog("orderId:"+orderId);
		LogUtil.writeLog("txnTime:"+txnTime);

		Map<String, String> data = new HashMap<String, String>();

		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
		data.put("version", DemoBase.version);                 //版本号
		data.put("encoding", DemoBase.encoding);          //字符集编码 可以使用UTF-8,GBK两种方式
		data.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
		data.put("txnType", "00");                             //交易类型 00-默认
		data.put("txnSubType", "00");                          //交易子类型  默认00
		data.put("bizType", "000201");                         //业务类型

		/***商户接入参数***/
		data.put("merId", merId);                  			   //商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
		data.put("accessType", "0");                           //接入类型，商户接入固定填0，不需修改

		/***要调通交易以下字段必须修改***/
		data.put("orderId", orderId);                 			//****商户订单号，每次发交易测试需修改为被查询的交易的订单号
		data.put("txnTime", txnTime);                			//****订单发送时间，每次发交易测试需修改为被查询的交易的订单发送时间

		/**请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文------------->**/

		Map<String, String> reqData = AcpService.sign(data,DemoBase.encoding);			//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		String url = SDKConfig.getConfig().getSingleQueryUrl();								//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.singleQueryUrl
		Map<String, String> rspData = AcpService.post(reqData, url,DemoBase.encoding); //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
		if(!rspData.isEmpty()){
			if(AcpService.validate(rspData, DemoBase.encoding)){
				LogUtil.writeLog("验证签名成功");
				if(("00").equals(rspData.get("respCode"))){//如果查询交易成功
					String origRespCode = rspData.get("origRespCode");
					if(("00").equals(origRespCode)){
						//交易成功，更新商户订单状态
						return true;
						//TODO
					}else if(("03").equals(origRespCode)||
							("04").equals(origRespCode)||
							("05").equals(origRespCode)){
						//订单处理中或交易状态未明，需稍后发起交易状态查询交易 【如果最终尚未确定交易是否成功请以对账文件为准】
						//TODO
					}else{
						//其他应答码为交易失败
						//TODO
					}
				}else if(("34").equals(rspData.get("respCode"))){
					//订单不存在，可认为交易状态未明，需要稍后发起交易状态查询，或依据对账结果为准

				}else{//查询交易本身失败，如应答码10/11检查查询报文是否正确
					//TODO
				}
			}else{
				LogUtil.writeErrorLog("验证签名失败");
				//TODO 检查验证签名失败的原因
			}
		}else{
			//未返回正确的http状态
			LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
		}

		String reqMessage = DemoBase.genHtmlResult(reqData);
		String rspMessage = DemoBase.genHtmlResult(rspData);
		resp.getWriter().write("交易状态查询交易</br>请求报文:<br/>"+reqMessage+"<br/>" + "应答报文:</br>"+rspMessage+"");
		return false;
	}

	public static void main(String[] args) {


		String orderId 	= DemoBase.getOrderId();
		String txnTime 	= DemoBase.getCurrentTime();
		String merId 	= "777290058191834";

		System.err.println("merId==========="+merId);
		System.err.println("orderId==========="+orderId);
		System.err.println("txnTime==========="+txnTime);


		//String html = onLinePay(merId, orderId, "10000", txnTime);
		//System.err.println(html);
		//System.err.println(JSONObject.toJSONString(AppPayA("20210719194114575","1000" , txnTime, "1")));
		//String onLinePay = onLinePay(merId, orderId, "10000", txnTime);System.err.println(onLinePay);


		try {
			applePay(merId,orderId,txnTime,"1000");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 *
	 * @param orderId 	订单号
	 * @param txnAmt	金额
	 * @param txnTime	交易时间
	 * @param OrderType	订单类型
	 */
	public static Map<String, Object> payMoney(String orderId, String txnAmt , String txnTime, String OrderType){

		return AppPayA(orderId,txnAmt,txnTime,OrderType);
	}

	/**
	 *
	 * @param orderId 	订单号
	 * @param txnAmt	金额
	 * @param txnTime	交易时间
	 */
	public static Map<String,Object>  AppPayA(String orderId,String txnAmt ,String txnTime,String orderType){
		Map<String,Object> logs=new HashMap<>(3);
		//加载classpath下的acp_sdk.properties文件内容
		SDKConfig.getConfig().loadPropertiesFromSrc();
		Map<String, String> contentData = new HashMap<String, String>();

		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
		contentData.put("version", DemoBase.version);            //版本号 全渠道默认值
		contentData.put("encoding", DemoBase.encoding);     //字符集编码 可以使用UTF-8,GBK两种方式
		contentData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
		contentData.put("txnType", "01");              		 	//交易类型 01:消费
		contentData.put("txnSubType", "01");           		 	//交易子类 01：消费
		contentData.put("bizType", "000201");          		 	//填写000201
		contentData.put("channelType", "08");          		 	//渠道类型 08手机

		/***商户接入参数***/
		contentData.put("merId", SDKConfig.getConfig().getMerId());   		 				//商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
		contentData.put("accessType", "0");            		 	//接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）
		contentData.put("orderId", orderId);        	 	    //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
		contentData.put("txnTime", txnTime);		 		    //订单发送时间，取系统时间，格式为yyyyMMddHHmmss，必须取当前时间，否则会报txnTime无效
		contentData.put("accType", "01");					 	//账号类型 01：银行卡02：存折03：IC卡帐号类型(卡介质)
		contentData.put("txnAmt", txnAmt);						//交易金额 单位为分，不能带小数点
		contentData.put("currencyCode", "156");                 //境内商户固定 156 人民币

		// 请求方保留域，
		// 透传字段，查询、通知、对账文件中均会原样出现，如有需要请启用并修改自己希望透传的数据。
		// 出现部分特殊字符时可能影响解析，请按下面建议的方式填写：
		// 1. 如果能确定内容不会出现&={}[]"'等符号时，可以直接填写数据，建议的方法如下。
//		contentData.put("reqReserved", "透传信息1|透传信息2|透传信息3");
		// 2. 内容可能出现&={}[]"'符号时：
		// 1) 如果需要对账文件里能显示，可将字符替换成全角＆＝｛｝【】“‘字符（自己写代码，此处不演示）；
		// 2) 如果对账文件没有显示要求，可做一下base64（如下）。
		//    注意控制数据长度，实际传输的数据长度不能超过1024位。
		//    查询、通知等接口解析时使用new String(Base64.decodeBase64(reqReserved), DemoBase.encoding);解base64后再对数据做后续解析。
//		contentData.put("reqReserved", Base64.encodeBase64String("任意格式的信息都可以".toString().getBytes(DemoBase.encoding)));

		//后台通知地址（需设置为外网能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，【支付失败的交易银联不会发送后台通知】
		//后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
		//注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
		//    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200或302，那么银联会间隔一段时间再次发送。总共发送5次，银联后续间隔1、2、4、5 分钟后会再次通知。
		//    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败

		contentData.put("backUrl", SDKConfig.getConfig().getBackUrl());
		//区分支付类型
        contentData.put("reqReserved",orderType);


		logs.put("contentData",contentData);

		/**对请求参数进行签名并发送http post请求，接收同步应答报文**/
		Map<String, String> reqData = AcpService.sign(contentData,DemoBase.encoding);			 //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		logs.put("reqData",reqData);
		String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();								 //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
		Map<String, String> rspData = AcpService.post(reqData,requestAppUrl,DemoBase.encoding);  //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
		logs.put("rspData",rspData);
		String tn = null;
		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
		if(!rspData.isEmpty()){
			if(AcpService.validate(rspData, DemoBase.encoding)){
				LogUtil.writeLog("验证签名成功");
				String respCode = rspData.get("respCode") ;
				if(("00").equals(respCode)){
					//成功,获取tn号
					 tn = rspData.get("tn");
					//TODO
				}else{
					//其他应答码为失败请排查原因或做失败处理
					//TODO
				}
			}else{
				LogUtil.writeErrorLog("验证签名失败");
				//TODO 检查验证签名失败的原因
			}
		}else{
			//未返回正确的http状态
			LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
		}
		String reqMessage = DemoBase.genHtmlResult(reqData);
		String rspMessage = DemoBase.genHtmlResult(rspData);
		LogUtil.writeLog("请求报文:<br/>"+reqMessage+"<br/>" + "应答报文:</br>"+rspMessage+"");
		//return tn;
		return logs;
	}

	public static String getTn(Map<String, String> rspData,Map<String, String> reqData){
		String tn = null;
		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
		if(!rspData.isEmpty()){
			if(AcpService.validate(rspData, DemoBase.encoding)){
				LogUtil.writeLog("验证签名成功");
				String respCode = rspData.get("respCode") ;
				if(("00").equals(respCode)){
					//成功,获取tn号
					tn = rspData.get("tn");
					//TODO
				}else{
					//其他应答码为失败请排查原因或做失败处理
					//TODO
				}
			}else{
				LogUtil.writeErrorLog("验证签名失败");
				//TODO 检查验证签名失败的原因
			}
		}else{
			//未返回正确的http状态
			LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
		}
		String reqMessage = DemoBase.genHtmlResult(reqData);
		String rspMessage = DemoBase.genHtmlResult(rspData);
		LogUtil.writeLog("请求报文:<br/>"+reqMessage+"<br/>" + "应答报文:</br>"+rspMessage+"");

		return tn;
	}

	/**
	 *
	 * @param merId 	商户号
	 * @param orderId 	订单号
	 * @param txnAmt	金额
	 * @param txnTime	交易时间
	 */
	public static String onLinePay(String merId,String orderId,String txnAmt ,String txnTime){

		//加载classpath下的acp_sdk.properties文件内容
		SDKConfig.getConfig().loadPropertiesFromSrc();

		Map<String, String> requestData = new HashMap<String, String>();


		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/

		requestData.put("version", DemoBase.version);//版本号，全渠道默认值

		requestData.put("encoding", DemoBase.encoding);//字符集编码，可以使用UTF-8,GBK两种方式

		requestData.put("signMethod", SDKConfig.getConfig().getSignMethod());//签名方法

		requestData.put("txnType", "01");//交易类型 ，01：消费

		requestData.put("txnSubType", "01");//交易子类型， 01：自助消费

		requestData.put("bizType", "000201");//业务类型，B2C网关支付，手机wap支付

		requestData.put("channelType", "07");//渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板 08：手机


		/***商户接入参数***/

		requestData.put("merId", merId);//商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号

		requestData.put("accessType", "0");//接入类型，0：直连商户

		requestData.put("orderId",orderId);//商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则

		requestData.put("txnTime", txnTime);//订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效

		requestData.put("currencyCode", "156");//交易币种（境内商户一般是156 人民币）

		requestData.put("txnAmt", txnAmt);//交易金额，单位分，不要带小数点

		requestData.put("backUrl", SDKConfig.getConfig().getBackUrl());//回调地址

		requestData.put("orderDesc", "test");//订单描述

		requestData.put("frontUrl", "http://www.specialUrl.com");//回调地址

		requestData.put("bizScene", "100001");//业务种类


		requestData.put("accType",  "01");   //账号类型 01：银行卡02：存折03：IC卡帐号类型(卡介质)
		//获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
		Map<String, String> submitFromData = AcpService.sign(requestData,DemoBase.encoding);




		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();

		//生成自动跳转的Html表单
		String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData,DemoBase.encoding);


		LogUtil.writeLog("打印请求HTML，此为请求报文，为联调排查问题的依据："+html);

		//将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
		return html;
		//resp.getWriter().write(html);
	}


	public  static  JSONObject applePay(String  merId,
										String  orderId,
										String  txnTime,
										String  txnAmt
										) throws IOException {
		//加载classpath下的acp_sdk.properties文件内容
		SDKConfig.getConfig().loadPropertiesFromSrc();
		JSONObject result = new JSONObject();
		Map<String, String> contentData = new HashMap<String, String>();
		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
		contentData.put("version", DemoBase.version); //版本号
		contentData.put("encoding", DemoBase.encoding); //字符集编码
		contentData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
		contentData.put("txnType", "01"); //交易类型
		contentData.put("txnSubType", "01"); //交易子类型
		contentData.put("bizType", "000802"); //业务类型
		contentData.put("channelType", "08"); //渠道类型
		/***商户接入参数***/
		contentData.put("merId", merId);  //商户号码（商户号码777290058110097仅做为测试调通交易使用，该商户号配置了需要对敏感信息加密）测试时请改成自己申请的商户号，【自己注册的测试777开头的商户号不支持代收产品】
		contentData.put("accessType", "0"); //接入类型，商户接入固定填0，不需修改
		contentData.put("orderId", orderId);  //商户订单号，8-32位数字字母，不能含“-”或“_”，可以自行定制规则
		contentData.put("txnTime", txnTime);   //订单发送时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
		contentData.put("currencyCode", "156");  //交易币种（境内商户一般是156 人民币）
		contentData.put("txnAmt", txnAmt);  //交易金额，单位分，不要带小数点
	/*	*//**
		 * 测试卡说明：
		 * 测试环境仅能支持下面代码写的accNo+pin+ICCardData测试，请切换生产后再用真实数据测试。
		 *//*
		contentData.put("accNo", "6259020040003629"); //卡号
		//////////如果商户号开通了 商户对敏感信息加密的权限那么，需要对 卡号accNo加密使用（正常情况业务默认配的都是不加密的，无视这里就可以）：
		// contentData.put("encryptCertId",AcpService.getEncryptCertId()); //上送敏感信息加密域的加密证书序列号
		// String accNo = AcpService.encryptData("6259020040003629", DemoBase.encoding); //这里测试的时候使用的是测试卡号，正式环境请使用真实卡号
		// contentData.put("accNo", accNo);
		//////////
		Map<String, String> customerInfoMap = new HashMap<String, String>();
		//主要填写 持卡人姓名（PassKitApi 中的paymentData->data->cardholderName）和支付密码（PassKitApi 中的paymentData->data-> encryptedPINData）
		//若PassKitApi中未返回，则无需上送
		customerInfoMap.put("customerNm", "tom");
		customerInfoMap.put("pin", "81103e8d9ea8afc5c6147b09e7cd2d8db88b82080021");
		//按规范加密和组装然后设置到map里。注意此域为非常规用法，请勿调用AcpService.getCustomerInfo方法组装，
		String customerInfoStr = AcpService.getCustomerInfo(customerInfoMap,"6259020040003629", DemoBase.encoding);

		contentData.put("customerInfo", customerInfoStr);
		Map<String, String> cardTransDataMap = new HashMap<String, String>();
		//PassKitApi 中的paymentData->data-> paymentData-> emvData
		cardTransDataMap.put("ICCardData", "nyYI/RZhrTzcBF+fNgIAAYECACGDaMOcdhXu5YlSgbnWkLdA1R4D4A7AQ3BAxKYgz7w6ElseNh0uWnMWPtLLtu+/Cg98b/1MSLOhfPfkTtm9QSBb8jqVBMC96llxK7aWP2yY/rt1tCLy9Up6dxjmf2IhbvvTX1LnOCnOEQn+");
		cardTransDataMap.put("carrierTp", "6"); //勿改，applePay后台接入固定用法
		cardTransDataMap.put("carrierAppTp", "2"); //勿改，applePay后台接入固定用法
		String cardTransDataStr = AcpService.getCardTransData(cardTransDataMap,null, DemoBase.encoding);
		contentData.put("cardTransData", cardTransDataStr);*/ //请参考getCardTransData方法内部，相关要素从读卡器采集。
		//后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
		//后台通知参数详见open.unionpay.com帮助中心 下载 产品接口规范 代收产品接口规范 代收交易 商户通知
		//注意:1.需设置为外网能访问，否则收不到通知 2.http https均可 3.收单后台通知后需要10秒内返回http200或302状态码
		// 4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
		// 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
		contentData.put("backUrl",  SDKConfig.getConfig().getBackUrl());
		// 请求方保留域，
		// 透传字段，查询、通知、对账文件中均会原样出现，如有需要请启用并修改自己希望透传的数据。
		// 出现部分特殊字符时可能影响解析，请按下面建议的方式填写：
		// 1. 如果能确定内容不会出现&={}[]"'等符号时，可以直接填写数据，建议的方法如下。
		// contentData.put("reqReserved", "透传信息1|透传信息2|透传信息3");
		// 2. 内容可能出现&={}[]"'符号时：
		// 1) 如果需要对账文件里能显示，可将字符替换成全角＆＝｛｝【】“‘字符（自己写代码，此处不演示）；
		// 2) 如果对账文件没有显示要求，可做一下base64（如下）。
		// 注意控制数据长度，实际传输的数据长度不能超过1024位。
		// 查询、通知等接口解析时使用new String(Base64.decodeBase64(reqReserved), DemoBase.encoding);解base64后再对数据做后续解析。
		// contentData.put("reqReserved", Base64.encodeBase64String("任意格式的信息都可以".toString().getBytes(DemoBase.encoding)));
		/**对请求参数进行签名并发送http post请求，接收同步应答报文**/
		Map<String, String> reqData = AcpService.sign(contentData, DemoBase.encoding); //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		//String url = SDKConfig.getConfig().getCardRequestUrl();
		String url = SDKConfig.getConfig().getAppRequestUrl(); //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl

		Map<String, String> rspData = AcpService.post(reqData, url, DemoBase.encoding); //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
		String reqMessage = DemoBase.genHtmlResult(reqData);
		String rspMessage = DemoBase.genHtmlResult(rspData);



		result.put("请求报文",reqMessage);
		result.put("应答报文",rspMessage);


		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		//应答码规范参考open.unionpay.com帮助中心 下载 产品接口规范 《平台接入接口规范-第5部分-附录
		if (!rspData.isEmpty()) {
			if (AcpService.validate(rspData, DemoBase.encoding)) {
				LogUtil.writeLog("验证签名成功");
				String respCode = rspData.get("respCode");
				if (("00").equals(respCode)) {
				//交易已受理(不代表交易已成功），等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。
				//TODO
					result.put("msg","受理成功");
				} else if (("03").equals(respCode) ||
						("04").equals(respCode) ||
						("05").equals(respCode)) {
					//后续需发起交易状态查询交易确定交易状态
					//TODO
					result.put("msg","处理超时，请稍后查询。");
				} else {
					//其他应答码为失败请排查原因
					//TODO
					result.put("msg","msg"+rspData.get("respMsg"));
				}
			} else {
				LogUtil.writeErrorLog("验证签名失败");
				//TODO 检查验证签名失败的原因
				result.put("msg","验证签名失败。");
			}
		} else {
			//TODO 未返回正确的http状态
			LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
			//resp.getWriter().write("未获取到返回报文或返回http状态码非200。<br>\n");
		}

		return null;
	}
}
