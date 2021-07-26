package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.QrySealData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 18:31
 * @version 
 */
public class QryPersonSealsResponse extends Response {
    private QrySealData data;

    public QrySealData getData() {
        return data;
    }

    public void setData(QrySealData data) {
        this.data = data;
    }
}
