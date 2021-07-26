package com.ruoyi.system.domain.combomeal;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 【请填写功能名称】对象 ffwy_combomeal_category
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class FfwyCombomealCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 套餐分类id */
    private Long categoryId;

    /** 二级分类id */
    @Excel(name = "二级分类id")
    private Long parentId;

    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String combomaealName;

    /** 套餐价格 */
    @Excel(name = "套餐价格")
    private BigDecimal price;

    /** 家电产品(商品id) */
    @Excel(name = "家电产品(商品id)")
    private String wiringProductId;

    /** 软装产品(商品id) */
    @Excel(name = "软装产品(商品id)")
    private String softProductId;

    /** 轮播图链接 */
    @Excel(name = "轮播图链接")
    private String slideshowImg;

    /** 生活日用品(商品id) */
    @Excel(name = "生活日用品(商品id)")
    private String dityProductId;

    /** 套餐说明 */
    @Excel(name = "套餐说明")
    private String categoryNote;

    /** 封面图3D链接和图片 */
    @Excel(name = "封面图3D链接和图片")
    private String coverFile;

    /** 套餐详情图 */
    @Excel(name = "套餐详情图")
    private String categoryFile;

    /** 基础硬装 */
    @Excel(name = "基础硬装")
    private String hardId;

    /** 智能化设备 */
    @Excel(name = "智能化设备")
    private String smartId;

    /** 空间布置 */
    @Excel(name = "空间布置")
    private String spaceLayout;



    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setCombomaealName(String combomaealName)
    {
        this.combomaealName = combomaealName;
    }

    public String getCombomaealName()
    {
        return combomaealName;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setWiringProductId(String wiringProductId)
    {
        this.wiringProductId = wiringProductId;
    }

    public String getWiringProductId()
    {
        return wiringProductId;
    }
    public void setSoftProductId(String softProductId)
    {
        this.softProductId = softProductId;
    }

    public String getSoftProductId()
    {
        return softProductId;
    }
    public void setSlideshowImg(String slideshowImg)
    {
        this.slideshowImg = slideshowImg;
    }

    public String getSlideshowImg()
    {
        return slideshowImg;
    }
    public void setDityProductId(String dityProductId)
    {
        this.dityProductId = dityProductId;
    }

    public String getDityProductId()
    {
        return dityProductId;
    }
    public void setCategoryNote(String categoryNote)
    {
        this.categoryNote = categoryNote;
    }

    public String getCategoryNote()
    {
        return categoryNote;
    }
    public void setCoverFile(String coverFile)
    {
        this.coverFile = coverFile;
    }

    public String getCoverFile()
    {
        return coverFile;
    }
    public void setCategoryFile(String categoryFile)
    {
        this.categoryFile = categoryFile;
    }

    public String getCategoryFile()
    {
        return categoryFile;
    }
    public void setHardId(String hardId)
    {
        this.hardId = hardId;
    }

    public String getHardId()
    {
        return hardId;
    }
    public void setSmartId(String smartId)
    {
        this.smartId = smartId;
    }

    public String getSmartId()
    {
        return smartId;
    }
    public void setSpaceLayout(String spaceLayout)
    {
        this.spaceLayout = spaceLayout;
    }

    public String getSpaceLayout()
    {
        return spaceLayout;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("categoryId", getCategoryId())
                .append("parentId", getParentId())
                .append("combomaealName", getCombomaealName())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("price", getPrice())
                .append("wiringProductId", getWiringProductId())
                .append("softProductId", getSoftProductId())
                .append("slideshowImg", getSlideshowImg())
                .append("dityProductId", getDityProductId())
                .append("categoryNote", getCategoryNote())
                .append("coverFile", getCoverFile())
                .append("categoryFile", getCategoryFile())
                .append("hardId", getHardId())
                .append("smartId", getSmartId())
                .append("spaceLayout", getSpaceLayout())
                .toString();
    }
}
