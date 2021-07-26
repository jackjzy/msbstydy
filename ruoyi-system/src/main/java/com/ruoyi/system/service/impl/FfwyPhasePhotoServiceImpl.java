package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhasePhoto;
import com.ruoyi.system.mapper.combomealorders.FfwyPhasePhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyPhasePhotoService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-22
 */
@Service
public class FfwyPhasePhotoServiceImpl implements IFfwyPhasePhotoService 
{
    @Autowired
    private FfwyPhasePhotoMapper ffwyPhasePhotoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param phasePhotoId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyPhasePhoto selectFfwyPhasePhotoById(Long phasePhotoId)
    {
        return ffwyPhasePhotoMapper.selectFfwyPhasePhotoById(phasePhotoId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPhasePhoto 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyPhasePhoto> selectFfwyPhasePhotoList(FfwyPhasePhoto ffwyPhasePhoto)
    {
        return ffwyPhasePhotoMapper.selectFfwyPhasePhotoList(ffwyPhasePhoto);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPhasePhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyPhasePhoto(FfwyPhasePhoto ffwyPhasePhoto)
    {
        ffwyPhasePhoto.setCreateTime(DateUtils.getNowDate());
        return ffwyPhasePhotoMapper.insertFfwyPhasePhoto(ffwyPhasePhoto);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPhasePhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyPhasePhoto(FfwyPhasePhoto ffwyPhasePhoto)
    {
        ffwyPhasePhoto.setUpdateTime(DateUtils.getNowDate());
        return ffwyPhasePhotoMapper.updateFfwyPhasePhoto(ffwyPhasePhoto);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param phasePhotoIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPhasePhotoByIds(Long[] phasePhotoIds)
    {
        return ffwyPhasePhotoMapper.deleteFfwyPhasePhotoByIds(phasePhotoIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param phasePhotoId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPhasePhotoById(Long phasePhotoId)
    {
        return ffwyPhasePhotoMapper.deleteFfwyPhasePhotoById(phasePhotoId);
    }
}
