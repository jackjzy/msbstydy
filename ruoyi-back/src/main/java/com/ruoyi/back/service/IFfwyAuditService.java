package com.ruoyi.back.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.back.domain.FfwyAudit;
import com.ruoyi.back.domain.queryentity.QueryAudti;
import org.springframework.web.multipart.MultipartFile;

/**
 * 审核Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyAuditService 
{
    /**
     * 查询审核
     * 
     * @param auditId 审核ID
     * @return 审核
     */
    public FfwyAudit selectFfwyAuditById(Long auditId);
    /**
     * 审核通过
     *
     * @param ffwyAudit 审核信息
     * @return 审核
     */
    public int auditByYes(FfwyAudit ffwyAudit);

    /**
     * 查询审核-根据用户ID
     *
     * @param userId
     * @return
     */
    public FfwyAudit selectFfwyAuditByUserId(Long userId);

    /**
     * 查询审核列表
     * 
     * @param ffwyAudit 审核
     * @return 审核集合
     */
    public List<FfwyAudit> selectFfwyAuditList(FfwyAudit ffwyAudit);


    public List<FfwyAudit> faindAllAuditList(QueryAudti ffwyAudit);

    /**
     * 新增审核
     * 
     * @param ffwyAudit 审核
     * @return 结果
     */
    public Map<String, Object> insertFfwyAudit(FfwyAudit ffwyAudit);

    /**
     * 修改审核
     * 
     * @param ffwyAudit 审核
     * @return 结果
     */
    public int updateFfwyAudit(FfwyAudit ffwyAudit);

    /**
     * 批量删除审核
     * 
     * @param auditIds 需要删除的审核ID
     * @return 结果
     */
    public int deleteFfwyAuditByIds(Long[] auditIds);

    /**
     * 删除审核信息
     * 
     * @param auditId 审核ID
     * @return 结果
     */
    public int deleteFfwyAuditById(Long auditId);
}
