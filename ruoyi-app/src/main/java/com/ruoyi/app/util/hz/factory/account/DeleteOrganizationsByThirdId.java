package com.ruoyi.app.util.hz.factory.account;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.alibaba.fastjson.annotation.JSONField;
import com.ruoyi.app.util.hz.factory.response.DeleteOrganizationsByThirdIdResponse;

/**
 * @description  悟空API注销机构账号（按照第三方机构ID注销）
 * @author  澄泓
 * @date  2020/10/23 20:19
 * @version JDK1.7
 */
public class DeleteOrganizationsByThirdId extends Request<DeleteOrganizationsByThirdIdResponse> {
    @JSONField(serialize = false)
    private String thirdPartyUserId;

    //禁止构造无参对象
    private DeleteOrganizationsByThirdId() {}

    public DeleteOrganizationsByThirdId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    public String getThirdPartyUserId() {
        return thirdPartyUserId;
    }

    public void setThirdPartyUserId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/organizations/deleteByThirdId?thirdPartyUserId="+thirdPartyUserId);
        super.setRequestType(RequestType.DELETE);
    }
}
