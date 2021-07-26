package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyContractMapper;
import com.ruoyi.system.domain.FfwyContract;
import com.ruoyi.system.service.IFfwyContractService;

/**
 * 合同信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-07-15
 */
@Service
public class FfwyContractServiceImpl implements IFfwyContractService 
{
    @Autowired
    private FfwyContractMapper ffwyContractMapper;

    /**
     * 查询合同信息
     * 
     * @param contractId 合同信息ID
     * @return 合同信息
     */
    @Override
    public FfwyContract selectFfwyContractById(Long contractId)
    {
        return ffwyContractMapper.selectFfwyContractById(contractId);
    }

    /**
     * 查询合同信息列表
     * 
     * @param ffwyContract 合同信息
     * @return 合同信息
     */
    @Override
    public List<FfwyContract> selectFfwyContractList(FfwyContract ffwyContract)
    {
        return ffwyContractMapper.selectFfwyContractList(ffwyContract);
    }

    /**
     * 新增合同信息
     * 
     * @param ffwyContract 合同信息
     * @return 结果
     */
    @Override
    public int insertFfwyContract(FfwyContract ffwyContract)
    {
        return ffwyContractMapper.insertFfwyContract(ffwyContract);
    }

    /**
     * 修改合同信息
     * 
     * @param ffwyContract 合同信息
     * @return 结果
     */
    @Override
    public int updateFfwyContract(FfwyContract ffwyContract)
    {
        return ffwyContractMapper.updateFfwyContract(ffwyContract);
    }

    /**
     * 批量删除合同信息
     * 
     * @param contractIds 需要删除的合同信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyContractByIds(Long[] contractIds)
    {
        return ffwyContractMapper.deleteFfwyContractByIds(contractIds);
    }

    /**
     * 删除合同信息信息
     * 
     * @param contractId 合同信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyContractById(Long contractId)
    {
        return ffwyContractMapper.deleteFfwyContractById(contractId);
    }
}
