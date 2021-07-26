package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.GetFileSignUrlData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 10:52
 * @version 
 */
public class GetFileSignUrlResponse extends Response {
    private GetFileSignUrlData data;

    public GetFileSignUrlData getData() {
        return data;
    }

    public void setData(GetFileSignUrlData data) {
        this.data = data;
    }
}
