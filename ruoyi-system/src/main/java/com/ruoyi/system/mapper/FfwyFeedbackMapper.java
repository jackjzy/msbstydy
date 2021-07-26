package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyFeedback;
import com.ruoyi.system.domain.vo.FeedbckVo;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface FfwyFeedbackMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param feedbackId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyFeedback selectFfwyFeedbackById(Long feedbackId);

    /**
     * 查询所有Feedback列表
     * 
     * @return list集合
     */
    public List<FeedbckVo> selectFfwyFeedbackList(FfwyFeedback ffwyFeedbacks);
    /**
     * 查询所有Feedback列表
     *
     * @return list集合
     */
    public List<FfwyFeedback> selectFfwyFeedback(FfwyFeedback ffwyFeedbacks);
    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyFeedback 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyFeedback(FfwyFeedback ffwyFeedback);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFeedback 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyFeedback(FfwyFeedback ffwyFeedback);

    /**
     * 删除接口
     * 
     * @param feedbackId 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyFeedbackById(Integer feedbackId);

    List<FeedbckVo> selectFfwyFeedbackLists();
}
