package com.ruoyi.system.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收货地址对象 ffwy_consignee_addr
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public class FfwyConsigneeAddr extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收货地址id */
    private Long consigneeAddrid;

    /** 所属的用户 */
    @Excel(name = "所属的用户")
    private Long userId;

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

    /** 所属的店铺地址 */
    @Excel(name = "店铺地址")
    private Long shopId;

    /** 1位默认收货地址*/
    private String addressStatus;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
    }

    public void setConsigneeAddrid(Long consigneeAddrid)
    {
        this.consigneeAddrid = consigneeAddrid;
    }

    public Long getConsigneeAddrid() 
    {
        return consigneeAddrid;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
            .append("userId", getUserId())
            .append("consigneeName", getConsigneeName())
            .append("consigneePhone", getConsigneePhone())
            .append("administrative", getAdministrative())
            .append("consigneeAddress", getConsigneeAddress())
            .append("addressStatus", getAddressStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
                .append("shopId",getShopId())
            .toString();
    }
}
