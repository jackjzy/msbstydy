package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 赵字豪
 * @Title: 订单查询
 * @date 2021/4/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    /**
     * 用户信息
     */
    @Excel(name = "用户信息")
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNumber;

    private Long shopId;

    /**
     * 下单状态
     */
    @Excel(name = "下单状态")
    private Integer orderStatus;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 商品名称
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String productName;

    /**
     * 所属分类id
     */
    @Excel(name = "所属分类id")
    private Long categoryId;

    private Integer categoryLevel;
    private String searchField;

    private Double beginPrice;
    private Double endPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;



}
