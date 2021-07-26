package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomeal.FfwyCombomealComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface IFfwyCombomealCommentService 
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

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealComment(FfwyCombomealComment ffwyCombomealComment);
    /**
     * 新增【请填写功能名称】
     *
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealCommentAndPhoto(FfwyCombomealComment ffwyCombomealComment, MultipartFile[] files);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealComment(FfwyCombomealComment ffwyCombomealComment);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param commentIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommentByIds(Long[] commentIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param commentId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommentById(Long commentId);
}
