package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_information
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public class FfwyInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资讯id */
    private Long informationId;

    /** 咨询名称 */
    @Excel(name = "咨询名称")
    private String informationName;

    /** 咨询内容 */
    @Excel(name = "咨询内容")
    private String informationMsg;

    /** 咨询视频 */
    @Excel(name = "咨询视频")
    private String informationVideo;

    /** 所属分类 */
    @Excel(name = "所属分类")
    private Long categoryId;

    /** 所属类型 */
    @Excel(name = "所属类型")
    private Long informationTypeId;

    /** 资讯封面 */
    @Excel(name = "资讯封面")
    private String informationCover;

    /** 是否发布：0代表发布、1代表存草稿 */
    @Excel(name = "是否发布：0代表发布、1代表存草稿")
    private String informationStatus;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likeCount;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long informationCommentCount;

    public void setInformationId(Long informationId) 
    {
        this.informationId = informationId;
    }

    public Long getInformationId() 
    {
        return informationId;
    }
    public void setInformationName(String informationName) 
    {
        this.informationName = informationName;
    }

    public String getInformationName() 
    {
        return informationName;
    }
    public void setInformationMsg(String informationMsg) 
    {
        this.informationMsg = informationMsg;
    }

    public String getInformationMsg() 
    {
        return informationMsg;
    }
    public void setInformationVideo(String informationVideo) 
    {
        this.informationVideo = informationVideo;
    }

    public String getInformationVideo() 
    {
        return informationVideo;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setInformationTypeId(Long informationTypeId) 
    {
        this.informationTypeId = informationTypeId;
    }

    public Long getInformationTypeId() 
    {
        return informationTypeId;
    }
    public void setInformationCover(String informationCover) 
    {
        this.informationCover = informationCover;
    }

    public String getInformationCover() 
    {
        return informationCover;
    }
    public void setInformationStatus(String informationStatus) 
    {
        this.informationStatus = informationStatus;
    }

    public String getInformationStatus() 
    {
        return informationStatus;
    }
    public void setLikeCount(Long likeCount) 
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount() 
    {
        return likeCount;
    }
    public void setInformationCommentCount(Long informationCommentCount) 
    {
        this.informationCommentCount = informationCommentCount;
    }

    public Long getInformationCommentCount() 
    {
        return informationCommentCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("informationId", getInformationId())
            .append("informationName", getInformationName())
            .append("informationMsg", getInformationMsg())
            .append("informationVideo", getInformationVideo())
            .append("categoryId", getCategoryId())
            .append("informationTypeId", getInformationTypeId())
            .append("informationCover", getInformationCover())
            .append("informationStatus", getInformationStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("likeCount", getLikeCount())
            .append("informationCommentCount", getInformationCommentCount())
            .toString();
    }
}
