package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyInformationType;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface FfwyInformationTypeMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param typeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyInformationType selectFfwyInformationTypeById(Long typeId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyInformationType 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyInformationType> selectFfwyInformationTypeList(FfwyInformationType ffwyInformationType);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyInformationType 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyInformationType(FfwyInformationType ffwyInformationType);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyInformationType 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyInformationType(FfwyInformationType ffwyInformationType);

    /**
     * 删除【请填写功能名称】
     * 
     * @param typeId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyInformationTypeById(Long typeId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param typeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyInformationTypeByIds(Long[] typeIds);
}
