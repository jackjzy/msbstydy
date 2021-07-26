package com.ruoyi.system.domain.aftersale;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 售后对象 ffwy_after_sale
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
@Data
public class FfwyAfterSale extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 售后id */
    private Long afterSaleid;

    /** 订单详情id */
    @Excel(name = "订单详情id")
    private String orderDetailsId;

    private int[] orderIds;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    /** 属于哪个用户的售后 */
    @Excel(name = "属于哪个用户的售后")
    private Long userId;

    /** 退款原因 */
    @Excel(name = "退款原因")
    private Long causeId;

    /** 售后类型 */
    @Excel(name = "售后类型")
    private Integer afterType;

    /** 售后状态 */
    @Excel(name = "售后状态")
    private String afterStatus;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String problemDescription;

    private FfwyAfterCause ffwyAfterCause;

    /** 商家留言 */
    @Excel(name = "商家留言")
    private String merchantLeave;

    /** 售后图片*/
    private List<FfwyAfterPhoto> photos;

    /**存储图片path*/
    private List<String> img;

    /**
     * 快递单号
     */
    private String kddh ;

    /**
     * 快递公司
     */
    private String kdgs;

    /**
     * 退货快递单号
     */
    private String refundOrderNumber;

}
