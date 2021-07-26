package com.ruoyi.back.service;

import java.util.List;
import com.ruoyi.back.domain.FfwyAuditStatus;

/**
 * 审核商户状态Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyAuditStatusService 
{
    /**
     * 查询审核商户状态
     * 
     * @param auditStatusId 审核商户状态ID
     * @return 审核商户状态
     */
    public FfwyAuditStatus selectFfwyAuditStatusById(Integer auditStatusId);

    /**
     * 查询审核商户状态列表
     * 
     * @param ffwyAuditStatus 审核商户状态
     * @return 审核商户状态集合
     */
    public List<FfwyAuditStatus> selectFfwyAuditStatusList(FfwyAuditStatus ffwyAuditStatus);

    /**
     * 新增审核商户状态
     * 
     * @param ffwyAuditStatus 审核商户状态
     * @return 结果
     */
    public int insertFfwyAuditStatus(FfwyAuditStatus ffwyAuditStatus);

    /**
     * 修改审核商户状态
     * 
     * @param ffwyAuditStatus 审核商户状态
     * @return 结果
     */
    public int updateFfwyAuditStatus(FfwyAuditStatus ffwyAuditStatus);

    /**
     * 批量删除审核商户状态
     * 
     * @param auditStatusIds 需要删除的审核商户状态ID
     * @return 结果
     */
    public int deleteFfwyAuditStatusByIds(Integer[] auditStatusIds);

    /**
     * 删除审核商户状态信息
     * 
     * @param auditStatusId 审核商户状态ID
     * @return 结果
     */
    public int deleteFfwyAuditStatusById(Integer auditStatusId);
}
