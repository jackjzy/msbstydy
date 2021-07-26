package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.shop.FfwyShopAddr;
import com.ruoyi.system.mapper.shop.FfwyShopAddrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyShopAddrService;

/**
 * 店铺收货地址对象Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Service
public class FfwyShopAddrServiceImpl implements IFfwyShopAddrService 
{
    @Autowired
    private FfwyShopAddrMapper ffwyShopAddrMapper;

    /**
     * 查询店铺收货地址对象
     * 
     * @param consigneeAddrid 店铺收货地址对象ID
     * @return 店铺收货地址对象
     */
    @Override
    public FfwyShopAddr selectFfwyShopAddrById(Long consigneeAddrid)
    {
        return ffwyShopAddrMapper.selectFfwyShopAddrById(consigneeAddrid);
    }

    /**
     * 查询店铺收货地址对象列表
     * 
     * @param ffwyShopAddr 店铺收货地址对象
     * @return 店铺收货地址对象
     */
    @Override
    public List<FfwyShopAddr> selectFfwyShopAddrList(FfwyShopAddr ffwyShopAddr)
    {
        return ffwyShopAddrMapper.selectFfwyShopAddrList(ffwyShopAddr);
    }

    /**
     * 新增店铺收货地址对象
     * 
     * @param ffwyShopAddr 店铺收货地址对象
     * @return 结果
     */
    @Override
    public int insertFfwyShopAddr(FfwyShopAddr ffwyShopAddr)
    {
        ffwyShopAddr.setCreateTime(DateUtils.getNowDate());
        return ffwyShopAddrMapper.insertFfwyShopAddr(ffwyShopAddr);
    }

    /**
     * 修改店铺收货地址对象
     * 
     * @param ffwyShopAddr 店铺收货地址对象
     * @return 结果
     */
    @Override
    public int updateFfwyShopAddr(FfwyShopAddr ffwyShopAddr)
    {
        ffwyShopAddr.setUpdateTime(DateUtils.getNowDate());
        return ffwyShopAddrMapper.updateFfwyShopAddr(ffwyShopAddr);
    }

    /**
     * 批量删除店铺收货地址对象
     * 
     * @param consigneeAddrids 需要删除的店铺收货地址对象ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopAddrByIds(Long[] consigneeAddrids)
    {
        return ffwyShopAddrMapper.deleteFfwyShopAddrByIds(consigneeAddrids);
    }

    /**
     * 删除店铺收货地址对象信息
     * 
     * @param consigneeAddrid 店铺收货地址对象ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopAddrById(Long consigneeAddrid)
    {
        return ffwyShopAddrMapper.deleteFfwyShopAddrById(consigneeAddrid);
    }
}
