package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 标签对象 ffwy_tag
 * 
 * @author ruoyi
 * @date 2021-05-08
 */
public class FfwyTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签id */
    private Long tagId;

    /** 父标签id */
    @Excel(name = "父标签id")
    private Long tagParentid;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    /** 标签数量 */
    @Excel(name = "标签数量")
    private Long tagCount;

    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public void setTagId(Long tagId)
    {
        this.tagId = tagId;
    }

    public Long getTagId()
    {
        return tagId;
    }
    public void setTagParentid(Long tagParentid) 
    {
        this.tagParentid = tagParentid;
    }

    public Long getTagParentid() 
    {
        return tagParentid;
    }
    public void setTagName(String tagName) 
    {
        this.tagName = tagName;
    }

    public String getTagName() 
    {
        return tagName;
    }
    public void setTagCount(Long tagCount) 
    {
        this.tagCount = tagCount;
    }

    public Long getTagCount() 
    {
        return tagCount == null ? 0 : tagCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tagId", getTagId())
            .append("tagParentid", getTagParentid())
            .append("tagName", getTagName())
            .append("createTime", getCreateTime())
            .append("tagCount", getTagCount())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
