package com.ruoyi.system.domain.video;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】对象 ffwy_video_hot
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
public class FfwyVideoHotVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排行榜id */
    private Integer hotId;

    /** 排行榜名称 */
    @Excel(name = "排行榜名称")
    private String hotName;

    private Date beginTime;

    private Date endTime;

    private List<FfwyVideoHotsVo> videos;

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

    public List<FfwyVideoHotsVo> getVideos() {
        return videos;
    }

    public void setVideos(List<FfwyVideoHotsVo> videos) {
        this.videos = videos;
    }

    public void setHotId(Integer hotId)
    {
        this.hotId = hotId;
    }

    public Integer getHotId() 
    {
        return hotId;
    }
    public void setHotName(String hotName) 
    {
        this.hotName = hotName;
    }

    public String getHotName() 
    {
        return hotName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hotId", getHotId())
            .append("hotName", getHotName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
