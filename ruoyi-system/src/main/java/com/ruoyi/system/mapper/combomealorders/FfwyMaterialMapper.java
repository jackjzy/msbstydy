package com.ruoyi.system.mapper.combomealorders;

import com.ruoyi.system.domain.combomealorders.FfwyMaterial;

import java.util.List;


/**
 * 装修材料Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface FfwyMaterialMapper 
{
    /**
     * 查询装修材料
     * 
     * @param materialId 装修材料ID
     * @return 装修材料
     */
    public FfwyMaterial selectFfwyMaterialById(Long materialId);

    /**
     * 查询装修材料列表
     * 
     * @param ffwyMaterial 装修材料
     * @return 装修材料集合
     */
    public List<FfwyMaterial> selectFfwyMaterialList(FfwyMaterial ffwyMaterial);

    /**
     * 新增装修材料
     * 
     * @param ffwyMaterial 装修材料
     * @return 结果
     */
    public int insertFfwyMaterial(FfwyMaterial ffwyMaterial);
    /**
     * 新增多个装修材料
     *
     * @param ffwyMaterials 多个装修材料
     * @return 结果
     */
    public int insertFfwyMaterials(List<FfwyMaterial> ffwyMaterials);

    /**
     * 修改装修材料
     * 
     * @param ffwyMaterial 装修材料
     * @return 结果
     */
    public int updateFfwyMaterial(FfwyMaterial ffwyMaterial);

    /**
     * 删除装修材料
     * 
     * @param materialId 装修材料ID
     * @return 结果
     */
    public int deleteFfwyMaterialById(Long materialId);

    /**
     * 批量删除装修材料
     * 
     * @param materialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyMaterialByIds(Long[] materialIds);
}
