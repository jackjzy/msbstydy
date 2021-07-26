package com.ruoyi.system.domain.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.shop.FfwyShop;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息对象 ffwy_product
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class FfwyProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long productId;

    private Integer countNum;

    /**
     * 商品图片id
     */
    @Excel(name = "商品图片id")
    private Long productPhotoid;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称", example = "沙发")
    private String productName;

    /**
     * 原价
     */
    @Excel(name = "原价")
    @ApiModelProperty(value = "原价", example = "100")
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    @Excel(name = "现价")
    @ApiModelProperty(value = "现价", example = "10")
    private BigDecimal currentPrice;

    /**
     * 销量
     */
    @Excel(name = "销量")
    @ApiModelProperty(value = "销量", example = "123")
    private Long sales;

    /**
     * 运费
     */
    @Excel(name = "运费")
    @ApiModelProperty(value = "运费", example = "12")
    private BigDecimal freight;

    /**
     * 商品状态 t0；下架 1：上架
     */
    @Excel(name = "商品状态")
    @ApiModelProperty(value = "商品状态", example = "1")
    private Integer productStatus;

    /**
     * 库存
     */
    @Excel(name = "库存")
    @ApiModelProperty(value = "库存", example = "1000")
    private Long stock;

    /**
     * 库存单位
     */
    @Excel(name = "库存单位")
    @ApiModelProperty(value = "库存单位", example = "个")
    private String stockUnit;

    /**
     * 类别id
     */
    @Excel(name = "类别id")
    private Long productCategoryId;

    /**
     * 类别名称
     */
    private String productCategoryName;
    /**
     * 店铺id
     */
    @Excel(name = "店铺id")
    private Long shopId;

    /**
     * 规格id
     */
    @Excel(name = "规格id")
    private Long specuiflcationsId;

    /**
     * 服务保证、上架承诺
     */
    @Excel(name = "服务保证、上架承诺")
    @ApiModelProperty(value = "服务保证、上架承诺", example = "。。。")
    private String promise;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * 风格
     */
    private Long styleId;

    /**
     * 封面图
     */
    private String photo;

    private String videoPath;
    private Integer pageNum;
    private Integer pageSize;

    /**
     * 商品图片（轮播图）
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> productPhoto;

    /**
     * 商品图片（轮播图）
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductPhoto> productPhotos;
    /**
     * 商品介绍
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String desc;

    /**
     * 商品规格
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwySpecification> attr;

    /**
     * 店铺名字
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyShop> shop;

    /**
     * 获取商品评论对象
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductComment> productComment;

    /**
     * 获取一条商品评论对象
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductComment> productCommentData;
    /**
     * 获取商品评论对象
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductCommentPhoto> productCommentPhotos;

    /**
     * 商品详情
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String productDescs;
    /**
     * 商品详情
     */
    private Integer products;
    private Integer userCount;
    private  String sumComment;

    private Double beginPrice;
    private Double endPrice;
    private Integer categoryLevel;
    private  int imgWidth;
    private  int imgHeight;

    /**
     * 商品详情 富文本
     */
    private String details;

    public FfwyProduct(Long shopId) {
        this.shopId = shopId;
    }

}


