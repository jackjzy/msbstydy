package com.ruoyi.system.domain.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.system.domain.FfwyPayment;
import com.ruoyi.system.domain.product.FfwyProductComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单详情对象 ffwy_order_details
 *
 * @author ruoyi
 * @date 2021-04-22
 */
@Data
@AllArgsConstructor
@ToString
public class FfwyOrderDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 详情id
     */
    private Long orderDetailsId;

    /**
     * 订单id
     */
    @Excel(name = "订单id")
    private Long orderId;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNumber;

    /**
     * 商品sku信息
     */
    @Excel(name = "商品sku信息")
    private Long productSkuId;

    /**
     * 规格id
     */
    @Excel(name = "规格id")
    private Integer specificationId;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Integer number;

    /**
     * 价格
     */
    @Excel(name = "价格")
    private BigDecimal price;

    /**
     * 小计
     */
    @Excel(name = "小计")
    private BigDecimal priceSum;

    /**
     * 商品sku名
     */
    @Excel(name = "商品sku名")
    private String productSkuName;
    private String skuSpec;
    @Excel(name = "规格")
    private String productSkuSpec;

    private Integer categoryLevel;

    /**
     * 商品图片
     */
    private String imgUrl;

    /**
     * 运费
     */
    private BigDecimal freight;

    /*--------------------------*/
    /**
     * 店铺id
     */
    private Long shopId;
    private Long productId;

    /**
     * 商品类目
     */
    private String categoryName;

    /**
     * 用户id
     */
    private Long userId;
    private Long isDel;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 商品图片
     */
    private String productPhoto;
    /**
     * 快递单号
     */
    private String trackIngNumber;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    private Integer statusId;

    /**
     * 收货id
     */
    @Excel(name = "收货地址id")
    private Long addrId;

    /**
     * 收货地址
     */
    /**
     * 店铺收货地址id
     */
    @Excel(name = "店铺收货地址id")
    private Long shopAddrId;

    /**
     * 收货地址
     */
    private FfwyConsigneeAddr addr;

    /**
     * 支付方式
     */
    private String payId;
    private Integer patmentType;
    private FfwyPayment payMentId;

    /**
     * 退货单号
     */
    private String backTrackIngNumber;
    /**
     * 快递名称
     */
    private String trackIngName;

    /**
     * 拒绝理由
     */
    private String rejectMsg;

    /**
     * 退款售后id
     */
    private Long afterSaleId;


    private Date beginTime;

    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date CateateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliverTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date knockdownTime;
    private List<FfwyConsigneeAddr> ffwyConsigneeAddrs;

    private Long skuId;

    private Long num;
    private String message;


    //用户订单状态
    private Long payment;
    private Long shipments;
    private Long receiving;
    private Long evaluate;
    private Long after;
    private List<FfwyOrderDetails> details;
    private FfwyOrderDetails ffwyOrderDetails;

    private Integer rating;
    private List<FfwyProductComment> comments;


    public FfwyOrderDetails(Long productSkuId, String productSkuName) {
        this.productSkuId = productSkuId;
        this.productSkuName = productSkuName;
    }

    public FfwyOrderDetails(Long orderId, String productSkuName, Date createTime) {
        super(createTime);
        this.orderId = orderId;
        this.productSkuName = productSkuName;
    }

    public FfwyOrderDetails(Long orderId) {
        this.orderId = orderId;
    }

    public String getTrackIngNumber() {
        return trackIngNumber;
    }

    public void setTrackIngNumber(String trackIngNumber) {
        this.trackIngNumber = trackIngNumber;
    }

    public FfwyOrderDetails() {
    }

    public String getBackTrackIngNumber() {
        return backTrackIngNumber;
    }

    public void setBackTrackIngNumber(String backTrackIngNumber) {
        this.backTrackIngNumber = backTrackIngNumber;
    }
}
