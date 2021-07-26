package com.ruoyi.system.mapper.combomealorders;

import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 节点订单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-15
 */
public interface FfwyPhaseMapper 
{
    /**
     * 查询节点订单
     * 
     * @param phaseId 节点订单ID
     * @return 节点订单
     */
    public FfwyPhase selectFfwyPhaseById(Long phaseId);
    /**
     * 查询节点订单
     *
     * @param  orderId 订单号
     * @return 节点订单
     */
    public FfwyPhase selectFfwyPhaseByOrder(String orderId);

    /**
     * 查询下一个父节点订单
     *
     * @param phaseId 节点订单ID
     * @return 节点订单
     */
    public FfwyPhase selectNextParentPhase(@Param("phaseId") Long phaseId,
                                           @Param("orderId") Long orderId);
    /**
     * 查询当前父节点订单
     *
     * @param phaseId 节点订单ID
     * @return 节点订单
     */
    public FfwyPhase selectNowParentPhase(@Param("phaseId") Long phaseId,
                                           @Param("orderId") Long orderId);
    /**
     * 查询下一个子节点订单
     *
     * @param phaseId 节点订单ID
     * @return 节点订单
     */
    public FfwyPhase selectNextChildPhase(@Param("phaseId") Long phaseId,
                                           @Param("orderId") Long orderId);

    public FfwyPhase selectFfwyPhaseByOrderIdAndLevel(@Param("orderId") Long orderId,
                                                      @Param("level") Integer level);

    /**
     * 查询节点订单列表
     * 
     * @param ffwyPhase 节点订单
     * @return 节点订单集合
     */
    public List<FfwyPhase> selectFfwyPhaseList(FfwyPhase ffwyPhase);



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
     * 删除节点订单
     * 
     * @param phaseId 节点订单ID
     * @return 结果
     */
    public int deleteFfwyPhaseById(Long phaseId);

    /**
     * 批量删除节点订单
     * 
     * @param phaseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyPhaseByIds(Long[] phaseIds);
}
