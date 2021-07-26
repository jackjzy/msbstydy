package com.ruoyi.system.domain.querys;

import java.util.Date;

public class QueryShop {

    private Long shopId;
    private String shopName;
    private String phone;
    private String userName;
    private Date beginTime;
    private Date endTime;
    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;

    }

    @Override
    public String toString() {
        return "QueryShop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
