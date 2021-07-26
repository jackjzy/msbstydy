package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.CreateTemplateData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 18:15
 * @version 
 */
public class CreateTemplateResponse extends Response {

    private CreateTemplateData createTemplateData;

    public CreateTemplateData getCreateTemplateData() {
        return createTemplateData;
    }

    public void setCreateTemplateData(CreateTemplateData createTemplateData) {
        this.createTemplateData = createTemplateData;
    }
}
