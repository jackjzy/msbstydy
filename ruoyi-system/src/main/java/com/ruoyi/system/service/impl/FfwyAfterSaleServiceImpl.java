package com.ruoyi.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.aftersale.FfwyAfterPhoto;
import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.aftersale.FfwyAfterStatus;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.vo.FfwyAftersaletypeVo;
import com.ruoyi.system.mapper.aftersale.FfwyAfterPhotoMapper;
import com.ruoyi.system.mapper.aftersale.FfwyAfterSaleMapper;
import com.ruoyi.system.mapper.aftersale.FfwyAfterTypeMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.service.FfwyexpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyAfterSaleService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 售后Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-27
 */
@Service
public class FfwyAfterSaleServiceImpl implements IFfwyAfterSaleService 
{
    @Autowired
    private FfwyAfterSaleMapper ffwyAfterSaleMapper;

    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;

    @Autowired
    private FfwyAfterTypeMapper ffwyAfterTypeMapper;

    @Autowired
    private FfwyAfterPhotoMapper ffwyAfterPhotoMapper;

    @Autowired
    private FfwyexpressService ffwyexpressService;

    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;



    /**
     * 查询售后
     * 
     * @param afterSaleid 售后ID
     * @return 售后
     */
    @Override
    public FfwyAfterSale selectFfwyAfterSaleById(Long afterSaleid)
    {
        return ffwyAfterSaleMapper.selectFfwyAfterSaleById(afterSaleid);
    }

    @Override
    public FfwyAfterSale selectByOrderId(Long orderId) {
        return ffwyAfterSaleMapper.selectFfwyAfterSaleByOrderId(orderId);
    }

    /**
     * 查询售后列表
     * 
     * @param ffwyAfterSale 售后
     * @return 售后
     */
    @Override
    public List<FfwyAfterSale> selectFfwyAfterSaleList(FfwyAfterSale ffwyAfterSale)
    {
        return ffwyAfterSaleMapper.selectFfwyAfterSaleList(ffwyAfterSale);
    }

    /**
     * 新增售后
     * 
     * @param ffwyAfterSale 售后
     * @return 结果
     */
    @Override
    public int insertFfwyAfterSale(FfwyAfterSale ffwyAfterSale)
    {
        int[] orderIds = ffwyAfterSale.getOrderIds();
        String orderDetailsId=null;
        for (int orderId : orderIds) {
            orderDetailsId += orderId+",";
            Integer afterType = ffwyAfterSale.getAfterType();
            FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
            ffwyOrderDetails.setOrderDetailsId(Long.valueOf(orderId));
            if (afterType == 1){
                ffwyOrderDetails.setOrderStatus(8);

            } else {
                ffwyOrderDetails.setOrderStatus(9);
            }
            undateOrderStatus(Long.valueOf(orderId),ffwyOrderDetails.getOrderStatus());
            ffwyOrderDetailsMapper.updateFfwyOrderDetails(ffwyOrderDetails);

        }

        Integer afterType = ffwyAfterSale.getAfterType();
        ffwyAfterSale.setCreateTime(DateUtils.getNowDate());
        ffwyAfterSale.setOrderDetailsId(StringUtils.remove(orderDetailsId,"null"));
        FfwyAfterStatus ffwyAfterStatus = new FfwyAfterStatus();

        if (afterType == 1){
            ffwyAfterStatus.setAuditStatusId(8);
            ffwyAfterSale.setAfterStatus("8");
        }else {
            ffwyAfterStatus.setAuditStatusId(9);
            ffwyAfterSale.setAfterStatus("9");
        }
        //订单号  毫秒+随机数
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis()).append(new Random().nextInt(1000));
        ffwyAfterSale.setRefundOrderNumber(stringBuilder.toString());
        int i = ffwyAfterSaleMapper.insertFfwyAfterSale(ffwyAfterSale);

        List<String> img = ffwyAfterSale.getImg();
        FfwyAfterPhoto ffwyAfterPhoto = new FfwyAfterPhoto();
        if (StringUtils.isNotEmpty(img)){
            for (String s : img) {
                ffwyAfterPhoto.setAfterPhoto(StringUtils.remove(s,prefixUrl));
                ffwyAfterPhoto.setAfterSaleId(ffwyAfterSale.getAfterSaleid());
                ffwyAfterPhoto.setCreateTime(new Date());
                ffwyAfterPhotoMapper.insertFfwyAfterPhoto(ffwyAfterPhoto);
            }
        }


        ffwyAfterStatus.setSaleId(ffwyAfterSale.getAfterSaleid());
        ffwyAfterStatus.setCreateTime(new Date());
        ffwyAfterSaleMapper.insertFfwyAfterStatus(ffwyAfterStatus);

       return i;
    }

    /**
     * 根据传入子订单更改父订单状态
     *
     * @param orderDetailId 子订单id
     * @param status 子订单要修改的状态 ！！！！ 修改后的状态
     */
    @Override
    public void undateOrderStatus(Long orderDetailId,int status){

        FfwyOrderDetails ffwyOrderDetailsDb = ffwyOrderDetailsMapper.selectFfwyOrderDetailsById(orderDetailId);
        if(null==ffwyOrderDetailsDb)return;

        List<Integer> ffwyOrderDetailst = ffwyOrderDetailsMapper.selectFfwyOrderDetailsByOrderId(ffwyOrderDetailsDb.getOrderId(),orderDetailId);
        if(ffwyOrderDetailst.size()<1)return;

        List<Integer> ffwyOrderDetailsStream = ffwyOrderDetailst.stream().filter(fod -> fod != status).collect(Collectors.toList());
        if(ffwyOrderDetailsStream.size() > 1)return;

        FfwyOrder ffwyOrder = new FfwyOrder();
        ffwyOrder.setOrderId(ffwyOrderDetailsDb.getOrderId());
        ffwyOrder.setStatusId(status);
        ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
    }
    /**
     * 修改售后
     * 
     * @param ffwyAfterSale 售后
     * @return 结果
     */
    @Override
    public int updateFfwyAfterSale(FfwyAfterSale ffwyAfterSale)
    {
        return ffwyAfterSaleMapper.updateFfwyAfterSale(ffwyAfterSale);
    }

    /**
     * 批量删除售后
     * 
     * @param afterSaleids 需要删除的售后ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterSaleByIds(Long[] afterSaleids)
    {
        return ffwyAfterSaleMapper.deleteFfwyAfterSaleByIds(afterSaleids);
    }

    /**
     * 删除售后信息
     * 
     * @param afterSaleid 售后ID
     * @return 结果
     */
    @Override
    public int deleteFfwyAfterSaleById(Long afterSaleid)
    {
        ffwyAfterSaleMapper.deleteFfwyAfterStatus(afterSaleid);

        return ffwyAfterSaleMapper.deleteFfwyAfterSaleById(afterSaleid);
    }

    @Override
    public int updateFfwyAfterSaleBysuatus(Integer orderDetailsId) {

        FfwyAfterSale ffwyAfterSale = new FfwyAfterSale();
        ffwyAfterSale.setAfterStatus("12");
        ffwyAfterSale.setAfterSaleid(Long.valueOf(orderDetailsId));
        ffwyAfterSaleMapper.updateFfwyAfterSale(ffwyAfterSale);

        FfwyAfterStatus ffwyAfterStatus = new FfwyAfterStatus();
        ffwyAfterStatus.setSaleId(Long.valueOf(orderDetailsId));
        ffwyAfterStatus.setAuditStatusId(12);
        ffwyAfterStatus.setCreateTime(new Date());
        ffwyAfterSaleMapper.insertFfwyAfterStatus(ffwyAfterStatus);

        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
        ffwyOrderDetails.setOrderDetailsId(orderDetailsId.longValue());
        ffwyOrderDetails.setOrderStatus(12);
        return ffwyOrderDetailsMapper.updateFfwyAfterSaleBysuatus(ffwyOrderDetails);
    }

    @Override
    public Map selectFfwyOrderDetailsByorderDetailsId(Integer afterSaleid) {
        Map map = new HashMap<>();

        List<FfwyAftersaletypeVo> ffwyAftersaletypeVos = ffwyAfterTypeMapper.selectFfwyOrderDetailsByorderDetailsId(afterSaleid);
        for (FfwyAftersaletypeVo ffwyAftersaletypeVo : ffwyAftersaletypeVos) {
            String trackIngNumber = ffwyAftersaletypeVo.getKddh();
            if (StringUtils.isNotEmpty(trackIngNumber)){
                AjaxResult order = ffwyexpressService.findOrder(trackIngNumber);
                map.put("order",order.get("data"));
            } else {
                map.put("order",null);
            }


            List<FfwyAfterStatus> ffwyAfterStatuses = ffwyAfterTypeMapper.selectFfwyAfterStatus(afterSaleid);
//            ffwyAftersaletypeVo.setFfwyAfterStatuses(ffwyAfterStatuses);
            map.put("ords",ffwyAfterStatuses);
        }
        map.put("ffwyAftersaletypeVos",ffwyAftersaletypeVos);
        return map;
    }
}
