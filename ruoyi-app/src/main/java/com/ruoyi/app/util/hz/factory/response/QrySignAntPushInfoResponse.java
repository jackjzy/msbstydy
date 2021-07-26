package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.QrySignAntPushInfoData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 15:57
 * @version 
 */
public class QrySignAntPushInfoResponse extends Response {
    private QrySignAntPushInfoData data;

    public QrySignAntPushInfoData getData() {
        return data;
    }

    public void setData(QrySignAntPushInfoData data) {
        this.data = data;
    }
}
