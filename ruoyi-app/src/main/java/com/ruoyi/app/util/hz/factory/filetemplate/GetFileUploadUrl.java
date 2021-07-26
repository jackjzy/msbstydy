package com.ruoyi.app.util.hz.factory.filetemplate;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.GetFileUploadUrlResponse;

/**
 * @description  悟空API通过上传方式创建文件
 * @author  澄泓
 * @date  2020/10/26 14:33
 * @version JDK1.7
 */
public class GetFileUploadUrl extends Request<GetFileUploadUrlResponse>{
    private String contentMd5;
    private String contentType;
    private boolean convert2Pdf;
    private String fileName;
    private Integer fileSize;
    private String accountId;

    //禁止构造无参对象
    private GetFileUploadUrl() {}

    public GetFileUploadUrl(String contentMd5, String contentType, boolean convert2Pdf, String fileName, Integer fileSize) {
        this.contentMd5 = contentMd5;
        this.contentType = contentType;
        this.convert2Pdf = convert2Pdf;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getContentMd5() {
        return contentMd5;
    }

    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isConvert2Pdf() {
        return convert2Pdf;
    }

    public void setConvert2Pdf(boolean convert2Pdf) {
        this.convert2Pdf = convert2Pdf;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/files/getUploadUrl");
        super.setRequestType(RequestType.POST);
    }
}
