package com.ruoyi.system.mapper.aftersale;

import com.ruoyi.system.domain.aftersale.FfwyAfterPhoto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-28
 */
@Repository
public interface FfwyAfterPhotoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param afterPhotoId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyAfterPhoto selectFfwyAfterPhotoById(Long afterPhotoId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyAfterPhoto 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyAfterPhoto> selectFfwyAfterPhotoList(FfwyAfterPhoto ffwyAfterPhoto);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyAfterPhoto 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyAfterPhoto(FfwyAfterPhoto ffwyAfterPhoto);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyAfterPhoto 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyAfterPhoto(FfwyAfterPhoto ffwyAfterPhoto);

    /**
     * 删除【请填写功能名称】
     * 
     * @param afterPhotoId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyAfterPhotoById(Long afterPhotoId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param afterPhotoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyAfterPhotoByIds(Long[] afterPhotoIds);

    int insertFfwyAfterPhotos(List<FfwyAfterPhoto> ffwyAfterPhotos);
}
