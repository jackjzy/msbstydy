package com.ruoyi.app.util.hz.factory.seals;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.CreateOfficialTemplateResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API创建机构模板印章
 * @author  澄泓
 * @date  2020/10/26 10:15
 * @version JDK1.7
 */
public class CreateOfficialTemplate extends Request<CreateOfficialTemplateResponse> {
    @JSONField(serialize = false)
    private String orgId;
    private String alias;
    private String color;
    private Integer height;
    private Integer width;
    private String htext;
    private String qtext;
    private String type;
    private String central;

    //禁止构造无参对象
    private CreateOfficialTemplate() {}

    public CreateOfficialTemplate(String orgId, String color, String type, String central) {
        this.orgId = orgId;
        this.color = color;
        this.type = type;
        this.central = central;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String accountId) {
        this.orgId = accountId;
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

    public String getHtext() {
        return htext;
    }

    public void setHtext(String htext) {
        this.htext = htext;
    }

    public String getQtext() {
        return qtext;
    }

    public void setQtext(String qtext) {
        this.qtext = qtext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
    }

    @Override
    public void build() {
        super.setUrl("/v1/organizations/"+orgId+"/seals/officialtemplate");
        super.setRequestType(RequestType.POST);
    }
}
