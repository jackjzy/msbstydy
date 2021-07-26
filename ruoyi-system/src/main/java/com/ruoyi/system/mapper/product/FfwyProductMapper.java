package com.ruoyi.system.mapper.product;

import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.to.ProductTo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@Repository
public interface FfwyProductMapper {
    /**
     * 查询商品信息
     *
     * @param productId 商品信息ID
     * @return 商品信息
     */
    public FfwyProduct selectFfwyProductById(Long productId);
    /**
     * 查询收藏商品信息
     *
     * @param productId 商品信息ID
     * @param userId 用户id
     * @return 商品信息
     */
    // public FfwyProduct selectStroeProductById(@Param("productId") Long productId,@Param("userId") Long userId);

    /**
     * 查询商品信息
     *
     * @param productCategoryId 商品信息ID
     * @return 商品信息
     */
    public Long selectFfwyProductByProductCategoryId(Long productCategoryId);


    /**
     * 查询商品信息列表
     *
     * @param ffwyProduct 商品信息
     * @return 商品信息集合
     */
    public List<FfwyProduct> selectFfwyProductList(FfwyProduct ffwyProduct);

    public List<FfwyProduct> selectProductToList(ProductTo ffwyProduct);

    /**
     * 查询收藏商品信息列表
     *
     * @param userId 用户id
     * @return 商品信息集合
     */
    public List<FfwyProduct> selectStoreProductList(Long userId);


    /**
     * 查询商品信息列表
     *
     * @param ffwyProduct 商品信息
     * @return app商品信息集合
     */
    public List<FfwyProduct> selectFfwyProductCategoryList(FfwyProduct ffwyProduct);


    /**
     * 查询商品信息列表
     *
     * @param productCategoryId 商品信息
     * @return 商品信息集合
     */
    public List<FfwyProduct> selectFfwyProductListLong(Long productCategoryId);


    public List<FfwyProduct> selectFfwyProductByShopId(Long shopId);

    /**
     * 新增商品信息
     *
     * @param ffwyProduct 商品信息
     * @return 结果
     */
    public int insertFfwyProduct(FfwyProduct ffwyProduct);

    /**
     * 新增收藏商品信息
     *
     * @param productId 商品id
     * @param userId    用户id
     * @return 结果
     */
    public int insertStroeProduct(@Param("productId") Long productId, @Param("userId") Long userId);

    /**
     * 修改商品信息
     *
     * @param ffwyProduct 商品信息
     * @return 结果
     */
    public int updateFfwyProduct(FfwyProduct ffwyProduct);

    /**
     * 删除商品信息
     *
     * @param productId 商品信息ID
     * @return 结果
     */
    public int deleteFfwyProductById(Long productId);

    /**
     * 批量删除商品信息
     *
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProductByIds(Long[] productIds);

    /**
     * 删除收藏商品信息
     *
     * @param productId 商品id
     * @param userId    用户id
     * @return 结果
     */
    public int deleteStroeProduct(@Param("productId") Long productId, @Param("userId") Long userId);

    FfwyProduct selectStroeProductById(FfwyProduct ffwyProduct1);



    FfwyProduct selectStroeProduct(@Param("userId") Long userId, @Param("productId") Long productId);

    List<String> selectFfwyfindByproductcatej(Long productCategoryId);

    List<FfwyProduct> selectFfwyfindByproductcatName(Long productId);

    FfwyProduct selectFfwyProductCategoryCountList(Long productCategoryId);

    FfwyProduct selectProductCouont(@Param("productId")Long productId);
}
