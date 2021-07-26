package com.ruoyi.app.util.hz.factory.response;

import com.ruoyi.app.util.hz.factory.response.other.SearchWordsPositionDataList;

/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 17:11
 * @version 
 */
public class SearchWordsPositionResponse extends Response {
    private SearchWordsPositionDataList data;

    public SearchWordsPositionDataList getData() {
        return data;
    }

    public void setData(SearchWordsPositionDataList data) {
        this.data = data;
    }
}
