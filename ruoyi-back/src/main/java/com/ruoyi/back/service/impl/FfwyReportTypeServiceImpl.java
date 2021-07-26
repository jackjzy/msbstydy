package com.ruoyi.back.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.back.mapper.FfwyReportTypeMapper;
import com.ruoyi.back.domain.FfwyReportType;
import com.ruoyi.back.service.IFfwyReportTypeService;

/**
 * 举报管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Service
public class FfwyReportTypeServiceImpl implements IFfwyReportTypeService 
{
    @Autowired
    private FfwyReportTypeMapper ffwyReportTypeMapper;

    /**
     * 查询举报管理
     * 
     * @param reportTypeId 举报管理ID
     * @return 举报管理
     */
    @Override
    public FfwyReportType selectFfwyReportTypeById(Long reportTypeId)
    {
        return ffwyReportTypeMapper.selectFfwyReportTypeById(reportTypeId);
    }

    /**
     * 查询举报管理列表
     * 
     * @param ffwyReportType 举报管理
     * @return 举报管理
     */
    @Override
    public List<FfwyReportType> selectFfwyReportTypeList(FfwyReportType ffwyReportType)
    {
        return ffwyReportTypeMapper.selectFfwyReportTypeList(ffwyReportType);
    }

    /**
     * 新增举报管理
     * 
     * @param ffwyReportType 举报管理
     * @return 结果
     */
    @Override
    public int insertFfwyReportType(FfwyReportType ffwyReportType)
    {
        return ffwyReportTypeMapper.insertFfwyReportType(ffwyReportType);
    }

    /**
     * 修改举报管理
     * 
     * @param ffwyReportType 举报管理
     * @return 结果
     */
    @Override
    public int updateFfwyReportType(FfwyReportType ffwyReportType)
    {
        return ffwyReportTypeMapper.updateFfwyReportType(ffwyReportType);
    }

    /**
     * 批量删除举报管理
     * 
     * @param reportTypeIds 需要删除的举报管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyReportTypeByIds(Long[] reportTypeIds)
    {
        return ffwyReportTypeMapper.deleteFfwyReportTypeByIds(reportTypeIds);
    }

    /**
     * 删除举报管理信息
     * 
     * @param reportTypeId 举报管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyReportTypeById(Long reportTypeId)
    {
        return ffwyReportTypeMapper.deleteFfwyReportTypeById(reportTypeId);
    }
}
