package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FfwyFeedbackReply;
import com.ruoyi.system.domain.Reply;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyFeedbackReplyService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param replyId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyFeedbackReply selectFfwyFeedbackReplyById(Long replyId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyFeedbackReply 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyFeedbackReply> selectFfwyFeedbackReplyList(FfwyFeedbackReply ffwyFeedbackReply);

    /**
     * 回复接口
     * 
     * @param reply 实体类
     * @return 结果
     */
    public int insertFfwyFeedbackReply(Reply reply);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFeedbackReply 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyFeedbackReply(FfwyFeedbackReply ffwyFeedbackReply);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param replyIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyFeedbackReplyByIds(Long[] replyIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param replyId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyFeedbackReplyById(Long replyId);
}
