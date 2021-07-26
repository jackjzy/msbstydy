package com.ruoyi.system.service;

import com.ruoyi.system.domain.product.FfwyProductPhoto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品图片Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyProductPhotoService
{
    /**
     * 查询商品图片
     * 
     * @param id 商品图片ID
     * @return 商品图片
     */
    public FfwyProductPhoto selectFfwyProductPhotoById(Long id);

    /**
     * 查询商品图片列表
     * 
     * @param ffwyProductPhoto 商品图片
     * @return 商品图片集合
     */
    public List<FfwyProductPhoto> selectFfwyProductPhotoList(FfwyProductPhoto ffwyProductPhoto);

    /**
     * 新增商品图片
     * @param productPhoto
     * @param productId
     * @return
     */
    public int insertFfwyProductPhoto(FfwyProductPhoto productPhoto,Long productId);

    /**
     * 上传图片
     * @param file
     * @return
     */
    public String uploadPhotos(MultipartFile file);

    /**
     * 多图片上传
     * @param productPhotos
     * @param productId
     * @return
     */
    public int insertFfwyProductPhotoList(List<FfwyProductPhoto> productPhotos, Long productId);

    /**
     * 上架/下架商品图片
     * @param id
     * @param photoStatus
     * @return
     */
    public int updateFfwyProductPhoto(Long id, String photoStatus);

    /**
     * 批量删除商品图片
     * 
     * @param ids 需要删除的商品图片ID
     * @return 结果
     */
    public int deleteFfwyProductPhotoByIds(Long[] ids);

    /**
     * 删除商品图片信息
     * 
     * @param id 商品图片ID
     * @return 结果
     */
    public int deleteFfwyProductPhotoById(Long id);

    public int deleteFfwyProductPhotoByProductId(Long productId);
}
