package com.ruoyi.system.domain.aftersale;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后类型对象 ffwy_after_type
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
public class FfwyAfterType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 售后类型id */
    private Long afterTypeId;

    /** 售后类型 */
    @Excel(name = "售后类型")
    private String afterType;

    public void setAfterTypeId(Long afterTypeId) 
    {
        this.afterTypeId = afterTypeId;
    }

    public Long getAfterTypeId() 
    {
        return afterTypeId;
    }
    public void setAfterType(String afterType) 
    {
        this.afterType = afterType;
    }

    public String getAfterType() 
    {
        return afterType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("afterTypeId", getAfterTypeId())
            .append("afterType", getAfterType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
