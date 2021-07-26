package com.ruoyi.app.util.hz.factory.signfile.attachments;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.QryAttachmentsResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API流程附件列表
 * @author  澄泓
 * @date  2020/10/28 14:33
 * @version JDK1.7
 */
public class QryAttachments extends Request<QryAttachmentsResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private QryAttachments(){};
    public QryAttachments(String flowId) {
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
        super.setUrl("/v1/signflows/"+flowId+"/attachments");
        super.setRequestType(RequestType.GET);
    }
}
