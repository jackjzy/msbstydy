package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.CheckAntfinNotaryData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 15:54
 * @version 
 */
public class CheckAntfinNotaryResponse extends Response {
    private CheckAntfinNotaryData data;

    public CheckAntfinNotaryData getData() {
        return data;
    }

    public void setData(CheckAntfinNotaryData data) {
        this.data = data;
    }
}
