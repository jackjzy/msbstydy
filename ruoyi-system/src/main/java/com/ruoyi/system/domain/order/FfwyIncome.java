package com.ruoyi.system.domain.order;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.system.domain.FfwyPayment;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收入明细对象 ffwy_income
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@NoArgsConstructor
public class FfwyIncome extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long incomeId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long orderId;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 收入或支出：0收入、1支出 */
    @Excel(name = "收入或支出：0收入、1支出")
    private String incomePay;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealTime;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private Integer paymentId;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    /** 表示n天内的营收*/
    private Integer day;


    private Date BeginTime;

    private Date endTime;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public Date getBeginTime() {
        return BeginTime;
    }

    @Override
    public void setBeginTime(Date beginTime) {
        BeginTime = beginTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * 付款方式
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private FfwyPayment ffwyPayment;

    /** 订单详情*/
    private FfwyOrderDetails orderDetails;

    public FfwyOrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(FfwyOrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public FfwyIncome(Long userId, String incomePay, Date dealTime) {
        this.userId = userId;
        this.incomePay = incomePay;
        this.dealTime = dealTime;
    }

    public FfwyIncome(Long orderId, Long userId, Date dealTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.dealTime = dealTime;
    }

    public void setIncomeId(Long incomeId)
    {
        this.incomeId = incomeId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getIncomeId()
    {
        return incomeId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setIncomePay(String incomePay) 
    {
        this.incomePay = incomePay;
    }

    public String getIncomePay() 
    {
        return incomePay;
    }
    public void setDealTime(Date dealTime) 
    {
        this.dealTime = dealTime;
    }

    public Date getDealTime() 
    {
        return dealTime;
    }
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
    }
    public void setPaymentId(Integer paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Integer getPaymentId() 
    {
        return paymentId;
    }

    public FfwyPayment getFfwyPayment() {
        return ffwyPayment;
    }

    public void setFfwyPayment(FfwyPayment ffwyPayment) {
        this.ffwyPayment = ffwyPayment;
    }

    @Override
    public String toString() {
        return "FfwyIncome{" +
                "incomeId=" + incomeId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", incomePay='" + incomePay + '\'' +
                ", dealTime=" + dealTime +
                ", money=" + money +
                ", paymentId=" + paymentId +
                ", orderNumber='" + orderNumber + '\'' +
                ", day=" + day +
                ", BeginTime=" + BeginTime +
                ", endTime=" + endTime +
                ", ffwyPayment=" + ffwyPayment +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
