package com.ruoyi.system.domain.shop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺收货地址对象对象 ffwy_shop_addr
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
public class FfwyShopAddr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收货地址id */
    private Long consigneeAddrid;

    /** 所属的用户 */
    @Excel(name = "所属的用户")
    private Long shopId;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String consigneeName;

    /** 收货人号码 */
    @Excel(name = "收货人号码")
    private String consigneePhone;

    /** 行政区域 */
    @Excel(name = "行政区域")
    private String administrative;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String consigneeAddress;

    public void setConsigneeAddrid(Long consigneeAddrid) 
    {
        this.consigneeAddrid = consigneeAddrid;
    }

    public Long getConsigneeAddrid() 
    {
        return consigneeAddrid;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }
    public void setConsigneeName(String consigneeName) 
    {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeName() 
    {
        return consigneeName;
    }
    public void setConsigneePhone(String consigneePhone) 
    {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneePhone() 
    {
        return consigneePhone;
    }
    public void setAdministrative(String administrative) 
    {
        this.administrative = administrative;
    }

    public String getAdministrative() 
    {
        return administrative;
    }
    public void setConsigneeAddress(String consigneeAddress) 
    {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeAddress() 
    {
        return consigneeAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("consigneeAddrid", getConsigneeAddrid())
            .append("shopId", getShopId())
            .append("consigneeName", getConsigneeName())
            .append("consigneePhone", getConsigneePhone())
            .append("administrative", getAdministrative())
            .append("consigneeAddress", getConsigneeAddress())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
