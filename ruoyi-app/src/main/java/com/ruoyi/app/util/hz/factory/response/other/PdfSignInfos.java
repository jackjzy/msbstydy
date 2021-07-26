package com.ruoyi.app.util.hz.factory.response.other;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 10:42
 * @version 
 */
public class PdfSignInfos extends ArrayList {
    @Override
    public PdfSignInfo get(int index) {
        Object o = super.get(index);
        PdfSignInfo pdfSignInfo = JSON.parseObject(o.toString(), PdfSignInfo.class);
        return pdfSignInfo;
    }
}
