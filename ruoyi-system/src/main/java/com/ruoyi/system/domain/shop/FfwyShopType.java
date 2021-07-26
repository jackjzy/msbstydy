package com.ruoyi.system.domain.shop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺类型对象 ffwy_shop_type
 * 
 * @author ruoyi
 * @date 2021-04-20
 */
public class FfwyShopType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer shopType;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String typeName;

    public void setShopType(Integer shopType) 
    {
        this.shopType = shopType;
    }

    public Integer getShopType() 
    {
        return shopType;
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
            .append("shopType", getShopType())
            .append("typeName", getTypeName())
            .toString();
    }
}
