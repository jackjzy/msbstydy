package com.ruoyi.back.service.impl;

import java.util.List;

import com.ruoyi.back.domain.FfwyAbout;
import com.ruoyi.back.mapper.FfwyAboutMapper;
import com.ruoyi.back.service.IFfwyAboutService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
@Service
public class FfwyAboutServiceImpl implements IFfwyAboutService
{
    @Autowired
    private FfwyAboutMapper ffwyAboutMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param appId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyAbout selectFfwyAboutById(Long appId)
    {
        return ffwyAboutMapper.selectFfwyAboutById(appId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyAbout 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyAbout> selectFfwyAboutList(FfwyAbout ffwyAbout)
    {
        return ffwyAboutMapper.selectFfwyAboutList(ffwyAbout);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyAbout 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyAbout(FfwyAbout ffwyAbout)
    {
        ffwyAbout.setCreateTime(DateUtils.getNowDate());
        return ffwyAboutMapper.insertFfwyAbout(ffwyAbout);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyAbout 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyAbout(FfwyAbout ffwyAbout)
    {
        ffwyAbout.setUpdateTime(DateUtils.getNowDate());
        return ffwyAboutMapper.updateFfwyAbout(ffwyAbout);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param appIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAboutByIds(Long[] appIds)
    {
        return ffwyAboutMapper.deleteFfwyAboutByIds(appIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param appId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAboutById(Long appId)
    {
        return ffwyAboutMapper.deleteFfwyAboutById(appId);
    }
}
