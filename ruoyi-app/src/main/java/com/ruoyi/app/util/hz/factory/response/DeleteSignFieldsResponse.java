package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.DeleteSignFieldsData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 11:07
 * @version 
 */
public class DeleteSignFieldsResponse extends Response {
    private DeleteSignFieldsData data;

    public DeleteSignFieldsData getData() {
        return data;
    }

    public void setData(DeleteSignFieldsData data) {
        this.data = data;
    }
}
