package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyContract;

/**
 * 合同信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-07-15
 */
public interface FfwyContractMapper 
{
    /**
     * 查询合同信息
     * 
     * @param contractId 合同信息ID
     * @return 合同信息
     */
    public FfwyContract selectFfwyContractById(Long contractId);

    /**
     * 查询合同信息列表
     * 
     * @param ffwyContract 合同信息
     * @return 合同信息集合
     */
    public List<FfwyContract> selectFfwyContractList(FfwyContract ffwyContract);

    /**
     * 新增合同信息
     * 
     * @param ffwyContract 合同信息
     * @return 结果
     */
    public int insertFfwyContract(FfwyContract ffwyContract);

    /**
     * 修改合同信息
     * 
     * @param ffwyContract 合同信息
     * @return 结果
     */
    public int updateFfwyContract(FfwyContract ffwyContract);

    /**
     * 删除合同信息
     * 
     * @param contractId 合同信息ID
     * @return 结果
     */
    public int deleteFfwyContractById(Long contractId);

    /**
     * 批量删除合同信息
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyContractByIds(Long[] contractIds);
}
