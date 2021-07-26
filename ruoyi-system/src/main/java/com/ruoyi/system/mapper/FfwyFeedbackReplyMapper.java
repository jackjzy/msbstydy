package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyFeedbackReply;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface FfwyFeedbackReplyMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param replyId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyFeedbackReply selectFfwyFeedbackReplyById(Long replyId);

    /**
     * 查询【请填写功能名称】
     *
     * @param feedBackId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyFeedbackReply selectFfwyFeedbackReplyByFeedbackId(Long feedBackId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyFeedbackReply 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyFeedbackReply> selectFfwyFeedbackReplyList(FfwyFeedbackReply ffwyFeedbackReply);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyFeedbackReply 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyFeedbackReply(FfwyFeedbackReply ffwyFeedbackReply);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFeedbackReply 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyFeedbackReply(FfwyFeedbackReply ffwyFeedbackReply);

    /**
     * 删除【请填写功能名称】
     * 
     * @param replyId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyFeedbackReplyById(Long replyId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param replyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyFeedbackReplyByIds(Long[] replyIds);
}
