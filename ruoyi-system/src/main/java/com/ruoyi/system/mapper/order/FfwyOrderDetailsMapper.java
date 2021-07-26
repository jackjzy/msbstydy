package com.ruoyi.system.mapper.order;

import java.util.List;

import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.product.FfwyProductSku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 订单详情Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Repository
public interface FfwyOrderDetailsMapper {
    /**
     * 查询订单详情
     *
     * @param orderDetailsId 订单详情ID
     * @return 订单详情
     */
    public FfwyOrderDetails selectFfwyOrderDetailsById(Long orderDetailsId);

    /**
     * 查询订单详情列表
     *
     * @param ffwyOrderDetails 订单详情
     * @return 订单详情集合
     */
    public List<FfwyOrderDetails> selectFfwyOrderDetailsList(FfwyOrderDetails ffwyOrderDetails);


    /**
     * 查询后台订单详情列表
     *
     * @param ffwyOrderDetails 订单详情
     * @return 订单详情集合
     */
    public List<FfwyOrderDetails> backOrderDetailsList(FfwyOrderDetails ffwyOrderDetails);

    public List<FfwyOrderDetails> selectFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails);

    /**
     * 新增订单详情
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int insertFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails);

    /**
     * 修改订单详情
     *
     * @param ffwyOrderDetails 订单详情
     * @return 结果
     */
    public int updateFfwyOrderDetails(FfwyOrderDetails ffwyOrderDetails);
    public int updateFfwyOrderDetailsId(FfwyOrderDetails ffwyOrderDetails);


    /**
     * 删除订单详情
     *
     * @param orderDetailsId 订单详情ID
     * @return 结果
     */
    public int deleteFfwyOrderDetailsById(Long orderDetailsId);

    /**
     * 批量删除订单详情
     *
     * @param orderId 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyOrderDetailsByIds(Long orderId);

    public List<FfwyOrderDetails> selectFfwyOrderDetailsRepertory(FfwyOrderDetails ffwyOrderDetails);

    public FfwyOrderDetails selectFfwyOrderDetailsRepertorys(FfwyOrderDetails ffwyOrderDetails);

    List<FfwyOrderDetails> selectFfwyOrderallList(FfwyOrderDetails ffwyOrderDetails);

    List<FfwyOrderDetails> selectFfwyOrderById(Long orderId);

    FfwyOrderDetails selectFfwyOrderCombomaealUnlockStock(String orderSn);

    List<FfwyOrderDetails> selectFfwyOrderStatusList(FfwyOrderDetails order);

    List<FfwyOrderDetails> selectFfwyOrderAllList(FfwyOrderDetails order);

    List<FfwyOrderDetails> selectpublishEvaluate(FfwyOrderDetails ffwyOrderDetails);

    List<FfwyOrderDetails> selectFfwyorderdetailsByseales(Long userId);

    List<FfwyAfterSale> selectFfwyOrderDetailsByorderDetailsId(Integer orderDetailsId);

    int updateFfwyAfterSaleBysuatus(FfwyOrderDetails ffwyOrderDetails);


    FfwyOrderDetails selectFfwyOrderDetailsSku(@Param("orderId") Long orderId);

    FfwyOrderDetails selectFfwyOrderExpressById(Long orderId);

    List<Integer> selectFfwyOrderDetailsByOrderId(@Param("orderId") Long orderId,@Param("orderDetailId") Long orderDetailId);
}
