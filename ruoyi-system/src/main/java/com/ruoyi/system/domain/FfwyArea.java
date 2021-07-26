package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 省市县对象 ffwy_area
 * 
 * @author ruoyi
 * @date 2021-06-30
 */
@Data
public class FfwyArea {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="code值")
    private String code;

    private String name;

    private String perentCode;

    private Date createTime;

    private List<FfwyArea> FfwyAreaList;

}
