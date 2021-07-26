package com.ruoyi.system.domain.to;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/19
 **/
@Data
@NoArgsConstructor
public class ProductTo {
    /** 商品id */
    private Long productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称",example = "沙发")
    private String productName;

    /** 商品状态 t0；下架 1：上架 */
    @Excel(name = "商品状态")
    @ApiModelProperty(value = "商品状态",example = "1")
    private String productStatus;

    /** 类别id */
    @Excel(name = "类别id")
    private Long productCategoryId;
    /**
     * 店铺id
     */
    private Long shopId;
    /**
     * 价格
     */

    private String currentPrice;
    /**
     * 原价
     */
    private String originalPrice;

    @ApiModelProperty(value = "运费", example = "12")
    private String freight;

    /**
     * 封面图
     */
    private String photo;

    /**
     * 商品轮播图  productPhotopath：图片路径    photoType：文件类型：1：轮播图 2：视频
     * 7.7更改为只传轮播图图片 更改为数组接受
     */
    private String[] productPhoto;

    //视频链接
    private String videoPath;
    /**
     * 商品详情（包含文字、图片）
     * decript：文字内容或图片路径    descType：类型 0：文字 1：图片 现在去除类型
     */
    private String productDesc;

    /**
     * 分类级别
     */
    private Integer categoryLevel;
    /**
     * 开始上架时间
     */
    private Date beginTime;
    private Date endTime;
    /**
     * 价格下限
     */
    private Double beginPrice;
    private Double endPrice;

    private String details;
}
