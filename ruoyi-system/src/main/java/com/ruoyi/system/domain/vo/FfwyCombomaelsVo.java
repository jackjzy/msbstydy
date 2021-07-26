package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyCombomaelsVo {

    private Long combomealId;

    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String combomealName;


}
