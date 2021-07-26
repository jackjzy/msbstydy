package com.ruoyi.system.service;

import com.ruoyi.system.domain.search.FfwyVideoSearch;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;

import java.util.List;

/**
 * 视频Service接口
 * 
 * @author ruoyi
 * @date 2021-05-13
 */
public interface IFfwyAppVideoService 
{

    List<FfwyvideouserVo> selectFfwyvideoByhotVido(Long userId,List<FfwyvideouserVo> ffwyvideouserVoList);

    List<FfwyVideoSearch> selectFfwyvideoByhotlikeperson(Long userId);

    List<FfwyvideouserVo> selectFfwyvideohotVido();

    List<FfwyvideouserVo> selectFfwyvideolikesum(Long videoId);

    List<FfwyvideouserVo> selectFfwyvideocountcommentsum( Long videoId);

    int insertVideoForward(Long userId, Long videoId);

}
