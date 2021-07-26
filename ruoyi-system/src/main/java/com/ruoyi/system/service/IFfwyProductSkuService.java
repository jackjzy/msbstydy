package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.product.FfwyProductSku;

/**
 * 商品skuService接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyProductSkuService 
{
    /**
     * 查询商品sku
     * 
     * @param skuId 商品skuID
     * @return 商品sku
     */
    public FfwyProductSku selectFfwyProductSkuById(Long skuId);

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
     * 批量删除商品sku
     * 
     * @param skuIds 需要删除的商品skuID
     * @return 结果
     */
    public int deleteFfwyProductSkuByIds(Long[] skuIds);

    /**
     * 删除商品sku信息
     * 
     * @param skuId 商品skuID
     * @return 结果
     */
    public int deleteFfwyProductSkuById(Long skuId);

    /**
     * 套餐库存解锁
     * @param materials
     */
    public Boolean UnlockStock(List<FfwyMaterial> materials);

    //Boolean UnlockProStock(List<FfwyOrderDetails> proLocks);
}
