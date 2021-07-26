package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;

/**
 * 收货地址Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwyConsigneeAddrService 
{
    /**
     * 查询收货地址
     * 
     * @param consigneeAddrid 收货地址ID
     * @return 收货地址
     */
    public FfwyConsigneeAddr selectFfwyConsigneeAddrById(Long consigneeAddrid);

    /**
     * 查询收货地址列表
     * 
     * @param ffwyConsigneeAddr 收货地址
     * @return 收货地址集合
     */
    public List<FfwyConsigneeAddr> selectFfwyConsigneeAddrList(FfwyConsigneeAddr ffwyConsigneeAddr);
    /**
     * 查询收货地址列表
     *
     * @param ffwyConsigneeAddr 收货地址
     * @return 收货地址集合
     */
    public List<FfwyConsigneeAddr> selectShopAddrList(FfwyConsigneeAddr ffwyConsigneeAddr);

    /**
     * 新增用户收货地址
     * 
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    public AjaxResult insertFfwyConsigneeAddr(FfwyConsigneeAddr ffwyConsigneeAddr);
    public int insertFfwyConsigneeAddrs(FfwyConsigneeAddr ffwyConsigneeAddr);

    /**
     * 新增店铺收货地址
     *
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    public int insertShopAddr(FfwyConsigneeAddr ffwyConsigneeAddr);

    /**
     * 修改收货地址
     * 
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    public int updateFfwyConsigneeAddr(FfwyConsigneeAddr ffwyConsigneeAddr);

    /**
     * 批量删除收货地址
     * 
     * @param consigneeAddrids 需要删除的收货地址ID
     * @return 结果
     */
    public int deleteFfwyConsigneeAddrByIds(Long[] consigneeAddrids);

    /**
     * 删除用户收货地址信息
     * 
     * @param consigneeAddrid 收货地址ID
     * @return 结果
     */
    public int deleteFfwyConsigneeAddrById(Long consigneeAddrid);
    /**
     * 删除店铺收货地址信息
     *
     * @param ffwyConsigneeAddr 收货地址ID
     * @return 结果
     */
    public int deleteShopAddrById(FfwyConsigneeAddr ffwyConsigneeAddr);

    List<FfwyConsigneeAddr> selectFfwyConsigneeAddressStatus(FfwyConsigneeAddr ffwyConsigneeAddr);
}
