package com.ruoyi.back.service;

import com.ruoyi.back.domain.FfwyVideoHot;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-06-11
 */
public interface IFfwyVideoHotService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param hotId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyVideoHot selectFfwyVideoHotById(Integer hotId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyVideoHot 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyVideoHot> selectFfwyVideoHotList(FfwyVideoHot ffwyVideoHot);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyVideoHot 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyVideoHot(FfwyVideoHot ffwyVideoHot);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyVideoHot 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyVideoHot(FfwyVideoHot ffwyVideoHot);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param hotIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoHotByIds(Integer[] hotIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param hotId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoHotById(Integer hotId);
}
