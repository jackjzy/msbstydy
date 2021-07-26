package com.ruoyi.system.domain.shop;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.system.domain.FfwyAuditStatuss;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductPhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺信息对象 ffwy_shop
 *
 * @author ruoyi
 * @date 2021-04-20
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class FfwyShop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店铺id */
    private Long shopId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺logo */
    @Excel(name = "店铺logo")
    private String shopLogo;

    /** 店铺编号 */
    @Excel(name = "店铺编号")
    private String shopNumber;

    /** 店铺介绍 */
    @Excel(name = "店铺介绍")
    private String introduction;

    /** 主营商品 */
    @Excel(name = "主营商品")
    private String coreProdect;

    /** 店铺位置 */
    @Excel(name = "店铺位置")
    private String shopLocation;

    /** 店主id */
    @Excel(name = "店主id")
    private Long userId;

    /** 店铺评分 */
    @Excel(name = "店铺评分")
    private Float score;

    /** 店铺评星 */
    @Excel(name = "店铺评星")
    private Integer rating;

    /** 许可证 */
    @Excel(name = "许可证")
    private String licence;

    /** 店主身份证 */
    @Excel(name = "店主身份证")
    private String idCard;

    /** 平台分成比例 */
    @Excel(name = "平台分成比例")
    private BigDecimal
            terraceRatio;

    /** 商家分成比例 */
    @Excel(name = "商家分成比例")
    private BigDecimal shopRetio;

    /** 收入 */
    @Excel(name = "收入")
    private BigDecimal income;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal balance;

    /** 销量 */
    @Excel(name = "销量")
    private Long shopSales;

    /** 收藏数量 */
    @Excel(name = "收藏数量")
    private Long collect;

    /** 店铺状态 */
    @Excel(name = "店铺状态")
    private Integer shopStatus;

    /** 类别id */
    @Excel(name = "类别id")
    private Long shopType;

    /** 店主名称 */
    @Excel(name = "店主名称")
    private String userName;

    /** 店主手机号 */
    @Excel(name = "手机号")
    private String phone;

    @Excel(name = "银行卡号")
    private String bankCard;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "通过时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date passTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "通过时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "通过时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 店主名称 */
    @Excel(name = "审核状态对象")
    private FfwyAuditStatuss ffwyAuditStatuss;

    /** 营业执照照片 */
    @Excel(name = "营业执照照片")
    private String licensePhoto;
    /** 网络经营店铺链接 */
    @Excel(name = "网络经营店铺链接")
    private String shopLink;
    /** 法人身份证照片 */
    @Excel(name = "法人身份证照片")
    private String cardPhoto;
    /** 联系人邮箱 */
    @Excel(name = "联系人邮箱")
    private String email;
    /** 拒绝原因 */
    @Excel(name = "拒绝原因")
    private String reject;

    /** 店铺默认收货地址 */
    @Excel(name = "店铺默认收货地址")
    private Long shopDefaultAddr;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "禁用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date disableTime;

    private String searchField;
	
	private String searchStr;
    /**
     * 商品信息
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyProduct> products;
 /**
     * 店铺图片
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyShopPhoto> shopPhotos;
 /**
     * 店铺图片
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FfwyShop> shop;
    private Long ShopCollect;


    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public FfwyShop(Long shopId, Integer shopStatus, Date disableTime) {
        this.shopId = shopId;
        this.shopStatus = shopStatus;
        this.disableTime = disableTime;
    }

    public FfwyShop(Date disableTime) {
        this.disableTime = disableTime;
    }

    public FfwyShop(Long userId, Long shopId) {
        this.userId = userId;
        this.shopId = shopId;
    }












}
