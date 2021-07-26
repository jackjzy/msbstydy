package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.FfwyInformationTag;
import com.ruoyi.system.domain.FfwyTag;
import com.ruoyi.system.domain.video.FfwyVideoTag;
import com.ruoyi.system.mapper.FfwyInformationTagMapper;
import com.ruoyi.system.mapper.video.FfwyAppVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyTagMapper;
import com.ruoyi.system.service.IFfwyTagService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyTagServiceImpl implements IFfwyTagService 
{
    @Autowired
    private FfwyTagMapper ffwyTagMapper;

    @Autowired
    private FfwyAppVideoMapper ffwyAppVideoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param tagId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyTag selectFfwyTagById(Long tagId)
    {
        return ffwyTagMapper.selectFfwyTagById(tagId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyTag 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyTag> selectFfwyTagList(FfwyTag ffwyTag)
    {

        List<FfwyTag> ffwyTags = ffwyTagMapper.selectFfwyTagList(ffwyTag);
        return ffwyTags;
    }

    /**
     * 新增标签
     * 
     * @param ffwyTag 标签实体类
     * @return 结果
     */
    @Override
    public int insertFfwyTag(FfwyTag ffwyTag)
    {
        ffwyTag.setCreateTime(DateUtils.getNowDate());
        return ffwyTagMapper.insertFfwyTag(ffwyTag);
    }

    /**
     * 修改标签
     * 
     * @param ffwyTag 标签实体类
     * @return 结果
     */
    @Override
    public int updateFfwyTag(FfwyTag ffwyTag)
    {
        ffwyTag.setUpdateTime(DateUtils.getNowDate());
        return ffwyTagMapper.updateFfwyTag(ffwyTag);
    }

    /**
     * 删除标签
     * 
     * @param tagId 需要删除的标签ID
     * @return 结果
     */
    @Override
    public int deleteFfwyTagById(Long tagId)
    {
        List<FfwyVideoTag> ffwyVideoTags = ffwyAppVideoMapper.selectTag(tagId);
        if (ffwyVideoTags.size()>0){
            return -1;

        }else {

            int i = ffwyTagMapper.deleteFfwyTagById(tagId);
            return i;

        }

    }

    @Override
    public List<FfwyTag> selectFfwyTagListSum(FfwyTag ffwyTag) {
        return ffwyTagMapper.selectFfwyTagListSum(ffwyTag);
    }

}
