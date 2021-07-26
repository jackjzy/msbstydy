package com.ruoyi.system.domain.video;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频对象 ffwy_video
 * 
 * @author ruoyi
 * @date 2021-05-13
 */
public class FfwyAppVideo extends BaseEntity
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("videoId", getVideoId())
            .append("userId", getUserId())
            .append("videoCover", getVideoCover())
            .append("videoTitle", getVideoTitle())
            .append("videoIntro", getVideoIntro())
            .append("videoPath", getVideoPath())
            .append("productCategoryId", getProductCategoryId())
            .append("createTime", getCreateTime())
            .append("videoStatus", getVideoStatus())
            .toString();
    }
}
