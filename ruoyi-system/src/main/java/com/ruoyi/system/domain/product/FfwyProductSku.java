package com.ruoyi.system.domain.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品sku对象 ffwy_product_sku
 *
 * @author ruoyi
 * @date 2021-04-21
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class FfwyProductSku extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long skuId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String skuName;

    /**
     * 规格
     */
    @Excel(name = "规格")
    private String skuSpec;

    /**
     * sku介绍描述
     */
    @Excel(name = "sku介绍描述")
    private String skuDesc;

    /**
     * 所属分类id
     */
    @Excel(name = "所属分类id")
    private Long catalogId;

    /**
     * 默认图片
     */
    @Excel(name = "默认图片")
    private String skuDefaultImg;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String skuTitle;

    /**
     * 副标题
     */
    @Excel(name = "副标题")
    private String skuSubtitle;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private BigDecimal price;

    /**
     * 销量
     */
    @Excel(name = "销量")
    private Long saleCount;

    /**
     * 库存
     */
    @Excel(name = "库存")
    private Long stock;
    private Long num;

    /**
     * 是否展示 1:展示 2:不展示 默认是1
     */
    private Integer isOpen;

    /**
     * 库存单位
     */
    @Excel(name = "库存单位")
    private String stockUnit;
    private BigDecimal currentPrice;
    private String productName;
    private String photo;
    private List<String> value;
    private String[] values;
    private  String specification;
    private  Map skuValues;

    public FfwyProductSku(Long productId) {
        this.productId = productId;
    }

    public FfwyProductSku(Long skuId, Long productId, String skuName, String skuSpec, String skuDesc) {
        this.skuId = skuId;
        this.productId = productId;
        this.skuName = skuName;
        this.skuSpec = skuSpec;
        this.skuDesc = skuDesc;
    }

    public FfwyProductSku(Long skuId, Long productId, String skuName, String skuSpec, String skuTitle, BigDecimal price, Long saleCount, Long stock, String stockUnit) {
        this.skuId = skuId;
        this.productId = productId;
        this.skuName = skuName;
        this.skuSpec = skuSpec;
        this.skuTitle = skuTitle;
        this.price = price;
        this.saleCount = saleCount;
        this.stock = stock;
        this.stockUnit = stockUnit;
    }
}
