package com.ruoyi.back.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审核对象 ffwy_audit
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public class FfwyAudit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核id */
    private Long auditId;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 店铺名称 */
    @JSONField(name = "name")
    @Excel(name = "店铺名称")
    private String name;

    /** 联系人姓名 */
    @JSONField(name = "userName")
    @Excel(name = "联系人姓名")
    private String shopUserName;

    /** 联系电话 */
    @JSONField(name = "phone")
    @Excel(name = "联系电话")
    private String phone;

    /** 联系人邮箱 */
    @Excel(name = "联系人邮箱")
    private String email;

    /** 店铺网址 */
    @Excel(name = "店铺网址")
    private String shopUrl;

    /** 身份证正面 */
    @Excel(name = "身份证正面")
    private String idCard;

    /** 身份证背面 */
    @Excel(name = "身份证背面")
    private String idCardBack;

    /** 营业执照 */
    @Excel(name = "营业执照")
    private String licence;

    /** 审核状态：1代表待审核中、2代表审核通过、3代表审核驳回 */
    @Excel(name = "审核状态：1代表待审核中、2代表审核通过、3代表审核驳回")
    private FfwyAuditStatus auditStatus;

    /** 拒绝原因 */
    @Excel(name = "拒绝原因")
    private String reject;

    /** 通过时间 */
    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    @Excel(name = "通过时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date passTime;

    public void setAuditId(Long auditId) 
    {
        this.auditId = auditId;
    }

    public Long getAuditId() 
    {
        return auditId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShopUserName(String shopUserName)
    {
        this.shopUserName = shopUserName;
    }

    public String getShopUserName() 
    {
        return shopUserName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setShopUrl(String shopUrl) 
    {
        this.shopUrl = shopUrl;
    }

    public String getShopUrl() 
    {
        return shopUrl;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setIdCardBack(String idCardBack) 
    {
        this.idCardBack = idCardBack;
    }

    public String getIdCardBack() 
    {
        return idCardBack;
    }
    public void setLicence(String licence) 
    {
        this.licence = licence;
    }

    public String getLicence() 
    {
        return licence;
    }
    public void setAuditStatus(FfwyAuditStatus auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public FfwyAuditStatus getAuditStatus()
    {
        return auditStatus;
    }
    public void setReject(String reject) 
    {
        this.reject = reject;
    }

    public String getReject() 
    {
        return reject;
    }
    public void setPassTime(Date passTime) 
    {
        this.passTime = passTime;
    }

    public Date getPassTime() 
    {
        return passTime;
    }


    @Override
    public String toString() {
        return "FfwyAudit{" +
                "auditId=" + auditId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", shopUserName='" + shopUserName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", shopUrl='" + shopUrl + '\'' +
                ", idCard='" + idCard + '\'' +
                ", idCardBack='" + idCardBack + '\'' +
                ", licence='" + licence + '\'' +
                ", auditStatus=" + auditStatus +
                ", reject='" + reject + '\'' +
                ", searchStr='" + searchStr + '\'' +
                ", passTime=" + passTime +
                '}';
    }
}
