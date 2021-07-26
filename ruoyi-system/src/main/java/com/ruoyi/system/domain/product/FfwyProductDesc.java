package com.ruoyi.system.domain.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 【请填写功能名称】对象 ffwy_product_desc
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Data
@NoArgsConstructor
public class FfwyProductDesc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long descId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long productId;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String decript;

    /** $column.columnComment */
    @Excel(name = "商品排序")
    private Long descSort;
    /**
     * 图片集合
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductPhoto> img;

    public List<FfwyProductPhoto> getPhotot() {
        return photot;
    }
    /**
     * 介绍类型 0：文字 1：图片
     */
    private String descType;
    /**
     * 状态 true：上架   false：下架
     */
    private Boolean descStatus;
    /**
     * 图片
     */
    private MultipartFile file;
    public void setPhotot(List<FfwyProductPhoto> photot) {
        this.photot = photot;
    }
    public FfwyProductDesc(Long productId, String descType, Boolean descStatus) {
        this.productId = productId;
        this.descType = descType;
        this.descStatus = descStatus;
    }
    /**
     * 图片集合
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductPhoto> photot;

    public FfwyProductDesc(String decript, String descType) {
        this.decript = decript;
        this.descType = descType;
    }

    public void setDescId(Long descId)
    {
        this.descId = descId;
    }

    public Long getDescId()
    {
        return descId;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setDecript(String decript)
    {
        this.decript = decript;
    }

    public String getDecript()
    {
        return decript;
    }
    public void setDescSort(Long descSort)
    {
        this.descSort = descSort;
    }

    public Long getDescSort()
    {
        return descSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("descId", getDescId())
            .append("productId", getProductId())
            .append("decript", getDecript())
            .append("descSort", getDescSort())
            .toString();
    }
    public FfwyProductDesc(Long descId, Boolean descStatus) {
        this.descId = descId;
        this.descStatus = descStatus;
    }
}
