package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.aftersale.FfwyAfterStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FfwyAftersaletypeVo {

    /**
     * 售后Id
     */
    private Integer afterSaleid;

    /** 售后类型 */
    @Excel(name = "售后类型")
    private String afterType;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    /** 问题描述 */
    @Excel(name = "问题描述")
    private String problemDescription;

    /**
     * 售后状态
     */
    private String afterStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String  merchantLeave;

    /**
     * 快递单号
     */
    private String kddh;

    /**
     * 快递单位
     */
    private String kdgs;

    List<FfwyAfterStatus> ffwyAfterStatuses;

    private String phone;
}
