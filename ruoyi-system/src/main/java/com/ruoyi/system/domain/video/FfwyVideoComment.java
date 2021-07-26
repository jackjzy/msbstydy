package com.ruoyi.system.domain.video;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_video_comment
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Data
public class FfwyVideoComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频评论id */
    private Long videoCommentId;

    /** 视频id */
    @Excel(name = "视频id")
    private Long videoId;

    /** 父级评论id */
    @Excel(name = "父级评论id")
    private Long parentId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String videoComment;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    private String userName;

    private String photo;
    private String commentId;

    /** 评论时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "评论时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date commentTime;

    private Long commentDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Long commentDate) {
        this.commentDate = commentDate;
    }

    public void setVideoCommentId(Long videoCommentId)
    {
        this.videoCommentId = videoCommentId;
    }

    public Long getVideoCommentId() 
    {
        return videoCommentId;
    }
    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setVideoComment(String videoComment) 
    {
        this.videoComment = videoComment;
    }

    public String getVideoComment() 
    {
        return videoComment;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCommentTime(Date commentTime) 
    {
        this.commentTime = commentTime;
    }

    public Date getCommentTime() 
    {
        return commentTime;
    }

    @Override
    public String toString() {
        return "FfwyVideoComment{" +
                "videoCommentId=" + videoCommentId +
                ", videoId=" + videoId +
                ", parentId=" + parentId +
                ", videoComment='" + videoComment + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", photo='" + photo + '\'' +
                ", commentTime=" + commentTime +
                ", commentDate=" + commentDate +
                '}';
    }
}
