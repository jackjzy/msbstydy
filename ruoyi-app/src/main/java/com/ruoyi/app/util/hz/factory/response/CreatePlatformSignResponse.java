package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.CreateSignData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 11:07
 * @version 
 */
public class CreatePlatformSignResponse extends Response {
    private CreateSignData data;

    public CreateSignData getData() {
        return data;
    }

    public void setData(CreateSignData data) {
        this.data = data;
    }
}
