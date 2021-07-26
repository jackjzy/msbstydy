package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyPayRecordMapper;
import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.service.IFfwyPayRecordService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-24
 */
@Service
public class FfwyPayRecordServiceImpl implements IFfwyPayRecordService 
{
    @Autowired
    private FfwyPayRecordMapper ffwyPayRecordMapper;

    @Override
    public FfwyPayRecord selectFfwyPayRecordByOrderNumber(String orderNumber) {
        return ffwyPayRecordMapper.selectFfwyPayRecordByOrderNumber(orderNumber);
    }

    /**
     * 查询【请填写功能名称】
     * 
     * @param recordId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyPayRecord selectFfwyPayRecordById(Long recordId)
    {
        return ffwyPayRecordMapper.selectFfwyPayRecordById(recordId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPayRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyPayRecord> selectFfwyPayRecordList(FfwyPayRecord ffwyPayRecord)
    {
        return ffwyPayRecordMapper.selectFfwyPayRecordList(ffwyPayRecord);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPayRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyPayRecord(FfwyPayRecord ffwyPayRecord)
    {
        ffwyPayRecord.setCreateTime(DateUtils.getNowDate());
        return ffwyPayRecordMapper.insertFfwyPayRecord(ffwyPayRecord);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPayRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyPayRecord(FfwyPayRecord ffwyPayRecord)
    {
        return ffwyPayRecordMapper.updateFfwyPayRecord(ffwyPayRecord);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param recordIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPayRecordByIds(Long[] recordIds)
    {
        return ffwyPayRecordMapper.deleteFfwyPayRecordByIds(recordIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param recordId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPayRecordById(Long recordId)
    {
        return ffwyPayRecordMapper.deleteFfwyPayRecordById(recordId);
    }
}
