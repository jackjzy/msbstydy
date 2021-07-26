package com.ruoyi.system.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单状态对象 ffwy_order_status
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public class FfwyOrderStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单状态id */
    private Long statusId;

    /** 状态名称 */
    @Excel(name = "状态名称")
    private String statusName;

    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public void setStatusId(Long statusId) 
    {
        this.statusId = statusId;
    }

    public Long getStatusId() 
    {
        return statusId;
    }
    public void setStatusName(String statusName) 
    {
        this.statusName = statusName;
    }

    public String getStatusName() 
    {
        return statusName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("statusId", getStatusId())
            .append("statusName", getStatusName())
            .toString();
    }
}
