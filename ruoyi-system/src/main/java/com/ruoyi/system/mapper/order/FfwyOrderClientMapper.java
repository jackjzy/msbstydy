package com.ruoyi.system.mapper.order;

import com.ruoyi.system.domain.order.FfwyOrderClient;
import com.ruoyi.system.domain.vo.OrderClientVo;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
public interface FfwyOrderClientMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param orderClientId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyOrderClient selectFfwyOrderClientById(Long orderClientId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderClient 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<OrderClientVo> selectFfwyOrderClientList(FfwyOrderClient ffwyOrderClient);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyOrderClient 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyOrderClient(FfwyOrderClient ffwyOrderClient);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyOrderClient 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyOrderClient(FfwyOrderClient ffwyOrderClient);

    /**
     * 删除【请填写功能名称】
     * 
     * @param orderClientId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyOrderClientById(Long orderClientId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param orderClientIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyOrderClientByIds(Long[] orderClientIds);

    List<OrderClientVo> selectOrderClientList();

}
