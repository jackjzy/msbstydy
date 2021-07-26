package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.FfwyInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyInformationVo{

    /** 是否发布：0代表发布、1代表存草稿 */
    @Excel(name = "是否发布：0代表发布、1代表存草稿")
    private String informationStatus;


    /** 所属类型 */
    @Excel(name = "所属类型")
    private Long informationTypeId;

    /** 咨询名称 */
    @Excel(name = "咨询名称")
    private String informationName;

    private Date createTime;


}
