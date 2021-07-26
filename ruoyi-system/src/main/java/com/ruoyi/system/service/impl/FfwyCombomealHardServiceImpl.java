package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.combomeal.FfwyCombomealHard;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealHardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCombomealHardService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyCombomealHardServiceImpl implements IFfwyCombomealHardService 
{
    @Autowired
    private FfwyCombomealHardMapper ffwyCombomealHardMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealHard selectFfwyCombomealHardById(String productId)
    {
        return ffwyCombomealHardMapper.selectFfwyCombomealHardById(productId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealHard 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealHard> selectFfwyCombomealHardList(FfwyCombomealHard ffwyCombomealHard)
    {
        return ffwyCombomealHardMapper.selectFfwyCombomealHardList(ffwyCombomealHard);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealHard 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealHard(FfwyCombomealHard ffwyCombomealHard)
    {
        return ffwyCombomealHardMapper.insertFfwyCombomealHard(ffwyCombomealHard);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealHard 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealHard(FfwyCombomealHard ffwyCombomealHard)
    {
        return ffwyCombomealHardMapper.updateFfwyCombomealHard(ffwyCombomealHard);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealHardByIds(String[] productIds)
    {
        return ffwyCombomealHardMapper.deleteFfwyCombomealHardByIds(productIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealHardById(String productId)
    {
        return ffwyCombomealHardMapper.deleteFfwyCombomealHardById(productId);
    }
}
