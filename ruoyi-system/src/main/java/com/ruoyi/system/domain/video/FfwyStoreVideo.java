package com.ruoyi.system.domain.video;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频收藏对象 ffwy_store_video
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public class FfwyStoreVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收藏视频id */
    private Long storeVideoId;

    /** 视频id */
    @Excel(name = "视频id")
    private Long videoId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    public void setStoreVideoId(Long storeVideoId) 
    {
        this.storeVideoId = storeVideoId;
    }

    public Long getStoreVideoId() 
    {
        return storeVideoId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("storeVideoId", getStoreVideoId())
            .append("videoId", getVideoId())
            .append("userId", getUserId())
            .toString();
    }
}
