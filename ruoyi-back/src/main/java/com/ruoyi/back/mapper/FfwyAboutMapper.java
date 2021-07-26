package com.ruoyi.back.mapper;

import com.ruoyi.back.domain.FfwyAbout;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
public interface FfwyAboutMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param appId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyAbout selectFfwyAboutById(Long appId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyAbout 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyAbout> selectFfwyAboutList(FfwyAbout ffwyAbout);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyAbout 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyAbout(FfwyAbout ffwyAbout);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyAbout 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyAbout(FfwyAbout ffwyAbout);

    /**
     * 删除【请填写功能名称】
     * 
     * @param appId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyAboutById(Long appId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param appIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyAboutByIds(Long[] appIds);
}
