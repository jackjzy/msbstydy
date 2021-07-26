package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.GetFileUploadUrlData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 16:41
 * @version 
 */
public class GetFileUploadUrlResponse extends Response {
    private GetFileUploadUrlData data;

    public GetFileUploadUrlData getData() {
        return data;
    }

    public void setData(GetFileUploadUrlData data) {
        this.data = data;
    }
}
