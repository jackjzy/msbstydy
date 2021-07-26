package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class Reply extends BaseEntity {

    /** id*/
    private Integer userid;

    /** 消息 */
    @Excel(name = "消息")
    private String replyMsg;

    /** 反馈id */
    @Excel(name = "反馈id")
    private Integer feedbackId;

    public Reply() {
    }

    public Reply(Integer userid, String replyMsg, Integer feedbackId) {
        this.userid = userid;
        this.replyMsg = replyMsg;
        this.feedbackId = feedbackId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "userid=" + userid +
                ", replyMsg='" + replyMsg + '\'' +
                ", feedbackId=" + feedbackId +
                '}';
    }
}

