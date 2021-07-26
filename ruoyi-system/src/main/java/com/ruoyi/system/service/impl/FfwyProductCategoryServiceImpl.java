package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductCategory;
import com.ruoyi.system.mapper.product.FfwyProductCategoryMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.service.IFfwyProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyProductCategoryServiceImpl implements IFfwyProductCategoryService
{
    @Autowired
    private FfwyProductCategoryMapper ffwyProductCategoryMapper;
    @Autowired
    private FfwyProductMapper ffwyProductMapper;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询商品类别
     * 
     * @param categoryId 商品类别ID
     * @return 商品类别
     */
    @Override
    public FfwyProductCategory selectFfwyProductCategoryById(Long categoryId)
    {
        return ffwyProductCategoryMapper.selectFfwyProductCategoryById(categoryId);
    }

    /**
     * 查询商品类别列表
     * 
     * @param ffwyProductCategory 商品类别
     * @return 商品类别
     */
    @Override
    public List<FfwyProductCategory> selectFfwyProductCategoryList(FfwyProductCategory ffwyProductCategory)
    {
        //1. 按条件查询出所有分类
        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyProductCategoryList(ffwyProductCategory);

        //2. 组装成父子的树形结构

        //2.1）、找到所有以及分类

        List<FfwyProductCategory> levelMenus = ffwyProductCategories.stream()
                .filter(c -> c.getParentId() == 0)
                .map((menu) -> {
                    menu.setChildCategory(getChildrens(menu, ffwyProductCategories));
                    return menu;
                }).collect(Collectors.toList());

        return levelMenus;
    }

    /**
     * 新增商品类别
     * 
     * @param ffwyProductCategory 商品类别
     * @return 结果
     */
    @Override
    public int insertFfwyProductCategory(FfwyProductCategory ffwyProductCategory)
    {
        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyProductCategoryList(new FfwyProductCategory());
        if (ffwyProductCategories.size() >= 10){
            return 3;
        }
        ffwyProductCategory.setCreateTime(DateUtils.getNowDate());
        if (ffwyProductCategory.getParentId() == null){
            ffwyProductCategory.setParentId(0L);
        }
        return ffwyProductCategoryMapper.insertFfwyProductCategory(ffwyProductCategory);
    }

    /**
     * 修改商品类别
     * 
     * @param ffwyProductCategory 商品类别
     * @return 结果
     */
    @Override
    public int updateFfwyProductCategory(FfwyProductCategory ffwyProductCategory)
    {   if (ffwyProductCategory.getCategoryName()!=null){
       redisCache.deleteObject("product");
    }
        ffwyProductCategory.setUpdateTime(DateUtils.getNowDate());
        return ffwyProductCategoryMapper.updateFfwyProductCategory(ffwyProductCategory);
    }

    /**
     * 批量删除商品类别
     * 
     * @param categoryIds 需要删除的商品类别ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCategoryByIds(Long[] categoryIds)
    {
        for (Long categoryId : categoryIds) {

            if (ffwyProductCategoryMapper.selectFfwyProductCategoryByParentId(categoryId) == 0){
                if (ffwyProductMapper.selectFfwyProductByProductCategoryId(categoryId) == 0){
                    ffwyProductCategoryMapper.deleteFfwyProductCategoryById(categoryId);
                }
            }

            return -1;

        }
        return 1;
    }

    /**
     * 删除商品类别信息
     * 
     * @param categoryId 商品类别ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCategoryById(Long categoryId)
    {




        FfwyProductCategory ffwyProductCategory = new FfwyProductCategory();
        ffwyProductCategory.setParentId(categoryId);
        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyProductCategoryList(ffwyProductCategory);

        if (null!=ffwyProductCategories){
            if (ffwyProductCategories.size()<=0){
                FfwyProduct ffwyProduct = new FfwyProduct();
                ffwyProduct.setProductCategoryId(categoryId);
                List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyProductList(ffwyProduct);

                if (null!=ffwyProducts){
                    if (ffwyProducts.size()<=0){
                        return ffwyProductCategoryMapper.deleteFfwyProductCategoryById(categoryId);
                    }


                }
                return -2;

            }

        }


        return -1;
    }

    @Override
    public int insertFfwyProductCategorys(FfwyProductCategory ffwyProductCategory) {
        return ffwyProductCategoryMapper.insertFfwyProductCategory(ffwyProductCategory);
    }

    @Override
    public List<FfwyProductCategory> selectFfwyProductCategoryclassifyList(FfwyProductCategory ffwyProductCategory) {
        return  ffwyProductCategoryMapper.selectFfwyProductCategoryList(ffwyProductCategory);
    }


    //递归查找所有菜单的子菜单
    private List<FfwyProductCategory> getChildrens(FfwyProductCategory root, List<FfwyProductCategory> all) {

        List<FfwyProductCategory> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentId().equals(root.getCategoryId());
        }).map(categoryEntity -> {
            //1、找到子菜单(递归)
            categoryEntity.setChildCategory(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).collect(Collectors.toList());

        return children;

    }
}
