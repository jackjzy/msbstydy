package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.combomeal.FfwyCombomeal;
import com.ruoyi.system.domain.combomeal.FfwyCombomealCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
public interface IFfwyCombomealCategoryService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param categoryId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealCategory selectFfwyCombomealCategoryById(Long categoryId);

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
     * 新增【请填写功能名称】
     *
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */
    public Map<String, Object> insertFfwyCombomealCategoryPC(FfwyCombomealCategory ffwyCombomealCategory,
                                                             MultipartFile fileCover, MultipartFile fileCategoryNote);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealCategory(FfwyCombomealCategory ffwyCombomealCategory);



    /**
     * 批量删除【请填写功能名称】
     * 
     * @param categoryIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCategoryByIds(Long[] categoryIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param categoryId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCategoryById(Long categoryId);
}
