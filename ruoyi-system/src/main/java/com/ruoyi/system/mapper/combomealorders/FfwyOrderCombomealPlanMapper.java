package com.ruoyi.system.mapper.combomealorders;

import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomealPlan;

import java.util.List;


/**
 * 套餐设计图Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
public interface FfwyOrderCombomealPlanMapper 
{
    /**
     * 查询套餐设计图
     * 
     * @param photoId 套餐设计图ID
     * @return 套餐设计图
     */
    public FfwyOrderCombomealPlan selectFfwyOrderCombomealPlanById(Long photoId);

    /**
     * 查询套餐设计图列表
     * 
     * @param ffwyOrderCombomealPlan 套餐设计图
     * @return 套餐设计图集合
     */
    public List<FfwyOrderCombomealPlan> selectFfwyOrderCombomealPlanList(FfwyOrderCombomealPlan ffwyOrderCombomealPlan);

    /**
     * 新增套餐设计图
     * 
     * @param ffwyOrderCombomealPlan 套餐设计图
     * @return 结果
     */
    public int insertFfwyOrderCombomealPlan(FfwyOrderCombomealPlan ffwyOrderCombomealPlan);

    /**
     * 修改套餐设计图
     * 
     * @param ffwyOrderCombomealPlan 套餐设计图
     * @return 结果
     */
    public int updateFfwyOrderCombomealPlan(FfwyOrderCombomealPlan ffwyOrderCombomealPlan);

    /**
     * 删除套餐设计图
     * 
     * @param photoId 套餐设计图ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealPlanById(Long photoId);

    /**
     * 删除套餐设计图
     *
     * @param OrderId 套餐设计图ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealPlanByOrderId(Long OrderId);

    /**
     * 批量删除套餐设计图
     * 
     * @param photoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealPlanByIds(Long[] photoIds);
}
