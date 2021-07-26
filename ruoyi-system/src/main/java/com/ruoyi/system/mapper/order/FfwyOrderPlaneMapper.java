package com.ruoyi.system.mapper.order;

import com.ruoyi.system.domain.order.FfwyOrderPlane;
import com.ruoyi.system.domain.vo.FfwyOrderPlaneVo;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface FfwyOrderPlaneMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param planeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyOrderPlane selectFfwyOrderPlaneById(Long planeId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderPlane 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyOrderPlaneVo> selectFfwyOrderPlaneList(FfwyOrderPlane ffwyOrderPlane);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyOrderPlane 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyOrderPlane(FfwyOrderPlane ffwyOrderPlane);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyOrderPlane 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyOrderPlane(FfwyOrderPlane ffwyOrderPlane);

    /**
     * 删除【请填写功能名称】
     * 
     * @param planeId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyOrderPlaneById(Long planeId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param planeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyOrderPlaneByIds(Long[] planeIds);

    int updateFfwyOrderPlanestutas(FfwyOrderPlane ffwyOrderPlane);

    List<FfwyOrderPlaneVo> selectFfwyOrderPlaneLists();
}
