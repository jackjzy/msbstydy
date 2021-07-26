package com.ruoyi.app.util.hz.factory.response.other;

import com.ruoyi.app.util.hz.factory.response.data.SearchWordsPositionData;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 17:24
 * @version 
 */
public class SearchWordsPositionDataList extends ArrayList {
    @Override
    public SearchWordsPositionData get(int index) {
        Object o = super.get(index);
        SearchWordsPositionData searchWordsPositionData = JSON.parseObject(o.toString(), SearchWordsPositionData.class);
        return searchWordsPositionData;
    }
}
