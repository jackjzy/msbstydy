package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.product.FfwyProductCommentPhoto;

/**
 * 商品评论图片Service接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IFfwyProductCommentPhotoService 
{
    /**
     * 查询商品评论图片
     * 
     * @param productCommentPhotoId 商品评论图片ID
     * @return 商品评论图片
     */
    public FfwyProductCommentPhoto selectFfwyProductCommentPhotoById(Long productCommentPhotoId);

    /**
     * 查询商品评论图片列表
     * 
     * @param ffwyProductCommentPhoto 商品评论图片
     * @return 商品评论图片集合
     */
    public List<FfwyProductCommentPhoto> selectFfwyProductCommentPhotoList(FfwyProductCommentPhoto ffwyProductCommentPhoto);

    /**
     * 新增商品评论图片
     * 
     * @param ffwyProductCommentPhoto 商品评论图片
     * @return 结果
     */
    public int insertFfwyProductCommentPhoto(FfwyProductCommentPhoto ffwyProductCommentPhoto);

    /**
     * 修改商品评论图片
     * 
     * @param ffwyProductCommentPhoto 商品评论图片
     * @return 结果
     */
    public int updateFfwyProductCommentPhoto(FfwyProductCommentPhoto ffwyProductCommentPhoto);

    /**
     * 批量删除商品评论图片
     * 
     * @param productCommentPhotoIds 需要删除的商品评论图片ID
     * @return 结果
     */
    public int deleteFfwyProductCommentPhotoByIds(Long[] productCommentPhotoIds);

    /**
     * 删除商品评论图片信息
     * 
     * @param productCommentPhotoId 商品评论图片ID
     * @return 结果
     */
    public int deleteFfwyProductCommentPhotoById(Long productCommentPhotoId);
}
