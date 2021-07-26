package com.ruoyi.back.service;

import java.util.List;
import com.ruoyi.back.domain.FfwyReportType;

/**
 * 举报管理Service接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface IFfwyReportTypeService 
{
    /**
     * 查询举报管理
     * 
     * @param reportTypeId 举报管理ID
     * @return 举报管理
     */
    public FfwyReportType selectFfwyReportTypeById(Long reportTypeId);

    /**
     * 查询举报管理列表
     * 
     * @param ffwyReportType 举报管理
     * @return 举报管理集合
     */
    public List<FfwyReportType> selectFfwyReportTypeList(FfwyReportType ffwyReportType);

    /**
     * 新增举报管理
     * 
     * @param ffwyReportType 举报管理
     * @return 结果
     */
    public int insertFfwyReportType(FfwyReportType ffwyReportType);

    /**
     * 修改举报管理
     * 
     * @param ffwyReportType 举报管理
     * @return 结果
     */
    public int updateFfwyReportType(FfwyReportType ffwyReportType);

    /**
     * 批量删除举报管理
     * 
     * @param reportTypeIds 需要删除的举报管理ID
     * @return 结果
     */
    public int deleteFfwyReportTypeByIds(Long[] reportTypeIds);

    /**
     * 删除举报管理信息
     * 
     * @param reportTypeId 举报管理ID
     * @return 结果
     */
    public int deleteFfwyReportTypeById(Long reportTypeId);
}
