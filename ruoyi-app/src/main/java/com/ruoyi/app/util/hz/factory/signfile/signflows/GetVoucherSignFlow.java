package com.ruoyi.app.util.hz.factory.signfile.signflows;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.GetVoucherSignFlowResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API流程签署数据存储凭据
 * @author  澄泓
 * @date  2020/10/28 10:58
 * @version JDK1.7
 */
public class GetVoucherSignFlow extends Request<GetVoucherSignFlowResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private GetVoucherSignFlow(){};
    public GetVoucherSignFlow(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    @Override
    public void build() {
        super.setUrl("/api/v2/signflows/"+flowId+"/getVoucher");
        super.setRequestType(RequestType.GET);
    }
}
