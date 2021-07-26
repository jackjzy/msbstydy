package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.vo.FfwyorderdespeVo;

/**
 * 订单详情Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwyOrderDetailsService 
{
    /**
     * 查询订单详情
     * 
     * @param orderDetailsId 订单详情ID
     * @return 订单详情
     */
    public FfwyOrderDetails selectFfwyOrderDetailsById(Long orderDetailsId);

    /**
     * 查询订单详情列表
     * 
     * @param ffwyOrderDetails 订单详情
     * @return 订单详情集合
     */
    public List<FfwyOrderDetails> selectFfwyOrderDetailsList(FfwyOrderDetails ffwyOrderDetails);
    /**
     * 查询订单详情列表
     *
     * @param ffwyOrderDetails 订单详情
     * @return 订单详情集合
     */
    public List<FfwyOrderDetails> backOrderDetailsList(FfwyOrderDetails ffwyOrderDetails);

    /**
     * 新增订单详情
     * 
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int insertFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails);

    /**
     * 修改订单详情
     * 
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int updateFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails);
    /**
     * 发货
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int shipments(FfwyOrderDetails ffwyOrderDetails);
    /**
     * 拒绝退款
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int reject(FfwyOrderDetails ffwyOrderDetails);
    /**
     * 同意退款
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int agree(FfwyOrderDetails ffwyOrderDetails);
    /**
     * 同意退货退款
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int agreeAndSales(FfwyOrderDetails ffwyOrderDetails);

    /**
     * 批量删除订单详情
     * 
     * @param orderId 需要删除的订单详情ID
     * @return 结果
     */
    public int deleteFfwyOrderDetailsByIds(FfwyOrder ffwyOrder);

    /**
     * 删除订单详情信息
     * 
     * @param orderDetailsId 订单详情ID
     * @return 结果
     */
    public int deleteFfwyOrderDetailsById(Long orderDetailsId);

    List<FfwyOrderDetails> selectFfwyOrderStatusList(FfwyOrderDetails order);

    List<FfwyOrderDetails> selectFfwyOrderAllList(FfwyOrderDetails order);

    AjaxResult selectFfwyOrderUnpaidOrdersList(FfwyOrderDetails order);

    AjaxResult publishEvaluate(FfwyOrderDetails order);

    AjaxResult selectFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails);

    List<FfwyorderdespeVo> selectFfwyorderdetailsByseales(Long userId);

    List<FfwyAfterSale> selectFfwyOrderDetailsByorderDetailsId(Integer orderDetailsId);
}
