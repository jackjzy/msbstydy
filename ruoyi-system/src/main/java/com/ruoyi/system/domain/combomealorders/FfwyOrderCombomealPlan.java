package com.ruoyi.system.domain.combomealorders;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 套餐设计图对象 ffwy_order_combomeal_plan
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
public class FfwyOrderCombomealPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设计图片id */
    private Long photoId;

    /** 所属套餐订单id */
    @Excel(name = "所属套餐订单id")
    private Long orderId;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String photoPhath;

    public void setPhotoId(Long photoId) 
    {
        this.photoId = photoId;
    }

    public Long getPhotoId() 
    {
        return photoId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setPhotoPhath(String photoPhath) 
    {
        this.photoPhath = photoPhath;
    }

    public String getPhotoPhath() 
    {
        return photoPhath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId", getPhotoId())
            .append("orderId", getOrderId())
            .append("photoPhath", getPhotoPhath())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
