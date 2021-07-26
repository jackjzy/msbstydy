package com.ruoyi.system.mapper.shop;

import java.util.List;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺图片Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface FfwyShopPhotoMapper 
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
     * 根据shopId查询店铺图片列表
     *
     * @param shopId 店铺id
     * @return 店铺图片集合
     */
    public List<FfwyShopPhoto> selectFfwyShopPhotoListByShopId(Long shopId);

    /**
     * 新增店铺图片
     * 
     * @param ffwyShopPhoto 店铺图片
     * @return 结果
     */
    public int insertFfwyShopPhoto(FfwyShopPhoto ffwyShopPhoto);
    /**
     * 新增店铺图片
     *
     * @param ffwyShopPhotos 店铺图片
     * @return 结果
     */
    public int insertFfwyShopPhotos(@Param("list") List<FfwyShopPhoto> ffwyShopPhotos);

    /**
     * 修改店铺图片
     * 
     * @param ffwyShopPhoto 店铺图片
     * @return 结果
     */
    public int updateFfwyShopPhoto(FfwyShopPhoto ffwyShopPhoto);

    /**
     * 删除店铺图片
     * 
     * @param photoId 店铺图片ID
     * @return 结果
     */
    public int deleteFfwyShopPhotoById(Long photoId);

    /**
     * 批量删除店铺图片
     * 
     * @param photoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyShopPhotoByIds(Long[] photoIds);
}
