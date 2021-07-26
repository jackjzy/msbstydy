package com.ruoyi.system.mapper.combomeal;


import com.ruoyi.system.domain.combomeal.FfwyCombomeal;
import com.ruoyi.system.domain.video.FfwyVideoHotVo;
import com.ruoyi.system.domain.video.FfwyVideoHotsVo;
import com.ruoyi.system.domain.vo.CombomealCatVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resources;
import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface FfwyCombomealMapper
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param combomealId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyCombomeal selectFfwyCombomealById(Long combomealId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyCombomeal 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyCombomeal> selectFfwyCombomealList(FfwyCombomeal ffwyCombomeal);
    public List<FfwyCombomeal> selectFfwyCombomeal(FfwyCombomeal ffwyCombomeal);

    /**
     * 分类查询
     * @param categoryId
     * @return
     */
    public List<FfwyCombomeal> selectCombomealByCategoryId(@Param("categoryId") Long categoryId ,@Param("front") Integer front, @Param("after") Integer after);

    public FfwyCombomeal selectCombomealByCombomealId(Long combomealId);



    public Integer getCountByCategoryId(Long categoryId);
    /**softOutfit
     * 新增【请填写功能名称】
     * 
     * @param ffwyCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomeal(FfwyCombomeal ffwyCombomeal);


    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyCombomeal(FfwyCombomeal ffwyCombomeal);

    /**
     * 删除【请填写功能名称】
     * 
     * @param combomealId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealById(Long combomealId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param combomealIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyCombomealByIds(Long[] combomealIds);

    public CombomealCatVo selectCombomealById(Long combomealId);

    List<FfwyCombomeal> selectCombomealAll(FfwyCombomeal ffwyCombomeal);

    List<FfwyVideoHotVo> selectFfwyCombomealBylists();

    List<FfwyVideoHotsVo> selectFfwyCombomealfindBylists(@Param("hotId") Long hotId);
}
