package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.FfwyInformation;
import com.ruoyi.system.domain.vo.FfwyInformationVo;
import com.ruoyi.system.domain.vo.MultiVo;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface FfwyInformationMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param informationId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyInformation selectFfwyInformationById(Long informationId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyInformation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MultiVo> selectFfwyInformationList(FfwyInformation ffwyInformation);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyInformation 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyInformation(FfwyInformation ffwyInformation);

    /**
     * 修改咨询
     * 
     * @param ffwyInformation 实体类
     * @return 结果
     */
    public int updateFfwyInformation(FfwyInformation ffwyInformation);

    /**
     * 根据id删除咨询
     * 
     * @param informationId 咨询ID
     * @return 结果
     */
    public int deleteFfwyInformationById(Long informationId);

    List<MultiVo> selectFfwyInformationListAll();
}
