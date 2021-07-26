package com.ruoyi.system.service;

import com.ruoyi.system.domain.protocol.FfwyProtocol;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
public interface IFfwyProtocolService 
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
     * 修改用户协议【请填写功能名称】
     *
     * @param file 【上传的文件 pdf\word\txt】
     * @return 结果
     */
    public int chageProtocol(MultipartFile file);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param protocolIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyProtocolByIds(Integer[] protocolIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param protocolId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyProtocolById(Integer protocolId);
}
