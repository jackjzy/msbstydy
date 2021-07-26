package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.domain.FfwyPayRefund;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-07-07
 */
@Repository
public interface FfwyPayRefundMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyPayRefund selectFfwyPayRefundById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyPayRefund> selectFfwyPayRefundList(FfwyPayRefund ffwyPayRefund);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyPayRefund(FfwyPayRefund ffwyPayRefund);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyPayRefund(FfwyPayRefund ffwyPayRefund);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPayRefundById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyPayRefundByIds(Long[] ids);

    FfwyPayRefund selectFfwyPayRefundByRefundOrderId(String orderId);
}
