package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.QryOrganizationsData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 15:07
 * @version 
 */
public class QryOrganizationsByOrgIdResponse extends Response {
    private QryOrganizationsData data;

    public QryOrganizationsData getData() {
        return data;
    }

    public void setData(QryOrganizationsData data) {
        this.data = data;
    }
}
