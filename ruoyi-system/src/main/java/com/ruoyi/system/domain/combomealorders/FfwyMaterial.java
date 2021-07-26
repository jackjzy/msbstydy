package com.ruoyi.system.domain.combomealorders;

import java.math.BigDecimal;

import com.ruoyi.system.domain.product.FfwyProductSku;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 装修材料对象 ffwy_material
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Data
@NoArgsConstructor
public class FfwyMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 材料id */
    private Long materialId;
    /**
     * 商品skuID
     */
    private Long skuId;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String materialName;

    /** 材料数量 */
    @Excel(name = "材料数量")
    private Long materialNumber;

    /** 材料价格(单价) */
    @Excel(name = "材料价格")
    private BigDecimal materialPrice;

    /** 材料品牌 */
    @Excel(name = "材料品牌")
    private String brand;

    /** 套餐订单id */
    @Excel(name = "套餐订单id")
    private Long orderCombomealId;

    /** sku对象 */
    private FfwyProductSku sku;


    private Integer tagId;


}
