package com.ruoyi.back.mapper;

import java.util.List;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.system.domain.video.FfwyAppVideo;
import com.ruoyi.system.domain.video.FfwyVideoNotlove;
import com.ruoyi.system.domain.video.FfwyVideoTag;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 视频Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Component
public interface FfwyVideoMapper 
{
    /**
     * 查询视频
     * 
     * @param videoId 视频ID
     * @return 视频
     */
    public FfwyVideo selectFfwyVideoById(Long videoId);

    /**
     * 查询视频
     *
     * @param videoId 视频ID
     * @return 视频
     */
    public FfwyVideo selectFfwyVideoByIdAndUserId(@Param("videoId") Long videoId,@Param("userId") Long userId);

    /**
     * 根据userId查询视频
     *
     * @param userId 用于ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByUserId(Long userId);
    /**
     * 根据userId查询我喜欢视频
     *
     * @param userId 用户ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByLike(
            @Param("userId") Long userId,
            @Param("num") Long num,
            @Param("size") Long size);
    /**
     * 根据userId查询私有视频
     *
     * @param userId 用户ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByPrivate(Long userId);
    /**
     * 根据userId查詢收藏视频
     *
     * @param userId 用户ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByStroe(Long userId);

    /**
     * 查询视频列表
     * 
     * @param ffwyVideo 视频
     * @return 视频集合
     */
    public List<FfwyVideo> selectFfwyVideoList(FfwyVideo ffwyVideo);


    /**
     * 新增视频
     * 
     * @param ffwyVideo 视频
     * @return 结果
     */
    public int insertFfwyVideo(FfwyVideo ffwyVideo);

    /**
     * 新增我喜欢的视频
     *
     * @param videoId 视频id
     * @param userId 用户id
     * @return 结果
     */
    public int insertFfwyLoveVideo(@Param("videoId") Long videoId,@Param("userId") Long userId);
    /**
     * 新增我收藏的视频
     *
     * @param videoId 视频id
     * @param userId 用户id
     * @return 结果
     */
    public int insertFfwyStroeVideo(@Param("videoId") Long videoId,@Param("userId") Long userId);
    /**
     * 新增私有视频
     *
     * @param videoId 视频id
     * @param userId 用户id
     * @return 结果
     */
    public int insertFfwyPirvateVideo(@Param("videoId") Long videoId,@Param("userId") Long userId);

    /**
     * 修改视频
     * 
     * @param ffwyVideo 视频
     * @return 结果
     */
    public int updateFfwyVideo(FfwyVideo ffwyVideo);

    /**
     * 删除视频
     * 
     * @param videoId 视频ID
     * @return 结果
     */
    public int deleteFfwyVideoById(Long videoId);

    /**
     * 批量删除视频
     * 
     * @param videoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyVideoByIds(Long[] videoIds);

    /**
     * 私密视频统计数量
     *
     * @param userId 需要查询的数据ID
     * @return 结果
     */
    public int privateVideoCount(Long userId);

    /**
     * 喜欢视频视频统计数量
     *
     * @param userId 需要查询的数据ID
     * @return 结果
     */
    public int loveVideoCount(Long userId);

    /**
     * 删除喜歡的視頻
     *
     * @param videoId 视频ID
     * @return 结果
     */
    public int deleteLikeVideo(@Param("videoId") Long videoId,@Param("userId") Long userId);

    /**
     * 删除收藏的視頻
     *
     * @param videoId 视频ID
     * @return 结果
     */
    public int deleteStroeVideo(@Param("videoId") Long videoId,@Param("userId") Long userId);


    List<FfwyVideo> selectFfwyvideoByphth(FfwyAppVideo ffwyAppVideo);

    void insertvideotag(FfwyVideoTag ffwyVideoTag);

    List<FfwyVideoTag> selectFfwyvideoTag(Integer videoId);

    int insertFfwyNotLoveVideo(@Param("FfwyVideoNotlove") FfwyVideoNotlove ffwyVideoNotlove);

    List<FfwyVideoNotlove> selectFfwyVideoNotlove(Integer userId);

    int deleteFfwyVideoNotlove(Integer userId);

}
