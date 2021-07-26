package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【请填写功能名称】对象 ffwy_pay_refund
 * 
 * @author ruoyi
 * @date 2021-07-07
 */
@Data
public class FfwyPayRefund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增id */
    private Long id;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String payType;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 退款订单号 */
    @Excel(name = "退款订单号")
    private String reFundOrderId;

    /** 退款请求数据 */
    @Excel(name = "退款请求数据")
    private String reqData;

    /** 退款请求返回数据 */
    @Excel(name = "退款请求返回数据")
    private String resData;

    /** 用户id */
    @Excel(name = "用户id")
    private Integer userId;

    /** 退款用户id */
    @Excel(name = "退款用户id")
    private Integer refundUserId;

    /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal refundMoney;

    /** 退款回调数据 */
    @Excel(name = "退款回调数据")
    private String callBackData;

    /** 退款交易查询id */
    @Excel(name = "退款交易查询id")
    private String callBackQuerid;

    /** 退款回调时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款回调时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date callBackDate;

    /** 退款状态1:发起退款 2:退款成功  3:退款失败 */
    @Excel(name = "退款状态1:发起退款 2:退款成功  3:退款失败")
    private Integer status;

}
