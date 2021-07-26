package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.order.FfwyOrderPlane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyOrderPlaneVo extends FfwyOrderPlane {
    private String combomealname;

    private String patmenttype;

    /** 量房订单id */
    private Long planeId;

    /** 应付金额 */
    @Excel(name = "应付金额")
    private BigDecimal planeAmountpayable;

    /** 实付金额 */
    @Excel(name = "实付金额")
    private BigDecimal planeTheamountpayable;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planeRealpaytime;

    /** 订单状态  0已完成  1未完成 */
    @Excel(name = "订单状态  0已完成  1未完成")
    private Integer planeStatus;

    /** 下单用户 */
    @Excel(name = "下单用户")
    private String username;

    /** 下单手机号 */
    @Excel(name = "下单手机号")
    private Long orderClientPhone;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String orderClientAddr;

    /** 订单类型 */
    @Excel(name = "订单类型")
    private String orderClientType;

    /** 订单状态  0未量房  1已量房 2退款 */
    @Excel(name = "订单状态  0未量房  1已量房 2退款")
    private Integer orderClientStatus;

}
