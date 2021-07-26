package com.ruoyi.back.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现申请管理对象 ffwy_shop_withdraw
 *
 * @author Yapeng Guo
 * @date 2021-05-25
 */
public class FfwyShopWithdraw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 提现申请id */
    private Long withdrawId;

    /** 商户id */
    @Excel(name = "商户id")
    private Long shopId;

    /** 银行卡卡号 */
    @Excel(name = "银行卡卡号")
    private String bankCard;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private BigDecimal withdrawNum;

    /** 转账截图 */
    @Excel(name = "转账截图")
    private String transferAccount;

    /** 申请状态；0审核中；1已同意；2已拒绝； */
    @Excel(name = "申请状态；0审核中；1已同意；2已拒绝；")
    private String applyStatus;

    /** 拒绝理由 */
    @Excel(name = "拒绝理由")
    private String refuseReason;

    /** 商户名称 */
    private String shopName;

    private Date beginTime;

    private Date endTime;

    @Override
    public Date getBeginTime() {
        return beginTime;
    }

    @Override
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setWithdrawId(Long withdrawId)
    {
        this.withdrawId = withdrawId;
    }

    public Long getWithdrawId()
    {
        return withdrawId;
    }
    public void setShopId(Long shopId)
    {
        this.shopId = shopId;
    }

    public Long getShopId()
    {
        return shopId;
    }
    public void setBankCard(String bankCard)
    {
        this.bankCard = bankCard;
    }

    public String getBankCard()
    {
        return bankCard;
    }
    public void setWithdrawNum(BigDecimal withdrawNum)
    {
        this.withdrawNum = withdrawNum;
    }

    public BigDecimal getWithdrawNum()
    {
        return withdrawNum;
    }
    public void setTransferAccount(String transferAccount)
    {
        this.transferAccount = transferAccount;
    }

    public String getTransferAccount()
    {
        return transferAccount;
    }
    public void setApplyStatus(String applyStatus)
    {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus()
    {
        return applyStatus;
    }
    public void setRefuseReason(String refuseReason)
    {
        this.refuseReason = refuseReason;
    }

    public String getRefuseReason()
    {
        return refuseReason;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("withdrawId", getWithdrawId())
            .append("shopId", getShopId())
            .append("shopName", getShopName())
            .append("bankCard", getBankCard())
            .append("withdrawNum", getWithdrawNum())
            .append("transferAccount", getTransferAccount())
            .append("applyStatus", getApplyStatus())
            .append("refuseReason", getRefuseReason())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
