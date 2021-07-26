package com.ruoyi.back.service;

import java.util.List;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;

import org.springframework.web.multipart.MultipartFile;

/**
 * 视频Service接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface IFfwyVideoService 
{
    /**
     * 查询视频
     * 
     * @param videoId 视频ID
     * @return 视频
     */
    public FfwyVideo selectFfwyVideoById(Long videoId);

    /**
     * 查询视频以及状态
     *
     * @param videoId 视频ID
     * @return 视频
     */
    public FfwyVideo selectFfwyVideoByOne(Long videoId,Long userId);

    /**
     * 查询视频以及是否點讚和收藏
     *
     * @param videoId 视频ID
     * @param userId 用戶id
     * @return 视频
     */
    public FfwyVideo selectFfwyVideoByIdAndUserId(Long videoId,Long userId);
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
     * 查询视频列表
     * 
     * @param ffwyVideo 视频
     * @return 视频集合
     */

    public List<FfwyVideo> selectFfwyVideoList(FfwyVideo ffwyVideo);

    /**
     * 根据userId查询视频
     *
     * @param userId 用于ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByUserId(Long userId);
    /**
     * 根据userId查詢收藏视频
     *
     * @param userId 用户ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByStroe(Long userId);
    /**
     * 根据userId查询我喜欢视频
     *
     * @param userId 用户ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByLike(Long userId,Long pageNum,Long pageSize);
    /**
     * 根据userId查询私有视频
     *
     * @param userId 用户ID
     * @return 视频
     */
    public List<FfwyVideo> selectFfwyVideoByPrivate(Long userId);

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
    public int insertFfwyLoveVideo(Long videoId,  Long userId);
    /**
     * 新增私有视频
     *
     * @param videoId 视频id
     * @param userId 用户id
     * @return 结果
     */
    public int insertFfwyPirvateVideo( Long videoId, Long userId);
    /**
     * 新增我收藏的视频
     *
     * @param videoId 视频id
     * @param userId 用户id
     * @return 结果
     */
    public int insertFfwyStroeVideo( Long videoId, Long userId);

    /**
     * 修改视频
     * 
     * @param ffwyVideo 视频
     * @return 结果
     */
    public int updateFfwyVideo(FfwyVideo ffwyVideo);

    /**
     * 批量删除视频
     * 
     * @param videoIds 需要删除的视频ID
     * @return 结果
     */
    public int deleteFfwyVideoByIds(Long[] videoIds);

    /**
     * 删除视频信息
     * 
     * @param videoId 视频ID
     * @return 结果
     */
    public int deleteFfwyVideoById(Long videoId);

    /**
     * 點讚
     *
     * @param videoId 视频ID
     * @param userId 用戶ID
     * @return 结果
     */
    public long videoAddLike(Long videoId,Long userId);


    /**
     * 取消點讚
     *
     * @param videoId 视频ID
     * @param userId 用戶ID
     * @return 结果
     */
    public long videoRemoveLike(Long videoId,Long userId);

    /**
     * 删除收藏的視頻
     *
     * @param videoId 视频ID
     * @return 结果
     */
    public int deleteStroeVideo( Long videoId, Long userId);


    List<FfwyVideo> selectFfwyvideoByphth(Long videoId, Long id);

    int insertFfwyVideoandTag(FfwyVideo ffwyVideo,Long userId);

    int insertFfwyVideoanduserid(FfwyVideo ffwyvideo, Long id);

    int insertFfwyNotLoveVideo(Integer userId, Integer videoId);

    List<FfwyProduct> selectFfwyproductList(Long userId);

}
