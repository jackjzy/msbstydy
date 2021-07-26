package com.ruoyi.system.service;


import com.ruoyi.system.domain.video.FfwyVideoLove;

import java.util.List;

/**
 * 我喜欢的视频Service接口
 * 
 * @author ruoyi
 * @date 2021-07-08
 */
public interface IFfwyVideoLoveService 
{
    /**
     * 查询我喜欢的视频
     * 
     * @param userId 我喜欢的视频ID
     * @return 我喜欢的视频
     */
    public FfwyVideoLove selectFfwyVideoLoveById(Long userId);

    /**
     * 查询我喜欢的视频列表
     * 
     * @param ffwyVideoLove 我喜欢的视频
     * @return 我喜欢的视频集合
     */
    public List<FfwyVideoLove> selectFfwyVideoLoveList(FfwyVideoLove ffwyVideoLove);

    /**
     * 新增我喜欢的视频
     * 
     * @param ffwyVideoLove 我喜欢的视频
     * @return 结果
     */
    public int insertFfwyVideoLove(FfwyVideoLove ffwyVideoLove);

    /**
     * 修改我喜欢的视频
     * 
     * @param ffwyVideoLove 我喜欢的视频
     * @return 结果
     */
    public int updateFfwyVideoLove(FfwyVideoLove ffwyVideoLove);

    /**
     * 批量删除我喜欢的视频
     * 
     * @param userIds 需要删除的我喜欢的视频ID
     * @return 结果
     */
    public int deleteFfwyVideoLoveByIds(Long[] userIds);

    /**
     * 删除我喜欢的视频信息
     * 
     * @param userId 我喜欢的视频ID
     * @return 结果
     */
    public int deleteFfwyVideoLoveById(Long userId);
}
