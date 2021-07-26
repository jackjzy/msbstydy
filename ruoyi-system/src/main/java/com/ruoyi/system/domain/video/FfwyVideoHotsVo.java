package com.ruoyi.system.domain.video;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 【请填写功能名称】对象 ffwy_video_hots
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FfwyVideoHotsVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long videoHotId;

    /** 视频id */
    @Excel(name = "视频id")
    private String videoName;

    /** 排行标题 */
    @Excel(name = "排行标题")
    private Integer hotId;


}
