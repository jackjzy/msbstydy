package com.ruoyi.system.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FfwyorderDetailsVo {

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 数量 */
    @Excel(name = "数量")
    private Integer number;

    /** 小计 */
    @Excel(name = "小计")
    private BigDecimal priceSum;

    private String username;

    private String phonenumber;

    private String patmenttype;

    private String parentid;

    private String specificationName;

}
