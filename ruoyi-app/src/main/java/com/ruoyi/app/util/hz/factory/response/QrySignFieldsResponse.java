package com.ruoyi.app.util.hz.factory.response;


import com.ruoyi.app.util.hz.factory.response.data.QrySignFieldsData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 11:10
 * @version 
 */
public class QrySignFieldsResponse extends Response {
    private QrySignFieldsData data;

    public QrySignFieldsData getData() {
        return data;
    }

    public void setData(QrySignFieldsData data) {
        this.data = data;
    }
}
