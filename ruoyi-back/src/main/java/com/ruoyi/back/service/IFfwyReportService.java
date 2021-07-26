package com.ruoyi.back.service;

import com.ruoyi.back.domain.FfwyReport;

import java.util.List;


/**
 * 举报管理Service接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface IFfwyReportService 
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
     * 回复
     *
     * @param ffwyReport 举报管理
     * @return 结果
     */
    public int reply(FfwyReport ffwyReport);

    /**
     * 批量删除举报管理
     * 
     * @param reportIds 需要删除的举报管理ID
     * @return 结果
     */
    public int deleteFfwyReportByIds(Long[] reportIds);

    /**
     * 删除举报管理信息
     * 
     * @param reportId 举报管理ID
     * @return 结果
     */
    public int deleteFfwyReportById(Long reportId);

    int insertFfwyReportVideo(Long id,Integer videoId,String reportContent,Integer typeId);
}
