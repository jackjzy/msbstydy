package com.ruoyi.system.domain.aftersale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 售后买家填写物流对象 ffwy_after_addr
 * 
 * @author ruoyi
 * @date 2021-05-28
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyAfterAddr
{
    private static final long serialVersionUID = 1L;

    /** 售后地址id */
    private Long afterAddrId;

    /** 售后id */
    @Excel(name = "售后id")
    private Long afterSaleid;

    /** 物流号 */
    @Excel(name = "物流号")
    private String logisticsNumber;

    /** 物流名称 */
    @Excel(name = "物流名称")
    private String logisticsName;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String userPhone;

    private Date createTime;

    private Date updateTime;


}
