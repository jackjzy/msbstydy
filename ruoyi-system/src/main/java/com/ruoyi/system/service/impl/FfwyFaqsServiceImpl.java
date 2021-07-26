package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.FfwyFeedbackReply;
import com.ruoyi.system.domain.FfwyFqasReply;
import com.ruoyi.system.domain.vo.FaqsVo;
import com.ruoyi.system.domain.vo.FfwyFaqsVo;
import com.ruoyi.system.mapper.FfwyFqasReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyFaqsMapper;
import com.ruoyi.system.domain.FfwyFaqs;
import com.ruoyi.system.service.IFfwyFaqsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyFaqsServiceImpl implements IFfwyFaqsService 
{
    @Autowired
    private FfwyFaqsMapper ffwyFaqsMapper;

    @Autowired
    private FfwyFqasReplyMapper ffwyFqasReplyMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param faqsId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyFaqs selectFfwyFaqsById(Long faqsId)
    {
        return ffwyFaqsMapper.selectFfwyFaqsById(faqsId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyFaqsVo> selectFfwyFaqsList(FfwyFaqs ffwyFaqs)
    {
        List<FfwyFaqsVo> ffwyFaqs1 = null;
        String faqsMsg = ffwyFaqs.getFaqsMsg();
        Date bgecreateTime = ffwyFaqs.getBgecreateTime();
        Date actcreateTime = ffwyFaqs.getActcreateTime();
        if(faqsMsg !=null || bgecreateTime != null || actcreateTime != null){
            ffwyFaqs1 = ffwyFaqsMapper.selectFfwyFaqsList(ffwyFaqs);

        }
        if(faqsMsg ==null && bgecreateTime == null && actcreateTime == null){
            ffwyFaqs1 = ffwyFaqsMapper.selectFfwyFaqsLists();
        }
        return ffwyFaqs1;
    }

    @Override
    public List<FfwyFaqs> selectFfwyFaqsAndReplyList(FfwyFaqs ffwyFaqs) {
        return ffwyFaqsMapper.selectFfwyFaqsAndReplyList(ffwyFaqs);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * faqsVo 常见问题回复
     * @return 结果
     */
    @Override
    public int insertFfwyFaqs(FaqsVo faqsVo)
    {
        FfwyFaqs ffwyFaqs = new FfwyFaqs();
        ffwyFaqs.setFaqsMsg(faqsVo.getFaqMsg());
        ffwyFaqs.setUpdateTime(DateUtils.getNowDate());
        ffwyFaqs.setCreateTime(DateUtils.getNowDate());
        int i = ffwyFaqsMapper.insertFfwyFaqs(ffwyFaqs);
        FfwyFqasReply ffwyFqasReply = new FfwyFqasReply();
        ffwyFqasReply.setFaqsId(ffwyFaqs.getFaqsId());
        ffwyFqasReply.setReplyMsg(faqsVo.getFaqReply());
        ffwyFqasReply.setReplyTime(DateUtils.getNowDate());
        ffwyFqasReply.setUpdateTime(DateUtils.getNowDate());
        int i1 = ffwyFqasReplyMapper.insertFfwyFqasReply(ffwyFqasReply);
        return i+i1;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFaqsVo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyFaqs(FfwyFaqsVo ffwyFaqsVo)
    {
        FfwyFaqs ffwyFaqs = new FfwyFaqs();
        ffwyFaqsVo.setUpdateTime(DateUtils.getNowDate());
        ffwyFaqs.setFaqsId(ffwyFaqsVo.getFaqsId());
        ffwyFaqs.setFaqsMsg(ffwyFaqsVo.getFaqsMsg());
        int i = ffwyFaqsMapper.updateFfwyFaqs(ffwyFaqs);
        FfwyFqasReply ffwyFqasReply = new FfwyFqasReply();
        ffwyFqasReply.setUpdateTime(DateUtils.getNowDate());
        ffwyFqasReply.setReplyMsg(ffwyFaqsVo.getFaqReply());
        ffwyFqasReply.setFaqsId(ffwyFaqsVo.getFaqsId());
        int i1 = ffwyFqasReplyMapper.updateFfwyFqasReply(ffwyFqasReply);
        return i+i1;
    }

    /**
     * 删除常见问题
     * 
     * @param faqId 需要删除的常见问题ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFaqsById(Integer faqId)
    {
        ffwyFqasReplyMapper.deleteFfwyFqasReplyByFaqsId(Long.valueOf(faqId.toString()));
        return ffwyFaqsMapper.deleteFfwyFaqsById(faqId);
    }

}
