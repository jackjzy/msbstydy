package com.ruoyi.system.service;

import com.ruoyi.system.domain.admin.FfwyUserRoot;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
public interface IFfwyUserRootService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyUserRoot selectFfwyUserRootById(Long userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyUserRoot 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyUserRoot> selectFfwyUserRootList(FfwyUserRoot ffwyUserRoot);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyUserRoot 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyUserRoot(FfwyUserRoot ffwyUserRoot);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyUserRoot 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyUserRoot(FfwyUserRoot ffwyUserRoot);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyUserRootByIds(Long[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyUserRootById(Long userId);
}
