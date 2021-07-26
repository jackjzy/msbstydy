package com.ruoyi.system.domain.vo;


import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CombomealCatVo {
    /** 套餐id */
    private Long combomealId;


    private String combomealName;

    //承接关联表字段套餐类型名称
    private String combomealNames;


    /** 套餐价格 */
    @Excel(name = "套餐价格")
    private BigDecimal price;

    /**
     * 智能化设备
     */
    private String smartName;

    /** 分类 */
    @Excel(name = "分类")
    private Long categoryId;

    /**
     * 空间布置
     */
    private String arrangement;

    /**
     * 家电产品
     */
    private String applianceName;

    /**
     * 软装产品
     */
    private String softOutfit;

    /**
     * 生活日用品
     */
    private String articlesName;

    /**
     * 封面3D连接
     */
    private String coverLink;

    /**
     * 说明
     */
    private String explainl;

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

    /** 套餐状态   0下架  1上架 */
    @Excel(name = "套餐状态   0下架  1上架")
    private Integer combomealStuts;

    private String combomealcatname;

    private Date createTime;

    private Date updateTime;

}
