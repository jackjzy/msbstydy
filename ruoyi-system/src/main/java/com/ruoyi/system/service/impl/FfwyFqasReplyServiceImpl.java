package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyFqasReplyMapper;
import com.ruoyi.system.domain.FfwyFqasReply;
import com.ruoyi.system.service.IFfwyFqasReplyService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyFqasReplyServiceImpl implements IFfwyFqasReplyService 
{
    @Autowired
    private FfwyFqasReplyMapper ffwyFqasReplyMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param relpyId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyFqasReply selectFfwyFqasReplyById(Long relpyId)
    {
        return ffwyFqasReplyMapper.selectFfwyFqasReplyById(relpyId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyFqasReply> selectFfwyFqasReplyList()
    {
        return ffwyFqasReplyMapper.selectFfwyFqasReplyList();
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyFqasReply 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyFqasReply(FfwyFqasReply ffwyFqasReply)
    {
        return ffwyFqasReplyMapper.insertFfwyFqasReply(ffwyFqasReply);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFqasReply 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyFqasReply(FfwyFqasReply ffwyFqasReply)
    {
        ffwyFqasReply.setUpdateTime(DateUtils.getNowDate());
        return ffwyFqasReplyMapper.updateFfwyFqasReply(ffwyFqasReply);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param relpyIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFqasReplyByIds(Long[] relpyIds)
    {
        return ffwyFqasReplyMapper.deleteFfwyFqasReplyByIds(relpyIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param faqsId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFqasReplyById(Long faqsId)
    {
        return ffwyFqasReplyMapper.deleteFfwyFqasReplyByFaqsId(faqsId);
    }
}
