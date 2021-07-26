package com.ruoyi.back.service;

import com.ruoyi.back.domain.FfwyVideoHots;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
public interface IFfwyVideoHotsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param videoHotId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyVideoHots selectFfwyVideoHotsById(Long videoHotId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyVideoHots 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyVideoHots> selectFfwyVideoHotsList(FfwyVideoHots ffwyVideoHots);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyVideoHots 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyVideoHots(FfwyVideoHots ffwyVideoHots);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyVideoHots 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyVideoHots(FfwyVideoHots ffwyVideoHots);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param videoHotIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoHotsByIds(Long[] videoHotIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param videoHotId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoHotsById(Long videoHotId);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param videoHotId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoHotsByVideoIdAndhotId(Long videoid,Long videoHotId);
}
