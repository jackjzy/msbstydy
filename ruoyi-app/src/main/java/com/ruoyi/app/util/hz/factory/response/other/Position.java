package com.ruoyi.app.util.hz.factory.response.other;
/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/29 17:13
 * @version 
 */
public class Position {
    private int pageIndex;
    private CoordinateList coordinateList;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public CoordinateList getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(CoordinateList coordinateList) {
        this.coordinateList = coordinateList;
    }
}
