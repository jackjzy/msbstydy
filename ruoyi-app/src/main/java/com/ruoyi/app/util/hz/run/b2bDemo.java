package com.ruoyi.app.util.hz.run;

import com.ruoyi.app.util.hz.bean.*;
import com.ruoyi.app.util.hz.comm.DeclareDetails;
import com.ruoyi.app.util.hz.comm.FileBean;
import com.ruoyi.app.util.hz.enums.HeaderConstant;
import com.ruoyi.app.util.hz.exception.DefineException;
import com.ruoyi.app.util.hz.factory.Factory;
import com.ruoyi.app.util.hz.factory.account.CreateOrganizationsByThirdPartyUserId;
import com.ruoyi.app.util.hz.factory.account.CreatePersonByThirdPartyUserId;
import com.ruoyi.app.util.hz.factory.base.Account;
import com.ruoyi.app.util.hz.factory.base.FileTemplate;
import com.ruoyi.app.util.hz.factory.base.Seals;
import com.ruoyi.app.util.hz.factory.base.SignFile;
import com.ruoyi.app.util.hz.factory.filetemplate.GetFileUploadUrl;
import com.ruoyi.app.util.hz.factory.filetemplate.UploadFile;
import com.ruoyi.app.util.hz.factory.response.*;
import com.ruoyi.app.util.hz.factory.seals.CreateOfficialTemplate;
import com.ruoyi.app.util.hz.factory.signfile.documents.CreateDocuments;
import com.ruoyi.app.util.hz.factory.signfile.signers.GetFileSignUrl;
import com.ruoyi.app.util.hz.factory.signfile.signfields.CreateHandSign;
import com.ruoyi.app.util.hz.factory.signfile.signflows.CreateSignFlow;
import com.ruoyi.app.util.hz.factory.signfile.signflows.StartSignFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @description  悟空API企业和企业签署示例
 * @author  澄泓
 * @date  2020/10/30 15:13
 * @version JDK1.7
 */
public class b2bDemo {
    //此示例为企业和企业签署场景的示例代码，签署方式为分步发起签署，如果需要一步发起签署，签署部分代码示例可参考b2bDemo
    static{
        DeclareDetails.showImportantMessage();//demo声明明细
//        正式环境域名：https://openapi.esign.cn
//        模拟环境（沙箱环境）域名：https://smlopenapi.esign.cn
        String host="https://smlopenapi.esign.cn"; //网关
        String project_id="7438866430";//应用id
        String project_scert="b9f3eb85f1aab0b2df793b09dd2eb45e";//密钥
        Factory.init(host,project_id,project_scert);//初始化，传入请求网关和应用id以及密钥,全局运行一次
        Factory.setDebug(true);//开启日志，测试阶段建议开启，方便记录数据，日志保存在根目录的log.txt文件中
    }
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(b2bDemo.class);
        try{
            String filePath = "files/test.pdf";//文件地址
            //-----------------------个人账号信息用于创建个人账号接口传入-----------------------------
            String thirdPartyUserIdPsn="12321323";//thirdPartyUserId参数，用户唯一标识，自定义保持唯一即可
            String namePsn="杨志杰";//name参数，姓名
            String idTypePsn="CRED_PSN_CH_IDCARD";//idType参数，证件类型
            String idNumberPsn="420902199707115954";//idNumber参数，证件号
            String mobilePsn="17671850175";//mobile参数，手机号

            //------------------------企业账号信息1用于创建机构账号接口传入----------------
            String thirdPartyUserIdOrg1="121232131212312312";//thirdPartyUserId参数，用户唯一标识，自定义保持唯一即可
            String nameOrg1="测试公司1";//name参数，机构名称
            String idTypeOrg1="CRED_ORG_USCC";//idType参数，证件类型
            String idNumberOrg1="";//idNumber参数,机构证件号

            //------------------------企业账号信息2用于创建机构账号接口传入----------------
//            String thirdPartyUserIdOrg2="1212321231213312312";//thirdPartyUserId参数，用户唯一标识，自定义保持唯一即可
//            String nameOrg2="测试公司2";//name参数，机构名称
//            String idTypeOrg2="CRED_ORG_USCC";//idType参数，证件类型
//            String idNumberOrg2="";//idNumber参数,机构证件号

            //创建个人账号,企业账号签署需要有经办人，先创建经办人个人账号
            //com.ruoyi.app.util.hz.factory.base.Account;
            //Account类是账号相关的功能类，所有账号相关方法由Account发起
            logger.info("------------------ 创建个人账号 start ---------------");
            CreatePersonByThirdPartyUserId createPsn = Account.createPersonByThirdPartyUserId(
                    thirdPartyUserIdPsn,
                    namePsn,
                    idTypePsn,
                    idNumberPsn);
            createPsn.setMobile(mobilePsn);
            CreatePersonByThirdPartyUserIdResponse createPsnResp = createPsn.execute();//execute方法发起请求
            String accountId = createPsnResp.getData().getAccountId();//生成的个人账号保存好，后续接口调用需要使用
            logger.info("------------------ 创建个人账号 end -----------------");

            logger.info("------------------ 创建企业账号 start -----------------");
            CreateOrganizationsByThirdPartyUserId createOrg1 = Account.createOrganizationsByThirdPartyUserId(
                    thirdPartyUserIdOrg1,
                    accountId,
                    nameOrg1,
                    idTypeOrg1,
                    idNumberOrg1);
            CreateOrganizationsByThirdPartyUserIdResponse createOrgResp1 = createOrg1.execute();//execute方法发起请求
            String orgId1 = createOrgResp1.getData().getOrgId();//生成的企业账号1保存好，后续接口调用需要使用

//            CreateOrganizationsByThirdPartyUserId createOrg2 = Account.createOrganizationsByThirdPartyUserId(
//                    thirdPartyUserIdOrg2,
//                    accountId,
//                    nameOrg2,
//                    idTypeOrg2,
//                    idNumberOrg2);
//            CreateOrganizationsByThirdPartyUserIdResponse createOrgResp2 = createOrg2.execute();//execute方法发起请求
//            String orgId2 = createOrgResp2.getData().getOrgId();//生成的企业账号2保存好，后续接口调用需要使用
            logger.info("------------------ 创建企业账号 end -----------------");

            logger.info("------------------ 创建企业模板印章 start -----------------");
            CreateOfficialTemplate orgSealTemp1 = Seals.createOfficialTemplate(orgId1, "RED", "TEMPLATE_ROUND", "STAR");
            CreateOfficialTemplateResponse orgSealTempResp1 = orgSealTemp1.execute();
            String orgSealId1 = orgSealTempResp1.getData().getSealId();//企业1印章id保存好，后续需要使用

//            CreateOfficialTemplate orgSealTemp2 = Seals.createOfficialTemplate(orgId2, "RED", "TEMPLATE_ROUND", "STAR");
//            CreateOfficialTemplateResponse orgSealTempResp2 = orgSealTemp2.execute();
//            String orgSealId2 = orgSealTempResp2.getData().getSealId();//企业2印章id保存好，后续需要使用
            logger.info("------------------ 创建企业模板印章 end -----------------");

            logger.info("------------------ 通过上传方式创建文件 start -----------------");
            //com.ruoyi.app.util.hz.factory.base.FileTemplate;
            //FileTemplate是文件模板功能类，所有文件模板相关的接口由FileTemplate发起
            FileBean fileBean = new FileBean(filePath);//用于本地获取文件大小，名称等信息
            GetFileUploadUrl fileUploadUrl = FileTemplate.getFileUploadUrl(fileBean.getFileContentMD5(),
                    HeaderConstant.CONTENTTYPE_PDF.VALUE(),
                    false,
                    fileBean.getFileName(),
                    fileBean.getFileSize());
            GetFileUploadUrlResponse fileUploadUrlResp = fileUploadUrl.execute();
            String fileId = fileUploadUrlResp.getData().getFileId();//文件id保存好，后续使用
            String uploadUrl = fileUploadUrlResp.getData().getUploadUrl();//上传url保存好，后续使用
            logger.info("------------------ 通过上传方式创建文件 end -----------------");

            logger.info("------------------ 文件流上传方法 start -----------------");
            UploadFile uploadFile = FileTemplate.uploadFile(uploadUrl, filePath, HeaderConstant.CONTENTTYPE_PDF.VALUE());
            UploadFileResponse uploadFileResp = uploadFile.excute();
            logger.info("------------------ 文件流上传方法 end -----------------");

            logger.info("------------------ 分步发起签署 start -----------------");
            //这里以分步发起签署为例，如果想查看一步发起签署代码示例，可以参考b2cDemo
            //com.ruoyi.app.util.hz.factory.base.SignFile;
            //SignFile是文件模板功能类，所有文件模板相关的接口由SignFile发起
            logger.info("------ 签署流程创建 start ---------");
            CreateSignFlow createSignFlow = SignFile.createSignFlow("b2b合同签署测试");
            CreateSignFlowResponse createSignFlowResp = createSignFlow.execute();
            String flowId = createSignFlowResp.getData().getFlowId();//流程id，保存好
            logger.info("------ 签署流程创建 end ---------");

            logger.info("------ 流程文档添加 start ---------");
            Docs docs = new Docs();//入参是array格式时，先构造对象参数的array
            docs.add(new Doc().setFileId(fileId));//向array传入对象
            CreateDocuments documents = SignFile.createDocuments(flowId, docs);
            documents.execute();
            logger.info("------ 流程文档添加 end ---------");

            logger.info("------ 添加手动盖章签署区 start ---------");
            Signfields signfields = new Signfields();
            signfields.add(new Signfield()//企业1签署去信息添加
                    .setFileId(fileId)
                    .setSignerAccountId(accountId)
                    .setAuthorizedAccountId(orgId1)
                    .setPosBean(new PosBean()
                            .setPosPage("1")
                            .setPosX(234)
                            .setPosY(20))
                    .setActorIndentityType("2"));

//            signfields.add(new Signfield()//企业2签署去信息添加
//                    .setFileId(fileId)
//                    .setSignerAccountId(accountId)
//                    .setAuthorizedAccountId(orgId2)
//                    .setPosBean(new PosBean()
//                            .setPosPage("1")
//                            .setPosX(456)
//                            .setPosY(345))
//                    .setActorIndentityType("2"));

            CreateHandSign handSign = SignFile.createHandSign(flowId, signfields);
            handSign.execute();
            logger.info("------ 添加手动盖章签署区 end ---------");
            logger.info("------------------ 分步发起签署 end -----------------");

            logger.info("------------------ 签署流程开启 start -----------------");
            StartSignFlow startSignFlow = SignFile.startSignFlow(flowId);
            startSignFlow.execute();
            logger.info("------------------ 签署流程开启 end -----------------");

            //开启流程后会向个人实名手机号发送签署信息，会向企业签署经办人发送信息，也可以调用获取签署地址接口获取签署链接
            logger.info("------------------ 获取签署地址 start -----------------");
            GetFileSignUrl fileSignUrl = SignFile.getFileSignUrl(flowId, accountId);
            fileSignUrl.setOrganizeId(orgId1);
            GetFileSignUrlResponse fileSignUrlResp1 = fileSignUrl.execute();
            String shortUrl1 = fileSignUrlResp1.getData().getShortUrl();//响应的签署链接，复制到浏览器访问即可打开签署页面
            System.out.println("企业1的签署短连接,复制到浏览器打开\n"+shortUrl1);


//            fileSignUrl.setOrganizeId(orgId2);
//            GetFileSignUrlResponse fileSignUrlResp2 = fileSignUrl.execute();
//            String shortUrl2 = fileSignUrlResp2.getData().getShortUrl();//响应的签署链接，复制到浏览器访问即可打开签署页面
//            System.out.println("企业2的签署短连接,复制到浏览器打开\n"+shortUrl2);

            logger.info("------------------ 获取签署地址 end -----------------");

            //全部签署完成以后进行归档，归档以后就不能再修改了
//            logger.info("------------------ 签署流程归档 start -----------------");
//            ArchiveSignFlow archiveSignFlow = SignFile.archiveSignFlow(flowId);
//            archiveSignFlow.execute();
//            logger.info("------------------ 签署流程归档 end -----------------");

        }catch (DefineException e){
            e.printStackTrace();
            System.out.println(e.getMessage()+e.getCause());
        }
        System.out.println("正常运行结束");
    }


}
