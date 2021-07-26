package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.mapper.combomealorders.FfwyMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyMaterialService;

/**
 * 装修材料Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
@Service
public class FfwyMaterialServiceImpl implements IFfwyMaterialService 
{
    @Autowired
    private FfwyMaterialMapper ffwyMaterialMapper;

    /**
     * 查询装修材料
     * 
     * @param materialId 装修材料ID
     * @return 装修材料
     */
    @Override
    public FfwyMaterial selectFfwyMaterialById(Long materialId)
    {
        return ffwyMaterialMapper.selectFfwyMaterialById(materialId);
    }

    /**
     * 查询装修材料列表
     * 
     * @param ffwyMaterial 装修材料
     * @return 装修材料
     */
    @Override
    public List<FfwyMaterial> selectFfwyMaterialList(FfwyMaterial ffwyMaterial)
    {
        return ffwyMaterialMapper.selectFfwyMaterialList(ffwyMaterial);
    }

    @Override
    public List<FfwyMaterial> selectProductlList(FfwyMaterial ffwyMaterial) {
        List<FfwyMaterial> ffwyMaterials = ffwyMaterialMapper.selectFfwyMaterialList(ffwyMaterial);
        ffwyMaterials.forEach(ffwyMaterial1 -> {
            FfwyProductSku sku = ffwyMaterial1.getSku();
            ffwyMaterial1.setMaterialName(sku.getSkuName()+sku.getSkuSpec());

        });
        return ffwyMaterials;
    }

    /**
     * 新增装修材料
     * 
     * @param ffwyMaterial 装修材料
     * @return 结果
     */
    @Override
    public int insertFfwyMaterial(FfwyMaterial ffwyMaterial)
    {
        ffwyMaterial.setCreateTime(DateUtils.getNowDate());
        return ffwyMaterialMapper.insertFfwyMaterial(ffwyMaterial);
    }

    @Override
    public int insertFfwyMaterials(List<FfwyMaterial> ffwyMaterials) {
        return 0;
    }

    /**
     * 修改装修材料
     * 
     * @param ffwyMaterial 装修材料
     * @return 结果
     */
    @Override
    public int updateFfwyMaterial(FfwyMaterial ffwyMaterial)
    {
        ffwyMaterial.setUpdateTime(DateUtils.getNowDate());
        return ffwyMaterialMapper.updateFfwyMaterial(ffwyMaterial);
    }

    /**
     * 批量删除装修材料
     * 
     * @param materialIds 需要删除的装修材料ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMaterialByIds(Long[] materialIds)
    {
        return ffwyMaterialMapper.deleteFfwyMaterialByIds(materialIds);
    }

    /**
     * 删除装修材料信息
     * 
     * @param materialId 装修材料ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMaterialById(Long materialId)
    {
        return ffwyMaterialMapper.deleteFfwyMaterialById(materialId);
    }
}
