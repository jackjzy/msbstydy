package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_fqas_reply
 * 
 * @author ruoyi
 * @date 2021-04-15
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FfwyFqasReply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 常见问题回复id */
    private Long relpyId;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String replyMsg;
    /** 常见问题id*/
    private Long faqsId;
    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date replyTime;

    private Date beginTime;

    private Date endTime;


}
