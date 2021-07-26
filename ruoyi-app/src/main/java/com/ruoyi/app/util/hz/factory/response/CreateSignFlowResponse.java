package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.CreateSignFlowData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 11:20
 * @version 
 */
public class CreateSignFlowResponse extends Response {
    private CreateSignFlowData data;

    public CreateSignFlowData getData() {
        return data;
    }

    public void setData(CreateSignFlowData data) {
        this.data = data;
    }
}
