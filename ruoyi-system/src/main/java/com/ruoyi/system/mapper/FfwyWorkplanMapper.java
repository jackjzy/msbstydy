package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyWorkplan;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
public interface FfwyWorkplanMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param workplanId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyWorkplan selectFfwyWorkplanById(Integer workplanId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyWorkplan 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyWorkplan> selectFfwyWorkplanList(FfwyWorkplan ffwyWorkplan);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyWorkplan 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyWorkplan(FfwyWorkplan ffwyWorkplan);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyWorkplan 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyWorkplan(FfwyWorkplan ffwyWorkplan);

    /**
     * 删除【请填写功能名称】
     * 
     * @param workplanId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyWorkplanById(Integer workplanId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param workplanIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyWorkplanByIds(Integer[] workplanIds);

    List<FfwyWorkplan> selectFfwyWorkplan();

    List<FfwyWorkplan> selectByuserid(Integer userId);

    int myupdateFfwyWorkplan(FfwyWorkplan ffwyWorkplan);
}
