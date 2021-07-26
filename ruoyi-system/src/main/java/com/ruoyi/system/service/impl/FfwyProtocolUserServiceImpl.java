package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.protocol.FfwyProtocolUser;
import com.ruoyi.system.mapper.protocol.FfwyProtocolUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyProtocolUserService;

/**
 * 记录用户是否同意过用户协议Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
@Service
public class FfwyProtocolUserServiceImpl implements IFfwyProtocolUserService 
{
    @Autowired
    private FfwyProtocolUserMapper ffwyProtocolUserMapper;

    /**
     * 查询记录用户是否同意过用户协议
     * 
     * @param protocolUserId 记录用户是否同意过用户协议ID
     * @return 记录用户是否同意过用户协议
     */
    @Override
    public FfwyProtocolUser selectFfwyProtocolUserById(Integer protocolUserId)
    {
        return ffwyProtocolUserMapper.selectFfwyProtocolUserById(protocolUserId);
    }

    /**
     * 查询记录用户是否同意过用户协议列表
     * 
     * @param ffwyProtocolUser 记录用户是否同意过用户协议
     * @return 记录用户是否同意过用户协议
     */
    @Override
    public List<FfwyProtocolUser> selectFfwyProtocolUserList(FfwyProtocolUser ffwyProtocolUser)
    {
        return ffwyProtocolUserMapper.selectFfwyProtocolUserList(ffwyProtocolUser);
    }

    /**
     * 新增记录用户是否同意过用户协议
     * 
     * @param ffwyProtocolUser 记录用户是否同意过用户协议
     * @return 结果
     */
    @Override
    public int insertFfwyProtocolUser(FfwyProtocolUser ffwyProtocolUser)
    {
        ffwyProtocolUser.setCreateTime(DateUtils.getNowDate());
        return ffwyProtocolUserMapper.insertFfwyProtocolUser(ffwyProtocolUser);
    }

    /**
     * 修改记录用户是否同意过用户协议
     * 
     * @param ffwyProtocolUser 记录用户是否同意过用户协议
     * @return 结果
     */
    @Override
    public int updateFfwyProtocolUser(FfwyProtocolUser ffwyProtocolUser)
    {
        return ffwyProtocolUserMapper.updateFfwyProtocolUser(ffwyProtocolUser);
    }

    /**
     * 批量删除记录用户是否同意过用户协议
     * 
     * @param protocolUserIds 需要删除的记录用户是否同意过用户协议ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProtocolUserByIds(Integer[] protocolUserIds)
    {
        return ffwyProtocolUserMapper.deleteFfwyProtocolUserByIds(protocolUserIds);
    }

    /**
     * 删除记录用户是否同意过用户协议信息
     * 
     * @param protocolUserId 记录用户是否同意过用户协议ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProtocolUserById(Integer protocolUserId)
    {
        return ffwyProtocolUserMapper.deleteFfwyProtocolUserById(protocolUserId);
    }
}
