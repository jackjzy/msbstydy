package com.ruoyi.system.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_order_client
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public class FfwyOrderClient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户下单量房id */
    private Long orderClientId;

    /** 下单用户 */
    @Excel(name = "下单用户")
    private Integer userId;

    /** 下单手机号 */
    @Excel(name = "下单手机号")
    private Long orderClientPhone;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String orderClientAddr;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private String orderClientType;

    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    /** 订单状态  0未量房  1已量房 2退款 */
    @Excel(name = "订单状态  0未量房  1已量房 2退款")
    private Integer orderClientStatus;

    public void setOrderClientId(Long orderClientId) 
    {
        this.orderClientId = orderClientId;
    }

    public Long getOrderClientId() 
    {
        return orderClientId;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getUserId()
    {
        return userId;
    }
    public void setOrderClientPhone(Long orderClientPhone) 
    {
        this.orderClientPhone = orderClientPhone;
    }

    public Long getOrderClientPhone() 
    {
        return orderClientPhone;
    }
    public void setOrderClientAddr(String orderClientAddr) 
    {
        this.orderClientAddr = orderClientAddr;
    }

    public String getOrderClientAddr() 
    {
        return orderClientAddr;
    }
    public void setOrderClientType(String orderClientType) 
    {
        this.orderClientType = orderClientType;
    }

    public String getOrderClientType() 
    {
        return orderClientType;
    }
    public void setOrderClientStatus(Integer orderClientStatus) 
    {
        this.orderClientStatus = orderClientStatus;
    }

    public Integer getOrderClientStatus() 
    {
        return orderClientStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderClientId", getOrderClientId())
            .append("userId", getUserId())
            .append("orderClientPhone", getOrderClientPhone())
            .append("orderClientAddr", getOrderClientAddr())
            .append("orderClientType", getOrderClientType())
            .append("orderClientStatus", getOrderClientStatus())
            .toString();
    }
}
