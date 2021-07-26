package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.order.FfwyIncome;

/**
 * 收入明细Service接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IFfwyIncomeService 
{
    /**
     * 查询收入明细
     * 
     * @param incomeId 收入明细ID
     * @return 收入明细
     */
    public FfwyIncome selectFfwyIncomeById(Long incomeId);

    /**
     * 查询收入明细列表
     * 
     * @param ffwyIncome 收入明细
     * @return 收入明细集合
     */
    public List<FfwyIncome> selectFfwyIncomeList(FfwyIncome ffwyIncome);

    /**
     * 新增收入明细
     * 
     * @param ffwyIncome 收入明细
     * @return 结果
     */
    public int insertFfwyIncome(FfwyIncome ffwyIncome);

    /**
     * 修改收入明细
     * 
     * @param ffwyIncome 收入明细
     * @return 结果
     */
    public int updateFfwyIncome(FfwyIncome ffwyIncome);

    /**
     * 批量删除收入明细
     * 
     * @param incomeIds 需要删除的收入明细ID
     * @return 结果
     */
    public int deleteFfwyIncomeByIds(Long[] incomeIds);

    /**
     * 删除收入明细信息
     * 
     * @param incomeId 收入明细ID
     * @return 结果
     */
    public int deleteFfwyIncomeById(Long incomeId);
}
