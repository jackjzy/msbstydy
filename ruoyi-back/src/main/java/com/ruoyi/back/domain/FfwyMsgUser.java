package com.ruoyi.back.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息发送的用户对象 ffwy_msg_user
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public class FfwyMsgUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息id */
    @Excel(name = "消息id")
    private Long msgId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    public void setMsgId(Long msgId) 
    {
        this.msgId = msgId;
    }

    public Long getMsgId() 
    {
        return msgId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("msgId", getMsgId())
            .append("userId", getUserId())
            .toString();
    }
}
