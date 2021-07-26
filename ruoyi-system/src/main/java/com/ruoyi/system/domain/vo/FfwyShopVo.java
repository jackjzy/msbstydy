package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.FfwyAuditStatuss;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyShopVo{
    private String phone;

    private String shopName;

    private String searchStr;

    /** 店铺id */
    private Long shopId;


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
    private BigDecimal score;

    /** 店铺评星 */
    @Excel(name = "店铺评星")
    private BigDecimal rating;

    /** 许可证 */
    @Excel(name = "许可证")
    private String licence;

    /** 店主身份证 */
    @Excel(name = "店主身份证")
    private String idCard;

    /** 平台分成比例 */
    @Excel(name = "平台分成比例")
    private Double terraceRatio;

    /** 商家分成比例 */
    @Excel(name = "商家分成比例")
    private Double shopRetio;

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

    private String reject;

    private String auditStatus;

}
