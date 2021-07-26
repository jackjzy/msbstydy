package com.ruoyi.back.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 举报管理对象 ffwy_report
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Data
public class FfwyReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 举报id */
    private Long reportId;

    /** 举报人id */
    @Excel(name = "举报人id")
    private Long userId;

    /** 举报人手机号 */
    @Excel(name = "举报人手机号")
    private String phone;

    /** 举报类型 */
    @Excel(name = "举报类型")
    private Integer typeId;

    /** 举报人姓名 */
    @Excel(name = "举报人姓名")
    private String userName;

    private FfwyReportType ffwyReportType;

    public FfwyReportType getFfwyReportType() {
        return ffwyReportType;
    }

    public void setFfwyReportType(FfwyReportType ffwyReportType) {
        this.ffwyReportType = ffwyReportType;
    }

    /** 被举报人id */
    @Excel(name = "被举报人id")
    private Long reportUserId;

    /** 被举报人姓名 */
    @Excel(name = "被举报人姓名")
    private String reportUserName;

    /** 举报内容 */
    @Excel(name = "举报内容")
    private String reportContent;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String reply;

    /** 回复状态 */
    @Excel(name = "回复状态")
    private String replyStatus;

    //被举报视频Id
    private Integer reportVideo;

    private Date createTime;

    private String searchStr;

}
