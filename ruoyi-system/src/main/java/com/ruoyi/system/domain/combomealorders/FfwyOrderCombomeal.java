package com.ruoyi.system.domain.combomealorders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_order_combomeal
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyOrderCombomeal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 工长id */
    @Excel(name = "工长id")
    private Long workerId;

    /** 工长姓名 */
    @Excel(name = "工长姓名")
    private String workerName;
    /** 工长照片 */
    private String workerPhoto;

    /** 设计师id */
    @Excel(name = "设计师id")
    private Long designerId;

    /** 设计师名称 */
    @Excel(name = "设计师名称")
    private String designerName;

    /** 设计师照片 */
    @Excel(name = "设计师照片")
    private String designerPhoto;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String proectName;

    /** 户型 */
    @Excel(name = "户型")
    private String houseType;

    /** 房屋面积 */
    @Excel(name = "房屋面积")
    private BigDecimal houseArea;

    /** 订单备注 */
    @Excel(name = "订单备注")
    private String orderRemarks;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /** 套餐封面 */
    @Excel(name = "套餐封面")
    private String combomealCover;

    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String combomealName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String orderUserName;

    /** 联系手机号码 */
    @Excel(name = "联系手机号码")
    private String orderPhone;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String orderAddr;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Integer payId;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 上门时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上门时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workingTime;

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 订单状态  0:量房款待付，1:量房款已付, 2:等待上门、3装修中、4表示已完成 ,5:已关闭*/
    @Excel(name = "订单状态")
    private String orderStatus;

    /** 是否升级（0不升级，1表示升级） */
    @Excel(name = "是否升级", readConverterExp = "0=不升级，1表示升级")
    private String isUpgrade;

    /** 订单类型(0表示量房订单,1表示毛坯房订单,2表示精装房订单) */
    @Excel(name = "订单类型(0表示量房订单,1表示毛坯房订单,2表示精装房订单)")
    private String orderType;

    /** 服务金额 */
    @Excel(name = "服务金额")
    private BigDecimal serviceMoney;

    /** 实际付款金额 */
    @Excel(name = "实际付款金额")
    private BigDecimal realityMoney;

    /** 项目总金额 */
    @Excel(name = "项目总金额")
    private BigDecimal proectMoney;

    private String allotStatus;

    /** 搜索信息 */
    private String searchStr;

    /** 当前套餐类型 */
    @Excel(name = "当前套餐信息")
    private String combomealType;

    /** 支付所占比例*/
    private String payRatio;

    /** 装修材料*/
    private List<FfwyMaterial> materialList;
    /** 订单节点*/
    private List<FfwyPhase> phasesList;
    /** 合同id*/
    private Long contractId;
    /** 联系人手机号*/
    private String idCard;
    public FfwyOrderCombomeal(Long orderId, String orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
