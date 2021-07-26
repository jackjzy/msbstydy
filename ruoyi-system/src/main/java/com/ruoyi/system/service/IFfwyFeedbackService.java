package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FfwyFeedback;
import com.ruoyi.system.domain.vo.FeedbckVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyFeedbackService 
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
     *
     * @return list集合
     */
    public List<FeedbckVo> selectFfwyFeedbackList(FfwyFeedback ffwyFeedback);

    /**
     * 查询所有Feedback列表
     *
     *
     * @return list集合
     */
    public List<FfwyFeedback> selectFfwyFeedback(FfwyFeedback ffwyFeedback);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyFeedback 【反馈信息】
     * @return 结果
     */
    public int insertFfwyFeedback(FfwyFeedback ffwyFeedback,Long userId);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFeedback 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyFeedback(FfwyFeedback ffwyFeedback);

    /**
     * 删除系统信息
     * 
     * @param feedbackId 反馈ID
     * @return 结果
     */
    public int deleteFfwyFeedbackById(Integer feedbackId);
}
