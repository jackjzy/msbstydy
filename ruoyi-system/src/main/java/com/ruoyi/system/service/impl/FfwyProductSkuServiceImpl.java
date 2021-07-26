package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.product.FfwyProductSkuMapper;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.service.IFfwyProductSkuService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品skuService业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyProductSkuServiceImpl implements IFfwyProductSkuService 
{
    @Autowired
    private FfwyProductSkuMapper ffwyProductSkuMapper;

    /**
     * 查询商品sku
     * 
     * @param skuId 商品skuID
     * @return 商品sku
     */
    @Override
    public FfwyProductSku selectFfwyProductSkuById(Long skuId)
    {
        return ffwyProductSkuMapper.selectFfwyProductSkuById(skuId);
    }

    /**
     * 查询商品sku列表
     * 
     * @param ffwyProductSku 商品sku
     * @return 商品sku
     */
    @Override
    public List<FfwyProductSku> selectFfwyProductSkuList(FfwyProductSku ffwyProductSku)
    {
        return ffwyProductSkuMapper.selectFfwyProductSkuList(ffwyProductSku);
    }

    /**
     * 新增商品sku
     * 
     * @param ffwyProductSku 商品sku
     * @return 结果
     */
    @Override
    public int insertFfwyProductSku(FfwyProductSku ffwyProductSku)
    {
        return ffwyProductSkuMapper.insertFfwyProductSku(ffwyProductSku);
    }

    /**
     * 修改商品sku
     * 
     * @param ffwyProductSku 商品sku
     * @return 结果
     */
    @Override
    public int updateFfwyProductSku(FfwyProductSku ffwyProductSku)
    {
        return ffwyProductSkuMapper.updateFfwyProductSku(ffwyProductSku);
    }

    /**
     * 批量删除商品sku
     * 
     * @param skuIds 需要删除的商品skuID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductSkuByIds(Long[] skuIds)
    {
        return ffwyProductSkuMapper.deleteFfwyProductSkuByIds(skuIds);
    }

    /**
     * 删除商品sku信息
     * 
     * @param skuId 商品skuID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductSkuById(Long skuId)
    {
        return ffwyProductSkuMapper.deleteFfwyProductSkuById(skuId);
    }

    @Override
    @Transactional
    public Boolean UnlockStock(List<FfwyMaterial> materials) {
        materials.forEach(material -> {
            FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(material.getSkuId());
            ffwyProductSku.setStock(ffwyProductSku.getStock() + material.getMaterialNumber());
            ffwyProductSkuMapper.updateFfwyProductSku(ffwyProductSku);
        });
        return true;
    }

//    @Override
//    public Boolean UnlockProStock(List<FfwyOrderDetails> proLocks) {
//        proLocks.forEach(pro->{
//            FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(pro.getSkuId());
//            ffwyProductSku.setStock(ffwyProductSku.getStock() + pro.getNumber());
//            ffwyProductSkuMapper.updateFfwyProductSku(ffwyProductSku);
//        });
//        return true;
//
//    }
}
