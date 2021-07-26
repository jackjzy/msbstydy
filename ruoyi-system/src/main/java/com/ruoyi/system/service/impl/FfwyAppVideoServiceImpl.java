package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.admin.FfwyUserFans;
import com.ruoyi.system.domain.search.FfwyVideoSearch;
import com.ruoyi.system.domain.video.FfwyAppVideo;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;
import com.ruoyi.system.mapper.admin.FfwyUserFansMapper;
import com.ruoyi.system.mapper.search.FfwysearchMapper;
import com.ruoyi.system.mapper.video.FfwyAppVideoMapper;
import com.ruoyi.system.service.IFfwyAppVideoService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 视频Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-13
 */
@Service
public class FfwyAppVideoServiceImpl implements IFfwyAppVideoService
{
    @Resource
    private FfwyAppVideoMapper ffwyAppVideoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private FfwyUserFansMapper ffwyUserFansMapper;

    @Resource
    private FfwysearchMapper ffwysearchMapper;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    @Override
    public List<FfwyvideouserVo> selectFfwyvideoByhotVido(Long userId,List<FfwyvideouserVo> ffwyvideouserVos1) {
        SetOperations<String, String> stringStringSetOperations = stringRedisTemplate.opsForSet();
        List<FfwyvideouserVo> ffwyvideouserVoList = new ArrayList<>();
        if (ffwyvideouserVos1.size()>0){
            List<String> videoIds = new ArrayList<>();
            for (FfwyvideouserVo ffwyvideouserVo1 : ffwyvideouserVos1) {
                Integer videoId = ffwyvideouserVo1.getVideoId();
                videoIds.add(String.valueOf(videoId));
            }
            List<FfwyvideouserVo> ffwyvideouserVos = ffwyAppVideoMapper.selectFfwyvideoByhotVido(videoIds,userId);

            //ffwyvideouserVos.addAll(ffwyAppVideoMapper.selectFfwyvideoByNothotVido(videoIds,userId));
            for (FfwyvideouserVo ffwyvideouserVo : ffwyvideouserVos) {
                Boolean member = stringStringSetOperations.isMember("videoAndUser" + userId,
                        ffwyvideouserVo.getVideoId().toString());
                ffwyvideouserVo.setVideostuta(member);
                ffwyvideouserVo.setPhoto(prefixUrl+ffwyvideouserVo.getPhoto());
            }
            ffwyvideouserVoList.addAll(ffwyvideouserVos);
        }else {
            List<String> videoIds = new ArrayList<>();

            List<FfwyvideouserVo> ffwyvideouserVos = ffwyAppVideoMapper.selectFfwyvideoByNothotVido(videoIds,userId);

            for (FfwyvideouserVo ffwyvideouserVo : ffwyvideouserVos) {
                Boolean member = stringStringSetOperations.isMember("videoAndUser" + userId,
                        ffwyvideouserVo.getVideoId().toString());
                ffwyvideouserVo.setVideostuta(member);
                ffwyvideouserVo.setPhoto(prefixUrl+ffwyvideouserVo.getPhoto());
            }
            ffwyvideouserVoList.addAll(ffwyvideouserVos);
        }


        return ffwyvideouserVoList;

    }

    @Override
    public List<FfwyVideoSearch> selectFfwyvideoByhotlikeperson(Long userId) {
        FfwyUserFans ffwyUserFans = new FfwyUserFans();
        ffwyUserFans.setFansId(userId);
        List<FfwyVideoSearch> ffwyvideouserVos = new ArrayList<FfwyVideoSearch>();
        //根据当前用户Id查询自己关注的用户
        List<FfwyUserFans> ffwyUserFans1 = ffwyUserFansMapper.selectFfwyUserFansList(ffwyUserFans);
        //遍历自己关注的用户的信息
        if (StringUtils.isNotEmpty(ffwyUserFans1)) {
            for (FfwyUserFans userFans : ffwyUserFans1) {
                Long userId1 = userFans.getUserId();
                List<FfwyVideoSearch> ffwyVideoSearches = ffwysearchMapper.selectvideolikepersonAll(userId1);
                if (StringUtils.isNotEmpty(ffwyVideoSearches)) {
                    for (FfwyVideoSearch ffwyVideoSearch : ffwyVideoSearches) {
                        Long videoId = ffwyVideoSearch.getVideoId();
                        if (videoId != null) {
                            List<FfwyVideoSearch> selectvidedzsum = ffwysearchMapper.selectvidedzsum(videoId);
                            for (FfwyVideoSearch videoSearch : selectvidedzsum) {
                                Integer videoLove = videoSearch.getVideoLove();
                                ffwyVideoSearch.setVideoLove(videoLove);
                            }
                        } else {
                            ffwyVideoSearch.setVideoLove(0);
                        }
                    }

                }
                ffwyvideouserVos.addAll(ffwyVideoSearches);
            }
            for (FfwyVideoSearch ffwyvideouserVo : ffwyvideouserVos) {
                ffwyvideouserVo.setPhoto(prefixUrl + ffwyvideouserVo.getPhoto());
            }
        }

        return ffwyvideouserVos;
    }

    @Override
    public List<FfwyvideouserVo> selectFfwyvideohotVido() {
        List<FfwyvideouserVo> ffwyvideouserVos = ffwyAppVideoMapper.selectFfwyvideohotVido();
        for (FfwyvideouserVo ffwyvideouserVo : ffwyvideouserVos) {
            ffwyvideouserVo.setPhoto(prefixUrl+ffwyvideouserVo.getPhoto());
        }
        return ffwyvideouserVos;
    }

    @Override
    public List<FfwyvideouserVo> selectFfwyvideolikesum( Long videoId) {

        return ffwyAppVideoMapper.selectFfwyvideolikesum(videoId);
    }

    @Override
    public List<FfwyvideouserVo> selectFfwyvideocountcommentsum(Long videoId) {
        return ffwyAppVideoMapper.selectFfwyvideocountcommentsum(videoId);
    }

    @Override
    public int insertVideoForward(Long userId, Long videoId) {

        return ffwyAppVideoMapper.insertFfwyVideoForward(userId,videoId);

    }

}
