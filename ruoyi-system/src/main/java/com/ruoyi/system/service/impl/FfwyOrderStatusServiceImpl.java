package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.order.FfwyOrderStatusMapper;
import com.ruoyi.system.domain.order.FfwyOrderStatus;
import com.ruoyi.system.service.IFfwyOrderStatusService;

/**
 * 订单状态Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FfwyOrderStatusServiceImpl implements IFfwyOrderStatusService 
{
    @Autowired
    private FfwyOrderStatusMapper ffwyOrderStatusMapper;

    /**
     * 查询订单状态
     * 
     * @param statusId 订单状态ID
     * @return 订单状态
     */
    @Override
    public String selectFfwyOrderStatusById(Long statusId)
    {
        return ffwyOrderStatusMapper.selectFfwyOrderStatusById(statusId);
    }

    /**
     * 查询订单状态列表
     * 
     * @param ffwyOrderStatus 订单状态
     * @return 订单状态
     */
    @Override
    public List<FfwyOrderStatus> selectFfwyOrderStatusList(FfwyOrderStatus ffwyOrderStatus)
    {
        return ffwyOrderStatusMapper.selectFfwyOrderStatusList(ffwyOrderStatus);
    }

    /**
     * 新增订单状态
     * 
     * @param ffwyOrderStatus 订单状态
     * @return 结果
     */
    @Override
    public int insertFfwyOrderStatus(FfwyOrderStatus ffwyOrderStatus)
    {
        return ffwyOrderStatusMapper.insertFfwyOrderStatus(ffwyOrderStatus);
    }

    /**
     * 修改订单状态
     * 
     * @param ffwyOrderStatus 订单状态
     * @return 结果
     */
    @Override
    public int updateFfwyOrderStatus(FfwyOrderStatus ffwyOrderStatus)
    {
        return ffwyOrderStatusMapper.updateFfwyOrderStatus(ffwyOrderStatus);
    }

    /**
     * 批量删除订单状态
     * 
     * @param statusIds 需要删除的订单状态ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderStatusByIds(Long[] statusIds)
    {
        return ffwyOrderStatusMapper.deleteFfwyOrderStatusByIds(statusIds);
    }

    /**
     * 删除订单状态信息
     * 
     * @param statusId 订单状态ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderStatusById(Long statusId)
    {
        return ffwyOrderStatusMapper.deleteFfwyOrderStatusById(statusId);
    }
}
