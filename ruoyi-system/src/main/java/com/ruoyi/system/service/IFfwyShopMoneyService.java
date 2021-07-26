package com.ruoyi.system.service;

import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.vo.FfwyShopVo;
import com.ruoyi.system.domain.vo.ShopVo;

import java.util.List;

/**
 * 店铺信息Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyShopMoneyService 
{
    /**
     * 查询店铺信息
     * 
     * @param shopId 店铺信息ID
     * @return 店铺信息
     */
    public FfwyShop selectFfwyShopById(Long shopId);

    /**
     * 查询店铺信息
     * 
     * @param ffwyShopVo 店铺信息
     * @return 店铺信息集合
     */
    public List<ShopVo> selectFfwyShopListBydetail(FfwyShopVo ffwyShopVo);

    /**
     * 新增店铺信息
     * 
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    public int insertFfwyShop(FfwyShop ffwyShop);

    /**
     * 修改分成比例
     * 
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    public int updateFfwyShop(FfwyShop ffwyShop);

    /**
     * 批量删除店铺信息
     * 
     * @param shopIds 需要删除的店铺信息ID
     * @return 结果
     */
    public int deleteFfwyShopByIds(Long[] shopIds);

    /**
     * 删除店铺信息信息
     * 
     * @param shopId 店铺信息ID
     * @return 结果
     */
    public int deleteFfwyShopById(Long shopId);
}
