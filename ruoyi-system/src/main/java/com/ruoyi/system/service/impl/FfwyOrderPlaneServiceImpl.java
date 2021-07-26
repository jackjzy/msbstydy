package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.order.FfwyOrderPlane;
import com.ruoyi.system.domain.vo.FfwyOrderPlaneVo;
import com.ruoyi.system.mapper.FfwyPaymentMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealMapper;
import com.ruoyi.system.mapper.order.FfwyOrderClientMapper;
import com.ruoyi.system.mapper.order.FfwyOrderPlaneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyOrderPlaneService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-27
 */
@Service
public class FfwyOrderPlaneServiceImpl implements IFfwyOrderPlaneService 
{
    @Autowired
    private FfwyOrderPlaneMapper ffwyOrderPlaneMapper;

    @Autowired
    private FfwyCombomealMapper ffwyCombomealMapper;

    @Autowired
    private FfwyPaymentMapper ffwyPaymentMapper;

    @Autowired
    private FfwyOrderClientMapper ffwyOrderClientMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param planeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyOrderPlane selectFfwyOrderPlaneById(Long planeId)
    {
        return ffwyOrderPlaneMapper.selectFfwyOrderPlaneById(planeId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderPlane 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyOrderPlaneVo> selectFfwyOrderPlaneList(FfwyOrderPlane ffwyOrderPlane)
    {
        List<FfwyOrderPlaneVo> lists = null;
        Integer paymentId = ffwyOrderPlane.getPaymentId();
        Long planeId = ffwyOrderPlane.getPlaneId();
        Date planeRealpaytime = ffwyOrderPlane.getPlaneRealpaytime();
        BigDecimal planeTheamountpayable = ffwyOrderPlane.getPlaneTheamountpayable();
        Integer combomealId = ffwyOrderPlane.getCombomealId();
        Long orderClientId = ffwyOrderPlane.getOrderClientId();
        BigDecimal planeAmountpayable = ffwyOrderPlane.getPlaneAmountpayable();
        Integer planeStatus = ffwyOrderPlane.getPlaneStatus();
        Integer planeCombomealId = ffwyOrderPlane.getPlaneCombomealId();
        if(paymentId != null || planeRealpaytime != null || planeId != null || planeTheamountpayable != null || combomealId!= null || orderClientId !=null || planeAmountpayable != null || planeStatus != null || planeCombomealId != null){
            lists = ffwyOrderPlaneMapper.selectFfwyOrderPlaneList(ffwyOrderPlane);

        }
        if(paymentId == null && planeRealpaytime == null && planeId == null && planeTheamountpayable == null && combomealId == null && orderClientId ==null && planeAmountpayable == null && planeStatus == null && planeCombomealId == null) {
            lists = ffwyOrderPlaneMapper.selectFfwyOrderPlaneLists();

        }
        return lists;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyOrderPlane 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyOrderPlane(FfwyOrderPlane ffwyOrderPlane)
    {
        return ffwyOrderPlaneMapper.insertFfwyOrderPlane(ffwyOrderPlane);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyOrderPlane 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyOrderPlane(FfwyOrderPlane ffwyOrderPlane)
    {
        ffwyOrderPlane.setPlaneStatus(3);
        return ffwyOrderPlaneMapper.updateFfwyOrderPlanestutas(ffwyOrderPlane);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param planeIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderPlaneByIds(Long[] planeIds)
    {
        return ffwyOrderPlaneMapper.deleteFfwyOrderPlaneByIds(planeIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param planeId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyOrderPlaneById(Long planeId)
    {
        return ffwyOrderPlaneMapper.deleteFfwyOrderPlaneById(planeId);
    }
}
