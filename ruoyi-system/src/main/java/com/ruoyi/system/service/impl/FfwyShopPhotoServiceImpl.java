package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.shop.FfwyShopPhotoMapper;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import com.ruoyi.system.service.IFfwyShopPhotoService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 店铺图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FfwyShopPhotoServiceImpl implements IFfwyShopPhotoService 
{
    @Autowired
    private FfwyShopPhotoMapper ffwyShopPhotoMapper;

    /**
     * 查询店铺图片
     * 
     * @param photoId 店铺图片ID
     * @return 店铺图片
     */
    @Override
    public FfwyShopPhoto selectFfwyShopPhotoById(Long photoId)
    {
        return ffwyShopPhotoMapper.selectFfwyShopPhotoById(photoId);
    }

    /**
     * 查询店铺图片列表
     * 
     * @param ffwyShopPhoto 店铺图片
     * @return 店铺图片
     */
    @Override
    public List<FfwyShopPhoto> selectFfwyShopPhotoList(FfwyShopPhoto ffwyShopPhoto)
    {
        return ffwyShopPhotoMapper.selectFfwyShopPhotoList(ffwyShopPhoto);
    }

    @Override
    public int insertFfwyShopPhotos(List<FfwyShopPhoto> ffwyShopPhotos) {
        return ffwyShopPhotoMapper.insertFfwyShopPhotos(ffwyShopPhotos);
    }

    /**
     * 新增店铺图片
     * 
     * @param shopId 店铺图片
     * @return 结果
     */
    @Override
    public Map<String,Object> insertFfwyShopPhoto(Long shopId , MultipartFile file)
    {
        Map<String,Object> map=new HashMap<>();
        FfwyShopPhoto ffwyShopPhoto = new FfwyShopPhoto();
        ffwyShopPhoto.setShopId(shopId);
        String s = CosUtil.CosUpload(file);
        ffwyShopPhoto.setPhototPath(s);
        ffwyShopPhoto.setCreateTime(DateUtils.getNowDate());
        ffwyShopPhoto.setTime(DateUtils.getNowDate().getTime());
        ffwyShopPhoto.setStatus("1");
         ffwyShopPhotoMapper.insertFfwyShopPhoto(ffwyShopPhoto);

        map.put("photoUrl",s);
        map.put("photoId",ffwyShopPhoto.getPhotoId());

         return map;
    }

    /**
     * 修改店铺图片
     * 
     * @param ffwyShopPhoto 店铺图片
     * @return 结果
     */
    @Override
    public int updateFfwyShopPhoto(FfwyShopPhoto ffwyShopPhoto)
    {

        return ffwyShopPhotoMapper.updateFfwyShopPhoto(ffwyShopPhoto);
    }

    /**
     * 批量删除店铺图片
     * 
     * @param photoIds 需要删除的店铺图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopPhotoByIds(Long[] photoIds)
    {
        int i = ffwyShopPhotoMapper.deleteFfwyShopPhotoByIds(photoIds);
        //删除服务器上的文件
        for (Long photoId : photoIds) {
            FfwyShopPhoto ffwyShopPhoto = ffwyShopPhotoMapper.selectFfwyShopPhotoById(photoId);
            String phototPath = ffwyShopPhoto.getPhototPath();
            CosUtil.deletePhoto(phototPath);
        }
        return i;
    }

    /**
     * 删除店铺图片信息
     * 
     * @param photoId 店铺图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopPhotoById(Long photoId)
    {
        FfwyShopPhoto ffwyShopPhoto = ffwyShopPhotoMapper.selectFfwyShopPhotoById(photoId);
        System.out.println(ffwyShopPhoto);
        String phototPath = ffwyShopPhoto.getPhototPath();
        CosUtil.deletePhoto(phototPath);
        return ffwyShopPhotoMapper.deleteFfwyShopPhotoById(photoId);
    }
}
