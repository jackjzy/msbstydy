package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.aftersale.FfwyAfterAddr;
import com.ruoyi.system.mapper.aftersale.FfwyAfterAddrMapper;
import com.ruoyi.system.service.IFfwyAfterAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 售后买家填写物流Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
@Service
public class FfwyAfterAddrServiceImpl implements IFfwyAfterAddrService
{
    @Autowired
    private FfwyAfterAddrMapper ffwyAfterAddrMapper;

    /**
     * 查询售后买家填写物流
     * 
     * @param afterAddrId 售后买家填写物流ID
     * @return 售后买家填写物流
     */
    @Override
    public FfwyAfterAddr selectFfwyAfterAddrById(Long afterAddrId)
    {
        return ffwyAfterAddrMapper.selectFfwyAfterAddrById(afterAddrId);
    }

    /**
     * 查询售后买家填写物流列表
     * 
     * @param ffwyAfterAddr 售后买家填写物流
     * @return 售后买家填写物流
     */
    @Override
    public List<FfwyAfterAddr> selectFfwyAfterAddrList(FfwyAfterAddr ffwyAfterAddr)
    {
        ffwyAfterAddr.setCreateTime(new Date());
        return ffwyAfterAddrMapper.selectFfwyAfterAddrList(ffwyAfterAddr);
    }

    /**
     * 新增售后买家填写物流
     * 
     * @param ffwyAfterAddr 售后买家填写物流
     * @return 结果
     */
    @Override
    public int insertFfwyAfterAddr(FfwyAfterAddr ffwyAfterAddr)
    {
        return ffwyAfterAddrMapper.insertFfwyAfterAddr(ffwyAfterAddr);
    }

    /**
     * 修改售后买家填写物流
     * 
     * @param ffwyAfterAddr 售后买家填写物流
     * @return 结果
     */
    @Override
    public int updateFfwyAfterAddr(FfwyAfterAddr ffwyAfterAddr)
    {
        ffwyAfterAddr.setUpdateTime(new Date());
        return ffwyAfterAddrMapper.updateFfwyAfterAddr(ffwyAfterAddr);
    }

    /**
     * 批量删除售后买家填写物流
     * 
     * @param afterAddrIds 需要删除的售后买家填写物流ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterAddrByIds(Long[] afterAddrIds)
    {
        return ffwyAfterAddrMapper.deleteFfwyAfterAddrByIds(afterAddrIds);
    }

    /**
     * 删除售后买家填写物流信息
     * 
     * @param afterAddrId 售后买家填写物流ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterAddrById(Long afterAddrId)
    {
        return ffwyAfterAddrMapper.deleteFfwyAfterAddrById(afterAddrId);
    }
}
