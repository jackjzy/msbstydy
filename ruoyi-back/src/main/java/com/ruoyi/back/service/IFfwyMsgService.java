package com.ruoyi.back.service;

import java.util.List;
import com.ruoyi.back.domain.FfwyMsg;
import com.ruoyi.back.domain.queryentity.QueryMsg;

/**
 * 消息管理Service接口
 * 
 * @author wemem
 * @date 2021-04-23
 */
public interface IFfwyMsgService 
{
    /**
     * 查询消息管理
     * 
     * @param msgId 消息管理ID
     * @return 消息管理
     */
    public FfwyMsg selectFfwyMsgById(Long msgId);

    /**
     * 查询消息管理列表
     * 
     * @param ffwyMsg 消息管理
     * @return 消息管理集合
     */
    public List<FfwyMsg> selectFfwyMsgList(QueryMsg ffwyMsg);

    /**
     * 新增消息管理
     * 
     * @param ffwyMsg 消息管理
     * @return 结果
     */
    public int insertFfwyMsg(FfwyMsg ffwyMsg);

    /**
     * 修改消息管理
     * 
     * @param ffwyMsg 消息管理
     * @return 结果
     */
    public int updateFfwyMsg(FfwyMsg ffwyMsg);

    /**
     * 批量删除消息管理
     * 
     * @param msgIds 需要删除的消息管理ID
     * @return 结果
     */
    public int deleteFfwyMsgByIds(Long[] msgIds);

    /**
     * 删除消息管理信息
     * 
     * @param msgId 消息管理ID
     * @return 结果
     */
    public int deleteFfwyMsgById(Long msgId);
}
