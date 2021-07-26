package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 反馈图片对象 ffwy_feedback_photo
 * 
 * @author ruoyi
 * @date 2021-04-30
 */
public class FfwyFeedbackPhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    public FfwyFeedbackPhoto(Long photoId, Long feedbackId, String path, Date createTime) {
        this.photoId = photoId;
        this.feedbackId = feedbackId;
        this.path = path;
        this.createTime = createTime;
    }
    public FfwyFeedbackPhoto(){}
    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 图片id */
    private Long photoId;

    /** 反馈id */
    @Excel(name = "反馈id")
    private Long feedbackId;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String path;

    private Date createTime;

    public Date getCrateTime() {
        return createTime;
    }

    public FfwyFeedbackPhoto(Long feedbackId, String path, Date createTime) {
        this.feedbackId = feedbackId;
        this.path = path;
        this.createTime = createTime;
    }

    public void setCrateTime(Date crateTime) {
        this.createTime = crateTime;
    }

    public void setPhotoId(Long photoId)
    {
        this.photoId = photoId;
    }

    public Long getPhotoId() 
    {
        return photoId;
    }
    public void setFeedbackId(Long feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() 
    {
        return feedbackId;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId", getPhotoId())
            .append("feedbackId", getFeedbackId())
            .append("path", getPath())
            .append("createTime", getCreateTime())
            .toString();
    }
}
