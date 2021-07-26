package com.ruoyi.system.service;

import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.vo.FfwyAftersaletypeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 售后Service接口
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
public interface IFfwyAfterSaleService 
{
    /**
     * 查询售后
     * 
     * @param afterSaleid 售后ID
     * @return 售后
     */
    public FfwyAfterSale selectFfwyAfterSaleById(Long afterSaleid);


    /**
     * 根据订单id
     *
     * @param orderId 售后ID
     * @return 售后
     */
    public FfwyAfterSale selectByOrderId(Long orderId);

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
     * 批量删除售后
     * 
     * @param afterSaleids 需要删除的售后ID
     * @return 结果
     */
    public int deleteFfwyAfterSaleByIds(Long[] afterSaleids);

    /**
     * 删除售后信息
     * 
     * @param afterSaleid 售后ID
     * @return 结果
     */
    public int deleteFfwyAfterSaleById(Long afterSaleid);

    int updateFfwyAfterSaleBysuatus(Integer orderDetailsId);

    Map selectFfwyOrderDetailsByorderDetailsId(@Param("afterSaleid") Integer afterSaleid);

     void undateOrderStatus(Long orderDetailId,int status);
}
