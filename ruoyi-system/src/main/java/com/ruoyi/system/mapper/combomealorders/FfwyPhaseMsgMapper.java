package com.ruoyi.system.mapper.combomealorders;

import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhaseMsg;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-22
 */
public interface FfwyPhaseMsgMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param phaseMsgId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyPhaseMsg selectFfwyPhaseMsgById(Long phaseMsgId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyPhaseMsg> selectFfwyPhaseMsgList(FfwyPhaseMsg ffwyPhaseMsg);
    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyPhaseMsg> selectFfwyPhaseMsgAndPhoto(FfwyPhaseMsg ffwyPhaseMsg);



    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyPhaseMsg(FfwyPhaseMsg ffwyPhaseMsg);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyPhaseMsg(FfwyPhaseMsg ffwyPhaseMsg);

    /**
     * 删除【请填写功能名称】
     * 
     * @param phaseMsgId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPhaseMsgById(Long phaseMsgId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param phaseMsgIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyPhaseMsgByIds(Long[] phaseMsgIds);
}
