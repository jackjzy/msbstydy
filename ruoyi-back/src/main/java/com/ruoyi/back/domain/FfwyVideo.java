package com.ruoyi.back.domain;

import com.ruoyi.system.domain.FfwyTag;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 视频对象 ffwy_video
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public class FfwyVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频id */
    private Long videoId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 视频封面 */
    @Excel(name = "视频封面")
    private String videoCover;

    /** 视频标题 */
    @Excel(name = "视频标题")
    private String videoTitle;

    /** 视频简介 */
    @Excel(name = "视频简介")
    private String videoIntro;

    /** 视频路径 */
    @Excel(name = "视频路径")
    private String videoPath;

    /** 视频分类 */
    @Excel(name = "视频分类")
    private Long productCategoryId;

    /** 视频状态 */
    @Excel(name = "视频状态")
    private String videoStatus;

    //视频时长
    private Long videoDuration;

    /** 是否点赞*/
    private Boolean isLikes;
    /** 是否收藏*/
    private Boolean isStroe;
    /** 是否关注该用户*/
    private Boolean isAttention;

    private List<FfwyTag> tagList;

    private List<String> tags;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /** 点赞数*/
    private Long like;

    /** 评论数*/
    private Long commentCount;

    private Integer prouductId;

    public List<FfwyTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<FfwyTag> tagList) {
        this.tagList = tagList;
    }

    public Integer getProuductId() {
        return prouductId;
    }

    public void setProuductId(Integer prouductId) {
        this.prouductId = prouductId;
    }

    public Boolean getAttention() {
        return isAttention;
    }

    public void setAttention(Boolean attention) {
        isAttention = attention;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean getLikes() {
        return isLikes;
    }

    public void setLikes(Boolean likes) {
        isLikes = likes;
    }

    public Boolean getStroe() {
        return isStroe;
    }

    public void setStroe(Boolean stroe) {
        isStroe = stroe;
    }



    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public void setVideoId(Long videoId)
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setVideoCover(String videoCover) 
    {
        this.videoCover = videoCover;
    }

    public String getVideoCover() 
    {
        return videoCover;
    }
    public void setVideoTitle(String videoTitle) 
    {
        this.videoTitle = videoTitle;
    }

    public String getVideoTitle() 
    {
        return videoTitle;
    }
    public void setVideoIntro(String videoIntro) 
    {
        this.videoIntro = videoIntro;
    }

    public String getVideoIntro() 
    {
        return videoIntro;
    }
    public void setVideoPath(String videoPath) 
    {
        this.videoPath = videoPath;
    }

    public String getVideoPath() 
    {
        return videoPath;
    }
    public void setProductCategoryId(Long productCategoryId) 
    {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductCategoryId() 
    {
        return productCategoryId;
    }
    public void setVideoStatus(String videoStatus) 
    {
        this.videoStatus = videoStatus;
    }

    public String getVideoStatus() 
    {
        return videoStatus;
    }

    public Long getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Long videoDuration) {
        this.videoDuration = videoDuration;
    }

    @Override
    public String toString() {
        return "FfwyVideo{" +
                "videoId=" + videoId +
                ", userId=" + userId +
                ", videoCover='" + videoCover + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoIntro='" + videoIntro + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", videoStatus='" + videoStatus + '\'' +
                ", videoDuration=" + videoDuration +
                ", isLikes=" + isLikes +
                ", isStroe=" + isStroe +
                ", isAttention=" + isAttention +
                ", tagList=" + tagList +
                ", tags=" + tags +
                ", like=" + like +
                ", commentCount=" + commentCount +
                ", prouductId=" + prouductId +
                '}';
    }
}
