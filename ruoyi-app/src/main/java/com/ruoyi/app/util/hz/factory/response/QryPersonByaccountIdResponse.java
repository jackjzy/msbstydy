package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.QryPersonData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 15:41
 * @version 
 */
public class QryPersonByaccountIdResponse extends Response {
    private QryPersonData data;

    public QryPersonData getData() {
        return data;
    }

    public void setData(QryPersonData data) {
        this.data = data;
    }
}
