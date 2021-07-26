package com.ruoyi.common.utils.pay;

import java.util.Map;
/**
 * @Author: yzj
 * @Date: 2020/9/23 15:51
 */
public class VxPayUtil {
    /**
     * 将map转换为xml
     */
    public static String getMapToXml(Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append("<" + entry.getKey() + ">");
            sb.append(entry.getValue());
            sb.append("</" + entry.getKey() + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }
}



