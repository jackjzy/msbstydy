package com.ruoyi.system.domain.admin;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_user_root
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public class FfwyUserRoot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 角色id */
    @Excel(name = "角色id")
    private Long rootId;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRootId(Long rootId) 
    {
        this.rootId = rootId;
    }

    public Long getRootId() 
    {
        return rootId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("rootId", getRootId())
            .toString();
    }
}
