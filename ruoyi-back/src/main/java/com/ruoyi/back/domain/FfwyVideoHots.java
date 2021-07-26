package com.ruoyi.back.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

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
public class FfwyVideoHots extends BaseEntity
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
