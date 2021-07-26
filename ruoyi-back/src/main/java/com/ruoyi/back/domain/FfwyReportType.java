package com.ruoyi.back.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 举报管理对象 ffwy_report_type
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public class FfwyReportType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 举报类型id */
    private Long reportTypeId;

    /** 举报类型名称 */
    @Excel(name = "举报类型名称")
    private String typeName;

    public void setReportTypeId(Long reportTypeId) 
    {
        this.reportTypeId = reportTypeId;
    }

    public Long getReportTypeId() 
    {
        return reportTypeId;
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
            .append("reportTypeId", getReportTypeId())
            .append("typeName", getTypeName())
            .toString();
    }
}
