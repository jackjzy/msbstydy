package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.FfwyInformation;
import com.ruoyi.system.domain.FfwyInformationTag;
import com.ruoyi.system.domain.FfwyTag;
import com.ruoyi.system.domain.vo.FfwyTagVo;
import com.ruoyi.system.domain.vo.MultiVo;
import com.ruoyi.system.mapper.FfwyInformationTagMapper;
import com.ruoyi.system.mapper.FfwyInformationTypeMapper;
import com.ruoyi.system.mapper.FfwyTagMapper;
import com.ruoyi.system.mapper.product.FfwyProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyInformationMapper;
import com.ruoyi.system.service.IFfwyInformationService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FfwyInformationServiceImpl implements IFfwyInformationService 
{
    @Autowired
    private FfwyInformationMapper ffwyInformationMapper;

    @Autowired
    private FfwyTagMapper ffwyTagMapper;

    @Autowired
    private FfwyInformationTagMapper ffwyInformationTagMapper;

    @Autowired
    private FfwyProductCategoryMapper ffwyProductCategoryMapper;

    @Autowired
    private FfwyInformationTypeMapper ffwyInformationTypeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param informationId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyInformation selectFfwyInformationById(Long informationId)
    {
        return ffwyInformationMapper.selectFfwyInformationById(informationId);
    }

    /**
     * 查询所有内容
     * 
     * @param ffwyInformation 实体类
     * @return 结果
     */
    @Override
    public List<MultiVo> selectFfwyInformationList(FfwyInformation ffwyInformation)
    {
        List<MultiVo> multiVos = null;
        String informationStatus = ffwyInformation.getInformationStatus();
        Date createTime = ffwyInformation.getCreateTime();
        String informationName = ffwyInformation.getInformationName();
        Long informationTypeId = ffwyInformation.getInformationTypeId();
        if(informationName != null || informationStatus != null || createTime != null || informationTypeId != null){
            multiVos = ffwyInformationMapper.selectFfwyInformationList(ffwyInformation);
        }
        if(informationName == null && informationStatus == null && createTime == null && informationTypeId == null) {
            multiVos = ffwyInformationMapper.selectFfwyInformationListAll();
        }
        return  multiVos;
    }

    /**
     * 为咨询添加标签
     * 
     * @param ffwyTagVo 实体类
     * @return 结果
     */
    @Override
    public int insertFfwyInformation(FfwyTagVo ffwyTagVo)
    {
        FfwyTag ffwyTag = new FfwyTag();
        ffwyTag.setTagId(ffwyTagVo.getTagId());
        ffwyTag.setTagName(ffwyTagVo.getTagName());
        ffwyTag.setCreateTime(DateUtils.getNowDate());
        int i = ffwyTagMapper.insertFfwyTag(ffwyTag);
        Long tagId = ffwyTag.getTagId();
        FfwyInformationTag ffwyInformationTag = new FfwyInformationTag();
        ffwyInformationTag.setInformationId(ffwyTagVo.getInformationId());
        ffwyInformationTag.setTagId(tagId);
        int i1 = ffwyInformationTagMapper.insertFfwyInformationTag(ffwyInformationTag);
        return i+i1;
    }

    /**
     * 修改咨询
     * 
     * @param ffwyInformation 实体类
     * @return 结果
     */
    @Override
    public int updateFfwyInformation(FfwyInformation ffwyInformation)
    {
        ffwyInformation.setUpdateTime(DateUtils.getNowDate());
        return ffwyInformationMapper.updateFfwyInformation(ffwyInformation);
    }

    /**
     * 根据id删除咨询
     * 
     * @param informationId 咨询ID
     * @return 结果
     */
    @Override
    public int deleteFfwyInformationById(Long informationId)
    {
        int i = ffwyInformationTagMapper.deleteFfwyInformationTagById(informationId);
        int i1 = ffwyInformationMapper.deleteFfwyInformationById(informationId);
        return i+i1;
    }

    /**
     * 添加咨询
     *
     * @param ffwyInformation 实体类
     * @return 结果
     */
    @Override
    public int addFfwyInformation(FfwyInformation ffwyInformation) {
        return  ffwyInformationMapper.insertFfwyInformation(ffwyInformation);
    }
}
