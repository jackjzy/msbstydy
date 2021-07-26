package com.ruoyi.app.util.hz.factory.account;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.alibaba.fastjson.annotation.JSONField;
import com.ruoyi.app.util.hz.factory.response.QryOrganizationsByThirdIdResponse;

/**
 * @description  类说明
 * @author  澄泓
 * @date  2020/10/23 20:02
 * @version JDK1.7
 */
public class QryOrganizationsByThirdId extends Request<QryOrganizationsByThirdIdResponse> {
    @JSONField(serialize = false)
    private String thirdPartyUserId;

    //禁止构造无参对象
    public QryOrganizationsByThirdId() {}

    public QryOrganizationsByThirdId(String thirdPartyUserId) {
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
        super.setUrl("/v1/organizations/getByThirdId?thirdPartyUserId="+thirdPartyUserId);
        super.setRequestType(RequestType.GET);
    }
}
