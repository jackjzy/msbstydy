package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FfwyFqasReply;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyFqasReplyService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param relpyId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyFqasReply selectFfwyFqasReplyById(Long relpyId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @return 【请填写功能名称】集合
     */
    public List<FfwyFqasReply> selectFfwyFqasReplyList();

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyFqasReply 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyFqasReply(FfwyFqasReply ffwyFqasReply);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFqasReply 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyFqasReply(FfwyFqasReply ffwyFqasReply);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param relpyIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyFqasReplyByIds(Long[] relpyIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param faqsId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyFqasReplyById(Long faqsId);
}
