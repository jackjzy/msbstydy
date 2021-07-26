package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.order.FfwyOrderStatus;

/**
 * 订单状态Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwyOrderStatusService 
{
    /**
     * 查询订单状态
     * 
     * @param statusId 订单状态ID
     * @return 订单状态
     */
    public String selectFfwyOrderStatusById(Long statusId);

    /**
     * 查询订单状态列表
     * 
     * @param ffwyOrderStatus 订单状态
     * @return 订单状态集合
     */
    public List<FfwyOrderStatus> selectFfwyOrderStatusList(FfwyOrderStatus ffwyOrderStatus);

    /**
     * 新增订单状态
     * 
     * @param ffwyOrderStatus 订单状态
     * @return 结果
     */
    public int insertFfwyOrderStatus(FfwyOrderStatus ffwyOrderStatus);

    /**
     * 修改订单状态
     * 
     * @param ffwyOrderStatus 订单状态
     * @return 结果
     */
    public int updateFfwyOrderStatus(FfwyOrderStatus ffwyOrderStatus);

    /**
     * 批量删除订单状态
     * 
     * @param statusIds 需要删除的订单状态ID
     * @return 结果
     */
    public int deleteFfwyOrderStatusByIds(Long[] statusIds);

    /**
     * 删除订单状态信息
     * 
     * @param statusId 订单状态ID
     * @return 结果
     */
    public int deleteFfwyOrderStatusById(Long statusId);
}
