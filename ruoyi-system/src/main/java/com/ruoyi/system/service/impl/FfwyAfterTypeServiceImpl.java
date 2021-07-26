package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.aftersale.FfwyAfterType;
import com.ruoyi.system.mapper.aftersale.FfwyAfterTypeMapper;
import com.ruoyi.system.service.IFfwyAfterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 售后类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
@Service
public class FfwyAfterTypeServiceImpl implements IFfwyAfterTypeService
{
    @Autowired
    private FfwyAfterTypeMapper ffwyAfterTypeMapper;

    /**
     * 查询售后类型
     * 
     * @param afterTypeId 售后类型ID
     * @return 售后类型
     */
    @Override
    public FfwyAfterType selectFfwyAfterTypeById(Long afterTypeId)
    {
        return ffwyAfterTypeMapper.selectFfwyAfterTypeById(afterTypeId);
    }

    /**
     * 查询售后类型列表
     * 
     * @param ffwyAfterType 售后类型
     * @return 售后类型
     */
    @Override
    public List<FfwyAfterType> selectFfwyAfterTypeList(FfwyAfterType ffwyAfterType)
    {
        return ffwyAfterTypeMapper.selectFfwyAfterTypeList(ffwyAfterType);
    }

    /**
     * 新增售后类型
     * 
     * @param ffwyAfterType 售后类型
     * @return 结果
     */
    @Override
    public int insertFfwyAfterType(FfwyAfterType ffwyAfterType)
    {
        return ffwyAfterTypeMapper.insertFfwyAfterType(ffwyAfterType);
    }

    /**
     * 修改售后类型
     * 
     * @param ffwyAfterType 售后类型
     * @return 结果
     */
    @Override
    public int updateFfwyAfterType(FfwyAfterType ffwyAfterType)
    {
        return ffwyAfterTypeMapper.updateFfwyAfterType(ffwyAfterType);
    }

    /**
     * 批量删除售后类型
     * 
     * @param afterTypeIds 需要删除的售后类型ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterTypeByIds(Long[] afterTypeIds)
    {
        return ffwyAfterTypeMapper.deleteFfwyAfterTypeByIds(afterTypeIds);
    }

    /**
     * 删除售后类型信息
     * 
     * @param afterTypeId 售后类型ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterTypeById(Long afterTypeId)
    {
        return ffwyAfterTypeMapper.deleteFfwyAfterTypeById(afterTypeId);
    }

    @Override
    public List<FfwyAfterType> selectFfwyAfterTypeLists() {
        return ffwyAfterTypeMapper.selectFfwyAfterTypeLists();
    }
}
