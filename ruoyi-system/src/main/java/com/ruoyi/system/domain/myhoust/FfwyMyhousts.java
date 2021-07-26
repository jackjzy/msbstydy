package com.ruoyi.system.domain.myhoust;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ffwy_myhousts
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
public class FfwyMyhousts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 我的房子id */
    private Long myhoustId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String myhoustName;

    /** 套餐id */
    @Excel(name = "套餐id")
    private Long combomealId;

    /** 套餐价格 */
    @Excel(name = "套餐价格")
    private BigDecimal sumPrice;

    /** 户型 */
    @Excel(name = "户型")
    private String house;

    /** 面积 */
    @Excel(name = "面积")
    private BigDecimal area;

    /** 图纸 */
    @Excel(name = "图纸")
    private String blueprint;

    /** 工期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workTime;

    /** 项目设计师 */
    @Excel(name = "项目设计师")
    private Long stylist;

    /** 工长 */
    @Excel(name = "工长")
    private Long foreman;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

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

    /** 装修材料信息 */
    @Excel(name = "装修材料信息")
    private Integer shopId;

    /** 施工阶段 */
    @Excel(name = "施工阶段")
    private Integer phaseId;

    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public void setMyhoustId(Long myhoustId) 
    {
        this.myhoustId = myhoustId;
    }

    public Long getMyhoustId() 
    {
        return myhoustId;
    }
    public void setMyhoustName(String myhoustName) 
    {
        this.myhoustName = myhoustName;
    }

    public String getMyhoustName() 
    {
        return myhoustName;
    }
    public void setCombomealId(Long combomealId) 
    {
        this.combomealId = combomealId;
    }

    public Long getCombomealId() 
    {
        return combomealId;
    }
    public void setSumPrice(BigDecimal sumPrice)
    {
        this.sumPrice = sumPrice;
    }

    public BigDecimal getSumPrice()
    {
        return sumPrice;
    }
    public void setHouse(String house) 
    {
        this.house = house;
    }

    public String getHouse() 
    {
        return house;
    }
    public void setArea(BigDecimal area)
    {
        this.area = area;
    }

    public BigDecimal getArea()
    {
        return area;
    }
    public void setBlueprint(String blueprint) 
    {
        this.blueprint = blueprint;
    }

    public String getBlueprint() 
    {
        return blueprint;
    }
    public void setWorkTime(Date workTime) 
    {
        this.workTime = workTime;
    }

    public Date getWorkTime() 
    {
        return workTime;
    }
    public void setStylist(Long stylist) 
    {
        this.stylist = stylist;
    }

    public Long getStylist() 
    {
        return stylist;
    }
    public void setForeman(Long foreman) 
    {
        this.foreman = foreman;
    }

    public Long getForeman() 
    {
        return foreman;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setActDate(Date actDate) 
    {
        this.actDate = actDate;
    }

    public Date getActDate() 
    {
        return actDate;
    }
    public void setClosedDate(Date closedDate) 
    {
        this.closedDate = closedDate;
    }

    public Date getClosedDate() 
    {
        return closedDate;
    }
    public void setMyhoustRemark(String myhoustRemark) 
    {
        this.myhoustRemark = myhoustRemark;
    }

    public String getMyhoustRemark() 
    {
        return myhoustRemark;
    }
    public void setShopId(Integer shopId) 
    {
        this.shopId = shopId;
    }

    public Integer getShopId() 
    {
        return shopId;
    }
    public void setPhaseId(Integer phaseId) 
    {
        this.phaseId = phaseId;
    }

    public Integer getPhaseId() 
    {
        return phaseId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("myhoustId", getMyhoustId())
            .append("myhoustName", getMyhoustName())
            .append("combomealId", getCombomealId())
            .append("sumPrice", getSumPrice())
            .append("house", getHouse())
            .append("area", getArea())
            .append("blueprint", getBlueprint())
            .append("workTime", getWorkTime())
            .append("stylist", getStylist())
            .append("foreman", getForeman())
            .append("userId", getUserId())
            .append("actDate", getActDate())
            .append("closedDate", getClosedDate())
            .append("myhoustRemark", getMyhoustRemark())
            .append("shopId", getShopId())
            .append("phaseId", getPhaseId())
            .toString();
    }
}
