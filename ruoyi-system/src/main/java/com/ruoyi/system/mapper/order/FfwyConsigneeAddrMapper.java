package com.ruoyi.system.mapper.order;

import java.util.List;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;

/**
 * 收货地址Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface FfwyConsigneeAddrMapper 
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
     * 新增收货地址
     * 
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    public int insertFfwyConsigneeAddr(FfwyConsigneeAddr ffwyConsigneeAddr);

    /**
     * 修改收货地址
     * 
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    public int updateFfwyConsigneeAddr(FfwyConsigneeAddr ffwyConsigneeAddr);

    /**
     * 删除收货地址
     * 
     * @param consigneeAddrid 收货地址ID
     * @return 结果
     */
    public int deleteFfwyConsigneeAddrById(Long consigneeAddrid);

    /**
     * 批量删除收货地址
     * 
     * @param consigneeAddrids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyConsigneeAddrByIds(Long[] consigneeAddrids);

    List<FfwyConsigneeAddr> selectFfwyConsigneeAddrDefaultList(FfwyConsigneeAddr ffwyConsigneeAddr);
}
