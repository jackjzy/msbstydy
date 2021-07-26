package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;

import com.ruoyi.system.domain.FfwyArea;
import com.ruoyi.system.mapper.FfwyAreaMapper;
import com.ruoyi.system.service.IFfwyAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省市县Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-30
 */
@Service
public class FfwyAreaServiceImpl implements IFfwyAreaService
{
    @Autowired
    private FfwyAreaMapper ffwyAreaMapper;

    /**
     * 查询省市县
     * 
     * @param code 省市县ID
     * @return 省市县
     */
    @Override
    public FfwyArea selectFfwyAreaById(String code)
    {
        return ffwyAreaMapper.selectFfwyAreaById(code);
    }

    /**
     * 查询省市县列表
     * 
     * @param ffwyArea 省市县
     * @return 省市县
     */
    @Override
    public List<FfwyArea> selectFfwyAreaList(FfwyArea ffwyArea)
    {
        return ffwyAreaMapper.selectFfwyAreaList(ffwyArea);
    }

    /**
     * 新增省市县
     * 
     * @param ffwyArea 省市县
     * @return 结果
     */
    @Override
    public int insertFfwyArea(FfwyArea ffwyArea)
    {
        ffwyArea.setCreateTime(DateUtils.getNowDate());
        return ffwyAreaMapper.insertFfwyArea(ffwyArea);
    }

    /**
     * 修改省市县
     * 
     * @param ffwyArea 省市县
     * @return 结果
     */
    @Override
    public int updateFfwyArea(FfwyArea ffwyArea)
    {
        return ffwyAreaMapper.updateFfwyArea(ffwyArea);
    }

    /**
     * 批量删除省市县
     * 
     * @param codes 需要删除的省市县ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAreaByIds(String[] codes)
    {
        return ffwyAreaMapper.deleteFfwyAreaByIds(codes);
    }

    /**
     * 删除省市县信息
     * 
     * @param code 省市县ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAreaById(String code)
    {
        return ffwyAreaMapper.deleteFfwyAreaById(code);
    }
}
