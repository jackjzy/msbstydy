package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomealorders.FfwyPhase;

import java.util.List;


/**
 * 节点订单Service接口
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
public interface IFfwyPhaseService 
{
    /**
     * 查询节点订单
     * 
     * @param phaseId 节点订单ID
     * @return 节点订单
     */
    public FfwyPhase selectFfwyPhaseById(Long phaseId);

    /**
     * 查询节点订单列表
     * 
     * @param ffwyPhase 节点订单
     * @return 节点订单集合
     */
    public List<FfwyPhase> selectFfwyPhaseList(FfwyPhase ffwyPhase);

    /**
     * 查询节点订单
     *
     * @param  orderId 订单号
     * @return 节点订单
     */
    public FfwyPhase selectFfwyPhaseByOrder(String orderId);

    /**
     * 查询套餐订单的节点订单以及子节点
     *
     * @param ffwyPhase 节点订单
     * @return 节点订单集合
     */
    public List<FfwyPhase> selectFfwParent(FfwyPhase ffwyPhase);

    /**
     * 新增节点订单
     * 
     * @param ffwyPhase 节点订单
     * @return 结果
     */
    public int insertFfwyPhase(FfwyPhase ffwyPhase);
    /**
     * 新增多个节点订单
     *
     * @param ffwyPhases 多个节点订单
     * @return 结果
     */
    public int insertFfwyPhases(List<FfwyPhase> ffwyPhases);

    /**
     * 修改节点订单
     * 
     * @param ffwyPhase 节点订单
     * @return 结果
     */
    public int updateFfwyPhase(FfwyPhase ffwyPhase);

    /**
     * 修改节点订单
     *
     * @param ffwyPhase 节点订单
     * @return 结果
     */
    public int updateFfwyPhases(FfwyPhase ffwyPhase,Long userId,String usertype);

    /**
     * 批量删除节点订单
     * 
     * @param phaseIds 需要删除的节点订单ID
     * @return 结果
     */
    public int deleteFfwyPhaseByIds(Long[] phaseIds);

    /**
     * 删除节点订单信息
     * 
     * @param phaseId 节点订单ID
     * @return 结果
     */
    public int deleteFfwyPhaseById(Long phaseId);
}
