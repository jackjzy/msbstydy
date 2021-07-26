package com.ruoyi.system.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_spe_vo
 * 
 * @author ruoyi
 * @date 2021-05-13
 */
public class FfwySpeVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer isend;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String value;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String valueselect;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPid(String pid) 
    {
        this.pid = pid;
    }

    public String getPid() 
    {
        return pid;
    }
    public void setIsend(Integer isend) 
    {
        this.isend = isend;
    }

    public Integer getIsend() 
    {
        return isend;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setValueselect(String valueselect) 
    {
        this.valueselect = valueselect;
    }

    public String getValueselect() 
    {
        return valueselect;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("isend", getIsend())
            .append("name", getName())
            .append("value", getValue())
            .append("valueselect", getValueselect())
            .toString();
    }
}
