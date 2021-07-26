package com.ruoyi.system.service;

import com.ruoyi.system.domain.myhoust.FfwyMyhousts;
import com.ruoyi.system.domain.order.FfwyOrderClient;
import com.ruoyi.system.domain.vo.FfwyWorkplanVo;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
public interface IFfwyMyhoustsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param myhoustId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyMyhousts selectFfwyMyhoustsById(Long myhoustId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderClient 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyWorkplanVo> selectFfwyMyhoustsList(FfwyOrderClient ffwyOrderClient);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyMyhousts 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyMyhousts(FfwyMyhousts ffwyMyhousts);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyMyhousts 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyMyhousts(FfwyMyhousts ffwyMyhousts);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param myhoustIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyMyhoustsByIds(Long[] myhoustIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param myhoustId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyMyhoustsById(Long myhoustId);
}
