package com.ruoyi.back.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.back.mapper.FfwyMsgUserMapper;
import com.ruoyi.back.domain.FfwyMsgUser;
import com.ruoyi.back.service.IFfwyMsgUserService;

/**
 * 消息发送的用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
public class FfwyMsgUserServiceImpl implements IFfwyMsgUserService 
{
    @Autowired
    private FfwyMsgUserMapper ffwyMsgUserMapper;

    /**
     * 查询消息发送的用户
     * 
     * @param msgId 消息发送的用户ID
     * @return 消息发送的用户
     */
    @Override
    public FfwyMsgUser selectFfwyMsgUserById(Long msgId)
    {
        return ffwyMsgUserMapper.selectFfwyMsgUserById(msgId);
    }

    /**
     * 查询消息发送的用户列表
     * 
     * @param ffwyMsgUser 消息发送的用户
     * @return 消息发送的用户
     */
    @Override
    public List<FfwyMsgUser> selectFfwyMsgUserList(FfwyMsgUser ffwyMsgUser)
    {
        return ffwyMsgUserMapper.selectFfwyMsgUserList(ffwyMsgUser);
    }

    /**
     * 新增消息发送的用户
     * 
     * @param ffwyMsgUser 消息发送的用户
     * @return 结果
     */
    @Override
    public int insertFfwyMsgUser(FfwyMsgUser ffwyMsgUser)
    {
        return ffwyMsgUserMapper.insertFfwyMsgUser(ffwyMsgUser);
    }

    /**
     * 修改消息发送的用户
     * 
     * @param ffwyMsgUser 消息发送的用户
     * @return 结果
     */
    @Override
    public int updateFfwyMsgUser(FfwyMsgUser ffwyMsgUser)
    {
        return ffwyMsgUserMapper.updateFfwyMsgUser(ffwyMsgUser);
    }

    /**
     * 批量删除消息发送的用户
     * 
     * @param msgIds 需要删除的消息发送的用户ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMsgUserByIds(Long[] msgIds)
    {
        return ffwyMsgUserMapper.deleteFfwyMsgUserByIds(msgIds);
    }

    /**
     * 删除消息发送的用户信息
     * 
     * @param msgId 消息发送的用户ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMsgUserById(Long msgId)
    {
        return ffwyMsgUserMapper.deleteFfwyMsgUserById(msgId);
    }
}
