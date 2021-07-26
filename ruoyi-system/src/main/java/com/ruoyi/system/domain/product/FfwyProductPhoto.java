package com.ruoyi.system.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】对象 ffwy_product_photo
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FfwyProductPhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 所属商品id */
    @Excel(name = "所属商品id")
    private Long productId;

    /** 商品图片路径 */
    @Excel(name = "商品图片路径")
    private String productPhotopath;

    /** 图片排序 */
    @Excel(name = "图片排序")
    private Long imgSort;
    /**
     * 文件类型：1：轮播图 2：视频 4：3D效果图
     */
    private String photoType;
    /**
     * 状态： 0：上架 1：下架
     */
    private String photoStatus;
    /**
     * 3D全景图
     */
    private String panorama;
    private String productStatus;


    public FfwyProductPhoto(String productPhotopath, String photoType) {
        this.productPhotopath = productPhotopath;
        this.photoType = photoType;
    }

    public FfwyProductPhoto(Long id, String photoStatus) {
        this.id = id;
        this.photoStatus = photoStatus;
    }

    public FfwyProductPhoto(Long productId, String productPhotopath, Long imgSort, String photoType) {
        this.productId = productId;
        this.productPhotopath = productPhotopath;
        this.imgSort = imgSort;
        this.photoType = photoType;
    }

    public FfwyProductPhoto(Long productId, String photoType,String photoStatus) {
        this.productId = productId;
        this.photoType = photoType;
        this.photoStatus = photoStatus;
    }
}
