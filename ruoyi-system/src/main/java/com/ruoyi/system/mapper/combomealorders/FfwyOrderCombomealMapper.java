package com.ruoyi.system.mapper.combomealorders;

import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.vo.FfwyOrderCombomealVo;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface FfwyOrderCombomealMapper 
{
    /**
     * 查询【请填写功能名称】
     *
     * @param orderId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyOrderCombomeal selectFfwyOrderCombomealById(Long orderId);

    /**
     * 查询【请填写功能名称】
     *
     * @param orderNumber 【请填写功能名称】订单号
     * @return 【请填写功能名称】
     */
    public FfwyOrderCombomeal selectFfwyOrderCombomealByOrderNumber(String orderNumber);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyOrderCombomeal> selectFfwyOrderCombomealList(FfwyOrderCombomeal ffwyOrderCombomeal);

    /**
     * 查询【所有订单】列表PC
     *
     * @param ffwyOrderCombomealVo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyOrderCombomeal> selectFfwyOrderCombomealListPC(FfwyOrderCombomealVo ffwyOrderCombomealVo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyOrderCombomeal(FfwyOrderCombomeal ffwyOrderCombomeal);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyOrderCombomeal(FfwyOrderCombomeal ffwyOrderCombomeal);

    /**
     * 删除【请填写功能名称】
     * 
     * @param orderId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealById(Long orderId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealByIds(Long[] orderIds);

    List<FfwyOrderCombomeal> selectFfwyOrderCombomealByorderType();
}
