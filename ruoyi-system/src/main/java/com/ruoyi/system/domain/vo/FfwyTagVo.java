package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyTagVo {
    /** 资讯id */
    private Long informationId;

    /** 标签id */
    private Long tagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;
}
