package com.ruoyi.app.util.hz.factory.signfile;

import com.ruoyi.app.util.hz.bean.*;
import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.CreateFlowOneStepResponse;

/**
 * @description  悟空API一步发起签署 
 * @author  澄泓
 * @date  2020/10/27 11:26
 * @version JDK1.7
 */
public class CreateFlowOneStep extends Request<CreateFlowOneStepResponse> {
    private Attachments attachments;
    private Copiers copiers;
    private Docs docs;
    private FlowInfo flowInfo;
    private Signers signers;
    public CreateFlowOneStep(){
        super.setUrl("/api/v2/signflows/createFlowOneStep");
        super.setRequestType(RequestType.POST);
    }

    public CreateFlowOneStep(Docs docs, FlowInfo flowInfo, Signers signers) {
        this.docs = docs;
        this.flowInfo = flowInfo;
        this.signers = signers;
    }

    public Attachments getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

    public Copiers getCopiers() {
        return copiers;
    }

    public void setCopiers(Copiers copiers) {
        this.copiers = copiers;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    public FlowInfo getFlowInfo() {
        return flowInfo;
    }

    public void setFlowInfo(FlowInfo flowInfo) {
        this.flowInfo = flowInfo;
    }

    public Signers getSigners() {
        return signers;
    }

    public void setSigners(Signers signers) {
        this.signers = signers;
    }

    @Override
    public void build() {
        super.setUrl("/api/v2/signflows/createFlowOneStep");
        super.setRequestType(RequestType.POST);
    }
}
