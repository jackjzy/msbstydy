package com.ruoyi.app.util.hz.factory.seals;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.CreatePersonalTemplateResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API创建个人模板印章
 * @author  澄泓
 * @date  2020/10/26 9:54
 * @version JDK1.7
 */
public class CreatePersonalTemplate extends Request<CreatePersonalTemplateResponse>{
    @JSONField(serialize = false)
    private String accountId;
    private String alias;
    private String color;
    private Integer height;
    private Integer width;
    private String type;

    //禁止构造无参对象
    private CreatePersonalTemplate() {}

    public CreatePersonalTemplate(String accountId, String color, String type) {
        this.accountId = accountId;
        this.color = color;
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId){
        this.accountId = accountId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Override
    public void build() {
        super.setUrl("/v1/accounts/"+accountId+"/seals/personaltemplate");
        super.setRequestType(RequestType.POST);
    }
}
