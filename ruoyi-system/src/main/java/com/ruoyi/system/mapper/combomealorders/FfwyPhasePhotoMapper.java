package com.ruoyi.system.mapper.combomealorders;

import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhasePhoto;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-22
 */
public interface FfwyPhasePhotoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param phasePhotoId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyPhasePhoto selectFfwyPhasePhotoById(Long phasePhotoId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPhasePhoto 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyPhasePhoto> selectFfwyPhasePhotoList(FfwyPhasePhoto ffwyPhasePhoto);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPhasePhoto 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyPhasePhoto(FfwyPhasePhoto ffwyPhasePhoto);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPhasePhoto 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyPhasePhoto(FfwyPhasePhoto ffwyPhasePhoto);

    /**
     * 删除【请填写功能名称】
     * 
     * @param phasePhotoId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPhasePhotoById(Long phasePhotoId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param phasePhotoIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyPhasePhotoByIds(Long[] phasePhotoIds);
}
