package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_feedback_reply
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public class FfwyFeedbackReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈问题回复 */
    private Long replyId;

    /** 回复的信息 */
    @Excel(name = "回复的信息")
    private String replyMsg;

    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replyTime;

    /** 回复的用户 */
    @Excel(name = "回复的用户")
    private Long userId;

    /** 回复的管理员 */
    @Excel(name = "回复的管理员")
    private Long adminId;


    private Long feedBackId;

    public Long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

    public void setReplyId(Long replyId)
    {
        this.replyId = replyId;
    }

    public Long getReplyId() 
    {
        return replyId;
    }
    public void setReplyMsg(String replyMsg) 
    {
        this.replyMsg = replyMsg;
    }

    public String getReplyMsg() 
    {
        return replyMsg;
    }
    public void setReplyTime(Date replyTime) 
    {
        this.replyTime = replyTime;
    }

    public Date getReplyTime() 
    {
        return replyTime;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }

    public Long getAdminId() 
    {
        return adminId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("replyId", getReplyId())
            .append("replyMsg", getReplyMsg())
            .append("replyTime", getReplyTime())
            .append("userId", getUserId())
            .append("adminId", getAdminId())
                .append("feedBackId",getFeedBackId())
            .toString();
    }
}
