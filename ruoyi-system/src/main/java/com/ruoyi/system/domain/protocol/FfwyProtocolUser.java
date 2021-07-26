package com.ruoyi.system.domain.protocol;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 记录用户是否同意过用户协议对象 ffwy_protocol_user
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
public class FfwyProtocolUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer protocolUserId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 协议id */
    @Excel(name = "协议id")
    private Integer protocolId;

    public void setProtocolUserId(Integer protocolUserId) 
    {
        this.protocolUserId = protocolUserId;
    }

    public Integer getProtocolUserId() 
    {
        return protocolUserId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setProtocolId(Integer protocolId) 
    {
        this.protocolId = protocolId;
    }

    public Integer getProtocolId() 
    {
        return protocolId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("protocolUserId", getProtocolUserId())
            .append("userId", getUserId())
            .append("protocolId", getProtocolId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
