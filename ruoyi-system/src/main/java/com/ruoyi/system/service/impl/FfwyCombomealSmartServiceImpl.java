package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.combomeal.FfwyCombomealSmart;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealSmartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCombomealSmartService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyCombomealSmartServiceImpl implements IFfwyCombomealSmartService 
{
    @Autowired
    private FfwyCombomealSmartMapper ffwyCombomealSmartMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealSmart selectFfwyCombomealSmartById(String productId)
    {
        return ffwyCombomealSmartMapper.selectFfwyCombomealSmartById(productId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealSmart 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealSmart> selectFfwyCombomealSmartList(FfwyCombomealSmart ffwyCombomealSmart)
    {
        return ffwyCombomealSmartMapper.selectFfwyCombomealSmartList(ffwyCombomealSmart);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealSmart 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealSmart(FfwyCombomealSmart ffwyCombomealSmart)
    {
        return ffwyCombomealSmartMapper.insertFfwyCombomealSmart(ffwyCombomealSmart);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealSmart 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealSmart(FfwyCombomealSmart ffwyCombomealSmart)
    {
        return ffwyCombomealSmartMapper.updateFfwyCombomealSmart(ffwyCombomealSmart);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealSmartByIds(String[] productIds)
    {
        return ffwyCombomealSmartMapper.deleteFfwyCombomealSmartByIds(productIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealSmartById(String productId)
    {
        return ffwyCombomealSmartMapper.deleteFfwyCombomealSmartById(productId);
    }
}
