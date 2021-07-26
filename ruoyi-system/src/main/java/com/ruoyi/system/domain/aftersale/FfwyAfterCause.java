package com.ruoyi.system.domain.aftersale;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 退款原因对象 ffwy_after_cause
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
public class FfwyAfterCause extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 退款原因id */
    private Long refundId;

    /** 退款原因 */
    @Excel(name = "退款原因")
    private String refundName;

    public void setRefundId(Long refundId) 
    {
        this.refundId = refundId;
    }

    public Long getRefundId() 
    {
        return refundId;
    }
    public void setRefundName(String refundName) 
    {
        this.refundName = refundName;
    }

    public String getRefundName() 
    {
        return refundName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("refundId", getRefundId())
            .append("refundName", getRefundName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
