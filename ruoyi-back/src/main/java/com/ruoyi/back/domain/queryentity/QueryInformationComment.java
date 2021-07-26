package com.ruoyi.back.domain.queryentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class QueryInformationComment {


    private String phonenumber;
    private String userName;
    private String informationComment;
    private Long parentId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public QueryInformationComment(String phonenumber, String userName, String informationComment, Long parentId, Date beginTime, Date endTime) {
        this.phonenumber = phonenumber;
        this.userName = userName;
        this.informationComment = informationComment;
        this.parentId = parentId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public QueryInformationComment() {
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInformationComment() {
        return informationComment;
    }

    public void setInformationComment(String informationComment) {
        this.informationComment = informationComment;
    }
}
