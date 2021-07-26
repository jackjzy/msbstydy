package com.ruoyi.system.domain.withdrawdeposit;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class FfwyApplicaWithdrawal extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /** 申请提现id */
    private Long id;

    /** 商家id */
    @Excel(name = "商家id")
    private int merchantId;

    /** 账户类型 1-银行卡 2-支付宝  3-微信 */
    @Excel(name = "账户类型 1-银行卡 2-支付宝  3-微信")
    private int accountType;

    /** 申请状态  1-已申请，2-已驳回，3-已同意 */
    @Excel(name = "申请状态  1-已申请，2-已驳回，3-已同意")
    private int stuts;

    /** 套餐价格 */
    @Excel(name = "套餐价格")
    private BigDecimal priceAmount;

//    /** 创建时间 */
//    @Excel(name = "创建时间")
//    private Date createTime;
//
//    /** 修改时间 */
//    @Excel(name = "修改时间")
//    private Date updateTime;

    /** 申请提现id */
    public Long getId() {
        return id;
    }

    /** 申请提现id */
    public void  setId(Long id) {
        this.id = id;
    }

    /** 商家id */
    public int getMerchantId() {
        return merchantId;
    }

    /** 商家id */
    public void  setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    /** 账户类型 1-银行卡 2-支付宝  3-微信 */
    public int getAccountType() {
        return accountType;
    }

    /** 账户类型 1-银行卡 2-支付宝  3-微信 */
    public void  setAccountType(int accountType) {
        this.accountType = accountType;
    }

    /** 申请状态  1-已申请，2-已驳回，3-已同意 */
    public int getStuts() {
        return stuts;
    }

    /** 申请状态  1-已申请，2-已驳回，3-已同意 */
    public void  setStuts(int stuts) {
        this.stuts = stuts;
    }

    /** 套餐价格 */
    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    /** 套餐价格 */
    public void  setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

//    /** 创建时间 */
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    /** 创建时间 */
//    public void  setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    /** 修改时间 */
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    /** 修改时间 */
//    public void  setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }

}
