package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomeal.FfwyCombomealCommentPhoto;

import java.util.List;


/**
 * 套餐评论图片Service接口
 * 
 * @author ruoyi
 * @date 2021-05-24
 */
public interface IFfwyCombomealCommentPhotoService 
{
    /**
     * 查询套餐评论图片
     * 
     * @param photoId 套餐评论图片ID
     * @return 套餐评论图片
     */
    public FfwyCombomealCommentPhoto selectFfwyCombomealCommentPhotoById(Long photoId);

    /**
     * 查询套餐评论图片列表
     * 
     * @param ffwyCombomealCommentPhoto 套餐评论图片
     * @return 套餐评论图片集合
     */
    public List<FfwyCombomealCommentPhoto> selectFfwyCombomealCommentPhotoList(FfwyCombomealCommentPhoto ffwyCombomealCommentPhoto);

    /**
     * 新增套餐评论图片
     * 
     * @param ffwyCombomealCommentPhoto 套餐评论图片
     * @return 结果
     */
    public int insertFfwyCombomealCommentPhoto(FfwyCombomealCommentPhoto ffwyCombomealCommentPhoto);

    /**
     * 修改套餐评论图片
     * 
     * @param ffwyCombomealCommentPhoto 套餐评论图片
     * @return 结果
     */
    public int updateFfwyCombomealCommentPhoto(FfwyCombomealCommentPhoto ffwyCombomealCommentPhoto);

    /**
     * 批量删除套餐评论图片
     * 
     * @param photoIds 需要删除的套餐评论图片ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommentPhotoByIds(Long[] photoIds);

    /**
     * 删除套餐评论图片信息
     * 
     * @param photoId 套餐评论图片ID
     * @return 结果
     */
    public int deleteFfwyCombomealCommentPhotoById(Long photoId);
}
