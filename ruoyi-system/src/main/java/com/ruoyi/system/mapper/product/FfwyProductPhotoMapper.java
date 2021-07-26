package com.ruoyi.system.mapper.product;

import java.util.List;

import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductPhoto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Repository
public interface FfwyProductPhotoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyProductPhoto selectFfwyProductPhotoById(Long id);

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyProductPhoto selectFfwyProductPhotoByType(@Param("productId") Long productId, @Param("photoType") String photoType ,@Param("photoStatus") String photoStatus);


    public FfwyProductPhoto selectproductPhotoListBySort(@Param("productId") Long productId, @Param("imgSort")Long imgSort,@Param("photoType") String photoType ,@Param("photoStatus") String photoStatus);

    /**
     * 根据productId查询商品图片 信息
     * @param productId
     * @return
     */
    public List<FfwyProductPhoto> selectFfwyProductPhotoListByProductId(Long productId);

    public List<FfwyProductPhoto> selectFfwyProductPhotoListParticulars(Long productId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyProductPhoto 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyProductPhoto> selectFfwyProductPhotoList(FfwyProductPhoto ffwyProductPhoto);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyProductPhoto 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyProductPhoto(FfwyProductPhoto ffwyProductPhoto);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyProductPhoto 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyProductPhoto(FfwyProductPhoto ffwyProductPhoto);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyProductPhotoById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProductPhotoByIds(Long[] ids);

    List<FfwyProductPhoto> selectFfwyProductPhotoListByDescs(Long productId);

    int deleteFfwyProductPhotoByProductId(Long productId);
}
