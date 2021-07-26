package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyWorkplanVo {
    /** 我的房子id */
    private Long myhoustId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String myhoustName;

    /** 户型 */
    @Excel(name = "户型")
    private String house;

    /** 面积 */
    @Excel(name = "面积")
    private BigDecimal area;

    /** 项目设计师 */
    @Excel(name = "项目设计师")
    private Long stylist;

    /** 套餐价格 */
    @Excel(name = "套餐价格")
    private BigDecimal sumPrice;

    /** 起始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actDate;

    /** 终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closedDate;

    /** 房屋备注 */
    @Excel(name = "房屋备注")
    private String myhoustRemark;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺logo */
    @Excel(name = "店铺logo")
    private String shopLogo;

    /** 店铺位置 */
    @Excel(name = "店铺位置")
    private String shopLocation;

    /** 节点名称 */
    @Excel(name = "节点名称")
    private String phaseName;
}
