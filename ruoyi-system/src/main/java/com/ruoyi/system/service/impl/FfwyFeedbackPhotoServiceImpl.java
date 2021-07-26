package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.mapper.FfwyFeedbackPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.FfwyFeedbackPhoto;
import com.ruoyi.system.service.IFfwyFeedbackPhotoService;

/**
 * 反馈图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-30
 */
@Service
public class FfwyFeedbackPhotoServiceImpl implements IFfwyFeedbackPhotoService 
{
    @Autowired
    private FfwyFeedbackPhotoMapper ffwyFeedbackPhotoMapper;

    /**
     * 查询反馈图片
     * 
     * @param photoId 反馈图片ID
     * @return 反馈图片
     */
    @Override
    public FfwyFeedbackPhoto selectFfwyFeedbackPhotoById(Long photoId)
    {
        return ffwyFeedbackPhotoMapper.selectFfwyFeedbackPhotoById(photoId);
    }

    /**
     * 查询反馈图片列表
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 反馈图片
     */
    @Override
    public List<FfwyFeedbackPhoto> selectFfwyFeedbackPhotoList(FfwyFeedbackPhoto ffwyFeedbackPhoto)
    {
        return ffwyFeedbackPhotoMapper.selectFfwyFeedbackPhotoList(ffwyFeedbackPhoto);
    }

    /**
     * 新增反馈图片
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 结果
     */
    @Override
    public int insertFfwyFeedbackPhoto(List<FfwyFeedbackPhoto>  ffwyFeedbackPhoto)
    {

        return ffwyFeedbackPhotoMapper.insertFfwyFeedbackPhoto(ffwyFeedbackPhoto);
    }

    /**
     * 修改反馈图片
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 结果
     */
    @Override
    public int updateFfwyFeedbackPhoto(FfwyFeedbackPhoto ffwyFeedbackPhoto)
    {
        return ffwyFeedbackPhotoMapper.updateFfwyFeedbackPhoto(ffwyFeedbackPhoto);
    }

    /**
     * 批量删除反馈图片
     * 
     * @param photoIds 需要删除的反馈图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFeedbackPhotoByIds(Long[] photoIds)
    {
        return ffwyFeedbackPhotoMapper.deleteFfwyFeedbackPhotoByIds(photoIds);
    }

    /**
     * 删除反馈图片信息
     * 
     * @param photoId 反馈图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFeedbackPhotoById(Long photoId)
    {
        return ffwyFeedbackPhotoMapper.deleteFfwyFeedbackPhotoById(photoId);
    }
}
