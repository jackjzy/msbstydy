package com.ruoyi.back.mapper;

import com.ruoyi.back.domain.FfwyInformationComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-19
 */
@Component
public interface FfwyInformationCommentMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param informationCommentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyInformationComment selectFfwyInformationCommentById(Long informationCommentId);

    /**
     * 查询【查询所有一级评论】列表
     * 
     * @param  【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyInformationComment> selectFfwyInformationCommentList(
            @Param("userName")String userName,
            @Param("phone")String phone,
            @Param("informationComment")String informationComment,
            @Param("parentId")Long parentId,
            @Param("beginTime")Date beginTime,
            @Param("endTime")Date endTime,
            @Param("searchStr")String searchStr
            );

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyInformationComment 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyInformationComment(FfwyInformationComment ffwyInformationComment);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyInformationComment 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyInformationComment(FfwyInformationComment ffwyInformationComment);

    /**
     * 删除【请填写功能名称】
     * 
     * @param informationCommentId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyInformationCommentById(Long informationCommentId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param informationCommentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyInformationCommentByIds(Long[] informationCommentIds);
}
