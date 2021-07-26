package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyPaymentMapper;
import com.ruoyi.system.domain.FfwyPayment;
import com.ruoyi.system.service.IFfwyPaymentService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyPaymentServiceImpl implements IFfwyPaymentService 
{
    @Autowired
    private FfwyPaymentMapper ffwyPaymentMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param paymentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyPayment selectFfwyPaymentById(Integer paymentId)
    {
        return ffwyPaymentMapper.selectFfwyPaymentById(paymentId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPayment 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyPayment> selectFfwyPaymentList(FfwyPayment ffwyPayment)
    {
        return ffwyPaymentMapper.selectFfwyPaymentList(ffwyPayment);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPayment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyPayment(FfwyPayment ffwyPayment)
    {
        return ffwyPaymentMapper.insertFfwyPayment(ffwyPayment);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPayment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyPayment(FfwyPayment ffwyPayment)
    {
        return ffwyPaymentMapper.updateFfwyPayment(ffwyPayment);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param paymentIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPaymentByIds(Integer[] paymentIds)
    {
        return ffwyPaymentMapper.deleteFfwyPaymentByIds(paymentIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param paymentId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPaymentById(Integer paymentId)
    {
        return ffwyPaymentMapper.deleteFfwyPaymentById(paymentId);
    }
}
