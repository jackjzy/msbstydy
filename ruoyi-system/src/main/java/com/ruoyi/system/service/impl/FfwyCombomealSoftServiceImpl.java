package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.combomeal.FfwyCombomealSoft;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealSoftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCombomealSoftService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyCombomealSoftServiceImpl implements IFfwyCombomealSoftService 
{
    @Autowired
    private FfwyCombomealSoftMapper ffwyCombomealSoftMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealSoft selectFfwyCombomealSoftById(String productId)
    {
        return ffwyCombomealSoftMapper.selectFfwyCombomealSoftById(productId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealSoft 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealSoft> selectFfwyCombomealSoftList(FfwyCombomealSoft ffwyCombomealSoft)
    {
        return ffwyCombomealSoftMapper.selectFfwyCombomealSoftList(ffwyCombomealSoft);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealSoft 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealSoft(FfwyCombomealSoft ffwyCombomealSoft)
    {
        return ffwyCombomealSoftMapper.insertFfwyCombomealSoft(ffwyCombomealSoft);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealSoft 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealSoft(FfwyCombomealSoft ffwyCombomealSoft)
    {
        return ffwyCombomealSoftMapper.updateFfwyCombomealSoft(ffwyCombomealSoft);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealSoftByIds(String[] productIds)
    {
        return ffwyCombomealSoftMapper.deleteFfwyCombomealSoftByIds(productIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealSoftById(String productId)
    {
        return ffwyCombomealSoftMapper.deleteFfwyCombomealSoftById(productId);
    }
}
