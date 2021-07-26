package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频转发对象 ffwy_video_forward
 * 
 * @author ruoyi
 * @date 2021-07-12
 */
@Data
public class FfwyVideoForward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 转发者Id */
    @Excel(name = "转发者Id")
    private Long userId;

    /** 视频Id */
    @Excel(name = "视频Id")
    private Long videoId;


}
