package com.ruoyi.back.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.back.domain.queryentity.QueryMsg;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.back.mapper.FfwyMsgMapper;
import com.ruoyi.back.domain.FfwyMsg;
import com.ruoyi.back.service.IFfwyMsgService;

/**
 * 消息管理Service业务层处理
 * 
 * @author wemem
 * @date 2021-04-23
 */
@Service
public class FfwyMsgServiceImpl implements IFfwyMsgService 
{
    @Autowired
    private FfwyMsgMapper ffwyMsgMapper;

    /**
     * 查询消息管理
     * 
     * @param msgId 消息管理ID
     * @return 消息管理
     */
    @Override
    public FfwyMsg selectFfwyMsgById(Long msgId)
    {
        return ffwyMsgMapper.selectFfwyMsgById(msgId);
    }

    /**
     * 查询消息管理列表
     * 
     * @param ffwyMsg 消息管理
     * @return 消息管理
     */
    @Override
    public List<FfwyMsg> selectFfwyMsgList(QueryMsg ffwyMsg)
    {
        return ffwyMsgMapper.selectFfwyMsgList(ffwyMsg);
    }

    /**
     * 新增消息管理
     * 
     * @param ffwyMsg 消息管理
     * @return 结果
     */
    @Override
    public int insertFfwyMsg(FfwyMsg ffwyMsg)
    {
        ffwyMsg.setReceiveStatus((long) 0);
        ffwyMsg.setMsgTime(new Date());
        return ffwyMsgMapper.insertFfwyMsg(ffwyMsg);
    }

    /**
     * 修改消息管理
     * 
     * @param ffwyMsg 消息管理
     * @return 结果
     */
    @Override
    public int updateFfwyMsg(FfwyMsg ffwyMsg)
    {
        return ffwyMsgMapper.updateFfwyMsg(ffwyMsg);
    }

    /**
     * 批量删除消息管理
     * 
     * @param msgIds 需要删除的消息管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMsgByIds(Long[] msgIds)
    {
        return ffwyMsgMapper.deleteFfwyMsgByIds(msgIds);
    }

    /**
     * 删除消息管理信息
     * 
     * @param msgId 消息管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMsgById(Long msgId)
    {
        return ffwyMsgMapper.deleteFfwyMsgById(msgId);
    }
}
