package com.ruoyi.system.mapper.combomeal;

import com.ruoyi.system.domain.combomeal.FfwyCombomealWiring;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface FfwyCombomealWiringMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param productId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealWiringById(String productId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyCombomealWiringByIds(String[] productIds);

    void deleteFfwyCombomealWiringcId(@Param("combomealId") Long combomealId);
}
