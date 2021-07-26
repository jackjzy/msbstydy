package com.ruoyi.system.mapper.combomeal;

import com.ruoyi.system.domain.combomeal.CombomealCategory;

import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/6/16
 **/
public interface CombomealCategoryMapper {
    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyCombomeal 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CombomealCategory> selectCombomealCategoryList(Long categoryId);

}
