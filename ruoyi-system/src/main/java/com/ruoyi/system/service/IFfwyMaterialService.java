package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomealorders.FfwyMaterial;

import java.util.List;


/**
 * 装修材料Service接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface IFfwyMaterialService 
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
     * 查询套餐商品列表
     *
     * @param ffwyMaterial 装修材料
     * @return 装修材料集合
     */
    public List<FfwyMaterial> selectProductlList(FfwyMaterial ffwyMaterial);

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
     * 批量删除装修材料
     * 
     * @param materialIds 需要删除的装修材料ID
     * @return 结果
     */
    public int deleteFfwyMaterialByIds(Long[] materialIds);

    /**
     * 删除装修材料信息
     * 
     * @param materialId 装修材料ID
     * @return 结果
     */
    public int deleteFfwyMaterialById(Long materialId);
}
