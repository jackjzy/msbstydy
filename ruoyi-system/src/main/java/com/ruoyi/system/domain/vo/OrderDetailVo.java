package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 赵字豪
 * @Title: 订单详情组装
 * @date 2021/4/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品skuid
     */
    private Long productSkuId;

    /**
     * 商品图片
     */
    private String photot;

    /** 类别id */
    private Long productCategoryId;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal money;

    /** 数量 */
    @Excel(name = "数量")
    private Integer number;

    /** 库存 */
    @Excel(name = "库存")
    private Long stock;

    /** 库存单位 */
    @Excel(name = "库存单位")
    private String stockUnit;

    /** 销量 */
    @Excel(name = "销量")
    private Long saleCount;

    /** 规格 */
    @Excel(name = "规格")
    private String skuSpec;
}
