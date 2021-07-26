package com.ruoyi.system.domain.combomeal;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_combomeal_hard
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@NoArgsConstructor
public class FfwyCombomealHard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    @Excel(name = "商品id")
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;


    public FfwyCombomealHard(Long combomealId) {
        this.combomealId = combomealId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setCombomealId(Long combomealId) 
    {
        this.combomealId = combomealId;
    }

    public Long getCombomealId() 
    {
        return combomealId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("combomealId", getCombomealId())
            .toString();
    }
}
