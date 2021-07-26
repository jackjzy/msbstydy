package com.ruoyi.back.mapper;

import java.util.List;
import com.ruoyi.back.domain.FfwyMsgType;

/**
 * 消息类型Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
public interface FfwyMsgTypeMapper 
{
    /**
     * 查询消息类型
     * 
     * @param msgTypeId 消息类型ID
     * @return 消息类型
     */
    public FfwyMsgType selectFfwyMsgTypeById(Long msgTypeId);

    /**
     * 查询消息类型列表
     * 
     * @param ffwyMsgType 消息类型
     * @return 消息类型集合
     */
    public List<FfwyMsgType> selectFfwyMsgTypeList(FfwyMsgType ffwyMsgType);

    /**
     * 新增消息类型
     * 
     * @param ffwyMsgType 消息类型
     * @return 结果
     */
    public int insertFfwyMsgType(FfwyMsgType ffwyMsgType);

    /**
     * 修改消息类型
     * 
     * @param ffwyMsgType 消息类型
     * @return 结果
     */
    public int updateFfwyMsgType(FfwyMsgType ffwyMsgType);

    /**
     * 删除消息类型
     * 
     * @param msgTypeId 消息类型ID
     * @return 结果
     */
    public int deleteFfwyMsgTypeById(Long msgTypeId);

    /**
     * 批量删除消息类型
     * 
     * @param msgTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyMsgTypeByIds(Long[] msgTypeIds);
}
