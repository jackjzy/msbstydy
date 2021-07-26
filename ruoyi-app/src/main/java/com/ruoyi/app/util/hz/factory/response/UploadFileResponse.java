package com.ruoyi.app.util.hz.factory.response;
/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 16:48
 * @version 
 */
public class UploadFileResponse {
    private int status;
    private String body;
    private String errCode;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
