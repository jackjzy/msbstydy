package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.FfwyFeedback;
import com.ruoyi.system.domain.Reply;
import com.ruoyi.system.mapper.FfwyFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyFeedbackReplyMapper;
import com.ruoyi.system.domain.FfwyFeedbackReply;
import com.ruoyi.system.service.IFfwyFeedbackReplyService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyFeedbackReplyServiceImpl implements IFfwyFeedbackReplyService 
{
    @Autowired
    private FfwyFeedbackReplyMapper ffwyFeedbackReplyMapper;

    @Autowired
    private FfwyFeedbackMapper ffwyFeedbackMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param replyId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyFeedbackReply selectFfwyFeedbackReplyById(Long replyId)
    {
        return ffwyFeedbackReplyMapper.selectFfwyFeedbackReplyById(replyId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyFeedbackReply 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyFeedbackReply> selectFfwyFeedbackReplyList(FfwyFeedbackReply ffwyFeedbackReply)
    {
        return ffwyFeedbackReplyMapper.selectFfwyFeedbackReplyList(ffwyFeedbackReply);
    }

    /**
     * 回复接口
     * 
     * @param reply 实体类
     * @return 结果
     */
    @Override
    public int insertFfwyFeedbackReply(Reply reply)
    {
        FfwyFeedback ffwyFeedback = new FfwyFeedback();
        ffwyFeedback.setFeedbackId(reply.getFeedbackId().longValue());
        ffwyFeedback.setFeedbackStatus("1");
        ffwyFeedback.setCreateTime(DateUtils.getNowDate());
        int i = ffwyFeedbackMapper.updateFfwyFeedback(ffwyFeedback);
        FfwyFeedbackReply ffwyFeedbackReply = new FfwyFeedbackReply();
        ffwyFeedbackReply.setReplyMsg(reply.getReplyMsg());
        ffwyFeedbackReply.setUserId(reply.getUserid().longValue());
        ffwyFeedbackReply.setFeedBackId(reply.getFeedbackId().longValue());


        //消息推送给用户
        /*
        *
        *
        *
        * */
        FfwyFeedbackReply ffwyFeedbackReply1 = ffwyFeedbackReplyMapper.selectFfwyFeedbackReplyByFeedbackId(reply.getFeedbackId().longValue());
        if (null==ffwyFeedbackReply1){
            int i1 = ffwyFeedbackReplyMapper.insertFfwyFeedbackReply(ffwyFeedbackReply);
            i=i+i1;
        }else {
            ffwyFeedbackReplyMapper.updateFfwyFeedbackReply(ffwyFeedbackReply);
        }

        return i;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFeedbackReply 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyFeedbackReply(FfwyFeedbackReply ffwyFeedbackReply)
    {
        return ffwyFeedbackReplyMapper.updateFfwyFeedbackReply(ffwyFeedbackReply);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param replyIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFeedbackReplyByIds(Long[] replyIds)
    {
        return ffwyFeedbackReplyMapper.deleteFfwyFeedbackReplyByIds(replyIds);
    }
    
    /**
     * 删除【请填写功能名称】信息
     * 
     * @param replyId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFeedbackReplyById(Long replyId)
    {
        return ffwyFeedbackReplyMapper.deleteFfwyFeedbackReplyById(replyId);
    }
}
