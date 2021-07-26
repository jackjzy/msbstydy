package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.CreateTemplateByUploadUrlData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 16:47
 * @version 
 */
public class CreateTemplateByUploadUrlResponse extends Response {
    private CreateTemplateByUploadUrlData data;

    public CreateTemplateByUploadUrlData getData() {
        return data;
    }

    public void setData(CreateTemplateByUploadUrlData data) {
        this.data = data;
    }
}
