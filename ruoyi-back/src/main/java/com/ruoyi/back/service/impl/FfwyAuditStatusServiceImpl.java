package com.ruoyi.back.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.back.mapper.FfwyAuditStatusMapper;
import com.ruoyi.back.domain.FfwyAuditStatus;
import com.ruoyi.back.service.IFfwyAuditStatusService;

/**
 * 审核商户状态Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyAuditStatusServiceImpl implements IFfwyAuditStatusService 
{
    @Autowired
    private FfwyAuditStatusMapper ffwyAuditStatusMapper;

    /**
     * 查询审核商户状态
     * 
     * @param auditStatusId 审核商户状态ID
     * @return 审核商户状态
     */
    @Override
    public FfwyAuditStatus selectFfwyAuditStatusById(Integer auditStatusId)
    {
        return ffwyAuditStatusMapper.selectFfwyAuditStatusById(auditStatusId);
    }

    /**
     * 查询审核商户状态列表
     * 
     * @param ffwyAuditStatus 审核商户状态
     * @return 审核商户状态
     */
    @Override
    public List<FfwyAuditStatus> selectFfwyAuditStatusList(FfwyAuditStatus ffwyAuditStatus)
    {
        return ffwyAuditStatusMapper.selectFfwyAuditStatusList(ffwyAuditStatus);
    }

    /**
     * 新增审核商户状态
     * 
     * @param ffwyAuditStatus 审核商户状态
     * @return 结果
     */
    @Override
    public int insertFfwyAuditStatus(FfwyAuditStatus ffwyAuditStatus)
    {
        return ffwyAuditStatusMapper.insertFfwyAuditStatus(ffwyAuditStatus);
    }

    /**
     * 修改审核商户状态
     * 
     * @param ffwyAuditStatus 审核商户状态
     * @return 结果
     */
    @Override
    public int updateFfwyAuditStatus(FfwyAuditStatus ffwyAuditStatus)
    {
        return ffwyAuditStatusMapper.updateFfwyAuditStatus(ffwyAuditStatus);
    }

    /**
     * 批量删除审核商户状态
     * 
     * @param auditStatusIds 需要删除的审核商户状态ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAuditStatusByIds(Integer[] auditStatusIds)
    {
        return ffwyAuditStatusMapper.deleteFfwyAuditStatusByIds(auditStatusIds);
    }

    /**
     * 删除审核商户状态信息
     * 
     * @param auditStatusId 审核商户状态ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAuditStatusById(Integer auditStatusId)
    {
        return ffwyAuditStatusMapper.deleteFfwyAuditStatusById(auditStatusId);
    }
}
