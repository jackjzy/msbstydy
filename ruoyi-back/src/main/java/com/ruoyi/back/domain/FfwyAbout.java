package com.ruoyi.back.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_about
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
public class FfwyAbout extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** appid */
    private Long appId;

    /** app名称 */
    @Excel(name = "app名称")
    private String appName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String appPhone;

    /** 联系邮箱 */
    @Excel(name = "联系邮箱")
    private String appEmail;

    /** app图标 */
    @Excel(name = "app图标")
    private String appLogo;

    /** app介绍 */
    @Excel(name = "app介绍")
    private String appMsg;

    /** app版权信息 */
    @Excel(name = "app版权信息")
    private String appCopyright;

    public void setAppId(Long appId) 
    {
        this.appId = appId;
    }

    public Long getAppId() 
    {
        return appId;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setAppPhone(String appPhone) 
    {
        this.appPhone = appPhone;
    }

    public String getAppPhone() 
    {
        return appPhone;
    }
    public void setAppEmail(String appEmail) 
    {
        this.appEmail = appEmail;
    }

    public String getAppEmail() 
    {
        return appEmail;
    }
    public void setAppLogo(String appLogo) 
    {
        this.appLogo = appLogo;
    }

    public String getAppLogo() 
    {
        return appLogo;
    }
    public void setAppMsg(String appMsg) 
    {
        this.appMsg = appMsg;
    }

    public String getAppMsg() 
    {
        return appMsg;
    }
    public void setAppCopyright(String appCopyright) 
    {
        this.appCopyright = appCopyright;
    }

    public String getAppCopyright() 
    {
        return appCopyright;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appId", getAppId())
            .append("appName", getAppName())
            .append("appPhone", getAppPhone())
            .append("appEmail", getAppEmail())
            .append("appLogo", getAppLogo())
            .append("appMsg", getAppMsg())
            .append("appCopyright", getAppCopyright())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
