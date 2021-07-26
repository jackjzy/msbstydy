package com.ruoyi.system.service;


import com.ruoyi.system.domain.order.FfwyIncome;
import com.ruoyi.system.domain.vo.*;

import java.util.List;

public interface IFfwyShopUserService {

    /**
     *  统计用户订单量
     * @param userId
     * @return
     */
    StatisticsOrderVo statisticsOrder(Integer userId);

    /**
     *  七天营收统计
     * @param productByUserVo
     * @return
     */
    DetailedVo flowingWater(ProductByUserVo productByUserVo);

    /**
     *  收支明细
     * @param productByUserVo
     * @return
     */
    List<IncomeVo> detailed(ProductByUserVo productByUserVo);

    /**
     *  根据店铺id查询收支明细
     * @param ffwyIncome
     * @return
     */
    List<FfwyIncome> detailedByshop(FfwyIncome ffwyIncome);

    /**
     *  流水详情
     * @param productByUserVo
     * @return
     */
    OrderVo flowingWaterDetails(ProductByUserVo productByUserVo);


}
