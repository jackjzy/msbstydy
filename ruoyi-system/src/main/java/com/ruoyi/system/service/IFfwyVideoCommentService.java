package com.ruoyi.system.service;

import com.ruoyi.system.domain.video.FfwyVideoComment;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface IFfwyVideoCommentService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param videoCommentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyVideoComment selectFfwyVideoCommentById(Long videoCommentId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyVideoComment 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyVideoComment> selectFfwyVideoCommentList(FfwyVideoComment ffwyVideoComment);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyVideoComment 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyVideoComment(FfwyVideoComment ffwyVideoComment,Long id);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyVideoComment 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyVideoComment(FfwyVideoComment ffwyVideoComment);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param videoCommentIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoCommentByIds(Long[] videoCommentIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param videoCommentId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoCommentById(Long videoCommentId);


}
