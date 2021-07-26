package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.FfwyFeedbackPhoto;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;

/**
 * 反馈图片Service接口
 * 
 * @author ruoyi
 * @date 2021-04-30
 */
public interface IFfwyFeedbackPhotoService 
{
    /**
     * 查询反馈图片
     * 
     * @param photoId 反馈图片ID
     * @return 反馈图片
     */
    public FfwyFeedbackPhoto selectFfwyFeedbackPhotoById(Long photoId);

    /**
     * 查询反馈图片列表
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 反馈图片集合
     */
    public List<FfwyFeedbackPhoto> selectFfwyFeedbackPhotoList(FfwyFeedbackPhoto ffwyFeedbackPhoto);

    /**
     * 新增反馈图片
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 结果
     */
    public int insertFfwyFeedbackPhoto(List<FfwyFeedbackPhoto>  ffwyFeedbackPhoto);

    /**
     * 修改反馈图片
     * 
     * @param ffwyFeedbackPhoto 反馈图片
     * @return 结果
     */
    public int updateFfwyFeedbackPhoto(FfwyFeedbackPhoto ffwyFeedbackPhoto);

    /**
     * 批量删除反馈图片
     * 
     * @param photoIds 需要删除的反馈图片ID
     * @return 结果
     */
    public int deleteFfwyFeedbackPhotoByIds(Long[] photoIds);

    /**
     * 删除反馈图片信息
     * 
     * @param photoId 反馈图片ID
     * @return 结果
     */
    public int deleteFfwyFeedbackPhotoById(Long photoId);
}
