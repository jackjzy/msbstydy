package com.ruoyi.system.mapper.aftersale;

import com.ruoyi.system.domain.aftersale.FfwyAfterStatus;
import com.ruoyi.system.domain.aftersale.FfwyAfterType;
import com.ruoyi.system.domain.vo.FfwyAftersaletypeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 售后类型Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
@Repository
public interface FfwyAfterTypeMapper 
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
     * 删除售后类型
     * 
     * @param afterTypeId 售后类型ID
     * @return 结果
     */
    public int deleteFfwyAfterTypeById(Long afterTypeId);

    /**
     * 批量删除售后类型
     * 
     * @param afterTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyAfterTypeByIds(Long[] afterTypeIds);

    List<FfwyAfterType> selectFfwyAfterTypeLists();

    List<FfwyAftersaletypeVo> selectFfwyOrderDetailsByorderDetailsId(@Param("afterSaleid") Integer afterSaleid);

    List<FfwyAfterStatus> selectFfwyAfterStatus(Integer afterSaleid);
}
