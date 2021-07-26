package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.to.AllocationWokers;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyOrderCombomealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyWorkplanMapper;
import com.ruoyi.system.domain.FfwyWorkplan;
import com.ruoyi.system.service.IFfwyWorkplanService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-25
 */
@Service
public class FfwyWorkplanServiceImpl implements IFfwyWorkplanService 
{
    @Autowired
    private FfwyWorkplanMapper ffwyWorkplanMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwyOrderCombomealMapper ffwyOrderCombomealMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param workplanId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyWorkplan selectFfwyWorkplanById(Integer workplanId)
    {
        return ffwyWorkplanMapper.selectFfwyWorkplanById(workplanId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyUser> selectFfwyWorkplanUserList(FfwyUser ffwyUser) {
        return ffwyUserMapper.selectFfwyWorkplanUserList(ffwyUser);
    }
    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyWorkplan 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyWorkplan(FfwyWorkplan ffwyWorkplan)
    {
            return ffwyWorkplanMapper.updateFfwyWorkplan(ffwyWorkplan);
    }

    @Override
    public int allocations(AllocationWokers allocationWokers) {
        List<FfwyUser> wokers = allocationWokers.getWokers();
        List<FfwyOrderCombomeal> orderCombomeals = allocationWokers.getOrderCombomeals();
        int wokersNext=0;
        int orderNext=0;
        int wokerFinish=0;
        int orderFinish=0;
        int chage=0;
        if ((null!=wokers && wokers.size()>0)
            && (null!=orderCombomeals && orderCombomeals.size()>0)){
            wokerFinish = wokers.size()-1;
            orderFinish = orderCombomeals.size();
            //平均把项目平均分给工长
            while (orderNext<orderFinish){

                FfwyOrderCombomeal ffwyOrderCombomeal = orderCombomeals.get(orderNext);

                FfwyUser ffwyUser = wokers.get(wokersNext);

                ffwyOrderCombomeal.setWorkerId(ffwyUser.getUserId());
                //设置套餐状态为已分配
                ffwyOrderCombomeal.setAllotStatus("1");

               chage+= ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(ffwyOrderCombomeal);

                orderNext++;
                wokersNext++;
                if (wokersNext>wokerFinish){
                    wokersNext=0;
                }

            }
        }


        return chage;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyWorkplan 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyWorkplan(FfwyWorkplan ffwyWorkplan)
    {
        return ffwyWorkplanMapper.updateFfwyWorkplan(ffwyWorkplan);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param workplanIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyWorkplanByIds(Integer[] workplanIds)
    {
        return ffwyWorkplanMapper.deleteFfwyWorkplanByIds(workplanIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param workplanId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyWorkplanById(Integer workplanId)
    {
        return ffwyWorkplanMapper.deleteFfwyWorkplanById(workplanId);
    }


    @Override
    public int deleteFfwyWorkplanUserByIds(Integer userId)
    {
        int i = ffwyUserMapper.deleteFfwyUserById(userId.longValue());
        List<FfwyWorkplan> ffwyWorkplanList = ffwyWorkplanMapper.selectByuserid(userId);
        FfwyWorkplan ffwyWorkplan = new FfwyWorkplan();
        for (FfwyWorkplan workplan : ffwyWorkplanList) {
            ffwyWorkplan.setWorkplanId(workplan.getWorkplanId());
        }
        return ffwyWorkplanMapper.myupdateFfwyWorkplan(ffwyWorkplan);
    }
}
