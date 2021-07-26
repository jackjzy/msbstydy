package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.FfwyInformation;
import com.ruoyi.system.domain.vo.FfwyTagVo;
import com.ruoyi.system.domain.vo.MultiVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwyInformationService 
{
    /**
     * 查询
     * 
     * @param informationId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyInformation selectFfwyInformationById(Long informationId);

    /**
     * 查询所有内容
     * 
     * @param ffwyInformation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MultiVo> selectFfwyInformationList(FfwyInformation ffwyInformation);

    /**
     * 为咨询添加标签
     * 
     * @param ffwyTagVo 实体类
     * @return 结果
     */
    public int insertFfwyInformation(FfwyTagVo ffwyTagVo);

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
     * @param informationId 需要删除的咨询ID
     * @return 结果
     */
    public int deleteFfwyInformationById(Long informationId);

    /**
     * 新增咨询
     *
     * @param ffwyInformation 实体类
     * @return 结果
     */
    public int addFfwyInformation(FfwyInformation ffwyInformation);
}
