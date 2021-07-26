package com.ruoyi.app.util.hz.factory.account;


import com.alibaba.fastjson.annotation.JSONField;
import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.UpdatePersonAccountByAccountIdResponse;

/**
 * @description  悟空API个人账户修改(按照账号ID修改)
 * @author  澄泓
 * @date  2020/10/23 15:08
 * @version JDK1.7
 */
public class UpdatePersonAccountByAccountId extends Request<UpdatePersonAccountByAccountIdResponse> {
    @JSONField(serialize = false)
    private String accountId;
    private String email;
    private String mobile;
    private String name;
    private String idType;
    private String idNumber;

    //禁止构造无参对象
    private UpdatePersonAccountByAccountId(){}

    public UpdatePersonAccountByAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public void build() {
        super.setUrl("/v1/accounts/"+ accountId);
        super.setRequestType(RequestType.PUT);
    }
}
