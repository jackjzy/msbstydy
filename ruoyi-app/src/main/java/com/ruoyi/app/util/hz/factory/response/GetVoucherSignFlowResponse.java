package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.GetVoucherSignFlowData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 11:22
 * @version 
 */
public class GetVoucherSignFlowResponse extends Response {
    private GetVoucherSignFlowData data;

    public GetVoucherSignFlowData getData() {
        return data;
    }

    public void setData(GetVoucherSignFlowData data) {
        this.data = data;
    }
}
