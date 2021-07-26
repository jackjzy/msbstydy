package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopVo {

    /** 店铺id */
    private Long shopId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    private String userName;

    private Double terraceRatio;

    private Double shopRetio;
}
