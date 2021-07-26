package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.product.FfwyProductPhoto;
import com.ruoyi.system.mapper.product.FfwyProductPhotoMapper;
import com.ruoyi.system.service.IFfwyProductPhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyProductPhotoServiceImpl implements IFfwyProductPhotoService
{
    private final static Logger LOGGER = LoggerFactory.getLogger(FfwyProductPhotoServiceImpl.class);

    @Autowired
    private FfwyProductPhotoMapper ffwyProductPhotoMapper;

    /**
     * 查询商品图片
     * 
     * @param id 商品图片ID
     * @return 商品图片
     */
    @Override
    public FfwyProductPhoto selectFfwyProductPhotoById(Long id)
    {
        return ffwyProductPhotoMapper.selectFfwyProductPhotoById(id);
    }

    /**
     * 查询商品图片列表
     * 
     * @param ffwyProductPhoto 商品图片
     * @return 商品图片
     */
    @Override
    public List<FfwyProductPhoto> selectFfwyProductPhotoList(FfwyProductPhoto ffwyProductPhoto)
    {
        return ffwyProductPhotoMapper.selectFfwyProductPhotoList(ffwyProductPhoto);
    }

    /**
     * 新增商品图片
     * @param productPhoto
     * @param productId
     * @return
     */
    @Override
    public int insertFfwyProductPhoto(FfwyProductPhoto productPhoto,Long productId)
    {
       return this.insert(productPhoto,productId);
    }

    @Override
    public String uploadPhotos(MultipartFile file) {
        return CosUtil.CosUpload(file);
    }

    @Override
    public int insertFfwyProductPhotoList(List<FfwyProductPhoto> productPhotos, Long productId) {

        int i = 0;
        for (FfwyProductPhoto productPhoto : productPhotos) {
            i = this.insert(productPhoto,productId);
            if (i != 1){
                return i;
            }
        }
        return i;
    }

    private int insert(FfwyProductPhoto productPhoto,Long productId){
//        String path = CosUtil.CosUpload(file);
//        CosUtil.isFileType(file);
        FfwyProductPhoto newProductPhoto = null;


        if ("2".equals(productPhoto.getPhotoType()) || "4".equals(productPhoto.getPhotoType())) { // 3d图  或  视频
            newProductPhoto = ffwyProductPhotoMapper.selectFfwyProductPhotoByType(productId, productPhoto.getPhotoType(),"0");
            if (newProductPhoto != null){
                // 原有下架
                newProductPhoto.setPhotoStatus("1");
                ffwyProductPhotoMapper.updateFfwyProductPhoto(newProductPhoto);
            }

            newProductPhoto = productPhoto;
            if ("4".equals(newProductPhoto.getPhotoType())){
                // 如果是3d全景图，则把3d全景图的截图也保存下来
                String[] split = newProductPhoto.getProductPhotopath().split(Constants.PHOTO_SEPARATOR);
                if (split.length >=2){
                    newProductPhoto.setProductPhotopath(split[1]); // 截图
                    newProductPhoto.setPanorama(split[0]);    // 全景图
                }

            }
            newProductPhoto.setImgSort(0L);
        }else {
            List<FfwyProductPhoto> ffwyProductPhotos = ffwyProductPhotoMapper.selectFfwyProductPhotoList(new FfwyProductPhoto(productId, "1","0"));
            newProductPhoto = productPhoto;
            if (ffwyProductPhotos.size() >= 4){  // 库中上架状态轮播图超过四张，第一张下架
                ffwyProductPhotos.get(0).setPhotoStatus("1");
                ffwyProductPhotoMapper.updateFfwyProductPhoto(ffwyProductPhotos.get(0));
            }

            productPhoto.setImgSort(ffwyProductPhotos.size() + 1L);

        }


        newProductPhoto.setProductId(productId);
        newProductPhoto.setPhotoStatus("0");
//        newProductPhoto.setProductPhotopath(path);
        newProductPhoto.setCreateTime(new Date());

        LOGGER.info("FfwyProductPhotoServiceImpl   insert   productPhoto:",newProductPhoto);
        return ffwyProductPhotoMapper.insertFfwyProductPhoto(newProductPhoto);
    }

    /**
     * 上架/下架商品图片
     * @param id
     * @param photoStatus
     * @return
     */
    @Override
    public int updateFfwyProductPhoto(Long id, String photoStatus)
    {
        return ffwyProductPhotoMapper.updateFfwyProductPhoto(new FfwyProductPhoto(id,photoStatus));
    }

    /**
     * 批量删除商品图片
     * 
     * @param ids 需要删除的商品图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductPhotoByIds(Long[] ids)
    {
        return ffwyProductPhotoMapper.deleteFfwyProductPhotoByIds(ids);
    }

    /**
     * 删除商品图片信息
     * 
     * @param id 商品图片ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductPhotoById(Long id)
    {
        return ffwyProductPhotoMapper.deleteFfwyProductPhotoById(id);
    }

    @Override
    public int deleteFfwyProductPhotoByProductId(Long productId) {
        return ffwyProductPhotoMapper.deleteFfwyProductPhotoByProductId(productId);
    }
}
