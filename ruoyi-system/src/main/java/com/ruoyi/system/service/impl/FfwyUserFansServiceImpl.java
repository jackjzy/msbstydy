package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.admin.FfwyUserFans;
import com.ruoyi.system.mapper.admin.FfwyUserFansMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyUserFansService;

/**
 * 粉丝Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-12
 */
@Service
public class FfwyUserFansServiceImpl implements IFfwyUserFansService 
{
    @Autowired
    private FfwyUserFansMapper ffwyUserFansMapper;

    /**
     * 查询粉丝
     * 
     * @param userId 粉丝ID
     * @return 粉丝
     */
    @Override
    public FfwyUserFans selectFfwyUserFansById(Long userId)
    {
        return ffwyUserFansMapper.selectFfwyUserFansById(userId);
    }

    /**
     * 查询粉丝列表
     * 
     * @param ffwyUserFans 粉丝
     * @return 粉丝
     */
    @Override
    public List<FfwyUserFans> selectFfwyUserFansList(FfwyUserFans ffwyUserFans)
    {
        return ffwyUserFansMapper.selectFfwyUserFansList(ffwyUserFans);
    }

    /**
     * 新增粉丝
     * 
     * @param ffwyUserFans 粉丝
     * @return 结果
     */
    @Override
    public int insertFfwyUserFans(FfwyUserFans ffwyUserFans)
    {
        return ffwyUserFansMapper.insertFfwyUserFans(ffwyUserFans);
    }

    /**
     * 修改粉丝
     * 
     * @param ffwyUserFans 粉丝
     * @return 结果
     */
    @Override
    public int updateFfwyUserFans(FfwyUserFans ffwyUserFans)
    {
        return ffwyUserFansMapper.updateFfwyUserFans(ffwyUserFans);
    }

    /**
     * 批量删除粉丝
     * 
     * @param userIds 需要删除的粉丝ID
     * @return 结果
     */
    @Override
    public int deleteFfwyUserFansByIds(Long[] userIds)
    {
        return ffwyUserFansMapper.deleteFfwyUserFansByIds(userIds);
    }

    /**
     * 删除粉丝信息
     * 
     * @param userId 粉丝ID
     * @return 结果
     */
    @Override
    public int deleteFfwyUserFansById(Long userId)
    {
        return ffwyUserFansMapper.deleteFfwyUserFansById(userId);
    }
}
