package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_workplan
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
public class FfwyWorkplan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工期id */
    private Integer workplanId;

    /** 节点 */
    @Excel(name = "节点")
    private Integer phaseId;

    /** 工长 */
    @Excel(name = "工长")
    private Long userId;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;


    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    public void setWorkplanId(Integer workplanId) 
    {
        this.workplanId = workplanId;
    }

    public Integer getWorkplanId() 
    {
        return workplanId;
    }
    public void setPhaseId(Integer phaseId) 
    {
        this.phaseId = phaseId;
    }

    public Integer getPhaseId() 
    {
        return phaseId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCombomealId(Long combomealId) 
    {
        this.combomealId = combomealId;
    }

    public Long getCombomealId() 
    {
        return combomealId;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setFinishTime(Date finishTime) 
    {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() 
    {
        return finishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("workplanId", getWorkplanId())
            .append("phaseId", getPhaseId())
            .append("userId", getUserId())
            .append("combomealId", getCombomealId())
            .append("beginTime", getBeginTime())
            .append("finishTime", getFinishTime())
            .toString();
    }
}
