package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/19
 **/
@Data
@NoArgsConstructor
public class WareSkuLockVo {
    /**
     * 订单号
     */
    private String orderSn;

    /** 需要锁住的套餐订单下所有库存信息 **/
    private List<FfwyMaterial> comLocks;

    /** 需要锁住的商品订单下所有库存信息 **/
    private List<FfwyOrder> proLocks;

}
