package com.ruoyi.system.domain.combomeal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.system.domain.admin.FfwyUser;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】对象 ffwy_combomeal_comment
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@NoArgsConstructor
public class FfwyCombomealComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 套餐评论id */
    private Long commentId;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 父级评论 */
    @Excel(name = "父级评论")
    private Long parentId;
    /**
     * 装修态度
     */
    private Integer decorationAttitude;

    /**
     * 服务态度
     */
    private Integer serviceAttitude;

    /**
     * 装修质量
     */
    private Integer decorationQuality;


    /** 评论内容 */
    @Excel(name = "评论内容")
    private String comment;

    /** 组合搜索信息*/
    private String searchStr;

    /** 评论图片*/
    private List<FfwyCombomealCommentPhoto> photos;

    /** 套餐对象*/
    private FfwyCombomeal ffwyCombomeal;
    /** 用户对象*/
    private FfwyUser ffwyUser;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public FfwyUser getFfwyUser() {
        return ffwyUser;
    }

    public void setFfwyUser(FfwyUser ffwyUser) {
        this.ffwyUser = ffwyUser;
    }

    public FfwyCombomeal getFfwyCombomeal() {
        return ffwyCombomeal;
    }

    public void setFfwyCombomeal(FfwyCombomeal ffwyCombomeal) {
        this.ffwyCombomeal = ffwyCombomeal;
    }

    public Integer getDecorationAttitude() {
        return decorationAttitude;
    }

    public void setDecorationAttitude(Integer decorationAttitude) {
        this.decorationAttitude = decorationAttitude;
    }

    public Integer getServiceAttitude() {
        return serviceAttitude;
    }

    public void setServiceAttitude(Integer serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    public Integer getDecorationQuality() {
        return decorationQuality;
    }

    public void setDecorationQuality(Integer decorationQuality) {
        this.decorationQuality = decorationQuality;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getBeginTime() {
        return beginTime;
    }

    @Override
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<FfwyCombomealCommentPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<FfwyCombomealCommentPhoto> photos) {
        this.photos = photos;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }
    public void setCombomealId(Long combomealId) 
    {
        this.combomealId = combomealId;
    }

    public Long getCombomealId() 
    {
        return combomealId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    public FfwyCombomealComment(Long combomealId) {
        this.combomealId = combomealId;
    }

    public Integer getStar() {
        return decorationAttitude;
    }

    public void setStar(Integer star) {
        this.decorationAttitude = star;
    }


    @Override
    public String toString() {
        return "FfwyCombomealComment{" +
                "commentId=" + commentId +
                ", combomealId=" + combomealId +
                ", userId=" + userId +
                ", parentId=" + parentId +
                ", decorationAttitude=" + decorationAttitude +
                ", serviceAttitude=" + serviceAttitude +
                ", decorationQuality=" + decorationQuality +
                ", comment='" + comment + '\'' +
                ", searchStr='" + searchStr + '\'' +
                ", photos=" + photos +
                ", ffwyCombomeal=" + ffwyCombomeal +
                ", ffwyUser=" + ffwyUser +
                ", createTime=" + createTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
