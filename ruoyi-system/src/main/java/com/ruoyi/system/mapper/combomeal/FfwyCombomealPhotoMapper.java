package com.ruoyi.system.mapper.combomeal;

import com.ruoyi.system.domain.combomeal.FfwyCombomealPhoto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-07
 */
public interface FfwyCombomealPhotoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param photoId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomealPhoto selectFfwyCombomealPhotoById(Long photoId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomealPhoto 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomealPhoto> selectFfwyCombomealPhotoList(FfwyCombomealPhoto ffwyCombomealPhoto);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomealPhoto 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomealPhoto(FfwyCombomealPhoto ffwyCombomealPhoto);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealPhoto 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomealPhoto(FfwyCombomealPhoto ffwyCombomealPhoto);

    /**
     * 删除【请填写功能名称】
     * 
     * @param photoId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealPhotoById(Long photoId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param photoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyCombomealPhotoByIds(Long[] photoIds);

    int insertFfwyCombomealPhotos(List<FfwyCombomealPhoto> ffwycombomealPhotos);

    void deleteFfwyCombomealPhotocIds(@Param("combomealId") Long combomealId,@Param("photoType") String photoType);

    int deleteFfwyPhotos(@Param("path") String path);
}
