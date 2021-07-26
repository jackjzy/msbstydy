package com.ruoyi.app.util.hz.factory.signfile.signfields;

import com.ruoyi.app.util.hz.bean.Signfields;
import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.CreateHandSignResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API添加手动盖章签署区
 * @author  澄泓
 * @date  2020/10/28 15:57
 * @version JDK1.7
 */
public class CreateHandSign extends Request<CreateHandSignResponse> {
    @JSONField(serialize = false)
    private String flowId;
    private Signfields signfields;

    private CreateHandSign(){};
    public CreateHandSign(String flowId, Signfields signfields) {
        this.flowId = flowId;
        this.signfields = signfields;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Signfields getSignfields() {
        return signfields;
    }

    public void setSignfields(Signfields signfields) {
        this.signfields = signfields;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows/"+flowId+"/signfields/handSign");
        super.setRequestType(RequestType.POST);
    }
}
