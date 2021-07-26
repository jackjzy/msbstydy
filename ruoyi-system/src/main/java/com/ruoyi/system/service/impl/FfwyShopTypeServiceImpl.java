package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.shop.FfwyShopTypeMapper;
import com.ruoyi.system.domain.shop.FfwyShopType;
import com.ruoyi.system.service.IFfwyShopTypeService;

/**
 * 店铺类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-20
 */
@Service
public class FfwyShopTypeServiceImpl implements IFfwyShopTypeService 
{
    @Autowired
    private FfwyShopTypeMapper ffwyShopTypeMapper;

    /**
     * 查询店铺类型
     * 
     * @param shopType 店铺类型ID
     * @return 店铺类型
     */
    @Override
    public FfwyShopType selectFfwyShopTypeById(Integer shopType)
    {
        return ffwyShopTypeMapper.selectFfwyShopTypeById(shopType);
    }

    /**
     * 查询店铺类型列表
     * 
     * @param ffwyShopType 店铺类型
     * @return 店铺类型
     */
    @Override
    public List<FfwyShopType> selectFfwyShopTypeList(FfwyShopType ffwyShopType)
    {
        return ffwyShopTypeMapper.selectFfwyShopTypeList(ffwyShopType);
    }

    /**
     * 新增店铺类型
     * 
     * @param ffwyShopType 店铺类型
     * @return 结果
     */
    @Override
    public int insertFfwyShopType(FfwyShopType ffwyShopType)
    {
        return ffwyShopTypeMapper.insertFfwyShopType(ffwyShopType);
    }

    /**
     * 修改店铺类型
     * 
     * @param ffwyShopType 店铺类型
     * @return 结果
     */
    @Override
    public int updateFfwyShopType(FfwyShopType ffwyShopType)
    {
        return ffwyShopTypeMapper.updateFfwyShopType(ffwyShopType);
    }

    /**
     * 批量删除店铺类型
     * 
     * @param shopTypes 需要删除的店铺类型ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopTypeByIds(Integer[] shopTypes)
    {
        return ffwyShopTypeMapper.deleteFfwyShopTypeByIds(shopTypes);
    }

    /**
     * 删除店铺类型信息
     * 
     * @param shopType 店铺类型ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopTypeById(Integer shopType)
    {
        return ffwyShopTypeMapper.deleteFfwyShopTypeById(shopType);
    }
}
