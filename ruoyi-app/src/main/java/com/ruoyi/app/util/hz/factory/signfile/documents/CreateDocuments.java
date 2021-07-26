package com.ruoyi.app.util.hz.factory.signfile.documents;

import com.ruoyi.app.util.hz.bean.Docs;
import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.CreateDocumentsResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API流程文档添加
 * @author  澄泓
 * @date  2020/10/28 11:06
 * @version JDK1.7
 */
public class CreateDocuments extends Request<CreateDocumentsResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private Docs docs;

    private CreateDocuments(){};
    public CreateDocuments(String flowId, Docs docs) {
        this.flowId = flowId;
        this.docs = docs;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/"+flowId+"/documents");
        super.setRequestType(RequestType.POST);
    }
}
