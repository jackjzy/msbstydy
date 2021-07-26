package com.ruoyi.system.domain.combomealorders.phaseMsg;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 【请填写功能名称】对象 ffwy_phase_msg
 * 
 * @author ruoyi
 * @date 2021-05-22
 */
public class FfwyPhaseMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 节点信息id */
    private Long phaseMsgId;

    /** 节点描述 */
    @Excel(name = "节点描述")
    private String phaseMsg;

    /** 节点名称 */
    @Excel(name = "节点名称")
    private String phaseTitle;

    private Long orderCombomealId;

    private List<FfwyPhasePhoto> ffwyPhasePhotos;

    public List<FfwyPhasePhoto> getFfwyPhasePhotos() {
        return ffwyPhasePhotos;
    }

    public void setFfwyPhasePhotos(List<FfwyPhasePhoto> ffwyPhasePhotos) {
        this.ffwyPhasePhotos = ffwyPhasePhotos;
    }

    public void setPhaseMsgId(Long phaseMsgId)
    {
        this.phaseMsgId = phaseMsgId;
    }

    public Long getPhaseMsgId() 
    {
        return phaseMsgId;
    }
    public void setPhaseMsg(String phaseMsg) 
    {
        this.phaseMsg = phaseMsg;
    }

    public String getPhaseMsg() 
    {
        return phaseMsg;
    }
    public void setPhaseTitle(String phaseTitle) 
    {
        this.phaseTitle = phaseTitle;
    }

    public String getPhaseTitle() 
    {
        return phaseTitle;
    }

    public Long getOrderCombomealId() {
        return orderCombomealId;
    }

    public void setOrderCombomealId(Long orderCombomealId) {
        this.orderCombomealId = orderCombomealId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("phaseMsgId", getPhaseMsgId())
            .append("phaseMsg", getPhaseMsg())
            .append("phaseTitle", getPhaseTitle())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
