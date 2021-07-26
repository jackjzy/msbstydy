package com.ruoyi.back.service.impl;

import java.util.List;

import com.ruoyi.back.domain.FfwyVideoHot;
import com.ruoyi.back.mapper.FfwyVideoHotMapper;
import com.ruoyi.back.service.IFfwyVideoHotService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@Service
public class FfwyVideoHotServiceImpl implements IFfwyVideoHotService
{
    @Autowired
    private FfwyVideoHotMapper ffwyVideoHotMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param hotId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyVideoHot selectFfwyVideoHotById(Integer hotId)
    {
        return ffwyVideoHotMapper.selectFfwyVideoHotById(hotId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyVideoHot 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyVideoHot> selectFfwyVideoHotList(FfwyVideoHot ffwyVideoHot)
    {
        return ffwyVideoHotMapper.selectFfwyVideoHotList(ffwyVideoHot);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyVideoHot 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyVideoHot(FfwyVideoHot ffwyVideoHot)
    {
        ffwyVideoHot.setCreateTime(DateUtils.getNowDate());
        return ffwyVideoHotMapper.insertFfwyVideoHot(ffwyVideoHot);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyVideoHot 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyVideoHot(FfwyVideoHot ffwyVideoHot)
    {
        ffwyVideoHot.setUpdateTime(DateUtils.getNowDate());
        return ffwyVideoHotMapper.updateFfwyVideoHot(ffwyVideoHot);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param hotIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoHotByIds(Integer[] hotIds)
    {
        return ffwyVideoHotMapper.deleteFfwyVideoHotByIds(hotIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param hotId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoHotById(Integer hotId)
    {
        return ffwyVideoHotMapper.deleteFfwyVideoHotById(hotId);
    }
}
