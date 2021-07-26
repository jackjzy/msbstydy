package com.ruoyi.system.domain.admin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 粉丝对象 ffwy_user_fans
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public class FfwyUserFans extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 粉丝id */
    @Excel(name = "粉丝id")
    private Long fansId;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setFansId(Long fansId) 
    {
        this.fansId = fansId;
    }

    public Long getFansId() 
    {
        return fansId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("fansId", getFansId())
            .toString();
    }
}
