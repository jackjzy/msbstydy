package com.ruoyi.app.util.hz.factory.seals;


import com.alibaba.fastjson.annotation.JSONField;
import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.DeletePersonSealResponse;

/**
 * @description  悟空API删除个人印章
 * @author  澄泓
 * @date  2020/10/26 11:44
 * @version JDK1.7
 */
public class DeletePersonSeal extends Request<DeletePersonSealResponse> {
    @JSONField(serialize = false)
    private String accountId;
    @JSONField(serialize = false)
    private String sealId;

    //禁止构造无参对象
    private DeletePersonSeal() {}

    public DeletePersonSeal(String accountId, String sealId) {
        this.accountId = accountId;
        this.sealId = sealId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/accounts/"+accountId+"/seals/"+sealId);
        super.setRequestType(RequestType.DELETE);
    }
}
