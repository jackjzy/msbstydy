package com.ruoyi.system.service;

import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.vo.FfwyOrderVo;
import com.ruoyi.system.domain.vo.FfwyorderDetailsVo;
import com.ruoyi.system.domain.vo.PaymentVo;

import java.util.List;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyOrderMoneyService 
{
    /**
     * 查询订单
     * 
     * @param orderId 订单ID
     * @return 订单
     */
    public List<FfwyorderDetailsVo> selectFfwyOrderById(Long orderId);

    /**
     * 查询所有支付记录
     * 
     * @param ffwyOrderVo 订单
     * @return 订单集合
     */
   public List<PaymentVo>  selectFfwyOrderListBydetail(FfwyOrderVo ffwyOrderVo);

    /**
     * 新增订单
     * 
     * @param ffwyOrder 订单
     * @return 结果
     */
    public int insertFfwyOrder(FfwyOrder ffwyOrder);

    /**
     * 修改订单
     * 
     * @param ffwyOrder 订单
     * @return 结果
     */
    public int updateFfwyOrder(FfwyOrder ffwyOrder);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    public int deleteFfwyOrderByIds(Long[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteFfwyOrderById(Long orderId);

}
