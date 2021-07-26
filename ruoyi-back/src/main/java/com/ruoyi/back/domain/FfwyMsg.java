package com.ruoyi.back.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.system.domain.admin.FfwyUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息管理对象 ffwy_msg
 * 
 * @author wemem
 * @date 2021-04-23
 */
public class FfwyMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息id */
    private Long msgId;

    /** 消息标题 */
    @Excel(name = "消息标题")
    private String title;

    /** 发送的消息 */
    @Excel(name = "发送的消息")
    private String msg;

    /** 消息状态 */
    @Excel(name = "消息状态")
    private String msgStatus;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date msgTime;

    /** 接收时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接收时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;


    /** 接收状态 */
    @Excel(name = "接收状态")
    private Long receiveStatus;
    /*接收用户id*/
    private Long userId;
    /*接收用户名称*/
    private String userName;
    /*接收用户类型*/
    private Integer msgTypeId;

    public Integer getMsgTypeId() {
        return msgTypeId;
    }

    public void setMsgTypeId(Integer msgTypeId) {
        this.msgTypeId = msgTypeId;
    }

    /*接收用户类型*/
    private FfwyMsgType ffwyMsgType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public FfwyMsgType getFfwyMsgType() {
        return ffwyMsgType;
    }

    public void setFfwyMsgType(FfwyMsgType ffwyMsgType) {
        this.ffwyMsgType = ffwyMsgType;
    }

    public void setMsgId(Long msgId)
    {
        this.msgId = msgId;
    }

    public Long getMsgId() 
    {
        return msgId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setMsg(String msg) 
    {
        this.msg = msg;
    }

    public String getMsg() 
    {
        return msg;
    }
    public void setMsgStatus(String msgStatus) 
    {
        this.msgStatus = msgStatus;
    }

    public String getMsgStatus() 
    {
        return msgStatus;
    }
    public void setMsgTime(Date msgTime) 
    {
        this.msgTime = msgTime;
    }

    public Date getMsgTime() 
    {
        return msgTime;
    }
    public void setReceiveTime(Date receiveTime) 
    {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() 
    {
        return receiveTime;
    }
    public void setReceiveStatus(Long receiveStatus) 
    {
        this.receiveStatus = receiveStatus;
    }

    public Long getReceiveStatus() 
    {
        return receiveStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("msgId", getMsgId())
            .append("title", getTitle())
            .append("msg", getMsg())
            .append("msgStatus", getMsgStatus())
            .append("msgTime", getMsgTime())
            .append("receiveTime", getReceiveTime())
            .append("receiveStatus", getReceiveStatus())
                .append("ffwyMsgType",getFfwyMsgType())
            .toString();
    }
}
