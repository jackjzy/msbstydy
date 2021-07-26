package com.ruoyi.back.service;

import java.util.List;
import com.ruoyi.back.domain.FfwyMsgUser;

/**
 * 消息发送的用户Service接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IFfwyMsgUserService 
{
    /**
     * 查询消息发送的用户
     * 
     * @param msgId 消息发送的用户ID
     * @return 消息发送的用户
     */
    public FfwyMsgUser selectFfwyMsgUserById(Long msgId);

    /**
     * 查询消息发送的用户列表
     * 
     * @param ffwyMsgUser 消息发送的用户
     * @return 消息发送的用户集合
     */
    public List<FfwyMsgUser> selectFfwyMsgUserList(FfwyMsgUser ffwyMsgUser);

    /**
     * 新增消息发送的用户
     * 
     * @param ffwyMsgUser 消息发送的用户
     * @return 结果
     */
    public int insertFfwyMsgUser(FfwyMsgUser ffwyMsgUser);

    /**
     * 修改消息发送的用户
     * 
     * @param ffwyMsgUser 消息发送的用户
     * @return 结果
     */
    public int updateFfwyMsgUser(FfwyMsgUser ffwyMsgUser);

    /**
     * 批量删除消息发送的用户
     * 
     * @param msgIds 需要删除的消息发送的用户ID
     * @return 结果
     */
    public int deleteFfwyMsgUserByIds(Long[] msgIds);

    /**
     * 删除消息发送的用户信息
     * 
     * @param msgId 消息发送的用户ID
     * @return 结果
     */
    public int deleteFfwyMsgUserById(Long msgId);
}
