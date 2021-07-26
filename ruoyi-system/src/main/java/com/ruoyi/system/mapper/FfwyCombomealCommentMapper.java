package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.combomeal.FfwyCombomealComment;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface FfwyCombomealCommentMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param commentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealComment selectFfwyCombomealCommentById(Long commentId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealComment> selectFfwyCombomealCommentList(FfwyCombomealComment ffwyCombomealComment);


    public List<FfwyCombomealComment> selectCombomealCommentByCombomealId(long combomealId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealComment> selectFfwyCombomealCommentAndPhoto(FfwyCombomealComment ffwyCombomealComment);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealComment(FfwyCombomealComment ffwyCombomealComment);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealComment(FfwyCombomealComment ffwyCombomealComment);

    /**
     * 删除【请填写功能名称】
     * 
     * @param commentId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommentById(Long commentId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param commentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommentByIds(Long[] commentIds);
}
