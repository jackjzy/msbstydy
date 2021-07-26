package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomeal.FfwyCombomealHard;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyCombomealHardService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealHard selectFfwyCombomealHardById(String productId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealHard 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealHard> selectFfwyCombomealHardList(FfwyCombomealHard ffwyCombomealHard);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealHard 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealHard(FfwyCombomealHard ffwyCombomealHard);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealHard 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealHard(FfwyCombomealHard ffwyCombomealHard);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealHardByIds(String[] productIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealHardById(String productId);
}
