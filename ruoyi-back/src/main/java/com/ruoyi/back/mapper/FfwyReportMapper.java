package com.ruoyi.back.mapper;

import com.ruoyi.back.domain.FfwyReport;

import java.util.List;


/**
 * 举报管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface FfwyReportMapper 
{
    /**
     * 查询举报管理
     * 
     * @param reportId 举报管理ID
     * @return 举报管理
     */
    public FfwyReport selectFfwyReportById(Long reportId);

    /**
     * 查询举报管理列表
     * 
     * @param ffwyReport 举报管理
     * @return 举报管理集合
     */
    public List<FfwyReport> selectFfwyReportList(FfwyReport ffwyReport);

    /**
     * 新增举报管理
     * 
     * @param ffwyReport 举报管理
     * @return 结果
     */
    public int insertFfwyReport(FfwyReport ffwyReport);

    /**
     * 修改举报管理
     * 
     * @param ffwyReport 举报管理
     * @return 结果
     */
    public int updateFfwyReport(FfwyReport ffwyReport);

    /**
     * 删除举报管理
     * 
     * @param reportId 举报管理ID
     * @return 结果
     */
    public int deleteFfwyReportById(Long reportId);

    /**
     * 批量删除举报管理
     * 
     * @param reportIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyReportByIds(Long[] reportIds);
}
