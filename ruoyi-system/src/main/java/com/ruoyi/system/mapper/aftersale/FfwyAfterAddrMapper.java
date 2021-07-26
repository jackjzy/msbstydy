package com.ruoyi.system.mapper.aftersale;

import com.ruoyi.system.domain.aftersale.FfwyAfterAddr;

import java.util.List;

/**
 * 售后买家填写物流Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
public interface FfwyAfterAddrMapper 
{
    /**
     * 查询售后买家填写物流
     * 
     * @param afterAddrId 售后买家填写物流ID
     * @return 售后买家填写物流
     */
    public FfwyAfterAddr selectFfwyAfterAddrById(Long afterAddrId);

    /**
     * 查询售后买家填写物流列表
     * 
     * @param ffwyAfterAddr 售后买家填写物流
     * @return 售后买家填写物流集合
     */
    public List<FfwyAfterAddr> selectFfwyAfterAddrList(FfwyAfterAddr ffwyAfterAddr);

    /**
     * 新增售后买家填写物流
     * 
     * @param ffwyAfterAddr 售后买家填写物流
     * @return 结果
     */
    public int insertFfwyAfterAddr(FfwyAfterAddr ffwyAfterAddr);

    /**
     * 修改售后买家填写物流
     * 
     * @param ffwyAfterAddr 售后买家填写物流
     * @return 结果
     */
    public int updateFfwyAfterAddr(FfwyAfterAddr ffwyAfterAddr);

    /**
     * 删除售后买家填写物流
     * 
     * @param afterAddrId 售后买家填写物流ID
     * @return 结果
     */
    public int deleteFfwyAfterAddrById(Long afterAddrId);

    /**
     * 批量删除售后买家填写物流
     * 
     * @param afterAddrIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyAfterAddrByIds(Long[] afterAddrIds);
}
