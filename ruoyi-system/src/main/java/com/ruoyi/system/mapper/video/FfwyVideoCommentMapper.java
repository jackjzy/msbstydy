package com.ruoyi.system.mapper.video;

import com.ruoyi.system.domain.video.FfwyVideoComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2021-05-14
 */
public interface FfwyVideoCommentMapper {
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
    public int insertFfwyVideoComment(FfwyVideoComment ffwyVideoComment);

    /**
     * 查询【查询该视频的评论数】
     *
     * @param id 【视频】ID
     * @return 【请填写功能名称】
     */
    public FfwyVideoComment selectFfwyVideoCommentCounts(@Param("videoId") Long id);

    public int selectFfwyVideoCommentCount(Long videoId);

    /**
     * 修改【请填写功能名称】
     *
     * @param ffwyVideoComment 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyVideoComment(FfwyVideoComment ffwyVideoComment);

    /**
     * 删除【请填写功能名称】
     *
     * @param videoCommentId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyVideoCommentById(Long videoCommentId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param videoCommentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyVideoCommentByIds(Long[] videoCommentIds);

    FfwyVideoComment selectFfwyVideoComment(Long videoId);

}
