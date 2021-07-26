package com.ruoyi.back.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.back.constant.Comment;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.video.FfwyAppVideo;
import com.ruoyi.system.domain.video.FfwyVideoNotlove;
import com.ruoyi.system.domain.video.FfwyVideoTag;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.ruoyi.system.mapper.video.FfwyVideoCommentMapper;
import com.ruoyi.system.service.impl.FfwySearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import com.ruoyi.back.mapper.FfwyVideoMapper;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;

/**
 * 视频Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Service
public class FfwyVideoServiceImpl implements IFfwyVideoService 
{
    @Autowired
    private FfwyVideoMapper ffwyVideoMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private FfwyShopMapper ffwyShopMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwyProductMapper ffwyProductMapper;

    @Autowired
    private FfwyVideoCommentMapper ffwyVideoCommentMapper;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    @Autowired
    private FfwySearchServiceImpl ffwySearchService;
    /**
     * 查询视频
     * 
     * @param videoId 视频ID
     * @return 视频
     */
    @Override
    public FfwyVideo selectFfwyVideoById(Long videoId)
    {

        return ffwyVideoMapper.selectFfwyVideoById(videoId);
    }

    @Override
    public FfwyVideo selectFfwyVideoByOne(Long videoId, Long userId) {
        return setStatus(videoId,userId);
    }

    @Override
    public FfwyVideo selectFfwyVideoByIdAndUserId(Long videoId, Long userId) {
        FfwyVideo ffwyVideo = ffwyVideoMapper.selectFfwyVideoById(videoId);
        SetOperations<String, String> set = redisTemplate.opsForSet();
        ffwyVideo.setLikes(set.isMember(Comment.VIDEO_ADN_USER+userId,videoId.toString()));
        FfwyVideo ffwyVideo1 = ffwyVideoMapper.selectFfwyVideoByIdAndUserId(videoId, userId);
        ffwyVideo.setStroe(null==ffwyVideo1);
        return ffwyVideo;
    }

    @Override
    public int privateVideoCount(Long userId) {
        return ffwyVideoMapper.privateVideoCount(userId);
    }

    @Override
    public int loveVideoCount(Long userId) {
        return ffwyVideoMapper.loveVideoCount(userId);
    }

    /**
     * 查询视频列表
     * 
     * @param ffwyVideo 视频
     * @return 视频
     */
    @Override
    public List<FfwyVideo> selectFfwyVideoList(FfwyVideo ffwyVideo)
    {

        List<FfwyVideo> list = ffwyVideoMapper.selectFfwyVideoList(ffwyVideo);
        if (ffwyVideo!=null){
            if ("1".equals(ffwyVideo.getVideoStatus())) {

                return list;
            }else{
                return setLike(list);
            }
        }
        return list;
    }
    /**
     * 个根据userId查询视频列表
     *
     * @param userId 用户id
     * @return 视频
     */

    @Override
    public List<FfwyVideo> selectFfwyVideoByUserId(Long userId) {


        List<FfwyVideo> ffwyVideos = ffwyVideoMapper.selectFfwyVideoByUserId(userId);


        //添加视频点赞数,评论数
        return setLike(ffwyVideos);
    }

    @Override
    public List<FfwyVideo> selectFfwyVideoByStroe(Long userId) {
        List<FfwyVideo> list = ffwyVideoMapper.selectFfwyVideoByStroe(userId);

        return setLike(list);
    }

    /**
     * 查看喜欢
     *
     * @param userId 视频
     * @return 结果
     */


    @Override
    public List<FfwyVideo> selectFfwyVideoByLike(Long userId,Long pageNum,Long pageSize) {

        List<FfwyVideo> ffwyVideos = ffwyVideoMapper.selectFfwyVideoByLike(userId,(pageNum-1)*pageSize,pageSize);

        return  setStatusAll(ffwyVideos,userId);
    //    return ffwyVideos;
    }
    /**
     * 查看私有视频
     *
     * @param userId 视频
     * @return 结果
     */

    @Override
    public List<FfwyVideo> selectFfwyVideoByPrivate(Long userId) {
        return setLike(ffwyVideoMapper.selectFfwyVideoByPrivate(userId));
    }

    /**
     * 新增视频
     * 
     * @param ffwyVideo 视频
     * @return 结果
     */
    @Override
    public int insertFfwyVideo(FfwyVideo ffwyVideo)
    {
        ffwyVideo.setCreateTime(DateUtils.getNowDate());
        return ffwyVideoMapper.insertFfwyVideo(ffwyVideo);
    }

    @Override
    public int insertFfwyLoveVideo(Long videoId, Long userId) {
        return ffwyVideoMapper.insertFfwyLoveVideo(videoId,userId);
    }

    @Override
    public int insertFfwyPirvateVideo(Long videoId, Long userId) {
        return ffwyVideoMapper.insertFfwyPirvateVideo(videoId, userId);
    }

    @Override
    public int insertFfwyStroeVideo(Long videoId, Long userId) {
        return ffwyVideoMapper.insertFfwyStroeVideo(videoId, userId);
    }

    /**
     * 修改视频
     * 
     * @param ffwyVideo 视频
     * @return 结果
     */
    @Override
    public int updateFfwyVideo(FfwyVideo ffwyVideo)
    {
        return ffwyVideoMapper.updateFfwyVideo(ffwyVideo);
    }

    /**
     * 批量删除视频
     * 
     * @param videoIds 需要删除的视频ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoByIds(Long[] videoIds)
    {
        return ffwyVideoMapper.deleteFfwyVideoByIds(videoIds);
    }

    /**
     * 删除视频信息
     * 
     * @param videoId 视频ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoById(Long videoId)
    {
        return ffwyVideoMapper.deleteFfwyVideoById(videoId);
    }

    @Override
    public long videoAddLike(Long videoId, Long userId) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        FfwyVideo ffwyVideo = ffwyVideoMapper.selectFfwyVideoById(videoId);
        long sum =0;

        //判断当前用户是否给该视频点过赞
        if (!set.isMember(Comment.VIDEO_ADN_USER+userId,videoId.toString())){
            //点赞操作
            Long row1 = set.add(Comment.VIDEO_ADN_USER + userId, videoId.toString());
            ffwyVideoMapper.insertFfwyLoveVideo(videoId,userId);
            sum=sum+row1;

            //判断该视频的用户是否有人点赞
            if (!redisTemplate.hasKey(Comment.USER_LIKE+ffwyVideo.getUserId())){
                //没有点过赞则设置点赞数为1
                ops.set(Comment.USER_LIKE+ffwyVideo.getUserId(),"1");
                sum++;

                ops.set(Comment.VIDEO_LIKE+videoId,"1");
                sum++;
                return sum;

            }else {
                //点过赞则进行自增
                Long aLong = ops.increment(Comment.USER_LIKE + ffwyVideo.getUserId());
                sum=sum+aLong;

                //判断当前视频是否有人点赞
                if (!redisTemplate.hasKey(Comment.VIDEO_LIKE+videoId)){
                    //没点赞则设置点赞数为1
                    ops.set(Comment.VIDEO_LIKE+videoId,"1");
                    sum++;

                }else {
                    //点过赞则进行自增
                    Long increment = ops.increment(Comment.VIDEO_LIKE + videoId);
                    sum=sum+increment;

                }

            }

        }else {
            //当前用户已经点过赞了不能重复点赞
            //移除点赞记录
            Long remove = set.remove(Comment.VIDEO_ADN_USER + userId, videoId.toString());
            //对该视频点赞-1

            Long decrement = ops.decrement(Comment.VIDEO_LIKE + videoId);
            //对该视频的用户点赞-1
            Long decrement1 = ops.decrement(Comment.USER_LIKE + ffwyVideo.getUserId());
            int i = ffwyVideoMapper.deleteLikeVideo(videoId, userId);
            return i;
        }

        return sum;
    }

    @Override
    public long videoRemoveLike(Long videoId, Long userId) {

        SetOperations<String, String> set = redisTemplate.opsForSet();
        ValueOperations<String, String> ops = redisTemplate.opsForValue();



        FfwyVideo ffwyVideo = ffwyVideoMapper.selectFfwyVideoById(videoId);
        //移除点赞记录
        Long remove = set.remove(Comment.VIDEO_ADN_USER + userId, videoId.toString());
        //对该视频点赞-1

        Long decrement = ops.decrement(Comment.VIDEO_LIKE + videoId);
        //对该视频的用户点赞-1
        Long decrement1 = ops.decrement(Comment.USER_LIKE + ffwyVideo.getUserId());
        int i = ffwyVideoMapper.deleteLikeVideo(videoId, userId);
        return remove+decrement+decrement1+(long)i;
    }

    @Override
    public int deleteStroeVideo(Long videoId, Long userId) {
        return ffwyVideoMapper.deleteStroeVideo(videoId, userId);
    }

    @Override
    public List<FfwyVideo> selectFfwyvideoByphth(Long videoId, Long id) {
        FfwyAppVideo ffwyAppVideo = new FfwyAppVideo();
        ffwyAppVideo.setVideoId(videoId);
        ffwyAppVideo.setUserId(id);
        List<FfwyVideo> ffwyVideos = ffwyVideoMapper.selectFfwyvideoByphth(ffwyAppVideo);
        return ffwyVideos;
    }

    //App发布视频
    @Override
    public int insertFfwyVideoandTag(FfwyVideo ffwyVideo,  Long userId) {
        ffwyVideo.setUserId(userId);
        ffwyVideo.setCreateTime(new Date());
        int i = ffwyVideoMapper.insertFfwyVideo(ffwyVideo);

        FfwyVideoTag ffwyVideoTag = new FfwyVideoTag();
        List<String> tags = ffwyVideo.getTags();
        for (int i1 = 0; i1 < tags.size(); i1++) {
                ffwyVideoTag.setVideoId(ffwyVideo.getVideoId().intValue());
                ffwyVideoTag.setTagId(Integer.valueOf(tags.get(i1)));
                ffwyVideoMapper.insertvideotag(ffwyVideoTag);
            }
        ffwySearchService.importAllvideo(ffwyVideo.getVideoId());

        return i;
    }

    @Override
    public int insertFfwyVideoanduserid(FfwyVideo ffwyvideo, Long id) {
        ffwyvideo.setUserId(id);
        ffwyvideo.setCreateTime(DateUtils.getNowDate());
        return ffwyVideoMapper.insertFfwyVideo(ffwyvideo);
    }

    @Override
    public int insertFfwyNotLoveVideo(Integer userId, Integer videoId) {
        List<FfwyVideoTag> ffwyVideoTags = ffwyVideoMapper.selectFfwyvideoTag(videoId);
        int i = 0;
        if (ffwyVideoTags.size() > 0) {
            List<FfwyVideoNotlove> ffwyVideoNotloves = ffwyVideoMapper.selectFfwyVideoNotlove(userId);
            if (ffwyVideoNotloves.size()>0){
                ffwyVideoMapper.deleteFfwyVideoNotlove(userId);
            }
            for (FfwyVideoTag ffwyVideoTag : ffwyVideoTags) {
                i++;
                FfwyVideoNotlove ffwyVideoNotlove = new FfwyVideoNotlove();
                ffwyVideoNotlove.setTagId(ffwyVideoTag.getTagId());
                ffwyVideoNotlove.setUserId(userId);
                ffwyVideoMapper.insertFfwyNotLoveVideo(ffwyVideoNotlove);
            }

        }
        return i;
    }

    @Override
    public List<FfwyProduct> selectFfwyproductList(Long userId) {
        //根据用户Id查询shopId
        FfwyShop ffwyShop = ffwyShopMapper.selectFfwyByUserId(userId);
        if (ffwyShop !=null){
            FfwyProduct ffwyProduct = new FfwyProduct();
            ffwyProduct.setShopId(ffwyShop.getShopId());
            List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyProductList(ffwyProduct);
            ffwyProducts.forEach(ffwyProduct1 -> {
                ffwyProduct1.setPhoto(prefixUrl+ffwyProduct1.getPhoto());
            });
            return ffwyProducts;
        } else {
            return null;
        }



    }


    /**
     * 添加视频点赞数
     *
     * @param ffwyVideos 视频ID
     * @return 结果
     */

    private List<FfwyVideo> setLike(List<FfwyVideo> ffwyVideos){
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        if (null!=ffwyVideos&&ffwyVideos.size()>0){
            for (FfwyVideo ffwyVideo : ffwyVideos) {
                if (redisTemplate.hasKey(Comment.VIDEO_LIKE+ffwyVideo.getVideoId())){
                    String s = opsForValue.get(Comment.VIDEO_LIKE + ffwyVideo.getVideoId());
                    ffwyVideo.setLike(Long.parseLong(s));
                }else {
                    ffwyVideo.setLike((long) 0);
                }
                    ffwyVideo.setCommentCount((long)ffwyVideoCommentMapper.selectFfwyVideoCommentCount(ffwyVideo.getVideoId()));


            }
        }

        return ffwyVideos;
    }


    private List<FfwyVideo> setStatusAll(List<FfwyVideo> ffwyVideos,Long userId){
        List<FfwyVideo> list=new ArrayList<>();
        ffwyVideos.forEach(ffwyVideo -> {
            FfwyVideo ffwyVideo1 = setStatus(ffwyVideo.getVideoId(), userId);
            list.add(ffwyVideo1);
        });
        return list;
    }



    private FfwyVideo setStatus(Long videoId,Long userId){
        FfwyVideo ffwyVideo = ffwyVideoMapper.selectFfwyVideoById(videoId);
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        SetOperations<String, String> set = redisTemplate.opsForSet();
        ffwyVideo.setLikes(set.isMember(Comment.VIDEO_ADN_USER+userId,videoId.toString()));
        FfwyVideo ffwyVideo1 = ffwyVideoMapper.selectFfwyVideoByIdAndUserId(videoId, userId);
        ffwyVideo.setStroe(null==ffwyVideo1);
        FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserAndFans(ffwyVideo.getUserId(), userId);
        ffwyVideo.setAttention(null==ffwyUser);
        if (redisTemplate.hasKey(Comment.VIDEO_LIKE+ffwyVideo.getVideoId())){
            String s = opsForValue.get(Comment.VIDEO_LIKE + ffwyVideo.getVideoId());
            ffwyVideo.setLike(Long.parseLong(s));
        }else {
            ffwyVideo.setLike((long) 0);
        }
        int i = ffwyVideoCommentMapper.selectFfwyVideoCommentCount(videoId);
        ffwyVideo.setCommentCount((long) i);
        return ffwyVideo;
    }
}
