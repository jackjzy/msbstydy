package com.ruoyi.system.domain.cart;

import java.math.BigDecimal;

import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物车 对象 ffwy_cart_info
 *
 * @author ruoyi
 * @date 2021-05-14
 */
@Data
public class FfwyCartInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * skuid
     */
    @Excel(name = "skuid")
    private Long skuId;

    /**
     * 放入购物车时价格
     */
    @Excel(name = "放入购物车时价格")
    private BigDecimal cartPrice;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Integer skuNum;
    private Long num;

    /**
     * 图片文件
     */
    @Excel(name = "图片文件")
    private String imgUrl;

    /**
     * sku名称 (冗余)
     */
    @Excel(name = "sku名称 (冗余)")
    private String skuName;

    /**
     * $column.columnComment
     */
    @Excel(name = "sku名称 (冗余)")
    private Integer isChecked = 1;
    //规格
    private String skuSpec;

    //运费
    private BigDecimal freight;

    private  Integer repertory;

    private  String ids;

}
