package com.ruoyi.system.domain.combomeal;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 套餐对象 ffwy_combomeal
 *
 * @author ruoyi
 * @date 2021-06-01
 */
@Data
public class FfwyCombomeal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 套餐id */
    private Long combomealId;

    /** 套餐风格名称 */
    @Excel(name = "套餐风格名称")
    private String combomealName;

    //承接关联表字段
    private String combomealNames;

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

    /** 套餐状态   0下架  1上架 */
    @Excel(name = "套餐状态   0下架  1上架")
    private Integer combomealStuts;

    /** 智能化设备 */
    @Excel(name = "智能化设备")
    private String smartName;

    private String smartNames;

    /** 家电名称 */
    @Excel(name = "家电名称")
    private String applianceName;

    private String applianceNames;

    /** 空间布置 */
    @Excel(name = "空间布置")
    private String arrangement;

    private String arrangements;

    /** 生活日用品 */
    @Excel(name = "生活日用品")
    private String articlesName;

    private String articlesNames;

    /** 封面链接 */
    @Excel(name = "封面链接")
    private String coverLink;

    /**
     * 封面图片
     */
    private String fileCover;

    private String combomealType;

    /** 说明 */
    @Excel(name = "说明")
    private String explainl;
    //软装
    private String softOutfit;

    private String softOutfits;

    //硬装
    private String hardOutfit;

    private List<FfwyCombomealPhoto>combomealPhotos;

    /**
     * 详情图片
     */
    private List<String>img;


}
