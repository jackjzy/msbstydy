package com.ruoyi.app.util.hz.factory.seals;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.DeleteOrganizationsSealResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API删除机构印章
 * @author  澄泓
 * @date  2020/10/26 11:52
 * @version JDK1.7
 */
public class DeleteOrganizationsSeal extends Request<DeleteOrganizationsSealResponse> {
    @JSONField(serialize = false)
    private String orgId;
    @JSONField(serialize = false)
    private String sealId;

    //禁止构造无参对象
    private DeleteOrganizationsSeal() {}

    public DeleteOrganizationsSeal(String orgId, String sealId) {
        this.orgId = orgId;
        this.sealId = sealId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/organizations/"+orgId+"/seals/"+sealId);
        super.setRequestType(RequestType.DELETE);
    }
}
