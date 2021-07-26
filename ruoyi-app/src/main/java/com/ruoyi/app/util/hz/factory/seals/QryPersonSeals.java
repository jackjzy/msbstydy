package com.ruoyi.app.util.hz.factory.seals;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.QryPersonSealsResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API查询个人印章
 * @author  澄泓
 * @date  2020/10/26 11:27
 * @version JDK1.7
 */
public class QryPersonSeals extends Request<QryPersonSealsResponse> {
    @JSONField(serialize = false)
    private String accountId;
    private Integer offset;
    private Integer size;

    //禁止构造无参对象
    private QryPersonSeals() {}

    public QryPersonSeals(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public void build() {
        StringBuilder url=new StringBuilder("/v1/accounts/"+accountId+"/seals?");
        if(offset!=null){
            url.append("&offset="+offset);
        }
        if(size!=null){
            url.append("&size="+size);
        }
        super.setUrl(url.toString());
        super.setRequestType(RequestType.GET);
    }
}
