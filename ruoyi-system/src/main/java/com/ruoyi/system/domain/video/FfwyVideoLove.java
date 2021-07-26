package com.ruoyi.system.domain.video;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我喜欢的视频对象 ffwy_video_love
 * 
 * @author ruoyi
 * @date 2021-07-08
 */
public class FfwyVideoLove extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 视频id */
    @Excel(name = "视频id")
    private Long videoId;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("videoId", getVideoId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
