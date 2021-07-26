package com.ruoyi.back.service.impl;

import java.util.List;

import com.ruoyi.back.domain.FfwyReport;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.mapper.FfwyReportMapper;
import com.ruoyi.back.mapper.FfwyVideoMapper;
import com.ruoyi.back.service.IFfwyReportService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 举报管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Service
public class FfwyReportServiceImpl implements IFfwyReportService
{
    @Autowired
    private FfwyReportMapper ffwyReportMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwyVideoMapper ffwyVideoMapper;

    /**
     * 查询举报管理
     * 
     * @param reportId 举报管理ID
     * @return 举报管理
     */
    @Override
    public FfwyReport selectFfwyReportById(Long reportId)
    {
        return ffwyReportMapper.selectFfwyReportById(reportId);
    }

    /**
     * 查询举报管理列表
     * 
     * @param ffwyReport 举报管理
     * @return 举报管理
     */
    @Override
    public List<FfwyReport> selectFfwyReportList(FfwyReport ffwyReport)
    {
        return ffwyReportMapper.selectFfwyReportList(ffwyReport);
    }

    /**
     * 新增举报管理
     * 
     * @param ffwyReport 举报管理
     * @return 结果
     */
    @Override
    public int insertFfwyReport(FfwyReport ffwyReport)
    {
        return ffwyReportMapper.insertFfwyReport(ffwyReport);
    }

    /**
     * 修改举报管理
     * 
     * @param ffwyReport 举报管理
     * @return 结果
     */
    @Override
    public int updateFfwyReport(FfwyReport ffwyReport)
    {
        return ffwyReportMapper.updateFfwyReport(ffwyReport);
    }

    @Override
    public int reply(FfwyReport ffwyReport) {
        ffwyReport.setReplyStatus("1");
        return ffwyReportMapper.updateFfwyReport(ffwyReport);
    }

    /**
     * 批量删除举报管理
     * 
     * @param reportIds 需要删除的举报管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyReportByIds(Long[] reportIds)
    {
        return ffwyReportMapper.deleteFfwyReportByIds(reportIds);
    }

    /**
     * 删除举报管理信息
     * 
     * @param reportId 举报管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyReportById(Long reportId)
    {
        return ffwyReportMapper.deleteFfwyReportById(reportId);
    }

    @Override
    public int insertFfwyReportVideo(Long id ,Integer videoId,String reportContent,Integer typeId) {
        FfwyReport ffwyReport = new FfwyReport();
        FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(id);
        ffwyReport.setUserId(ffwyUser.getUserId());
        ffwyReport.setPhone(ffwyUser.getPhonenumber());
        ffwyReport.setUserName(ffwyUser.getUserName());
        FfwyVideo ffwyVideo = ffwyVideoMapper.selectFfwyVideoById(videoId.longValue());
        Long userId = ffwyVideo.getUserId();
        FfwyUser ffwyUser1 = ffwyUserMapper.selectFfwyUserById(userId);
        ffwyReport.setReportUserId(ffwyUser1.getUserId());
        ffwyReport.setReportUserName(ffwyUser1.getUserName());
        ffwyReport.setCreateTime(DateUtils.getNowDate());
        ffwyReport.setReportContent(reportContent);
        ffwyReport.setTypeId(typeId);
        ffwyReport.setReplyStatus("0");
        ffwyReport.setReportVideo(videoId);
        return ffwyReportMapper.insertFfwyReport(ffwyReport);
    }
}
