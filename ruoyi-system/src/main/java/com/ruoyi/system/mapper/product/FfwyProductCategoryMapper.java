package com.ruoyi.system.mapper.product;

import com.ruoyi.system.domain.product.FfwyProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品类别Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@Repository
public interface FfwyProductCategoryMapper {
    /**
     * 查询商品类别
     *
     * @param categoryId 商品类别ID
     * @return 商品类别
     */
    public FfwyProductCategory selectFfwyProductCategoryById(Long categoryId);


    /**
     * 查询商品类别
     *
     * @param parentId 商品类别ID
     * @return 商品类别
     */
    public Long selectFfwyProductCategoryByParentId(Long parentId);

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
     * 删除商品类别
     *
     * @param categoryId 商品类别ID
     * @return 结果
     */
    public int deleteFfwyProductCategoryById(Long categoryId);

    /**
     * 批量删除商品类别
     *
     * @param categoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProductCategoryByIds(Long[] categoryIds);

    List<FfwyProductCategory> selectFfwyProductCategoryAll(FfwyProductCategory ffwyProductCategory1);

    List<FfwyProductCategory> selectFfwyProductCategoryname(FfwyProductCategory ffwyProductCategory);

    List<FfwyProductCategory> selectFfwyCombomealByList(Long[] ids);

    List<String> selectFfwyfindByproductcat();

    List<FfwyProductCategory> selectByperentid(FfwyProductCategory ffwyProductCategory);

    List<FfwyProductCategory> selectFfwyfindBysoft();

    List<FfwyProductCategory> selectFfwyfindByarticles();

    FfwyProductCategory selectFfwyProductCategoryByName(@Param("categoryName") String liveCategoryName);

}
