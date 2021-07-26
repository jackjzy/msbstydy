package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyorderdespeVo {

    /** orderId */
    private Long orderId;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    /** 状态 */
    @Excel(name = "售后状态")
    private String orderStatus;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String productPhoto;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String productSkuName;

    /** 规格 */
    @Excel(name = "规格")
    private String productSkuSpec;

    /** 数量 */
    @Excel(name = "数量")
    private Integer number;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;


    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal zongj;

    //售后Id
    private Integer afterSaleid;

    //售后状态
    private String afterStatus;

    private String orderDetailsId;

    private List<FfwyOrderDetails> ffwyOrderDetails;


}
