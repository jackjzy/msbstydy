package com.ruoyi.app.util.hz.factory.signfile.signers;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.QrySignersResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API流程签署人列表
 * @author  澄泓
 * @date  2020/10/28 16:31
 * @version JDK1.7
 */
public class QrySigners extends Request<QrySignersResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private QrySigners(){};
    public QrySigners(String flowId) {
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
        super.setUrl("/v1/signflows/"+flowId+"/signers");
        super.setRequestType(RequestType.GET);
    }
}
