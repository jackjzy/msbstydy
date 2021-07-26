package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomealPlan;
import com.ruoyi.system.mapper.combomealorders.FfwyOrderCombomealPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyOrderCombomealPlanService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 套餐设计图Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-10
 */
@Service
public class FfwyOrderCombomealPlanServiceImpl implements IFfwyOrderCombomealPlanService 
{
    @Autowired
    private FfwyOrderCombomealPlanMapper ffwyOrderCombomealPlanMapper;

    /**
     * 查询套餐设计图
     * 
     * @param photoId 套餐设计图ID
     * @return 套餐设计图
     */
    @Override
    public FfwyOrderCombomealPlan selectFfwyOrderCombomealPlanById(Long photoId)
    {
        return ffwyOrderCombomealPlanMapper.selectFfwyOrderCombomealPlanById(photoId);
    }

    /**
     * 查询套餐设计图列表
     * 
     * @param ffwyOrderCombomealPlan 套餐设计图
     * @return 套餐设计图
     */
    @Override
    public List<FfwyOrderCombomealPlan> selectFfwyOrderCombomealPlanList(FfwyOrderCombomealPlan ffwyOrderCombomealPlan)
    {
        return ffwyOrderCombomealPlanMapper.selectFfwyOrderCombomealPlanList(ffwyOrderCombomealPlan);
    }

    /**
     * 新增套餐设计图
     *
     * @param orderId 订单的id
     * @param file 上传的图片
     * @return 结果
     */
    @Override
    public int insertFfwyOrderCombomealPlan(Long orderId , MultipartFile file)
    {
        FfwyOrderCombomealPlan ffwyOrderCombomealPlan = new FfwyOrderCombomealPlan();
        ffwyOrderCombomealPlan.setOrderId(orderId);
        String s = CosUtil.CosUpload(file);
        ffwyOrderCombomealPlan.setPhotoPhath(s);
        ffwyOrderCombomealPlan.setCreateTime(DateUtils.getNowDate());
        return ffwyOrderCombomealPlanMapper.insertFfwyOrderCombomealPlan(ffwyOrderCombomealPlan);
    }

    /**
     * 修改套餐设计图
     * 
     * @param ffwyOrderCombomealPlan 套餐设计图
     * @return 结果
     */
    @Override
    public int updateFfwyOrderCombomealPlan(FfwyOrderCombomealPlan ffwyOrderCombomealPlan)
    {
        ffwyOrderCombomealPlan.setUpdateTime(DateUtils.getNowDate());
        return ffwyOrderCombomealPlanMapper.updateFfwyOrderCombomealPlan(ffwyOrderCombomealPlan);
    }

    /**
     * 批量删除套餐设计图
     * 
     * @param photoIds 需要删除的套餐设计图ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderCombomealPlanByIds(Long[] photoIds)
    {
        return ffwyOrderCombomealPlanMapper.deleteFfwyOrderCombomealPlanByIds(photoIds);
    }

    /**
     * 删除套餐设计图信息
     * 
     * @param photoId 套餐设计图ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderCombomealPlanById(Long photoId)
    {
        return ffwyOrderCombomealPlanMapper.deleteFfwyOrderCombomealPlanById(photoId);
    }
}
