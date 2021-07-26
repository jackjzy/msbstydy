package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderClientVo {

    /** 客户下单量房id */
    private Long orderClientId;

    /** 下单用户 */
    @Excel(name = "下单用户")
    private String userName;

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
