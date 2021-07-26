package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.system.domain.admin.FfwyUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;

import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】对象 ffwy_feedback
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FfwyFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈id */
    private Long feedbackId;

    /** 所属于的用户 */
    @Excel(name = "所属于的用户")
    private Long userId;

    /** 反馈信息 */
    @Excel(name = "反馈信息")
    private String feedbackMessage;

    /** 反馈状态：0代表未回复、1代表已回复 */
    @Excel(name = "反馈状态：0代表未回复、1代表已回复")
    private String feedbackStatus;
    /** 反馈的用户*/
    private FfwyUser user;
    /** 反馈回复的信息*/
    private FfwyFeedbackReply reply;

    private List<FfwyFeedbackPhoto> photos;

    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date createTime;

    private Date beginTime;

    private Date endTime;

    private String feedType;

    private String searchStr;
}
