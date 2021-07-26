package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.vo.*;

/**
 * 订单Service接口
 *
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwyOrderService {
    /**
     * 查询订单
     *
     * @param orderId 订单ID
     * @param shopId  用户ID
     * @return 订单
     */
    public FfwyOrder selectFfwyOrderById(Long orderId, Long shopId);

    /**
     * 查询订单
     *
     * @param orderNumber 订单号
     * @param shopId      用户ID
     * @return 订单
     */
    public OrderVo selectFfwyOrderByOrderNumber(Long shopId, String orderNumber);

    /**
     * 查询订单列表
     *
     * @param orders 订单
     * @return 订单集合
     */
    public List<OrderVo> selectFfwyOrderList(Orders orders);

    /**
     * 新增订单
     *
     * @param ffwyOrder 订单
     * @return 结果
     */
    public int insertFfwyOrder(FfwyOrder ffwyOrder);

    /**
     * 发货
     *
     * @param orderId   订单号
     * @param ffwyOrder 快递单号
     * @return
     */
    public int shipmentsFfwyOrder(Integer orderId, String ffwyOrder);

    /**
     * 退款
     *
     * @param orderId       订单号
     * @param agreeToRefuse 是否同意
     * @return
     */
    public int refundFfwyOrder(Integer orderId, boolean agreeToRefuse);

    /**
     * 取消订单
     *
     * @param orderId 订单号
     * @return
     */
    public int cancelFfwyOrder(Integer orderId);

    /**
     * 修改订单
     *
     * @param ffwyOrder 订单
     * @return 结果
     */
    public int updateFfwyOrder(FfwyOrder ffwyOrder);

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    public int deleteFfwyOrderByIds(Long[] orderIds);

    /**
     * 删除订单信息
     *
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteFfwyOrderById(Long orderId);

    AjaxResult saveOrderInfo(FfwyOrder orderInfo);



    void combomaealUnlockStock(WareSkuLockVo lockVo);


    List<PaymentHistoryVo> selectFfwyOrderandCombomealList(Long userId);

    List<FfwyOrder> selectFfwyOrderallList(FfwyOrder order);

    AjaxResult selectFfwyOrderList(FfwyOrder order);

    AjaxResult selectFfwyOrderType(Long orderId, String type);
}
