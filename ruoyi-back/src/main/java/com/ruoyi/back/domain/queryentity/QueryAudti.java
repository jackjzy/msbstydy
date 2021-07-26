package com.ruoyi.back.domain.queryentity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.DatabaseMetaData;
import java.util.Date;

public class QueryAudti {
    private Long auditId;
    private String userName;
    private String phone;
    private Integer auditStatusId;//审核状态 1：审核中 、2：审核通过、3：审核驳回
    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date endTime;

    @Override
    public String toString() {
        return "QueryAudti{" +
                "auditId=" + auditId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", auditStatusId=" + auditStatusId +
                ", searchStr='" + searchStr + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }

    public QueryAudti() {
    }

    public QueryAudti(Long auditId, String userName, String phone, Integer auditStatusId, Date beginTime, Date endTime) {
        this.auditId = auditId;
        this.userName = userName;
        this.phone = phone;
        this.auditStatusId = auditStatusId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAuditStatusId() {
        return auditStatusId;
    }

    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
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
}
