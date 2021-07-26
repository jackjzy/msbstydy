package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.constant.CombomealGoodsCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/17
 **/
@Data
@NoArgsConstructor
public class CombomealDetailsVo {
    /** 套餐id */
    private Long combomealId;

    /**
     * 套餐名称
     */
    private String combomealName;

    /** 套餐价格 */
    @Excel(name = "套餐价格")
    private BigDecimal price;
    /**
     * 定金
     */
    private BigDecimal deposit;

    /** 分类 */
    @Excel(name = "分类")
    private Long categoryId;

    /** 每平米价格 */
    @Excel(name = "每平米价格")
    private BigDecimal prices;

    /** 套餐销量 */
    @Excel(name = "套餐销量")
    private Long sales;

    /** 套餐详情 */
    @Excel(name = "套餐详情")
    private String information;

    private String explainl;

    /**
     * 详情图
     */
    private List<String> informationPhoto;

    /**
     * 轮播图
     */
    private List<String> carouselPhoto;

    /**
     *
     */
    private List<CommentVO> commentVOS;
    /**
     * 条数
     */
    private Long commentSize;

    /**
     * 硬装
     */
    private List<CombomealHardVo> combomealHards;

    /**
     * 软装
     */
    private List<CombomealSoftVo>  combomealSofts;

    private String hardOutfit;

    /**
     * 智能家居
     */
    private List<CombomealSmartVo> combomealSmarts;

    /**
     * 家电
     */
    private List<CombomealWiringVo> combomealWirings;

    /**
     * 日用品
     */
    private List<commodityVo> commodityVos;

    public void setInformationPhoto(String iPhoto) {
        if (informationPhoto == null){
            informationPhoto = new ArrayList<>();
        }
        informationPhoto.add(iPhoto);
    }

    public void setCarouselPhoto(String cPhoto) {
        if (carouselPhoto == null){
            carouselPhoto = new ArrayList<>();
        }
        carouselPhoto.add(cPhoto);
    }

    public void setCombomealHard(String productId, String productName, Long combomealId,Long number){
        if (combomealHards == null){
            combomealHards = new ArrayList<>();
        }
        combomealHards.add(new CombomealHardVo(productId,productName,combomealId,number)) ;
    }

    public void setCombomealSoft(String productId,String productName,Long combomealId,Long number){
        if (combomealSofts == null){
            combomealSofts = new ArrayList<>();
        }
        combomealSofts.add(new CombomealSoftVo(productId,productName,combomealId,number));
    }

    public void setCombomealSmart(String productId,String productName,Long combomealId,Long number){
        if (combomealSmarts == null){
            combomealSmarts = new ArrayList<>();
        }
        combomealSmarts.add(new CombomealSmartVo(productId,productName,combomealId,number));
    }

    public void setCombomealWiring(String productId,String productName,Long combomealId,Long number){
        if (combomealWirings == null){
            combomealWirings = new ArrayList<>();
        }
        combomealWirings.add(new CombomealWiringVo(productId,productName,combomealId,number));
    }

    public void setCombomealDity(String productId,String productName,Long combomealId,Long number){
        if (commodityVos == null){
            commodityVos = new ArrayList<>();
        }
        commodityVos.add(new commodityVo(productId,productName,combomealId,number));
    }

    public void setCommentVO(String userId,String userName,String userPhoto,String comment,Date time,Integer star,List<String> commentPhoto){
        if (commentVOS == null){
            commentVOS = new ArrayList<>();
        }
        commentVOS.add(new CommentVO(userId,userName,userPhoto,comment,time,star,commentPhoto));
    }

}

/**
 * 套餐评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class CommentVO{
    private String userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userPhoto;
    /** 评论内容 */
    @Excel(name = "评论内容")
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    /**
     * 评分
     */
    private Integer star;
    /**
     * 评论图
     */
    private List<String> commentPhoto;
}

/**
 * 硬装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class CombomealHardVo{
    /** 商品id */
    @Excel(name = "商品id")
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /**
     * 数量
     */
    private Long number;

    private Boolean isSelect = true;

    /**
     * 分类  1:硬装  2:软装  3:智能家居  4:家电  5:日用品
     */
    private Integer category = CombomealGoodsCategory.HARD;

    public CombomealHardVo(String productId, String productName, Long combomealId) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
    }

    public CombomealHardVo(String productId, String productName, Long combomealId, Long number) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
        this.number = number;
    }
}

/**
 * 软装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class CombomealSoftVo{
    /** 商品id */
    @Excel(name = "商品id")
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /**
     * 数量
     */
    private Long number;

    private Boolean isSelect = true;

    private Integer category = CombomealGoodsCategory.SOFT;

    public CombomealSoftVo(String productId, String productName, Long combomealId) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
    }

    public CombomealSoftVo(String productId, String productName, Long combomealId, Long number) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
        this.number = number;
    }
}

/**
 * 智能家居
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class CombomealSmartVo{
    /** 商品id */
    @Excel(name = "商品id")
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /**
     * 数量
     */
    private Long number;

    private Boolean isSelect = true;

    private Integer category = CombomealGoodsCategory.SMART;

    public CombomealSmartVo(String productId, String productName, Long combomealId) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
    }

    public CombomealSmartVo(String productId, String productName, Long combomealId, Long number) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
        this.number = number;
    }
}

/**
 * 家电
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class CombomealWiringVo{
    /** 商品id */
    @Excel(name = "商品id")
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /**
     * 数量
     */
    private Long number;

    private Boolean isSelect = true;

    private Integer category = CombomealGoodsCategory.WIRING;

    public CombomealWiringVo(String productId, String productName, Long combomealId) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
    }

    public CombomealWiringVo(String productId, String productName, Long combomealId, Long number) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
        this.number = number;
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class commodityVo{
    /** 商品id */
    @Excel(name = "商品id")
    private String productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /**
     * 数量
     */
    private Long number;

    private Boolean isSelect = true;

    private Integer category = CombomealGoodsCategory.COMMODITY;

    public commodityVo(String productId, String productName, Long combomealId) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
    }

    public commodityVo(String productId, String productName, Long combomealId, Long number) {
        this.productId = productId;
        this.productName = productName;
        this.combomealId = combomealId;
        this.number = number;
    }
}