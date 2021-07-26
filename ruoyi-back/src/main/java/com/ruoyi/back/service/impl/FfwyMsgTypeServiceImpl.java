package com.ruoyi.back.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.back.mapper.FfwyMsgTypeMapper;
import com.ruoyi.back.domain.FfwyMsgType;
import com.ruoyi.back.service.IFfwyMsgTypeService;

/**
 * 消息类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-26
 */
@Service
public class FfwyMsgTypeServiceImpl implements IFfwyMsgTypeService 
{
    @Autowired
    private FfwyMsgTypeMapper ffwyMsgTypeMapper;

    /**
     * 查询消息类型
     * 
     * @param msgTypeId 消息类型ID
     * @return 消息类型
     */
    @Override
    public FfwyMsgType selectFfwyMsgTypeById(Long msgTypeId)
    {
        return ffwyMsgTypeMapper.selectFfwyMsgTypeById(msgTypeId);
    }

    /**
     * 查询消息类型列表
     * 
     * @param ffwyMsgType 消息类型
     * @return 消息类型
     */
    @Override
    public List<FfwyMsgType> selectFfwyMsgTypeList(FfwyMsgType ffwyMsgType)
    {
        return ffwyMsgTypeMapper.selectFfwyMsgTypeList(ffwyMsgType);
    }

    /**
     * 新增消息类型
     * 
     * @param ffwyMsgType 消息类型
     * @return 结果
     */
    @Override
    public int insertFfwyMsgType(FfwyMsgType ffwyMsgType)
    {
        return ffwyMsgTypeMapper.insertFfwyMsgType(ffwyMsgType);
    }

    /**
     * 修改消息类型
     * 
     * @param ffwyMsgType 消息类型
     * @return 结果
     */
    @Override
    public int updateFfwyMsgType(FfwyMsgType ffwyMsgType)
    {
        return ffwyMsgTypeMapper.updateFfwyMsgType(ffwyMsgType);
    }

    /**
     * 批量删除消息类型
     * 
     * @param msgTypeIds 需要删除的消息类型ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMsgTypeByIds(Long[] msgTypeIds)
    {
        return ffwyMsgTypeMapper.deleteFfwyMsgTypeByIds(msgTypeIds);
    }

    /**
     * 删除消息类型信息
     * 
     * @param msgTypeId 消息类型ID
     * @return 结果
     */
    @Override
    public int deleteFfwyMsgTypeById(Long msgTypeId)
    {
        return ffwyMsgTypeMapper.deleteFfwyMsgTypeById(msgTypeId);
    }
}
