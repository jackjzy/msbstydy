package com.ruoyi.system.domain.product;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品评论图片对象 ffwy_product_comment_photo
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Data
public class FfwyProductCommentPhoto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long productCommentPhotoId;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long productCommentId;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String productCommentPhoto;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Long sort;

    public FfwyProductCommentPhoto(Long productCommentId) {
        this.productCommentId = productCommentId;
    }


    public void setProductCommentPhotoId(Long productCommentPhotoId) {
        this.productCommentPhotoId = productCommentPhotoId;
    }

    public Long getProductCommentPhotoId() {
        return productCommentPhotoId;
    }

    public void setProductCommentId(Long productCommentId) {
        this.productCommentId = productCommentId;
    }

    public Long getProductCommentId() {
        return productCommentId;
    }

    public void setProductCommentPhoto(String productCommentPhoto) {
        this.productCommentPhoto = productCommentPhoto;
    }

    public String getProductCommentPhoto() {
        return productCommentPhoto;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("productCommentPhotoId", getProductCommentPhotoId())
                .append("productCommentId", getProductCommentId())
                .append("productCommentPhoto", getProductCommentPhoto())
                .append("sort", getSort())
                .toString();
    }
}
