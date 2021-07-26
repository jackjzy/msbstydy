package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.to.ProductTo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 商品信息Service接口
 *
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyProductService {
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
     * @param userId    用户id
     * @return 商品信息
     */
    public FfwyProduct selectStroeProductById(Long productId, Long userId);

    /**
     * 查询商品信息列表
     *
     * @param ffwyProduct 商品信息
     * @return 商品信息集合
     */
    public List<FfwyProduct> selectFfwyProductList(ProductTo ffwyProduct);

    /**
     * 查询收藏商品信息列表
     *
     * @param userId 用户id
     * @return 商品信息集合
     */
    public List<FfwyProduct> selectStoreProductList(Long userId);

    /**
     * 新增商品信息
     *
     * @param ffwyProduct 商品信息
     * @return 结果
     */
    public Long insertFfwyProduct(ProductTo productTo);

    /**
     * 新增收藏商品信息
     *
     * @param ffwyProduct 商品
     * @return 结果
     */
    public AjaxResult insertStroeProduct( FfwyProduct ffwyProduct);

    /**
     * 修改商品信息
     *
     * @param
     * @return 结果
     */
    public int updateFfwyProduct(Long productId, String productStatus);

    /**
     * 批量删除商品信息
     *
     * @param productIds 需要删除的商品信息ID
     * @return 结果
     */
    public int deleteFfwyProductByIds(Long[] productIds);

    /**
     * 删除商品信息信息
     *
     * @param productId 商品信息ID
     * @return 结果
     */
    public int deleteFfwyProductById(Long productId);

    /**
     * 删除收藏商品信息
     *
     * @param ffwyProduct 商品
     * @return 结果
     */
    public AjaxResult deleteStroeProduct( FfwyProduct ffwyProduct);

    List<FfwyProduct> selectFfwyProductListId(FfwyProduct ffwyProduct);


    List<FfwyProduct> selectClassifyList(FfwyProduct ffwyProduct);


    Long updataProductSku(ProductTo productTo);
}
