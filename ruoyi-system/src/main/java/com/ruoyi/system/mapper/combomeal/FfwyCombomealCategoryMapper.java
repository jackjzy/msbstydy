package com.ruoyi.system.mapper.combomeal;

import com.ruoyi.system.domain.combomeal.FfwyCombomeal;
import com.ruoyi.system.domain.combomeal.FfwyCombomealCategory;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
public interface FfwyCombomealCategoryMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param categoryId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealCategory selectFfwyCombomealCategoryById(Long categoryId);

    /**
     * 查询【请填写功能名称】
     *
     * @param combomaealName 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealCategory selectFfwyCombomealCategoryByCombomaealName(String combomaealName);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealCategory> selectFfwyCombomealCategoryList(FfwyCombomealCategory ffwyCombomealCategory);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealCategory(FfwyCombomealCategory ffwyCombomealCategory);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealCategory(FfwyCombomealCategory ffwyCombomealCategory);

    /**
     * 删除【请填写功能名称】
     * 
     * @param categoryId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCategoryById(Long categoryId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param categoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyCombomealCategoryByIds(Long[] categoryIds);

}
