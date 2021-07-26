package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FfwyWorkplan;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.to.AllocationWokers;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
public interface IFfwyWorkplanService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param workplanId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyWorkplan selectFfwyWorkplanById(Integer workplanId);

    /**
     * 查询工长列表
     * 
     * @return 【请填写功能名称】集合
     */
    public List<FfwyUser> selectFfwyWorkplanUserList(FfwyUser ffwyUser);

    /**
     * 指派项目
     * 
     * @param ffwyWorkplan 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyWorkplan(FfwyWorkplan ffwyWorkplan);
    /**
     * 平均指派多个项目
     *
     * @param allocationWokers 【工长列表和订单列表】
     * @return 结果
     */
    public int allocations(AllocationWokers allocationWokers);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyWorkplan 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyWorkplan(FfwyWorkplan ffwyWorkplan);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param workplanIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyWorkplanByIds(Integer[] workplanIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param workplanId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyWorkplanById(Integer workplanId);


    int deleteFfwyWorkplanUserByIds(Integer userId);
}
