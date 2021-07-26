package com.ruoyi.system.mapper.shop;

import java.util.List;
import com.ruoyi.system.domain.shop.FfwyShopType;

/**
 * 店铺类型Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-20
 */
public interface FfwyShopTypeMapper 
{
    /**
     * 查询店铺类型
     * 
     * @param shopType 店铺类型ID
     * @return 店铺类型
     */
    public FfwyShopType selectFfwyShopTypeById(Integer shopType);

    /**
     * 查询店铺类型列表
     * 
     * @param ffwyShopType 店铺类型
     * @return 店铺类型集合
     */
    public List<FfwyShopType> selectFfwyShopTypeList(FfwyShopType ffwyShopType);

    /**
     * 新增店铺类型
     * 
     * @param ffwyShopType 店铺类型
     * @return 结果
     */
    public int insertFfwyShopType(FfwyShopType ffwyShopType);

    /**
     * 修改店铺类型
     * 
     * @param ffwyShopType 店铺类型
     * @return 结果
     */
    public int updateFfwyShopType(FfwyShopType ffwyShopType);

    /**
     * 删除店铺类型
     * 
     * @param shopType 店铺类型ID
     * @return 结果
     */
    public int deleteFfwyShopTypeById(Integer shopType);

    /**
     * 批量删除店铺类型
     * 
     * @param shopTypes 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyShopTypeByIds(Integer[] shopTypes);
}
