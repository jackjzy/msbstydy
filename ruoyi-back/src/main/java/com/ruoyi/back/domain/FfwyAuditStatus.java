package com.ruoyi.back.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审核商户状态对象 ffwy_audit_status
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public class FfwyAuditStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer auditStatusId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String auditName;

    public void setAuditStatusId(Integer auditStatusId) 
    {
        this.auditStatusId = auditStatusId;
    }

    public Integer getAuditStatusId() 
    {
        return auditStatusId;
    }
    public void setAuditName(String auditName) 
    {
        this.auditName = auditName;
    }

    public String getAuditName() 
    {
        return auditName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("auditStatusId", getAuditStatusId())
            .append("auditName", getAuditName())
            .toString();
    }
}
