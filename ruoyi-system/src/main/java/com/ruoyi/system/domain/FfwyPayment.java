package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_payment
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public class FfwyPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 付款方式id */
    private Integer paymentId;

    /** 付款类型 */
    @Excel(name = "付款类型")
    private String patmentType;

    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }


    public void setPaymentId(Integer paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Integer getPaymentId() 
    {
        return paymentId;
    }
    public void setPatmentType(String patmentType) 
    {
        this.patmentType = patmentType;
    }

    public String getPatmentType() 
    {
        return patmentType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paymentId", getPaymentId())
            .append("patmentType", getPatmentType())
            .toString();
    }
}
