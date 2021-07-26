package com.ruoyi.back.domain.queryentity;

import java.util.Date;

public class QueryMsg {

    private String msg;
    private Date beginTime;
    private Date msgTime;
    private Date endTime;

    public QueryMsg() {
    }

    public QueryMsg(String msg, Date beginTime, Date passTime, Date endTime) {
        this.msg = msg;
        this.beginTime = beginTime;
        this.msgTime = passTime;
        this.endTime = endTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getPassTime() {
        return msgTime;
    }

    public void setPassTime(Date passTime) {
        this.msgTime = passTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "QueryMsg{" +
                "msg='" + msg + '\'' +
                ", beginTime=" + beginTime +
                ", passTime=" + msgTime +
                ", endTime=" + endTime +
                '}';
    }
}
