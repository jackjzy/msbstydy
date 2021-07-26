package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.video.FfwyStoreVideo;
import com.ruoyi.system.mapper.video.FfwyStoreVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyStoreVideoService;

/**
 * 视频收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Service
public class FfwyStoreVideoServiceImpl implements IFfwyStoreVideoService 
{
    @Autowired
    private FfwyStoreVideoMapper ffwyStoreVideoMapper;

    /**
     * 查询视频收藏
     * 
     * @param storeVideoId 视频收藏ID
     * @return 视频收藏
     */
    @Override
    public FfwyStoreVideo selectFfwyStoreVideoById(Long storeVideoId)
    {
        return ffwyStoreVideoMapper.selectFfwyStoreVideoById(storeVideoId);
    }

    /**
     * 查询视频收藏列表
     * 
     * @param ffwyStoreVideo 视频收藏
     * @return 视频收藏
     */
    @Override
    public List<FfwyStoreVideo> selectFfwyStoreVideoList(FfwyStoreVideo ffwyStoreVideo)
    {
        return ffwyStoreVideoMapper.selectFfwyStoreVideoList(ffwyStoreVideo);
    }


    /**
     * 新增视频收藏
     * 
     * @param videoId 视频收藏
     * @return 结果
     */
    @Override
    public int insertFfwyStoreVideo(Integer videoId,Long id)
    {

        FfwyStoreVideo ffwyStoreVideo = new FfwyStoreVideo();
        ffwyStoreVideo.setVideoId(videoId.longValue());
        ffwyStoreVideo.setUserId(id);
        List<FfwyStoreVideo> ffwyStoreVideos = ffwyStoreVideoMapper.selectFfwyStoreVideoList(ffwyStoreVideo);
        if (ffwyStoreVideos.size() ==0) {
            int i = ffwyStoreVideoMapper.insertFfwyStoreVideo(ffwyStoreVideo);
            return i;
        }

        return -1;
    }

    /**
     * 修改视频收藏
     * 
     * @param ffwyStoreVideo 视频收藏
     * @return 结果
     */
    @Override
    public int updateFfwyStoreVideo(FfwyStoreVideo ffwyStoreVideo)
    {
        return ffwyStoreVideoMapper.updateFfwyStoreVideo(ffwyStoreVideo);
    }

    /**
     * 批量删除视频收藏
     * 
     * @param storeVideoIds 需要删除的视频收藏ID
     * @return 结果
     */
    @Override
    public int deleteFfwyStoreVideoByIds(Long[] storeVideoIds)
    {
        return ffwyStoreVideoMapper.deleteFfwyStoreVideoByIds(storeVideoIds);
    }

    /**
     * 删除视频收藏信息
     * 
     * @param storeVideoId 视频收藏ID
     * @return 结果
     */
    @Override
    public int deleteFfwyStoreVideoById(Long storeVideoId)
    {
        return ffwyStoreVideoMapper.deleteFfwyStoreVideoById(storeVideoId);
    }

    @Override
    public int deleteFfwyStoreVideo(Integer videoId, Long id) {
        return ffwyStoreVideoMapper.deleteFfwyStoreVideo(videoId,id);
    }
}
