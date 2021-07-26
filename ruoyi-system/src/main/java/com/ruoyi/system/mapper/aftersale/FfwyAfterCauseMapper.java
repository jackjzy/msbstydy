package com.ruoyi.system.mapper.aftersale;

import com.ruoyi.system.domain.aftersale.FfwyAfterCause;

import java.util.List;

/**
 * 退款原因Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
public interface FfwyAfterCauseMapper 
{
    /**
     * 查询退款原因
     * 
     * @param refundId 退款原因ID
     * @return 退款原因
     */
    public FfwyAfterCause selectFfwyAfterCauseById(Long refundId);

    /**
     * 查询退款原因列表
     * 
     * @param ffwyAfterCause 退款原因
     * @return 退款原因集合
     */
    public List<FfwyAfterCause> selectFfwyAfterCauseList(FfwyAfterCause ffwyAfterCause);

    /**
     * 新增退款原因
     * 
     * @param ffwyAfterCause 退款原因
     * @return 结果
     */
    public int insertFfwyAfterCause(FfwyAfterCause ffwyAfterCause);

    /**
     * 修改退款原因
     * 
     * @param ffwyAfterCause 退款原因
     * @return 结果
     */
    public int updateFfwyAfterCause(FfwyAfterCause ffwyAfterCause);

    /**
     * 删除退款原因
     * 
     * @param refundId 退款原因ID
     * @return 结果
     */
    public int deleteFfwyAfterCauseById(Long refundId);

    /**
     * 批量删除退款原因
     * 
     * @param refundIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyAfterCauseByIds(Long[] refundIds);

    List<FfwyAfterCause> selectFfwyAfterCauseLists();

}
