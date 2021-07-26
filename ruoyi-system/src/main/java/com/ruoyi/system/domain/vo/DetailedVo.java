package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流水明细
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailedVo {
    /**
     * 营收
     */
    private Double income;
    /**
     * 余额
     */
    private String balance;
}
