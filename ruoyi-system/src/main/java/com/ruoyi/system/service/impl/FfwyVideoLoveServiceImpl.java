package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.video.FfwyVideoLove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyVideoLoveMapper;
import com.ruoyi.system.service.IFfwyVideoLoveService;

/**
 * 我喜欢的视频Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-08
 */
@Service
public class FfwyVideoLoveServiceImpl implements IFfwyVideoLoveService 
{
    @Autowired
    private FfwyVideoLoveMapper ffwyVideoLoveMapper;

    /**
     * 查询我喜欢的视频
     * 
     * @param userId 我喜欢的视频ID
     * @return 我喜欢的视频
     */
    @Override
    public FfwyVideoLove selectFfwyVideoLoveById(Long userId)
    {
        return ffwyVideoLoveMapper.selectFfwyVideoLoveById(userId);
    }

    /**
     * 查询我喜欢的视频列表
     * 
     * @param ffwyVideoLove 我喜欢的视频
     * @return 我喜欢的视频
     */
    @Override
    public List<FfwyVideoLove> selectFfwyVideoLoveList(FfwyVideoLove ffwyVideoLove)
    {
        return ffwyVideoLoveMapper.selectFfwyVideoLoveList(ffwyVideoLove);
    }

    /**
     * 新增我喜欢的视频
     * 
     * @param ffwyVideoLove 我喜欢的视频
     * @return 结果
     */
    @Override
    public int insertFfwyVideoLove(FfwyVideoLove ffwyVideoLove)
    {
        ffwyVideoLove.setCreateTime(DateUtils.getNowDate());
        return ffwyVideoLoveMapper.insertFfwyVideoLove(ffwyVideoLove);
    }

    /**
     * 修改我喜欢的视频
     * 
     * @param ffwyVideoLove 我喜欢的视频
     * @return 结果
     */
    @Override
    public int updateFfwyVideoLove(FfwyVideoLove ffwyVideoLove)
    {
        return ffwyVideoLoveMapper.updateFfwyVideoLove(ffwyVideoLove);
    }

    /**
     * 批量删除我喜欢的视频
     * 
     * @param userIds 需要删除的我喜欢的视频ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoLoveByIds(Long[] userIds)
    {
        return ffwyVideoLoveMapper.deleteFfwyVideoLoveByIds(userIds);
    }

    /**
     * 删除我喜欢的视频信息
     * 
     * @param userId 我喜欢的视频ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoLoveById(Long userId)
    {
        return ffwyVideoLoveMapper.deleteFfwyVideoLoveById(userId);
    }
}
