package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyCombomealVo {
    /** 套餐id */
    private Long combomealId;

    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String combomealName;

    /** 套餐价格 */
    @Excel(name = "套餐价格")
    private BigDecimal price;

    /** 分类 */
    @Excel(name = "分类")
    private Long categoryId;

    /** 每平米价格 */
    @Excel(name = "每平米价格")
    private BigDecimal prices;

    /** 套餐图片 */
    @Excel(name = "套餐图片")
    private String photo;

    /** 套餐销量 */
    @Excel(name = "套餐销量")
    private Long sales;

    /** 套餐详情 */
    @Excel(name = "套餐详情")
    private String information;


    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String combomealcatname;


}
