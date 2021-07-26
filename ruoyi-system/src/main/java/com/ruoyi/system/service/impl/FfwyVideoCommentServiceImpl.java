package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.video.FfwyVideoComment;
import com.ruoyi.system.mapper.video.FfwyVideoCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyVideoCommentService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Service
public class FfwyVideoCommentServiceImpl implements IFfwyVideoCommentService 
{
    @Autowired
    private FfwyVideoCommentMapper ffwyVideoCommentMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param videoCommentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyVideoComment selectFfwyVideoCommentById(Long videoCommentId)
    {
        return ffwyVideoCommentMapper.selectFfwyVideoCommentById(videoCommentId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyVideoComment 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyVideoComment> selectFfwyVideoCommentList(FfwyVideoComment ffwyVideoComment)
    {
        return ffwyVideoCommentMapper.selectFfwyVideoCommentList(ffwyVideoComment);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyVideoComment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyVideoComment(FfwyVideoComment ffwyVideoComment,Long id)
    {
        ffwyVideoComment.setUserId(id);
        ffwyVideoComment.setCommentTime(DateUtils.getNowDate());
        return ffwyVideoCommentMapper.insertFfwyVideoComment(ffwyVideoComment);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyVideoComment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyVideoComment(FfwyVideoComment ffwyVideoComment)
    {
        return ffwyVideoCommentMapper.updateFfwyVideoComment(ffwyVideoComment);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param videoCommentIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoCommentByIds(Long[] videoCommentIds)
    {
        return ffwyVideoCommentMapper.deleteFfwyVideoCommentByIds(videoCommentIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param videoCommentId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyVideoCommentById(Long videoCommentId)
    {
        return ffwyVideoCommentMapper.deleteFfwyVideoCommentById(videoCommentId);
    }
}
