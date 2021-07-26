package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.Data;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 10:13
 * @version 
 */
public class DeleteAttachmentsResponse extends Response {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
