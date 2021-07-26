package com.ruoyi.system.domain.combomeal;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 套餐评论图片对象 ffwy_combomeal_comment_photo
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@NoArgsConstructor
public class FfwyCombomealCommentPhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 套餐评价图片id */
    private Long photoId;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String photoPath;

    /** 评论id */
    @Excel(name = "评论id")
    private Long combomealCommentId;

    public void setPhotoId(Long photoId) 
    {
        this.photoId = photoId;
    }

    public Long getPhotoId() 
    {
        return photoId;
    }
    public void setPhotoPath(String photoPath) 
    {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() 
    {
        return photoPath;
    }
    public void setCombomealCommentId(Long combomealCommentId) 
    {
        this.combomealCommentId = combomealCommentId;
    }

    public Long getCombomealCommentId() 
    {
        return combomealCommentId;
    }

    public FfwyCombomealCommentPhoto(Long combomealCommentId) {
        this.combomealCommentId = combomealCommentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId", getPhotoId())
            .append("photoPath", getPhotoPath())
            .append("combomealCommentId", getCombomealCommentId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
