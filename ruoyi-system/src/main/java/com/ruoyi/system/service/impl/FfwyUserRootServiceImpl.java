package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.admin.FfwyUserRoot;
import com.ruoyi.system.mapper.admin.FfwyUserRootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyUserRootService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Service
public class FfwyUserRootServiceImpl implements IFfwyUserRootService 
{
    @Autowired
    private FfwyUserRootMapper ffwyUserRootMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyUserRoot selectFfwyUserRootById(Long userId)
    {
        return ffwyUserRootMapper.selectFfwyUserRootById(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyUserRoot 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyUserRoot> selectFfwyUserRootList(FfwyUserRoot ffwyUserRoot)
    {
        return ffwyUserRootMapper.selectFfwyUserRootList(ffwyUserRoot);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyUserRoot 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyUserRoot(FfwyUserRoot ffwyUserRoot)
    {
        return ffwyUserRootMapper.insertFfwyUserRoot(ffwyUserRoot);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyUserRoot 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyUserRoot(FfwyUserRoot ffwyUserRoot)
    {
        return ffwyUserRootMapper.updateFfwyUserRoot(ffwyUserRoot);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyUserRootByIds(Long[] userIds)
    {
        return ffwyUserRootMapper.deleteFfwyUserRootByIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyUserRootById(Long userId)
    {
        return ffwyUserRootMapper.deleteFfwyUserRootById(userId);
    }
}
