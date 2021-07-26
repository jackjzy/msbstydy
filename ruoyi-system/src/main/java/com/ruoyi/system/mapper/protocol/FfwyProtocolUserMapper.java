package com.ruoyi.system.mapper.protocol;

import com.ruoyi.system.domain.protocol.FfwyProtocolUser;

import java.util.List;


/**
 * 记录用户是否同意过用户协议Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
public interface FfwyProtocolUserMapper 
{
    /**
     * 查询记录用户是否同意过用户协议
     * 
     * @param protocolUserId 记录用户是否同意过用户协议ID
     * @return 记录用户是否同意过用户协议
     */
    public FfwyProtocolUser selectFfwyProtocolUserById(Integer protocolUserId);

    /**
     * 查询记录用户是否同意过用户协议列表
     * 
     * @param ffwyProtocolUser 记录用户是否同意过用户协议
     * @return 记录用户是否同意过用户协议集合
     */
    public List<FfwyProtocolUser> selectFfwyProtocolUserList(FfwyProtocolUser ffwyProtocolUser);

    /**
     * 新增记录用户是否同意过用户协议
     * 
     * @param ffwyProtocolUser 记录用户是否同意过用户协议
     * @return 结果
     */
    public int insertFfwyProtocolUser(FfwyProtocolUser ffwyProtocolUser);

    /**
     * 修改记录用户是否同意过用户协议
     * 
     * @param ffwyProtocolUser 记录用户是否同意过用户协议
     * @return 结果
     */
    public int updateFfwyProtocolUser(FfwyProtocolUser ffwyProtocolUser);

    /**
     * 删除记录用户是否同意过用户协议
     * 
     * @param protocolUserId 记录用户是否同意过用户协议ID
     * @return 结果
     */
    public int deleteFfwyProtocolUserById(Integer protocolUserId);

    /**
     * 批量删除记录用户是否同意过用户协议
     * 
     * @param protocolUserIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProtocolUserByIds(Integer[] protocolUserIds);
}
