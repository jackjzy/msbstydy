package com.ruoyi.app.util.hz.factory.antfinsign;


import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.QrySignAntPushInfoResponse;

/**
 * @description  悟空API查询签署文件上链信息
 * @author  澄泓
 * @date  2020/10/28 17:37
 * @version JDK1.7
 */
public class QrySignAntPushInfo extends Request<QrySignAntPushInfoResponse> {
    private String flowId;

    public QrySignAntPushInfo(String flowId) {
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
        super.setUrl("/v1/querySignAntPushInfo");
        super.setRequestType(RequestType.POST);
    }
}
