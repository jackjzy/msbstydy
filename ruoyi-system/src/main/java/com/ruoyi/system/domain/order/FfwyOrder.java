package com.ruoyi.system.domain.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 ffwy_order
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FfwyOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用户信息
     */
    @Excel(name = "用户信息")
    private Long userId;

    /**
     * 收货地址id
     */
    @Excel(name = "收货地址id")
    private Long addrId;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNumber;

    /**
     * 下单状态
     */
    @Excel(name = "下单状态")
    private Integer statusId;

    private Integer isDel;

    /**
     * 实付金额
     */
    @Excel(name = "实付金额")
    private BigDecimal money;

    /**
     * 付款方式
     */
    @Excel(name = "付款方式")
    private Integer paymentId;

    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipmentsTime;

    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;
    //过期时间
    private Date ExpireTime;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String userName;

    /**
     * 用户电话
     */
    @Excel(name = "用户电话")
    private String phone;

    /**
     * 店铺id
     */
    @Excel(name = "店铺id")
    private Long shopId;

    /**
     * 套餐id
     */
    @Excel(name = "套餐id")
    private Integer combomealId;
    private Integer orderStatus;

    /**
     * 总工时
     */
    @Excel(name = "总工时")
    private Integer working;

    /**
     * 订单类型
     */
    @Excel(name = "订单类型")
    private String orderType;
    private String trackIngNumber;

    private String productName;

    String tradeNo;
    private String searchField;

    private Integer categoryLevel;

    private Double beginPrice;

    private Double endPrice;
    private List<FfwyOrderDetails> orderDetailList;
    //运费
    private BigDecimal freight;
    private BigDecimal price;
    private String photo;
    private Integer number;
    private String skuSpec;

    private String message;
    private Long skuId;
    private String skuDefaultImg;

    public FfwyOrder(String searchField, Integer statusId, Double beginPrice, Double endPrice, Date beginTime, Date endTime, Long shopId) {
        super(beginTime, endTime);
        this.statusId = statusId;
        this.beginPrice = beginPrice;
        this.endPrice = endPrice;
        this.shopId = shopId;
        this.searchField = searchField;
    }

    public FfwyOrder(Long orderId, Integer statusId, Long userId) {
        this.orderId = orderId;
        this.statusId = statusId;
        this.userId = userId;
    }

    public FfwyOrder(Long userId, Long shopId) {
        this.userId = userId;
        this.shopId = shopId;
    }

    public FfwyOrder(Integer statusId, Date createTime, Long userId) {
        super(createTime);
        this.statusId = statusId;
        this.userId = userId;
    }

}
