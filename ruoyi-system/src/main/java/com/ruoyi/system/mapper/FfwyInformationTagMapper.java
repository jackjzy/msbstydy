package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyInformationTag;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface FfwyInformationTagMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param informationId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyInformationTag selectFfwyInformationTagById(Long informationId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyInformationTag 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyInformationTag> selectFfwyInformationTagList(FfwyInformationTag ffwyInformationTag);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyInformationTag 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyInformationTag(FfwyInformationTag ffwyInformationTag);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyInformationTag 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyInformationTag(FfwyInformationTag ffwyInformationTag);

    /**
     * 删除【请填写功能名称】
     * 
     * @param informationId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyInformationTagById(Long informationId);

}
