package com.ruoyi.system.mapper.combomeal;

import com.ruoyi.system.domain.combomeal.FfwyCombomealSmart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface FfwyCombomealSmartMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealSmart selectFfwyCombomealSmartById(String productId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealSmart 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealSmart> selectFfwyCombomealSmartList(FfwyCombomealSmart ffwyCombomealSmart);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealSmart 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealSmart(FfwyCombomealSmart ffwyCombomealSmart);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealSmart 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealSmart(FfwyCombomealSmart ffwyCombomealSmart);

    /**
     * 删除【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealSmartById(String productId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyCombomealSmartByIds(String[] productIds);

    void deleteFfwyCombomealSmartcId(@Param("combomealId") Long combomealId);


}
