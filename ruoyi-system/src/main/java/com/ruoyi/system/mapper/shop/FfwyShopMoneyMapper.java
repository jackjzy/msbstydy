package com.ruoyi.system.mapper.shop;

import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.vo.FfwyShopVo;
import com.ruoyi.system.domain.vo.ShopVo;

import java.util.List;

/**
 * 店铺信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface FfwyShopMoneyMapper 
{
    /**
     * 查询店铺信息
     * 
     * @param shopId 店铺信息ID
     * @return 店铺信息
     */
    public FfwyShop selectFfwyShopById(Long shopId);

    /**
     * 查询店铺信息列表
     * @param ffwyShopVo 店铺信息
     * @return 店铺信息集合
     */
    public List<ShopVo> selectFfwyShopList(FfwyShopVo ffwyShopVo);

    /**
     * 新增店铺信息
     * 
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    public int insertFfwyShop(FfwyShop ffwyShop);

    /**
     * 修改店铺信息
     * 
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    public int updateFfwyShop(FfwyShop ffwyShop);

    /**
     * 删除店铺信息
     * 
     * @param shopId 店铺信息ID
     * @return 结果
     */
    public int deleteFfwyShopById(Long shopId);

    /**
     * 批量删除店铺信息
     * 
     * @param shopIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyShopByIds(Long[] shopIds);

    List<FfwyShop> selectFfwyShopLists();

    List<ShopVo> selectFfwyShopListBydetail(FfwyShopVo ffwyShopVo);

    List<ShopVo> selectFfwyShopListAll();
}
