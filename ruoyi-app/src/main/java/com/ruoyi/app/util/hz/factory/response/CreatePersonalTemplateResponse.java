package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.CreateTemplateData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 18:12
 * @version 
 */
public class CreatePersonalTemplateResponse extends Response {
    private CreateTemplateData data;

    public CreateTemplateData getData() {
        return data;
    }

    public void setData(CreateTemplateData data) {
        this.data = data;
    }
}
