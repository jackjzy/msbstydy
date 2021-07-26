package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.data.PdfVerifyData;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 10:40
 * @version 
 */
public class PdfVerifyResponse extends Response {
    private PdfVerifyData data;

    public PdfVerifyData getData() {
        return data;
    }

    public void setData(PdfVerifyData data) {
        this.data = data;
    }
}
