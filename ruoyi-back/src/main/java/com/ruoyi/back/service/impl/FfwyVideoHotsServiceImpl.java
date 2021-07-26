package com.ruoyi.back.service.impl;

import java.util.List;

import com.ruoyi.back.domain.FfwyVideoHots;
import com.ruoyi.back.mapper.FfwyVideoHotsMapper;
import com.ruoyi.back.service.IFfwyVideoHotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
@Service
public class FfwyVideoHotsServiceImpl implements IFfwyVideoHotsService
{
    @Autowired
    private FfwyVideoHotsMapper ffwyVideoHotsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param videoHotId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyVideoHots selectFfwyVideoHotsById(Long videoHotId)
    {
        return ffwyVideoHotsMapper.selectFfwyVideoHotsById(videoHotId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyVideoHots 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyVideoHots> selectFfwyVideoHotsList(FfwyVideoHots ffwyVideoHots)
    {
        return ffwyVideoHotsMapper.selectFfwyVideoHotsList(ffwyVideoHots);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyVideoHots 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyVideoHots(FfwyVideoHots ffwyVideoHots)
    {
        return ffwyVideoHotsMapper.insertFfwyVideoHots(ffwyVideoHots);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyVideoHots 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyVideoHots(FfwyVideoHots ffwyVideoHots)
    {
        return ffwyVideoHotsMapper.updateFfwyVideoHots(ffwyVideoHots);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param videoHotIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoHotsByIds(Long[] videoHotIds)
    {
        return ffwyVideoHotsMapper.deleteFfwyVideoHotsByIds(videoHotIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param videoHotId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoHotsById(Long videoHotId)
    {
        return ffwyVideoHotsMapper.deleteFfwyVideoHotsById(videoHotId);
    }

    @Override
    public int deleteFfwyVideoHotsByVideoIdAndhotId(Long videoid, Long videoHotId) {
        return ffwyVideoHotsMapper.deleteFfwyVideoHotsByVideoIdAndhotId(videoid, videoHotId);
    }
}
