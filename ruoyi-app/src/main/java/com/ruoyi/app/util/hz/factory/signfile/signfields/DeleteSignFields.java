package com.ruoyi.app.util.hz.factory.signfile.signfields;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.DeleteSignFieldsResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API删除签署区
 * @author  澄泓
 * @date  2020/10/28 16:23
 * @version JDK1.7
 */
public class DeleteSignFields extends Request<DeleteSignFieldsResponse> {
    @JSONField(serialize = false)
    private String flowId;
    @JSONField(serialize = false)
    private String signfieldIds;

    private DeleteSignFields(){};
    public DeleteSignFields(String flowId, String signfieldIds) {
        this.flowId = flowId;
        this.signfieldIds = signfieldIds;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getSignfieldIds() {
        return signfieldIds;
    }

    public void setSignfieldIds(String signfieldIds) {
        this.signfieldIds = signfieldIds;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/"+flowId+"/signfields?signfieldIds="+signfieldIds);
        super.setRequestType(RequestType.DELETE);
    }
}
