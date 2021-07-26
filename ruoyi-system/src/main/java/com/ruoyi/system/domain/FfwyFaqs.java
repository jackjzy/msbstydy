package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 【请填写功能名称】对象 ffwy_faqs
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public class FfwyFaqs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 常见问题 */
    private Long faqsId;

    /** 常见问题描述 */
    @Excel(name = "常见问题描述")
    private String faqsMsg;

    private String replyMsg;

    private FfwyFqasReply reply;

    private Date beginTime;

    private Date endTime;

    private String searchStr;

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    @Override
    public Date getBeginTime() {
        return beginTime;
    }

    @Override
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public FfwyFqasReply getReply() {
        return reply;
    }

    public void setReply(FfwyFqasReply reply) {
        this.reply = reply;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public Date getBgecreateTime() {
        return beginTime;
    }

    public void setBgecreateTime(Date bgecreateTime) {
        this.beginTime = bgecreateTime;
    }

    public Date getActcreateTime() {
        return endTime;
    }

    public void setActcreateTime(Date actcreateTime) {
        this.endTime = actcreateTime;
    }

    public void setFaqsId(Long faqsId)
    {
        this.faqsId = faqsId;
    }

    public Long getFaqsId() 
    {
        return faqsId;
    }
    public void setFaqsMsg(String faqsMsg) 
    {
        this.faqsMsg = faqsMsg;
    }

    public String getFaqsMsg() 
    {
        return faqsMsg;
    }

    @Override
    public String toString() {
        return "FfwyFaqs{" +
                "faqsId=" + faqsId +
                ", faqsMsg='" + faqsMsg + '\'' +
                ", replyMsg='" + replyMsg + '\'' +
                ", reply=" + reply +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", searchStr='" + searchStr + '\'' +
                '}';
    }
}
