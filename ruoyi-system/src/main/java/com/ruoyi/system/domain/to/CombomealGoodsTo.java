package com.ruoyi.system.domain.to;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/6/16
 **/
@Data
public class CombomealGoodsTo {
    /** 商品skuId */
    private String productSkuId;

    /** 商品名称 */
    private String productName;

    /** 套餐id */
    private Long combomealId;

    /**
     * 数量
     */
    private Long number;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 分类  1:硬装  2:软装  3:智能家居  4:家电  5:日用品
     */
    private Integer category;


}
