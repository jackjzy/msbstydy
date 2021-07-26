package com.ruoyi.system.domain.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 商品类别对象 ffwy_product_category
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FfwyProductCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别id */
    private Long categoryId;

    /** 父类别id */
    @Excel(name = "父类别id")
    private Long parentId;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /**
     * 分类级别
     */
    private Integer catLevel;

    /**
     * 所有子分类
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductCategory> childCategory;

    public FfwyProductCategory(Integer catLevel) {
    this.catLevel = catLevel;
}
}
