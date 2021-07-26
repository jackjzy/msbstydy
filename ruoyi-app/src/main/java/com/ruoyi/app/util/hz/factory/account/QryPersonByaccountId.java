package com.ruoyi.app.util.hz.factory.account;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.alibaba.fastjson.annotation.JSONField;
import com.ruoyi.app.util.hz.factory.response.QryPersonByaccountIdResponse;

/**
 * @description  悟空API查询个人账户（按照账户ID查询）
 * @author  澄泓
 * @date  2020/10/23 17:03
 * @version JDK1.7
 */
public class QryPersonByaccountId extends Request<QryPersonByaccountIdResponse> {
    @JSONField(serialize = false)
    private String accountId;

    //禁止构造无参对象
    private QryPersonByaccountId(){};

    public QryPersonByaccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/accounts/"+accountId);
        super.setRequestType(RequestType.GET);
    }
}
