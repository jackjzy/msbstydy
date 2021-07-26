package com.ruoyi.system.domain.product;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品收藏对象 ffwy_product_collect
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
public class FfwyProductCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id
 */
    private Long id;

    /** 用户id
 */
    @Excel(name = "用户id")
    private Long userId;

    /** 商品是否收藏 0是收藏 1是未收藏 */
    @Excel(name = "商品是否收藏 0是收藏 1是未收藏")
    private Integer collectStatus;

    /** 商品id */
    @Excel(name = "商品id")
    private Long productId;

    /** 删除 0未删除 1删除 */
    @Excel(name = "删除 0未删除 1删除")
    private Long isDel;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCollectStatus(Integer collectStatus) 
    {
        this.collectStatus = collectStatus;
    }

    public Integer getCollectStatus() 
    {
        return collectStatus;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setIsDel(Long isDel) 
    {
        this.isDel = isDel;
    }

    public Long getIsDel() 
    {
        return isDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("collectStatus", getCollectStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("productId", getProductId())
            .append("isDel", getIsDel())
            .toString();
    }
}
