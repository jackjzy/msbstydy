package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FfwyPayRecord;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-06-24
 */
public interface IFfwyPayRecordService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param orderNumber 订单号
     * @return 【请填写功能名称】
     */
    public FfwyPayRecord selectFfwyPayRecordByOrderNumber(String orderNumber);
    /**
     * 查询【请填写功能名称】
     *
     * @param recordId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyPayRecord selectFfwyPayRecordById(Long recordId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPayRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyPayRecord> selectFfwyPayRecordList(FfwyPayRecord ffwyPayRecord);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPayRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyPayRecord(FfwyPayRecord ffwyPayRecord);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPayRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyPayRecord(FfwyPayRecord ffwyPayRecord);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param recordIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPayRecordByIds(Long[] recordIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param recordId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPayRecordById(Long recordId);
}
