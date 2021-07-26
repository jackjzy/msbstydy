package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomeal.FfwyCombomealCommodity;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyCombomealCommodityService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealCommodity selectFfwyCombomealCommodityById(String productId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealCommodity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealCommodity> selectFfwyCombomealCommodityList(FfwyCombomealCommodity ffwyCombomealCommodity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealCommodity 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealCommodity(FfwyCombomealCommodity ffwyCombomealCommodity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealCommodity 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealCommodity(FfwyCombomealCommodity ffwyCombomealCommodity);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommodityByIds(String[] productIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommodityById(String productId);
}
