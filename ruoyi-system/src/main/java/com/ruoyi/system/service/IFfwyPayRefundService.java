package com.ruoyi.system.service;


import com.ruoyi.system.domain.FfwyPayRefund;
import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.order.FfwyOrderDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-07-07
 */
public interface IFfwyPayRefundService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyPayRefund selectFfwyPayRefundById(Long id);
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyPayRefund selectFfwyPayRefundByOrderId(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyPayRefund> selectFfwyPayRefundList(FfwyPayRefund ffwyPayRefund);

    /**
     * 新增退款信息
     * 
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyPayRefund(FfwyPayRefund ffwyPayRefund);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPayRefund 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyPayRefund(FfwyPayRefund ffwyPayRefund);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPayRefundByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyPayRefundById(Long id);

    public boolean refundUnionPay(String orderId,String orderRefundNumber ,String payType,Long userId,Long reFundUser,String money,Integer orderType);

    public boolean refunAliPay(String orderNo, Integer refundFee,String outRequestNo);

    public boolean refun(FfwyOrderDetails orderDetails);

    void callBackRefund(HttpServletRequest req, HttpServletResponse resp) ;

    void ffchageOrderRefund(String orderId, String reqReserved);


}
