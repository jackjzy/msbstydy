package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomealPlan;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 套餐设计图Service接口
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
public interface IFfwyOrderCombomealPlanService 
{
    /**
     * 查询套餐设计图
     * 
     * @param photoId 套餐设计图ID
     * @return 套餐设计图
     */
    public FfwyOrderCombomealPlan selectFfwyOrderCombomealPlanById(Long photoId);

    /**
     * 查询套餐设计图列表
     * 
     * @param ffwyOrderCombomealPlan 套餐设计图
     * @return 套餐设计图集合
     */
    public List<FfwyOrderCombomealPlan> selectFfwyOrderCombomealPlanList(FfwyOrderCombomealPlan ffwyOrderCombomealPlan);

    /**
     * 新增套餐设计图
     * 
     * @param orderId 订单的id
     * @param file 上传的图片
     * @return 结果
     */
    public int insertFfwyOrderCombomealPlan(Long orderId ,MultipartFile file);

    /**
     * 修改套餐设计图
     * 
     * @param ffwyOrderCombomealPlan 套餐设计图
     * @return 结果
     */
    public int updateFfwyOrderCombomealPlan(FfwyOrderCombomealPlan ffwyOrderCombomealPlan);

    /**
     * 批量删除套餐设计图
     * 
     * @param photoIds 需要删除的套餐设计图ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealPlanByIds(Long[] photoIds);

    /**
     * 删除套餐设计图信息
     * 
     * @param photoId 套餐设计图ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealPlanById(Long photoId);
}
