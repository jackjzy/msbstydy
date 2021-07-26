package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_information_tag
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public class FfwyInformationTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资讯id */
    @Excel(name = "资讯id")
    private Long informationId;

    /** 标签id */
    @Excel(name = "标签id")
    private Long tagId;

    public void setInformationId(Long informationId) 
    {
        this.informationId = informationId;
    }

    public Long getInformationId() 
    {
        return informationId;
    }
    public void setTagId(Long tagId)
    {
        this.tagId = tagId;
    }

    public Long getTagId()
    {
        return tagId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("informationId", getInformationId())
            .append("tagId", getTagId())
            .toString();
    }
}
