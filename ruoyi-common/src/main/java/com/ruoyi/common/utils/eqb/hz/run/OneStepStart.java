package com.ruoyi.common.utils.eqb.hz.run;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.eqb.hz.comm.DeclareDetails;
import com.ruoyi.common.utils.eqb.hz.exception.DefineException;
import com.ruoyi.common.utils.eqb.hz.helper.AccountHelper;
import com.ruoyi.common.utils.eqb.hz.helper.FileTemplateHelper;
import com.ruoyi.common.utils.eqb.hz.helper.SignHelper;
import com.ruoyi.common.utils.eqb.hz.helper.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 签署流程测试启动
 * @author 宫清
 * @date 2019年7月21日 下午9:38:31
 * @since JDK1.7
 */
public class OneStepStart {

	private static final Logger LOGGER = LoggerFactory.getLogger(OneStepStart.class);

	static {
		// 声明条款
		DeclareDetails.showImportantMessage();
	}

	// -------------------------------------------公有方法start-------------------------------------------------------

	public static void main(String[] args) {


		try {
			String filePath = "files/test.pdf";

			LOGGER.info("---------------------获取token start------------------------------");
			TokenHelper.getTokenData();

			LOGGER.info("---------------------创建个人账号start-------------------------------");
//			JSONObject personAcctJson = AccountHelper.createPersonAcct("ZS123", "杨志杰", null, "420902199707115954", "17671850175", "17671850175@163.com");
//			String acctId = personAcctJson.getString("accountId");

			//JSONObject copierAcctJson = AccountHelper.createPersonAcct("LS123", null, null, "420902199707115954", "17671850175", "17671850175@163.com");
			//JSONObject copierAcctJson = AccountHelper.createPersonAcct("ZS243", null, null, "142222199905221821", "18519582882", "18519582882@163.com");
			JSONObject copierAcctJson = AccountHelper.createPersonAcct("ZS683", "段瑞瑶", null, "142222199905221821", "18519582882", "18519582882@163.com");
			String copierAcctId = copierAcctJson.getString("accountId");

			LOGGER.info("---------------------创建机构账号start----------------------------------");
//			JSONObject orgAcctJson = AccountHelper.createOrgAcct("BBBB", acctId, "**********有限公司", null, "52227058XT51M4AL62");
//			String orgId = orgAcctJson.getString("orgId");

			LOGGER.info("---------------------创建机构印章start----------------------------------");
//			JSONObject orgSealJson = SealHelper.createOrgTemplateSeal(orgId, "********印章", "BLUE", null, null, null, null, "TEMPLATE_ROUND", "STAR");
//			String orgSealId = orgSealJson.getString("sealId");
//
			LOGGER.info("---------------------通过上传方式创建文件start-----------------------------");
			String fileName = "劳动合同.pdf";
			JSONObject uploadJson = FileTemplateHelper.createFileByUpload(filePath, fileName, copierAcctId);
			String uploadUrl = uploadJson.getString("uploadUrl");
			String fileId = uploadJson.getString("fileId");

			LOGGER.info("---------------------文件流上传文件start---------------------------------");
			FileTemplateHelper.streamUpload(filePath, uploadUrl);

			LOGGER.info("---------------------一步发起签署start---------------------------------");
			JSONObject flowJson = SignHelper.oneStepFlow(copierAcctId, fileId, fileName, copierAcctId);

			System.err.println(flowJson);
			String flowId = flowJson.getString("flowId");

			LOGGER.info("---------------------签署流程开启 start-----------------------------");
			SignHelper.startSignFlow(flowId);
			JSONObject object = SignHelper.qrySignUrl(flowId, copierAcctId, null, "0");
			System.err.println(object);
			LOGGER.info("---------------------签署完成后，通知回调，平台方进行签署流程归档 start-----------------------------");
			SignHelper.archiveSignFlow(flowId);

			LOGGER.info("---------------------归档后，获取文件下载地址 start-----------------------------");
			SignHelper.downloadFlowDoc(flowId);

		} catch (DefineException e) {
			e.getE().printStackTrace();
		}

	}
}
