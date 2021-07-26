package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.CreateOrganizationsByThirdPartyUserIdData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 14:10
 * @version 
 */
public class CreateOrganizationsByThirdPartyUserIdResponse extends Response {
    private CreateOrganizationsByThirdPartyUserIdData data;

    public CreateOrganizationsByThirdPartyUserIdData getData() {
        return data;
    }

    public void setData(CreateOrganizationsByThirdPartyUserIdData data) {
        this.data = data;
    }
}
