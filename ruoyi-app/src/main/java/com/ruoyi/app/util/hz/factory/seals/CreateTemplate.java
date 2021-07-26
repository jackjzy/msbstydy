package com.ruoyi.app.util.hz.factory.seals;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.CreateTemplateResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API创建个人/机构图片印章
 * @author  澄泓
 * @date  2020/10/26 10:37
 * @version JDK1.7
 */
public class CreateTemplate extends Request<CreateTemplateResponse> {
    @JSONField(serialize = false)
    private String accountId;
    private String alias;
    private Integer height;
    private Integer width;
    private String type;
    private String data;
    private boolean transparentFlag;

    //禁止构造无参对象
    private CreateTemplate() {}

    public CreateTemplate(String accountId, String type, String data) {
        this.accountId = accountId;
        this.type = type;
        this.data = data;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isTransparentFlag() {
        return transparentFlag;
    }

    public void setTransparentFlag(boolean transparentFlag) {
        this.transparentFlag = transparentFlag;
    }

    @Override
    public void build() {
        super.setUrl("/v1/accounts/"+accountId+"/seals/image");
        super.setRequestType(RequestType.POST);
    }
}
