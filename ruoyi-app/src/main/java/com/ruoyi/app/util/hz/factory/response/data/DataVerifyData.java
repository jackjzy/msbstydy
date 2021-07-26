package com.ruoyi.app.util.hz.factory.response.data;

import com.ruoyi.app.util.hz.factory.response.other.DataSignInfo;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 10:20
 * @version 
 */
public class DataVerifyData {
    private DataSignInfo signInfo;

    public DataSignInfo getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(DataSignInfo signInfo) {
        this.signInfo = signInfo;
    }
}
