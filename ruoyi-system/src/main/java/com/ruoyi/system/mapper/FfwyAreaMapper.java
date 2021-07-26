package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.FfwyArea;

import java.util.List;

/**
 * 省市县Mapper接口
 * 
 * @author ruoyi
 * @date 2021-06-30
 */
public interface FfwyAreaMapper 
{
    /**
     * 查询省市县
     * 
     * @param code 省市县ID
     * @return 省市县
     */
    public FfwyArea selectFfwyAreaById(String code);

    /**
     * 查询省市县列表
     * 
     * @param ffwyArea 省市县
     * @return 省市县集合
     */
    public List<FfwyArea> selectFfwyAreaList(FfwyArea ffwyArea);

    /**
     * 新增省市县
     * 
     * @param ffwyArea 省市县
     * @return 结果
     */
    public int insertFfwyArea(FfwyArea ffwyArea);

    /**
     * 修改省市县
     * 
     * @param ffwyArea 省市县
     * @return 结果
     */
    public int updateFfwyArea(FfwyArea ffwyArea);

    /**
     * 删除省市县
     * 
     * @param code 省市县ID
     * @return 结果
     */
    public int deleteFfwyAreaById(String code);

    /**
     * 批量删除省市县
     * 
     * @param codes 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyAreaByIds(String[] codes);
}
