package com.ruoyi.system.domain.combomealorders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 节点订单对象 ffwy_phase
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
public class FfwyPhase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 节点id */
    private Long phaseId;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 节点名称 */
    @Excel(name = "节点名称")
    private String phaseName;

    /** 节点描述 */
    @Excel(name = "节点描述")
    private String phaseIntro;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payMoney;

    /** 父节点id */
    @Excel(name = "父节点id")
    private Long parentId;

    /** 等级 */
    @Excel(name = "等级")
    private Integer level;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** （工长端状态）父节点：0 已完成，1进行中，2待进行/ 子节点 :0 确认 ,1 已确认 ，2待确认 */
    @Excel(name = "父节点：0 已完成，1进行中，2待进行/ 子节点 :0 确认 ,1 已确认，2待确认")
    private String phaseStatus;

    /** （用户端状态）父节点：0 支付，1已支付,2,待进行/ 子节点 :0 确认 ,1 已确认，2 待确认 */
    @Excel(name = "父节点：0 支付，1已支付，2待进行/ 子节点 :0 确认 ,1 已确认，2待确认")
    private String phaseStatusUser;

    /** 订单号 */
    @Excel(name = "等级")
    private String phaseOrderNumber;

    public String getPhaseOrderNumber() {
        return phaseOrderNumber;
    }

    public void setPhaseOrderNumber(String phaseOrderNumber) {
        this.phaseOrderNumber = phaseOrderNumber;
    }

    public String getPhaseStatusUser() {
        return phaseStatusUser;
    }

    public void setPhaseStatusUser(String phaseStatusUser) {
        this.phaseStatusUser = phaseStatusUser;
    }

    /**每个父节点下的子节点*/
    private List<FfwyPhase> ffwyPhaseList;

    public List<FfwyPhase> getFfwyPhaseList() {
        return ffwyPhaseList;
    }

    public void setFfwyPhaseList(List<FfwyPhase> ffwyPhaseList) {
        this.ffwyPhaseList = ffwyPhaseList;
    }

    public void setPhaseId(Long phaseId)
    {
        this.phaseId = phaseId;
    }

    public Long getPhaseId() 
    {
        return phaseId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setPhaseName(String phaseName) 
    {
        this.phaseName = phaseName;
    }

    public String getPhaseName() 
    {
        return phaseName;
    }
    public void setPhaseIntro(String phaseIntro) 
    {
        this.phaseIntro = phaseIntro;
    }

    public String getPhaseIntro() 
    {
        return phaseIntro;
    }
    public void setPayMoney(BigDecimal payMoney) 
    {
        this.payMoney = payMoney;
    }

    public BigDecimal getPayMoney() 
    {
        return payMoney;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setPhaseStatus(String phaseStatus) 
    {
        this.phaseStatus = phaseStatus;
    }

    public String getPhaseStatus() 
    {
        return phaseStatus;
    }

    @Override
    public String toString() {
        return "FfwyPhase{" +
                "phaseId=" + phaseId +
                ", orderId=" + orderId +
                ", phaseName='" + phaseName + '\'' +
                ", phaseIntro='" + phaseIntro + '\'' +
                ", payMoney=" + payMoney +
                ", parentId=" + parentId +
                ", level=" + level +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", phaseStatus='" + phaseStatus + '\'' +
                ", phaseStatusUser='" + phaseStatusUser + '\'' +
                ", ffwyPhaseList=" + ffwyPhaseList +
                '}';
    }
}
