package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.order.FfwyIncomeMapper;
import com.ruoyi.system.domain.order.FfwyIncome;
import com.ruoyi.system.service.IFfwyIncomeService;

/**
 * 收入明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
public class FfwyIncomeServiceImpl implements IFfwyIncomeService 
{
    @Autowired
    private FfwyIncomeMapper ffwyIncomeMapper;

    /**
     * 查询收入明细
     * 
     * @param incomeId 收入明细ID
     * @return 收入明细
     */
    @Override
    public FfwyIncome selectFfwyIncomeById(Long incomeId)
    {
        return ffwyIncomeMapper.selectFfwyIncomeById(incomeId);
    }

    /**
     * 查询收入明细列表
     * 
     * @param ffwyIncome 收入明细
     * @return 收入明细
     */
    @Override
    public List<FfwyIncome> selectFfwyIncomeList(FfwyIncome ffwyIncome)
    {
        return ffwyIncomeMapper.selectFfwyIncomeList(ffwyIncome);
    }

    /**
     * 新增收入明细
     * 
     * @param ffwyIncome 收入明细
     * @return 结果
     */
    @Override
    public int insertFfwyIncome(FfwyIncome ffwyIncome)
    {
        return ffwyIncomeMapper.insertFfwyIncome(ffwyIncome);
    }

    /**
     * 修改收入明细
     * 
     * @param ffwyIncome 收入明细
     * @return 结果
     */
    @Override
    public int updateFfwyIncome(FfwyIncome ffwyIncome)
    {
        return ffwyIncomeMapper.updateFfwyIncome(ffwyIncome);
    }

    /**
     * 批量删除收入明细
     * 
     * @param incomeIds 需要删除的收入明细ID
     * @return 结果
     */
    @Override
    public int deleteFfwyIncomeByIds(Long[] incomeIds)
    {
        return ffwyIncomeMapper.deleteFfwyIncomeByIds(incomeIds);
    }

    /**
     * 删除收入明细信息
     * 
     * @param incomeId 收入明细ID
     * @return 结果
     */
    @Override
    public int deleteFfwyIncomeById(Long incomeId)
    {
        return ffwyIncomeMapper.deleteFfwyIncomeById(incomeId);
    }
}
