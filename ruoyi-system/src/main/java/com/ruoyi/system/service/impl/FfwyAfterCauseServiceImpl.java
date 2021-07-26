package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.aftersale.FfwyAfterCause;
import com.ruoyi.system.mapper.aftersale.FfwyAfterCauseMapper;
import com.ruoyi.system.service.IFfwyAfterCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退款原因Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
@Service
public class FfwyAfterCauseServiceImpl implements IFfwyAfterCauseService
{
    @Autowired
    private FfwyAfterCauseMapper ffwyAfterCauseMapper;

    /**
     * 查询退款原因
     * 
     * @param refundId 退款原因ID
     * @return 退款原因
     */
    @Override
    public FfwyAfterCause selectFfwyAfterCauseById(Long refundId)
    {
        return ffwyAfterCauseMapper.selectFfwyAfterCauseById(refundId);
    }

    /**
     * 查询退款原因列表
     * 
     * @param ffwyAfterCause 退款原因
     * @return 退款原因
     */
    @Override
    public List<FfwyAfterCause> selectFfwyAfterCauseList(FfwyAfterCause ffwyAfterCause)
    {
        return ffwyAfterCauseMapper.selectFfwyAfterCauseList(ffwyAfterCause);
    }

    /**
     * 新增退款原因
     * 
     * @param ffwyAfterCause 退款原因
     * @return 结果
     */
    @Override
    public int insertFfwyAfterCause(FfwyAfterCause ffwyAfterCause)
    {
        return ffwyAfterCauseMapper.insertFfwyAfterCause(ffwyAfterCause);
    }

    /**
     * 修改退款原因
     * 
     * @param ffwyAfterCause 退款原因
     * @return 结果
     */
    @Override
    public int updateFfwyAfterCause(FfwyAfterCause ffwyAfterCause)
    {
        return ffwyAfterCauseMapper.updateFfwyAfterCause(ffwyAfterCause);
    }

    /**
     * 批量删除退款原因
     * 
     * @param refundIds 需要删除的退款原因ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterCauseByIds(Long[] refundIds)
    {
        return ffwyAfterCauseMapper.deleteFfwyAfterCauseByIds(refundIds);
    }

    /**
     * 删除退款原因信息
     * 
     * @param refundId 退款原因ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterCauseById(Long refundId)
    {
        return ffwyAfterCauseMapper.deleteFfwyAfterCauseById(refundId);
    }

    @Override
    public List<FfwyAfterCause> selectFfwyAfterCauseLists() {
        return ffwyAfterCauseMapper.selectFfwyAfterCauseLists();
    }
}
