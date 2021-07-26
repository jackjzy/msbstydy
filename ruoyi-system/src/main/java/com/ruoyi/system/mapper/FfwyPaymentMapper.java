package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyPayment;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface FfwyPaymentMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param paymentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyPayment selectFfwyPaymentById(Integer paymentId);

    /**
     * 查询【请填写功能名称】
     *
     * @param paymentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public String selectpatmentTypeById(Integer paymentId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPayment 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyPayment> selectFfwyPaymentList(FfwyPayment ffwyPayment);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPayment 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyPayment(FfwyPayment ffwyPayment);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPayment 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyPayment(FfwyPayment ffwyPayment);

    /**
     * 删除【请填写功能名称】
     * 
     * @param paymentId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPaymentById(Integer paymentId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param paymentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyPaymentByIds(Integer[] paymentIds);
}
