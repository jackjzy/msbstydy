package com.ruoyi.back.service.impl;

import java.util.List;

import com.ruoyi.back.domain.FfwyInformationComment;
import com.ruoyi.back.domain.queryentity.QueryInformationComment;
import com.ruoyi.back.mapper.FfwyInformationCommentMapper;
import com.ruoyi.back.service.IFfwyInformationCommentService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-19
 */
@Service
public class FfwyInformationCommentServiceImpl implements IFfwyInformationCommentService
{
    @Autowired
    private FfwyInformationCommentMapper ffwyInformationCommentMapper;
    @Autowired
    private IFfwyUserService iFfwyUserService;

    /**
     * 查询【请填写功能名称】
     * 
     * @param informationCommentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyInformationComment selectFfwyInformationCommentById(Long informationCommentId)
    {
        return ffwyInformationCommentMapper.selectFfwyInformationCommentById(informationCommentId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyInformationComment 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyInformationComment> selectFfwyInformationCommentList(QueryInformationComment ffwyInformationComment)
    {
        return ffwyInformationCommentMapper.
                selectFfwyInformationCommentList(ffwyInformationComment.getUserName(),
                        ffwyInformationComment.getPhonenumber(),
                        ffwyInformationComment.getInformationComment(),
                        ffwyInformationComment.getParentId(),
                        ffwyInformationComment.getBeginTime(),
                        ffwyInformationComment.getEndTime(),
                        ffwyInformationComment.getSearchStr()
                );
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyInformationComment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyInformationComment(FfwyInformationComment ffwyInformationComment)
    {
        ffwyInformationComment.setCreateTime(DateUtils.getNowDate());
        return ffwyInformationCommentMapper.insertFfwyInformationComment(ffwyInformationComment);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyInformationComment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyInformationComment(FfwyInformationComment ffwyInformationComment)
    {
        return ffwyInformationCommentMapper.updateFfwyInformationComment(ffwyInformationComment);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param informationCommentIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyInformationCommentByIds(Long[] informationCommentIds)
    {
        return ffwyInformationCommentMapper.deleteFfwyInformationCommentByIds(informationCommentIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param informationCommentId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyInformationCommentById(Long informationCommentId)
    {
        return ffwyInformationCommentMapper.deleteFfwyInformationCommentById(informationCommentId);
    }



    /**
     * 删除【请填写功能名称】信息
     *
     * @param informationCommentId 【删除的评论】ID userId【封禁的用户】
     * @return 结果
     */
    @Override
    public int deleteComentChageUser(Long informationCommentId, Long userId) {
        if (null!=userId){
        FfwyUser user=new FfwyUser();
        user.setUserId(userId);
        user.setUserStatus("1");
        iFfwyUserService.updateFfwyUser(user);
        }
        return ffwyInformationCommentMapper.deleteFfwyInformationCommentById(informationCommentId);
    }
}
