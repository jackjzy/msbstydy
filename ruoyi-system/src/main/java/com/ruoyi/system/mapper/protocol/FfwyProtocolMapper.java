package com.ruoyi.system.mapper.protocol;

import com.ruoyi.system.domain.protocol.FfwyProtocol;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
public interface FfwyProtocolMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param protocolId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyProtocol selectFfwyProtocolById(Integer protocolId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyProtocol 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyProtocol> selectFfwyProtocolList(FfwyProtocol ffwyProtocol);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyProtocol 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyProtocol(FfwyProtocol ffwyProtocol);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyProtocol 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyProtocol(FfwyProtocol ffwyProtocol);

    /**
     * 删除【请填写功能名称】
     * 
     * @param protocolId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyProtocolById(Integer protocolId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param protocolIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProtocolByIds(Integer[] protocolIds);
}
