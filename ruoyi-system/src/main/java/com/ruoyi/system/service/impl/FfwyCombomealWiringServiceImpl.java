package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.combomeal.FfwyCombomealWiring;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealWiringMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCombomealWiringService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyCombomealWiringServiceImpl implements IFfwyCombomealWiringService 
{
    @Autowired
    private FfwyCombomealWiringMapper ffwyCombomealWiringMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealWiring selectFfwyCombomealWiringById(String productId)
    {
        return ffwyCombomealWiringMapper.selectFfwyCombomealWiringById(productId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealWiring 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealWiring> selectFfwyCombomealWiringList(FfwyCombomealWiring ffwyCombomealWiring)
    {
        return ffwyCombomealWiringMapper.selectFfwyCombomealWiringList(ffwyCombomealWiring);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealWiring 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealWiring(FfwyCombomealWiring ffwyCombomealWiring)
    {
        return ffwyCombomealWiringMapper.insertFfwyCombomealWiring(ffwyCombomealWiring);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealWiring 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealWiring(FfwyCombomealWiring ffwyCombomealWiring)
    {
        return ffwyCombomealWiringMapper.updateFfwyCombomealWiring(ffwyCombomealWiring);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealWiringByIds(String[] productIds)
    {
        return ffwyCombomealWiringMapper.deleteFfwyCombomealWiringByIds(productIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealWiringById(String productId)
    {
        return ffwyCombomealWiringMapper.deleteFfwyCombomealWiringById(productId);
    }
}
