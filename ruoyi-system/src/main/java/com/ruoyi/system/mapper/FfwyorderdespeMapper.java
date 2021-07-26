package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.vo.FfwyorderdespeVo;

import java.util.List;

public interface FfwyorderdespeMapper {
    List<FfwyorderdespeVo> selectFfwyorderdetailsByseales(Long userId);

    List<FfwyorderdespeVo> selectFfwyorderdetails(String orderNumber);
}
