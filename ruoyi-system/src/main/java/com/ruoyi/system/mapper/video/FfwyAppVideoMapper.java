package com.ruoyi.system.mapper.video;

import com.ruoyi.system.domain.video.FfwyAppVideo;
import com.ruoyi.system.domain.video.FfwyVideoTag;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-13
 */
@Mapper
public interface FfwyAppVideoMapper 
{

    List<FfwyvideouserVo> selectFfwyvideoByhotVido(@Param("integers") List<String> integers,@Param("userId")Long userId);

    List<FfwyvideouserVo> selectFfwyvideoByNothotVido(@Param("integers") List<String> integers,@Param("userId")Long userId);


    List<FfwyvideouserVo> selectNotLove(Long userId);

    List<FfwyvideouserVo> selectFfwyvideohotVido();
    List<FfwyvideouserVo> selectFfwyVideoList(FfwyAppVideo ffwyAppVideo );

    List<FfwyVideoTag> selectTag(Long tagId);

    List<FfwyvideouserVo> selectFfwyvideolikesum(@Param("videoId") Long videoId);

    List<FfwyvideouserVo> selectFfwyvideocountcommentsum( @Param("videoId") Long videoId);


    int insertFfwyVideoForward(@Param("userId") Long userId,@Param("videoId") Long videoId);
}
