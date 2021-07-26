package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * 店铺图片Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwyShopPhotoService 
{
    /**
     * 查询店铺图片
     * 
     * @param photoId 店铺图片ID
     * @return 店铺图片
     */
    public FfwyShopPhoto selectFfwyShopPhotoById(Long photoId);

    /**
     * 查询店铺图片列表
     * 
     * @param ffwyShopPhoto 店铺图片
     * @return 店铺图片集合
     */
    public List<FfwyShopPhoto> selectFfwyShopPhotoList(FfwyShopPhoto ffwyShopPhoto);

    /**
     * 新增店铺图片
     * 
     * @param ffwyShopPhotos 店铺图片
     * @return 结果
     */
    public int insertFfwyShopPhotos( List<FfwyShopPhoto> ffwyShopPhotos);

    public Map<String,Object> insertFfwyShopPhoto(Long shopId, MultipartFile file);

    /**
     * 修改店铺图片
     * 
     * @param ffwyShopPhoto 店铺图片
     * @return 结果
     */

    public int updateFfwyShopPhoto(FfwyShopPhoto ffwyShopPhoto);

    /**
     * 批量删除店铺图片
     * 
     * @param photoIds 需要删除的店铺图片ID
     * @return 结果
     */
    public int deleteFfwyShopPhotoByIds(Long[] photoIds);

    /**
     * 删除店铺图片信息
     * 
     * @param photoId 店铺图片ID
     * @return 结果
     */
    public int deleteFfwyShopPhotoById(Long photoId);
}
