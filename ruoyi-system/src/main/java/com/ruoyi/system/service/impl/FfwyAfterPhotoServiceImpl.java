package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.aftersale.FfwyAfterPhoto;
import com.ruoyi.system.mapper.aftersale.FfwyAfterPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyAfterPhotoService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
@Service
public class FfwyAfterPhotoServiceImpl implements IFfwyAfterPhotoService 
{
    @Autowired
    private FfwyAfterPhotoMapper ffwyAfterPhotoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param afterPhotoId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyAfterPhoto selectFfwyAfterPhotoById(Long afterPhotoId)
    {
        return ffwyAfterPhotoMapper.selectFfwyAfterPhotoById(afterPhotoId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyAfterPhoto 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyAfterPhoto> selectFfwyAfterPhotoList(FfwyAfterPhoto ffwyAfterPhoto)
    {
        return ffwyAfterPhotoMapper.selectFfwyAfterPhotoList(ffwyAfterPhoto);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyAfterPhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyAfterPhoto(FfwyAfterPhoto ffwyAfterPhoto)
    {
        return ffwyAfterPhotoMapper.insertFfwyAfterPhoto(ffwyAfterPhoto);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyAfterPhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyAfterPhoto(FfwyAfterPhoto ffwyAfterPhoto)
    {
        return ffwyAfterPhotoMapper.updateFfwyAfterPhoto(ffwyAfterPhoto);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param afterPhotoIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterPhotoByIds(Long[] afterPhotoIds)
    {
        return ffwyAfterPhotoMapper.deleteFfwyAfterPhotoByIds(afterPhotoIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param afterPhotoId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterPhotoById(Long afterPhotoId)
    {
        return ffwyAfterPhotoMapper.deleteFfwyAfterPhotoById(afterPhotoId);
    }

    @Override
    public int insertFfwyAfterPhotos(List<FfwyAfterPhoto> ffwyAfterPhotos) {
        return ffwyAfterPhotoMapper.insertFfwyAfterPhotos(ffwyAfterPhotos);
    }
}
