package com.ruoyi.system.service;

import com.ruoyi.system.domain.aftersale.FfwyAfterType;

import java.util.List;

/**
 * 售后类型Service接口
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
public interface IFfwyAfterTypeService 
{
    /**
     * 查询售后类型
     * 
     * @param afterTypeId 售后类型ID
     * @return 售后类型
     */
    public FfwyAfterType selectFfwyAfterTypeById(Long afterTypeId);

    /**
     * 查询售后类型列表
     * 
     * @param ffwyAfterType 售后类型
     * @return 售后类型集合
     */
    public List<FfwyAfterType> selectFfwyAfterTypeList(FfwyAfterType ffwyAfterType);

    /**
     * 新增售后类型
     * 
     * @param ffwyAfterType 售后类型
     * @return 结果
     */
    public int insertFfwyAfterType(FfwyAfterType ffwyAfterType);

    /**
     * 修改售后类型
     * 
     * @param ffwyAfterType 售后类型
     * @return 结果
     */
    public int updateFfwyAfterType(FfwyAfterType ffwyAfterType);

    /**
     * 批量删除售后类型
     * 
     * @param afterTypeIds 需要删除的售后类型ID
     * @return 结果
     */
    public int deleteFfwyAfterTypeByIds(Long[] afterTypeIds);

    /**
     * 删除售后类型信息
     * 
     * @param afterTypeId 售后类型ID
     * @return 结果
     */
    public int deleteFfwyAfterTypeById(Long afterTypeId);

    List<FfwyAfterType> selectFfwyAfterTypeLists();
}
