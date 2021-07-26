package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.cart.FfwyCartInfo;

/**
 * 购物车 Service接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface IFfwyCartInfoService 
{
    /**
     * 查询购物车 
     * 
     * @param id 购物车 ID
     * @return 购物车 
     */
    public FfwyCartInfo selectFfwyCartInfoById(Long id);

    /**
     * 查询购物车 列表
     * 
     * @param ffwyCartInfo 购物车 
     * @return 购物车 集合
     */
    public AjaxResult selectFfwyCartInfoList(FfwyCartInfo ffwyCartInfo);

    /**
     * 新增购物车 
     * 
     * @param ffwyCartInfo 购物车 
     * @return 结果
     */
    public AjaxResult insertFfwyCartInfo(FfwyCartInfo ffwyCartInfo);

    /**
     * 修改购物车 
     * 
     * @param ffwyCartInfo 购物车 
     * @return 结果
     */
    public AjaxResult updateFfwyCartInfo(FfwyCartInfo ffwyCartInfo);

    /**
     * 批量删除购物车 
     * 
     * @param ids 需要删除的购物车 ID
     * @return 结果
     */
    public int deleteFfwyCartInfoByIds(String ids);

    /**
     * 删除购物车 信息
     * 
     * @param id 购物车 ID
     * @return 结果
     */
    public int deleteFfwyCartInfoById(Long id);

    Map<String, Object> getCartCheckedList(String id);

    Map<String, Object> getCartCheckedSkuList(String skuId, int skuNum);
}
