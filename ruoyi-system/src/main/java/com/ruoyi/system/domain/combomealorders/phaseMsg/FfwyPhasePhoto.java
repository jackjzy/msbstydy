package com.ruoyi.system.domain.combomealorders.phaseMsg;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_phase_photo
 * 
 * @author ruoyi
 * @date 2021-05-22
 */
public class FfwyPhasePhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 节点图片id */
    private Long phasePhotoId;

    /** 节点id */
    @Excel(name = "节点id")
    private Long phaseMsgId;

    /** 节点图片 */
    @Excel(name = "节点图片")
    private String phasePhoto;

    public void setPhasePhotoId(Long phasePhotoId) 
    {
        this.phasePhotoId = phasePhotoId;
    }

    public Long getPhasePhotoId() 
    {
        return phasePhotoId;
    }
    public void setPhaseMsgId(Long phaseMsgId) 
    {
        this.phaseMsgId = phaseMsgId;
    }

    public Long getPhaseMsgId() 
    {
        return phaseMsgId;
    }
    public void setPhasePhoto(String phasePhoto) 
    {
        this.phasePhoto = phasePhoto;
    }

    public String getPhasePhoto() 
    {
        return phasePhoto;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phasePhotoId", getPhasePhotoId())
            .append("phaseMsgId", getPhaseMsgId())
            .append("phasePhoto", getPhasePhoto())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
