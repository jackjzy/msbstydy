package com.ruoyi.system.mapper.order;

import java.util.List;

import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.vo.FfwyOrderVo;
import com.ruoyi.system.domain.vo.Orders;
import com.ruoyi.system.domain.vo.PaymentVo;
import com.ruoyi.system.domain.vo.WareSkuLockVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 订单Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Repository
public interface FfwyOrderMapper {
    /**
     * 查询订单
     *
     * @param orderId 订单ID
     * @param shopId  用户ID
     * @return 订单
     */
    public FfwyOrder selectFfwyOrderById(@Param("orderId") Long orderId, @Param("shopId") Long shopId);

//    public FfwyOrder selectFfwyOrderById(@Param("orderId") Long orderId);

    /**
     * 查询订单
     *
     * @param orderNumber 订单号
     * @param shopId      用户id
     * @return 订单
     */
    public FfwyOrder selectFfwyOrderByOrderNumber(@Param("shopId") Long shopId, @Param("orderNumber") String orderNumber);

    /**
     * 查询订单列表
     *
     * @param ffwyOrder 订单
     * @return 订单集合
     */
    public List<FfwyOrder> selectFfwyOrderList(FfwyOrder ffwyOrder);

    /**
     * 新增订单
     *
     * @param ffwyOrder 订单
     * @return 结果
     */
    public int insertFfwyOrder(FfwyOrder ffwyOrder);

    /**
     * 修改订单
     *
     * @param ffwyOrder 订单
     * @return 结果
     */
    public int updateFfwyOrder(FfwyOrder ffwyOrder);

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteFfwyOrderById(Long orderId);

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyOrderByIds(Long[] orderIds);

    List<PaymentVo> selectFfwyOrderListBycond(FfwyOrderVo ffwyOrderVo);


    List<PaymentVo> selectFfwyOrderfindBystatus(FfwyOrder ffwyOrder);

    List<PaymentVo> selectFfwyOrderfindBymone(FfwyOrder ffwyOrder);

    List<FfwyOrder> selectFfwyOrderStatusList(FfwyOrder order);

    List<FfwyOrder> selectFfwyOrderallList(FfwyOrder order);

    List<FfwyOrder> selectFfwyAddOrderList(FfwyOrder order);

    FfwyOrder selectFfwyOrderCombomaealUnlockStock(@Param("orderNumber") String lockVo);

    List<FfwyOrder> selectFfwyOrderUnpaidevaluatedList(FfwyOrder order);

    List<FfwyOrder> publishEvaluate(FfwyOrder order);

    List<FfwyOrder> selectFfwyOrderListAll(FfwyOrder order);


    String selectFfwyOrderType(Long orderId);
}
