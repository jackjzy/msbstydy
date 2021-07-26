package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 【请填写功能名称】对象 ffwy_pay_record
 *
 * @author ruoyi
 * @date 2021-06-24
 */
@Data
public class FfwyPayRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 支付记录id */
    private Long recordId;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String payTyep;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private String payMoney;
    /** 支付金额 */
    @Excel(name = "用户id")
    private Long userId;

    /** 商品说明 */
    @Excel(name = "商品说明")
    private String productMsg;

    /** 支付参数 */
    @Excel(name = "支付参数")
    private String contentData;

    /** 请求参数 */
    @Excel(name = "请求参数")
    private String requestData;

    /** 响应参数 */
    @Excel(name = "响应参数")
    private String responseData;

    /** 回调数据 **/
    private String cellBackData;

    /** 消费交易的流水号，供后续查询,退款用  **/
    private String cellBackQueryId;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    /** 支付订单类型 */
    @Excel(name = "支付订单类型")
    private String payOrderType;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String payStatus;

    private Date cteateOrderTime;

    /** 支付所需参数*/
    private String tn;

}
