package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.combomeal.FfwyCombomealCommodity;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealCommodityMapper;
import com.ruoyi.system.service.IFfwyCombomealCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyCombomealCommodityServiceImpl implements IFfwyCombomealCommodityService
{
    @Autowired
    private FfwyCombomealCommodityMapper ffwyCombomealCommodityMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealCommodity selectFfwyCombomealCommodityById(String productId)
    {
        return ffwyCombomealCommodityMapper.selectFfwyCombomealCommodityById(productId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealCommodity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealCommodity> selectFfwyCombomealCommodityList(FfwyCombomealCommodity ffwyCombomealCommodity)
    {
        return ffwyCombomealCommodityMapper.selectFfwyCombomealCommodityList(ffwyCombomealCommodity);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealCommodity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealCommodity(FfwyCombomealCommodity ffwyCombomealCommodity)
    {
        return ffwyCombomealCommodityMapper.insertFfwyCombomealCommodity(ffwyCombomealCommodity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealCommodity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealCommodity(FfwyCombomealCommodity ffwyCombomealCommodity)
    {
        return ffwyCombomealCommodityMapper.updateFfwyCombomealCommodity(ffwyCombomealCommodity);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCommodityByIds(String[] productIds)
    {
        return ffwyCombomealCommodityMapper.deleteFfwyCombomealCommodityByIds(productIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCommodityById(String productId)
    {
        return ffwyCombomealCommodityMapper.deleteFfwyCombomealCommodityById(productId);
    }
}
