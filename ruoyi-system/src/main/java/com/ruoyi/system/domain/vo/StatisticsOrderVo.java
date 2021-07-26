package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 赵字豪
 * @Title: 用户订单量统计信息
 * @date 2021/4/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsOrderVo {
    /**
     * 今日付款金额
     */
    private BigDecimal statisticsMoney;
    /**
     * 付款订单数
     */
    private Integer statisticsOrder;
    /**
     * 付款件数
     */
    private Integer statisticsCount;
    /**
     * 浏览人数
     */
    private Integer queryCount;
}
