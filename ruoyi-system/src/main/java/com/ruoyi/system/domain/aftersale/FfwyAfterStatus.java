package com.ruoyi.system.domain.aftersale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;


@Data
public class FfwyAfterStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer statusId;

    /** 售后Id */
    private Long saleId;

    /** 售后状态*/
    private Integer auditStatusId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String auditName;
}
