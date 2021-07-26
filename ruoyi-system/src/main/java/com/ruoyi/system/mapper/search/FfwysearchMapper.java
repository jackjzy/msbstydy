package com.ruoyi.system.mapper.search;

import com.ruoyi.system.domain.search.FfwyVideoSearch;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;

import java.util.List;

public interface FfwysearchMapper {
    List<FfwyVideoSearch> selectvideoAll();

    List<FfwyVideoSearch> selectvideolikepersonAll(Long userId1);

    List<FfwyVideoSearch> selectvidedzsum(Long videoId);

    List<FfwyVideoSearch> selectvideo(Long videoId);
}
