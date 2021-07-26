package com.ruoyi.app.util.hz.factory.account;



import com.alibaba.fastjson.annotation.JSONField;
import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.DeletePersonByThirdIdResponse;

/**
 * @description  悟空API注销个人账户（按照第三方用户ID注销）
 * @author  澄泓
 * @date  2020/10/23 17:48
 * @version JDK1.7
 */
public class DeletePersonByThirdId extends Request<DeletePersonByThirdIdResponse> {
    @JSONField(serialize = false)
    private String thirdPartyUserId;

    //禁止构造无参对象
    private DeletePersonByThirdId() {
    }

    public DeletePersonByThirdId(String thirdPartyUserId) {
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
        super.setUrl("/v1/accounts/deleteByThirdId?thirdPartyUserId="+thirdPartyUserId);
        super.setRequestType(RequestType.DELETE);
    }
}
