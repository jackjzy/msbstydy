package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.myhoust.FfwyMyhousts;
import com.ruoyi.system.domain.order.FfwyOrderClient;
import com.ruoyi.system.domain.vo.FfwyWorkplanVo;
import com.ruoyi.system.mapper.combomealorders.FfwyPhaseMapper;
import com.ruoyi.system.mapper.myhoust.FfwyMyhoustsMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyMyhoustsService;

/**
 *
 * @author ruoyi
 * @date 2021-04-26
 */
@Service
public class FfwyMyhoustsServiceImpl implements IFfwyMyhoustsService 
{
    @Autowired
    private FfwyMyhoustsMapper ffwyMyhoustsMapper;

    @Autowired
    private FfwyShopMapper ffwyShopMapper;

    @Autowired
    private FfwyPhaseMapper ffwyPhaseMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param myhoustId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyMyhousts selectFfwyMyhoustsById(Long myhoustId)
    {
        return ffwyMyhoustsMapper.selectFfwyMyhoustsById(myhoustId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderClient 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyWorkplanVo> selectFfwyMyhoustsList(FfwyOrderClient ffwyOrderClient)
    {
        Long orderClientId = ffwyOrderClient.getOrderClientId();
        List<FfwyWorkplanVo> myhoustlist = ffwyMyhoustsMapper.selectFfwyMyhoustsByid(orderClientId);
        return myhoustlist;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyMyhousts 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyMyhousts(FfwyMyhousts ffwyMyhousts)
    {
        return ffwyMyhoustsMapper.insertFfwyMyhousts(ffwyMyhousts);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyMyhousts 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyMyhousts(FfwyMyhousts ffwyMyhousts)
    {
        return ffwyMyhoustsMapper.updateFfwyMyhousts(ffwyMyhousts);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param myhoustIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMyhoustsByIds(Long[] myhoustIds)
    {
        return ffwyMyhoustsMapper.deleteFfwyMyhoustsByIds(myhoustIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param myhoustId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMyhoustsById(Long myhoustId)
    {
        return ffwyMyhoustsMapper.deleteFfwyMyhoustsById(myhoustId);
    }
}
