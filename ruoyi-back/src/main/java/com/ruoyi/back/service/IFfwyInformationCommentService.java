package com.ruoyi.back.service;

import com.ruoyi.back.domain.FfwyInformationComment;
import com.ruoyi.back.domain.queryentity.QueryInformationComment;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-19
 */
public interface IFfwyInformationCommentService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param informationCommentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyInformationComment selectFfwyInformationCommentById(Long informationCommentId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyInformationComment 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyInformationComment> selectFfwyInformationCommentList(QueryInformationComment ffwyInformationComment);

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
     * 批量删除【请填写功能名称】
     * 
     * @param informationCommentIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyInformationCommentByIds(Long[] informationCommentIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param informationCommentId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyInformationCommentById(Long informationCommentId);



    int deleteComentChageUser(Long informationCommentId,Long userId);
}
