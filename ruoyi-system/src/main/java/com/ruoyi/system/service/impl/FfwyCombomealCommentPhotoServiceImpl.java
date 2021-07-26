package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.combomeal.FfwyCombomealCommentPhoto;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealCommentPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyCombomealCommentPhotoService;

/**
 * 套餐评论图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
@Service
public class FfwyCombomealCommentPhotoServiceImpl implements IFfwyCombomealCommentPhotoService 
{
    @Autowired
    private FfwyCombomealCommentPhotoMapper ffwyCombomealCommentPhotoMapper;

    /**
     * 查询套餐评论图片
     * 
     * @param photoId 套餐评论图片ID
     * @return 套餐评论图片
     */
    @Override
    public FfwyCombomealCommentPhoto selectFfwyCombomealCommentPhotoById(Long photoId)
    {
        return ffwyCombomealCommentPhotoMapper.selectFfwyCombomealCommentPhotoById(photoId);
    }

    /**
     * 查询套餐评论图片列表
     * 
     * @param ffwyCombomealCommentPhoto 套餐评论图片
     * @return 套餐评论图片
     */
    @Override
    public List<FfwyCombomealCommentPhoto> selectFfwyCombomealCommentPhotoList(FfwyCombomealCommentPhoto ffwyCombomealCommentPhoto)
    {
        return ffwyCombomealCommentPhotoMapper.selectFfwyCombomealCommentPhotoList(ffwyCombomealCommentPhoto);
    }

    /**
     * 新增套餐评论图片
     * 
     * @param ffwyCombomealCommentPhoto 套餐评论图片
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealCommentPhoto(FfwyCombomealCommentPhoto ffwyCombomealCommentPhoto)
    {
        ffwyCombomealCommentPhoto.setCreateTime(DateUtils.getNowDate());
        return ffwyCombomealCommentPhotoMapper.insertFfwyCombomealCommentPhoto(ffwyCombomealCommentPhoto);
    }

    /**
     * 修改套餐评论图片
     * 
     * @param ffwyCombomealCommentPhoto 套餐评论图片
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealCommentPhoto(FfwyCombomealCommentPhoto ffwyCombomealCommentPhoto)
    {
        ffwyCombomealCommentPhoto.setUpdateTime(DateUtils.getNowDate());
        return ffwyCombomealCommentPhotoMapper.updateFfwyCombomealCommentPhoto(ffwyCombomealCommentPhoto);
    }

    /**
     * 批量删除套餐评论图片
     * 
     * @param photoIds 需要删除的套餐评论图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCommentPhotoByIds(Long[] photoIds)
    {
        return ffwyCombomealCommentPhotoMapper.deleteFfwyCombomealCommentPhotoByIds(photoIds);
    }

    /**
     * 删除套餐评论图片信息
     * 
     * @param photoId 套餐评论图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCommentPhotoById(Long photoId)
    {
        return ffwyCombomealCommentPhotoMapper.deleteFfwyCombomealCommentPhotoById(photoId);
    }
}
