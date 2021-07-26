package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.DownDocumentsData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 10:34
 * @version 
 */
public class DownDocumentsResponse extends Response {

    private DownDocumentsData data;

    public DownDocumentsData getData() {
        return data;
    }

    public void setData(DownDocumentsData data) {
        this.data = data;
    }
}
