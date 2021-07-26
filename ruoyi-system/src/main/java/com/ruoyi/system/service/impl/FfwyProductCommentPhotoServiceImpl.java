package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.product.FfwyProductCommentPhotoMapper;
import com.ruoyi.system.domain.product.FfwyProductCommentPhoto;
import com.ruoyi.system.service.IFfwyProductCommentPhotoService;

/**
 * 商品评论图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
public class FfwyProductCommentPhotoServiceImpl implements IFfwyProductCommentPhotoService 
{
    @Autowired
    private FfwyProductCommentPhotoMapper ffwyProductCommentPhotoMapper;

    /**
     * 查询商品评论图片
     * 
     * @param productCommentPhotoId 商品评论图片ID
     * @return 商品评论图片
     */
    @Override
    public FfwyProductCommentPhoto selectFfwyProductCommentPhotoById(Long productCommentPhotoId)
    {
        return ffwyProductCommentPhotoMapper.selectFfwyProductCommentPhotoById(productCommentPhotoId);
    }

    /**
     * 查询商品评论图片列表
     * 
     * @param ffwyProductCommentPhoto 商品评论图片
     * @return 商品评论图片
     */
    @Override
    public List<FfwyProductCommentPhoto> selectFfwyProductCommentPhotoList(FfwyProductCommentPhoto ffwyProductCommentPhoto)
    {
        return ffwyProductCommentPhotoMapper.selectFfwyProductCommentPhotoList(ffwyProductCommentPhoto);
    }

    /**
     * 新增商品评论图片
     * 
     * @param ffwyProductCommentPhoto 商品评论图片
     * @return 结果
     */
    @Override
    public int insertFfwyProductCommentPhoto(FfwyProductCommentPhoto ffwyProductCommentPhoto)
    {
        return ffwyProductCommentPhotoMapper.insertFfwyProductCommentPhoto(ffwyProductCommentPhoto);
    }

    /**
     * 修改商品评论图片
     * 
     * @param ffwyProductCommentPhoto 商品评论图片
     * @return 结果
     */
    @Override
    public int updateFfwyProductCommentPhoto(FfwyProductCommentPhoto ffwyProductCommentPhoto)
    {
        return ffwyProductCommentPhotoMapper.updateFfwyProductCommentPhoto(ffwyProductCommentPhoto);
    }

    /**
     * 批量删除商品评论图片
     * 
     * @param productCommentPhotoIds 需要删除的商品评论图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCommentPhotoByIds(Long[] productCommentPhotoIds)
    {
        return ffwyProductCommentPhotoMapper.deleteFfwyProductCommentPhotoByIds(productCommentPhotoIds);
    }

    /**
     * 删除商品评论图片信息
     * 
     * @param productCommentPhotoId 商品评论图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCommentPhotoById(Long productCommentPhotoId)
    {
        return ffwyProductCommentPhotoMapper.deleteFfwyProductCommentPhotoById(productCommentPhotoId);
    }
}
