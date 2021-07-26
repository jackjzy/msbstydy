package com.ruoyi.system.mapper.video;

import com.ruoyi.system.domain.video.FfwyStoreVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface FfwyStoreVideoMapper 
{
    /**
     * 查询视频收藏
     * 
     * @param storeVideoId 视频收藏ID
     * @return 视频收藏
     */
    public FfwyStoreVideo selectFfwyStoreVideoById(Long storeVideoId);

    /**
     * 查询视频收藏列表
     * 
     * @param ffwyStoreVideo 视频收藏
     * @return 视频收藏集合
     */
    public List<FfwyStoreVideo> selectFfwyStoreVideoList(FfwyStoreVideo ffwyStoreVideo);

    /**
     * 新增视频收藏
     * 
     * @param ffwyStoreVideo 视频收藏
     * @return 结果
     */
    public int insertFfwyStoreVideo(FfwyStoreVideo ffwyStoreVideo);

    /**
     * 修改视频收藏
     * 
     * @param ffwyStoreVideo 视频收藏
     * @return 结果
     */
    public int updateFfwyStoreVideo(FfwyStoreVideo ffwyStoreVideo);

    /**
     * 删除视频收藏
     * 
     * @param storeVideoId 视频收藏ID
     * @return 结果
     */
    public int deleteFfwyStoreVideoById(Long storeVideoId);

    /**
     * 批量删除视频收藏
     * 
     * @param storeVideoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyStoreVideoByIds(Long[] storeVideoIds);

    int deleteFfwyStoreVideo(@Param("videoId") Integer videoId,@Param("id") Long id);
}
