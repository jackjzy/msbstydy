package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomeal.FfwyCombomealSoft;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyCombomealSoftService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealSoft selectFfwyCombomealSoftById(String productId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealSoft 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealSoft> selectFfwyCombomealSoftList(FfwyCombomealSoft ffwyCombomealSoft);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealSoft 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealSoft(FfwyCombomealSoft ffwyCombomealSoft);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealSoft 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealSoft(FfwyCombomealSoft ffwyCombomealSoft);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealSoftByIds(String[] productIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealSoftById(String productId);
}
