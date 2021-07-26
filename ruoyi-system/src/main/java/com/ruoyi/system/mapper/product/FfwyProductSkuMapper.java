package com.ruoyi.system.mapper.product;

import java.util.List;

import com.ruoyi.system.domain.product.FfwyProductSku;
import org.apache.ibatis.annotations.Param;

/**
 * 商品skuMapper接口
 *
 * @author ruoyi
 * @date 2021-04-21
 */
public interface FfwyProductSkuMapper {
    /**
     * 查询商品sku
     *
     * @param skuId 商品skuID
     * @return 商品sku
     */
    public FfwyProductSku selectFfwyProductSkuById(Long skuId);

    public List<FfwyProductSku> selectFfwyProductSkuList(Long skuId);

    /**
     * 查询商品sku
     *
     * @param productId
     * @param skuSpec
     * @return
     */
    public FfwyProductSku selectFfwyProductSkuByproductIdAndskuSpec(Long productId, String skuSpec);

    /**
     * 查询商品sku列表
     *
     * @param ffwyProductSku 商品sku
     * @return 商品sku集合
     */
    public List<FfwyProductSku> selectFfwyProductSkuList(FfwyProductSku ffwyProductSku);


    /**
     * 新增商品sku
     *
     * @param ffwyProductSku 商品sku
     * @return 结果
     */
    public int insertFfwyProductSku(FfwyProductSku ffwyProductSku);

    /**
     * 修改商品sku
     *
     * @param ffwyProductSku 商品sku
     * @return 结果
     */
    public int updateFfwyProductSku(FfwyProductSku ffwyProductSku);

    /**
     * 删除商品sku
     *
     * @param skuId 商品skuID
     * @return 结果
     */
    public int deleteFfwyProductSkuById(Long skuId);

    /**
     * 批量删除商品sku
     *
     * @param skuIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProductSkuByIds(Long[] skuIds);

    List<FfwyProductSku> selectFfwyProductSkunUmber(FfwyProductSku ffwyProductSku);

    FfwyProductSku selectFfwyProductCartById(Long skuId);

    List<FfwyProductSku> selectFfwyfindByproductcatsj(Long productId);

    List<FfwyProductSku> selectFfwyProductSku(@Param("skuSpec") String skuSpec, @Param("productId") Long productId);
    FfwyProductSku selectFfwyProduct(@Param("s") String s, @Param("productId") Long productId);

    List<FfwyProductSku> selectFfwyfindByproductcatName(long skuId);

    /**
     * 删除一个商品下的所有sku信息
     * @param productId
     */
    void deleteFfwyProductSkuByProductId(Long productId);

    FfwyProductSku selectFfwyProductStock(@Param("productId") Long productId);
}
