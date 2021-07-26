package com.ruoyi.back.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息类型对象 ffwy_msg_type
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
public class FfwyMsgType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息类型id */
    private Integer msgTypeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    public void setMsgTypeId(Integer msgTypeId)
    {
        this.msgTypeId = msgTypeId;
    }

    public Integer getMsgTypeId()
    {
        return msgTypeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("msgTypeId", getMsgTypeId())
            .append("typeName", getTypeName())
            .toString();
    }
}
