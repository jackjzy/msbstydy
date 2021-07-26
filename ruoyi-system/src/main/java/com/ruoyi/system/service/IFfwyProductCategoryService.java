package com.ruoyi.system.service;

import com.ruoyi.system.domain.product.FfwyProductCategory;

import java.util.List;

/**
 * 商品类别Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyProductCategoryService 
{
    /**
     * 查询商品类别
     * 
     * @param categoryId 商品类别ID
     * @return 商品类别
     */
    public FfwyProductCategory selectFfwyProductCategoryById(Long categoryId);

    /**
     * 查询商品类别列表
     * 
     * @param ffwyProductCategory 商品类别
     * @return 商品类别集合
     */
    public List<FfwyProductCategory> selectFfwyProductCategoryList(FfwyProductCategory ffwyProductCategory);

    /**
     * 新增商品类别
     * 
     * @param ffwyProductCategory 商品类别
     * @return 结果
     */
    public int insertFfwyProductCategory(FfwyProductCategory ffwyProductCategory);

    /**
     * 修改商品类别
     * 
     * @param ffwyProductCategory 商品类别
     * @return 结果
     */
    public int updateFfwyProductCategory(FfwyProductCategory ffwyProductCategory);

    /**
     * 批量删除商品类别
     * 
     * @param categoryIds 需要删除的商品类别ID
     * @return 结果
     */
    public int deleteFfwyProductCategoryByIds(Long[] categoryIds);

    /**
     * 删除商品类别信息
     * 
     * @param categoryId 商品类别ID
     * @return 结果
     */
    public int deleteFfwyProductCategoryById(Long categoryId);

    int insertFfwyProductCategorys(FfwyProductCategory ffwyProductCategory);

    List<FfwyProductCategory> selectFfwyProductCategoryclassifyList(FfwyProductCategory ffwyProductCategory);
}
