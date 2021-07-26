package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhaseMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-05-22
 */
public interface IFfwyPhaseMsgService 
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
     * 新增【请填写功能名称】
     *
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyPhaseMsgAndPhoto(FfwyPhaseMsg ffwyPhaseMsg, MultipartFile[] files);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyPhaseMsg(FfwyPhaseMsg ffwyPhaseMsg);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param phaseMsgIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPhaseMsgByIds(Long[] phaseMsgIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param phaseMsgId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPhaseMsgById(Long phaseMsgId);
}
