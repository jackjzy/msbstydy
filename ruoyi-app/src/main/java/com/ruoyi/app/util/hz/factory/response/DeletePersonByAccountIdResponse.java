package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.Data;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 14:23
 * @version 
 */
public class DeletePersonByAccountIdResponse extends Response {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
