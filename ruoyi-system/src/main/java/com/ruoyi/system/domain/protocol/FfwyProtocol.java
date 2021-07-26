package com.ruoyi.system.domain.protocol;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_protocol
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
public class FfwyProtocol extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户协议id */
    private Integer protocolId;

    /** 用户协议内容 */
    @Excel(name = "用户协议内容")
    private String protocolMsg;

    public void setProtocolId(Integer protocolId) 
    {
        this.protocolId = protocolId;
    }

    public Integer getProtocolId() 
    {
        return protocolId;
    }
    public void setProtocolMsg(String protocolMsg) 
    {
        this.protocolMsg = protocolMsg;
    }

    public String getProtocolMsg() 
    {
        return protocolMsg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("protocolId", getProtocolId())
            .append("protocolMsg", getProtocolMsg())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
