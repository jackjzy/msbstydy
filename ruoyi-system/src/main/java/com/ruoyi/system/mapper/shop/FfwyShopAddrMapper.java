package com.ruoyi.system.mapper.shop;

import com.ruoyi.system.domain.shop.FfwyShopAddr;

import java.util.List;


/**
 * 店铺收货地址对象Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
public interface FfwyShopAddrMapper 
{
    /**
     * 查询店铺收货地址对象
     * 
     * @param consigneeAddrid 店铺收货地址对象ID
     * @return 店铺收货地址对象
     */
    public FfwyShopAddr selectFfwyShopAddrById(Long consigneeAddrid);

    /**
     * 查询店铺收货地址对象列表
     * 
     * @param ffwyShopAddr 店铺收货地址对象
     * @return 店铺收货地址对象集合
     */
    public List<FfwyShopAddr> selectFfwyShopAddrList(FfwyShopAddr ffwyShopAddr);

    /**
     * 新增店铺收货地址对象
     * 
     * @param ffwyShopAddr 店铺收货地址对象
     * @return 结果
     */
    public int insertFfwyShopAddr(FfwyShopAddr ffwyShopAddr);

    /**
     * 修改店铺收货地址对象
     * 
     * @param ffwyShopAddr 店铺收货地址对象
     * @return 结果
     */
    public int updateFfwyShopAddr(FfwyShopAddr ffwyShopAddr);

    /**
     * 删除店铺收货地址对象
     * 
     * @param consigneeAddrid 店铺收货地址对象ID
     * @return 结果
     */
    public int deleteFfwyShopAddrById(Long consigneeAddrid);

    /**
     * 批量删除店铺收货地址对象
     * 
     * @param consigneeAddrids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyShopAddrByIds(Long[] consigneeAddrids);
}
