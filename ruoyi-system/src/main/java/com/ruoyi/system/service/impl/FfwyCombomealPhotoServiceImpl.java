package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.system.domain.combomeal.FfwyCombomealPhoto;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCombomealPhotoService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-07
 */
@Service
public class FfwyCombomealPhotoServiceImpl implements IFfwyCombomealPhotoService 
{
    @Autowired
    private FfwyCombomealPhotoMapper ffwyCombomealPhotoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param photoId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealPhoto selectFfwyCombomealPhotoById(Long photoId)
    {
        return ffwyCombomealPhotoMapper.selectFfwyCombomealPhotoById(photoId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealPhoto 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealPhoto> selectFfwyCombomealPhotoList(FfwyCombomealPhoto ffwyCombomealPhoto)
    {
        List<FfwyCombomealPhoto> ffwyCombomealPhotos = ffwyCombomealPhotoMapper.selectFfwyCombomealPhotoList(ffwyCombomealPhoto);
        ffwyCombomealPhotos.forEach(ffwyCombomealPhoto1 -> {
            ffwyCombomealPhoto1.setPhotoPath(CosCofig.getPrefixUrl()+ffwyCombomealPhoto1.getPhotoPath());
        });
        return ffwyCombomealPhotos;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealPhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealPhoto(FfwyCombomealPhoto ffwyCombomealPhoto)
    {
        return ffwyCombomealPhotoMapper.insertFfwyCombomealPhoto(ffwyCombomealPhoto);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealPhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealPhoto(FfwyCombomealPhoto ffwyCombomealPhoto)
    {
        return ffwyCombomealPhotoMapper.updateFfwyCombomealPhoto(ffwyCombomealPhoto);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param photoIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealPhotoByIds(Long[] photoIds)
    {
        return ffwyCombomealPhotoMapper.deleteFfwyCombomealPhotoByIds(photoIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param photoId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealPhotoById(Long photoId)
    {
        return ffwyCombomealPhotoMapper.deleteFfwyCombomealPhotoById(photoId);
    }

    @Override
    public int insertFfwyCombomealPhotos(List<FfwyCombomealPhoto> ffwycombomealPhotos) {
        return ffwyCombomealPhotoMapper.insertFfwyCombomealPhotos(ffwycombomealPhotos);
    }
}
