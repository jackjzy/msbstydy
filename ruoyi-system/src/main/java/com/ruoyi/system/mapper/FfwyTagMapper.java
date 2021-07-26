package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.FfwyTag;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface FfwyTagMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param tagId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyTag selectFfwyTagById(Long tagId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyTag 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyTag> selectFfwyTagList(FfwyTag ffwyTag);

    /**
     * 新增标签
     * 
     * @param ffwyTag 标签实体类
     * @return 结果
     */
    public int insertFfwyTag(FfwyTag ffwyTag);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyTag 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyTag(FfwyTag ffwyTag);

    /**
     * 删除标签
     * 
     * @param tagId 标签ID
     * @return 结果
     */
    public int deleteFfwyTagById(Long tagId);

    List<FfwyTag> selectFfwyTagLists();

    List<FfwyTag> selectFfwyTagListSum(FfwyTag ffwyTag);
}
