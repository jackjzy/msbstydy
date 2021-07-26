package com.ruoyi.system.mapper.aftersale;

import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.aftersale.FfwyAfterStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 售后Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
@Repository
public interface FfwyAfterSaleMapper 
{
    /**
     * 查询售后
     * 
     * @param afterSaleid 售后ID
     * @return 售后
     */
    public FfwyAfterSale selectFfwyAfterSaleById(Long afterSaleid);


    /**
     * 根据订单查询售后
     *
     * @param orderId 订单id
     * @return 售后
     */
    public FfwyAfterSale selectFfwyAfterSaleByOrderId(Long orderId);
    /**
     * 查询售后列表
     * 
     * @param ffwyAfterSale 售后
     * @return 售后集合
     */
    public List<FfwyAfterSale> selectFfwyAfterSaleList(FfwyAfterSale ffwyAfterSale);

    /**
     * 新增售后
     * 
     * @param ffwyAfterSale 售后
     * @return 结果
     */
    public int insertFfwyAfterSale(FfwyAfterSale ffwyAfterSale);

    /**
     * 修改售后
     * 
     * @param ffwyAfterSale 售后
     * @return 结果
     */
    public int updateFfwyAfterSale(FfwyAfterSale ffwyAfterSale);

    /**
     * 删除售后
     * 
     * @param afterSaleid 售后ID
     * @return 结果
     */
    public int deleteFfwyAfterSaleById(Long afterSaleid);

    /**
     * 批量删除售后
     * 
     * @param afterSaleids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyAfterSaleByIds(Long[] afterSaleids);

    List<FfwyAfterSale> selectFfwyOrderDetailsByorderDetailsId(Integer orderDetailsId);

    int insertFfwyAfterStatus(FfwyAfterStatus ffwyAfterStatus);

    int deleteFfwyAfterStatus(Long afterSaleid);
}
