package com.ruoyi.system.domain.to;

import lombok.Data;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/19
 **/
@Data
public class LockStockResult {
    private Long skuId;
    private Long num;
    private Boolean locked;
}
