package com.ruoyi.system.domain.order;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_order_plane
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public class FfwyOrderPlane extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long planeId;

    /** 应付金额 */
    @Excel(name = "应付金额")
    private BigDecimal planeAmountpayable;

    /** 实付金额 */
    @Excel(name = "实付金额")
    private BigDecimal planeTheamountpayable;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Integer paymentId;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planeRealpaytime;

    /** 意向升级套餐 */
    @Excel(name = "意向升级套餐")
    private Integer planeCombomealId;

    /** 意向套餐 */
    @Excel(name = "意向套餐")
    private Integer combomealId;

    /** 客户下单的订单id */
    @Excel(name = "客户下单的订单id")
    private Long orderClientId;

    /** 订单状态  0已完成  1未完成 */
    @Excel(name = "订单状态  0已完成  1未完成")
    private Integer planeStatus;

    public void setPlaneId(Long planeId) 
    {
        this.planeId = planeId;
    }

    public Long getPlaneId() 
    {
        return planeId;
    }
    public void setPlaneAmountpayable(BigDecimal planeAmountpayable) 
    {
        this.planeAmountpayable = planeAmountpayable;
    }

    public BigDecimal getPlaneAmountpayable() 
    {
        return planeAmountpayable;
    }
    public void setPlaneTheamountpayable(BigDecimal planeTheamountpayable) 
    {
        this.planeTheamountpayable = planeTheamountpayable;
    }

    public BigDecimal getPlaneTheamountpayable() 
    {
        return planeTheamountpayable;
    }
    public void setPaymentId(Integer paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Integer getPaymentId() 
    {
        return paymentId;
    }
    public void setPlaneRealpaytime(Date planeRealpaytime) 
    {
        this.planeRealpaytime = planeRealpaytime;
    }

    public Date getPlaneRealpaytime() 
    {
        return planeRealpaytime;
    }
    public void setPlaneCombomealId(Integer planeCombomealId) 
    {
        this.planeCombomealId = planeCombomealId;
    }

    public Integer getPlaneCombomealId() 
    {
        return planeCombomealId;
    }
    public void setCombomealId(Integer combomealId) 
    {
        this.combomealId = combomealId;
    }

    public Integer getCombomealId() 
    {
        return combomealId;
    }
    public void setOrderClientId(Long orderClientId) 
    {
        this.orderClientId = orderClientId;
    }

    public Long getOrderClientId() 
    {
        return orderClientId;
    }
    public void setPlaneStatus(Integer planeStatus) 
    {
        this.planeStatus = planeStatus;
    }

    public Integer getPlaneStatus() 
    {
        return planeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("planeId", getPlaneId())
            .append("planeAmountpayable", getPlaneAmountpayable())
            .append("planeTheamountpayable", getPlaneTheamountpayable())
            .append("paymentId", getPaymentId())
            .append("planeRealpaytime", getPlaneRealpaytime())
            .append("planeCombomealId", getPlaneCombomealId())
            .append("combomealId", getCombomealId())
            .append("orderClientId", getOrderClientId())
            .append("planeStatus", getPlaneStatus())
            .toString();
    }
}
