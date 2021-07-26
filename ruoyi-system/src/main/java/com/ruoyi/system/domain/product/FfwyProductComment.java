package com.ruoyi.system.domain.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品评论对象 ffwy_product_comment
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Data
public class FfwyProductComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品评价id
     */
    private Long productCommentId;

    /**
     * 商品id
     */
    @Excel(name = "商品id")
    private Long productId;

    /**
     * 评价用户
     */
    @Excel(name = "评价用户")
    private Long userId;

    /**
     * 父级评论
     */
    @Excel(name = "父级评论")
    private Long parentId;

    /**
     * 评论文本内容
     */
    @Excel(name = "评论文本内容")
    private String comment;

    /**
     * 评价数量
     */
    @Excel(name = "评价数量")
    private Integer commentNumber;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    private Long orderId;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String productName;

    /**
     * 评星
     */
    @Excel(name = "评星")
    private Integer rating;

    /**
     * 好评差评（标签库里查找）
     */
    @Excel(name = "好评差评" , readConverterExp = "标=签库里查找")
    private Integer tagId;

    private Integer commentcount;

    /**
     * 评价时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评价时间" , width = 30, dateFormat = "yyyy-MM-dd")
    private Date commrnytTime;

    /**
     * 商家回复
     */
    @Excel(name = "商家回复")
    private String revert;
    /*
     * 个人评价
     * */
    private String productCommentPhoto;


    private String categoryName;
    /**
     * 用户姓名
     */
    private String userName;
    private FfwyUser ffwyUser;

    private String photo;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductComment> comments;
    /**
     * 图片集合
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProductCommentPhoto> photot;
  /**
     * 用户
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyUser> user;
    /**
     * 商品规格
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwySpecification> attr;

    private String sku;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String userPhoto;

    private String sumComment;

    private  String commentType;

    /** 套餐id */
    private Long combomealId;

    /** 评价图片 */
    private  String appraiseFile;


    /** 评论的商品*/
    private FfwyProduct ffwyProduct;

    /** 评论的订单*/
    private FfwyOrderDetails ffwyOrderDetails;

    /** 回复状态*/
    private String replyStatus;

    private List<String>img;

    public FfwyOrderDetails getFfwyOrderDetails() {
        return ffwyOrderDetails;
    }

    public void setFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails) {
        this.ffwyOrderDetails = ffwyOrderDetails;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }



    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public FfwyUser getFfwyUser() {
        return ffwyUser;
    }

    public void setFfwyUser(FfwyUser ffwyUser) {
        this.ffwyUser = ffwyUser;
    }

    public FfwyProduct getFfwyProduct() {
        return ffwyProduct;
    }

    public void setFfwyProduct(FfwyProduct ffwyProduct) {
        this.ffwyProduct = ffwyProduct;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProductCommentPhoto(String productCommentPhoto) {
        this.productCommentPhoto = productCommentPhoto;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSumComment(String sumComment) {
        this.sumComment = sumComment;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductCommentPhoto() {
        return productCommentPhoto;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getSumComment() {
        return sumComment;
    }


    public FfwyProductComment(Long productId) {
        this.productId = productId;
    }

    public void setProductCommentId(Long productCommentId) {
        this.productCommentId = productCommentId;
    }

    public List<FfwyProductCommentPhoto> getPhotot() {
        return photot;
    }

    public void setPhotot(List<FfwyProductCommentPhoto> photot) {
        this.photot = photot;
    }

    public Long getProductCommentId() {
        return productCommentId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }



    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }


    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setCommrnytTime(Date commrnytTime) {
        this.commrnytTime = commrnytTime;
    }

    public Date getCommrnytTime() {
        return commrnytTime;
    }

    public void setRevert(String revert) {
        this.revert = revert;
    }

    public String getRevert() {
        return revert;
    }

    public Long getCombomealId() {
        return combomealId;
    }

    public void setCombomealId(Long combomealId) {
        this.combomealId = combomealId;
    }

    public String getAppraiseFile() {
        return appraiseFile;
    }

    public void setAppraiseFile(String appraiseFile) {
        this.appraiseFile = appraiseFile;
    }

    public FfwyProductComment() {

    }

    @Override
    public String toString() {
        return "FfwyProductComment{" +
                "productCommentId=" + productCommentId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", parentId=" + parentId +
                ", comment='" + comment + '\'' +
                ", commentNumber=" + commentNumber +
                ", orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", rating=" + rating +
                ", tagId=" + tagId +
                ", commentcount=" + commentcount +
                ", commrnytTime=" + commrnytTime +
                ", revert='" + revert + '\'' +
                ", productCommentPhoto='" + productCommentPhoto + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", userName='" + userName + '\'' +
                ", photo='" + photo + '\'' +
                ", comments=" + comments +
                ", photot=" + photot +
                ", user=" + user +
                ", attr=" + attr +
                ", userPhoto='" + userPhoto + '\'' +
                ", sumComment='" + sumComment + '\'' +
                ", commentType='" + commentType + '\'' +
                ", combomealId=" + combomealId +
                ", appraiseFile='" + appraiseFile + '\'' +
                ", ffwyUser=" + ffwyUser +
                ", ffwyProduct=" + ffwyProduct +
                ", replyStatus=" + replyStatus +
                '}';
    }
}
