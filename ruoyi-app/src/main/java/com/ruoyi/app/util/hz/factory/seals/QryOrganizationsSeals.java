package com.ruoyi.app.util.hz.factory.seals;

import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.QryOrganizationsSealsResponse;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description  悟空API查询机构印章
 * @author  澄泓
 * @date  2020/10/26 11:35
 * @version JDK1.7
 */
public class QryOrganizationsSeals extends Request<QryOrganizationsSealsResponse> {
    @JSONField(serialize = false)
    private String orgId;
    private Integer offset;
    private Integer size;

    //禁止构造无参对象
    private QryOrganizationsSeals() {}

    public QryOrganizationsSeals(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
        StringBuilder url=new StringBuilder("/v1/organizations/"+orgId+"/seals?");
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
