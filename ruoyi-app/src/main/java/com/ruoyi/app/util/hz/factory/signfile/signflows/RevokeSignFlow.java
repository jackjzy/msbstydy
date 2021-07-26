package com.ruoyi.app.util.hz.factory.signfile.signflows;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.RevokeSignFlowResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API签署流程撤销
 * @author  澄泓
 * @date  2020/10/28 10:32
 * @version JDK1.7
 */
public class RevokeSignFlow extends Request<RevokeSignFlowResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private String operatorId;
    private String revokeReason;

    private RevokeSignFlow(){};
    public RevokeSignFlow(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/"+flowId+"/revoke");
        super.setRequestType(RequestType.PUT);
    }
}
