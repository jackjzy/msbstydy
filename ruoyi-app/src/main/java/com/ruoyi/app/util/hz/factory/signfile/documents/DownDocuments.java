package com.ruoyi.app.util.hz.factory.signfile.documents;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.DownDocumentsResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API流程文档下载
 * @author  澄泓
 * @date  2020/10/28 11:17
 * @version JDK1.7
 */
public class DownDocuments extends Request<DownDocumentsResponse> {
    @JSONField(serialize = false)
    private String flowId;

    private DownDocuments(){};
    public DownDocuments(String flowId) {
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
        super.setUrl("/v1/signflows/"+flowId+"/documents");
        super.setRequestType(RequestType.GET);
    }
}
