package com.ruoyi.system.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.domain.to.ProductSkuTo;
import com.ruoyi.system.domain.vo.SpeVO;

import java.util.List;

/**
 * 订单规格Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwySpecificationService 
{
    /**
     * 查询订单规格
     * 
     * @param specificationId 订单规格ID
     * @return 订单规格
     */
    public FfwySpecification selectFfwySpecificationById(Integer specificationId);

    /**
     * 查询订单规格列表
     * 
     * @param ffwySpecification 订单规格
     * @return 订单规格集合
     */
    public AjaxResult selectFfwySpecificationList(FfwySpecification ffwySpecification);

    /**
     *
     * @param productId 商品Id
     * @return
     */
    public List<JSONObject> selectSpeVoList(Long productId);


    /**
     * 商品sku
     * @param productSku
     * @return
     */
    public AjaxResult insertOrpdataProductSku(ProductSkuTo productSku);

    /**
     *
     * @param speVO
     * @return
     */
    public int insertOrUpdataSpecification(SpeVO speVO);

    /**
     * 添加修改
     * @param ffwySpecification
     * @return
     */
    public int insertOrUpdata(FfwySpecification ffwySpecification);

    /**
     * 修改订单规格
     * 
     * @param ffwySpecification 订单规格
     * @return 结果
     */
    public int updateFfwySpecification(FfwySpecification ffwySpecification);

    /**
     * 批量删除订单规格
     * 
     * @param specificationIds 需要删除的订单规格ID
     * @return 结果
     */
    public int deleteFfwySpecificationByIds(Integer[] specificationIds);

    /**
     * 删除订单规格信息
     * 
     * @param specificationId 订单规格ID
     * @return 结果
     */
    public int deleteFfwySpecificationById(Integer specificationId);

    AjaxResult selectFfwySpecificationListById(Long productId,  String value);

    ProductSkuTo selectAdminSpeVoList(Long productId);
}
