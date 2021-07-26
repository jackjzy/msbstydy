package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomeal.FfwyCombomealWiring;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyCombomealWiringService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealWiring selectFfwyCombomealWiringById(String productId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealWiring 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealWiring> selectFfwyCombomealWiringList(FfwyCombomealWiring ffwyCombomealWiring);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealWiring 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealWiring(FfwyCombomealWiring ffwyCombomealWiring);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealWiring 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealWiring(FfwyCombomealWiring ffwyCombomealWiring);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealWiringByIds(String[] productIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealWiringById(String productId);
}
