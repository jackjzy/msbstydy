package com.ruoyi.system.domain.product;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Map;

/**
 * 商品规格对象 ffwy_specification
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Data
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class FfwySpecification extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 规格id
     */
    private Integer specificationId;

    /**
     * 规格名称
     */
    @Excel(name = "规格名称")
    private String specificationName;

    /**
     * 父级规格
     */
    @Excel(name = "父级规格")
    private String parentid;

    /**
     * 商品id
     */
    @Excel(name = "商品id")
    private Long productId;

    /**
     * 可选值列表[用分号分隔]
     */
    @Excel(name = "可选值列表[用分号分隔]")
    private String valueSelect;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @Excel(name = "是否需要检索[0-不需要，1-需要]")
    private Integer searchType;

    /**
     * 属性类型[0-销售属性，1-基本属性
     */
    @Excel(name = "属性类型[0-销售属性，1-基本属性")
    private Integer specType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @Excel(name = "启用状态[0 - 禁用，1 - 启用]")
    private Integer enable;

    /**
     * 所属分类
     */
    @Excel(name = "所属分类")
    private Long catelogId;
    //sku库存
    private Long num;
    private FfwyProductSku skuSpec;
    private List<FfwyProductSku> sku;
    private List<FfwyProductSku> productSkus;
    private String[] values;
    private String value;
    private List<FfwyProduct> ffwyProducts;
    private List<FfwySpecification> specifications;
    private List<FfwySpecification> ffwySpecifications;


    public FfwySpecification(Long productId) {
        this.productId = productId;
    }

    public FfwySpecification(Integer specificationId, String specificationName, Long productId, String valueSelect) {
        this.specificationId = specificationId;
        this.specificationName = specificationName;
        this.productId = productId;
        this.valueSelect = valueSelect;
    }
}
