package com.ruoyi.system.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.querys.QueryShop;
import com.ruoyi.system.domain.shop.FfwyShop;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * 店铺Service接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public interface IFfwyShopService
{
    /**
     * 查询店铺
     * 
     * @param shopId 店铺ID
     * @return 店铺
     */
    public FfwyShop selectFfwyShopById(Long shopId);
    /**
     * 查询店铺
     *
     * @param userId 店铺ID
     * @return 店铺
     */
    public FfwyShop selectFfwyByUserId(Long userId);
    /**
     * 查询一個收藏店铺
     *
     * @param shopId 店铺ID
     * @return 店铺
     */
    public FfwyShop selectStroeShopByuserIdAndShopId(Long shopId, Long userId);

    /**
     * 查询店铺列表
     * 
     * @param ffwyShop 店铺
     * @return 店铺集合
     */
    public List<FfwyShop> selectFfwyShopList(FfwyShop ffwyShop);
    /**
     * 查询店铺审核列表
     *
     * @param queryShop 店铺
     * @return 店铺集合
     */

    public List<FfwyShop> selectByShopAdudtiStatus(QueryShop queryShop);
    /**
     * 查询收藏店铺列表
     *
     * @param  userId 用户id
     *
     * @return 店铺集合
     */
    public List<FfwyShop> selectStroeShop(Long userId);

    /**
     * 新增店铺
     * 
     * @param ffwyShop 店铺
     * @return 结果
     */
    public int insertFfwyShop(FfwyShop ffwyShop);

    /**
     * 新增收藏店铺
     *
     * @param shopId 店铺
     * @param userId 用户id
     * @return 结果
     */
    public AjaxResult insertStroeShop(FfwyShop ffwyShop);

    /**
     * 修改店铺
     * 
     * @param ffwyShop 店铺
     * @return 结果
     */
    public int updateFfwyShop(FfwyShop ffwyShop);

    /**
     * 修改店铺
     *
     * @param shopId 店铺id
     * @return 结果
     */
    public int updateFfwyShopLogo(MultipartFile file,Long shopId);

    /**
     * 批量删除店铺
     * 
     * @param shopIds 需要删除的店铺ID
     * @return 结果
     */
    public int deleteFfwyShopByIds(Long[] shopIds);

    /**
     * 删除店铺信息
     * 
     * @param shopId 店铺ID
     * @return 结果
     */
    public int deleteFfwyShopById(Long shopId);

    List<FfwyShop> selectFfwyShopAppList(FfwyShop ffwyShop);

    List<FfwyShop> selectFfwyShopIdList(FfwyShop ffwyShop);

    List<FfwyProduct> selectFfwyproductList(FfwyProduct ffwyProduct);

    /**
     * 删除收藏店铺
     *
     * @param shopId 店铺
     * @param userId 用户id
     * @return 结果
     */
    public AjaxResult deleteStroeShop(FfwyShop ffwyShop );


    /**
     * 查看店铺抽成比例
     * @return
     */
    String viewShare();

    /**
     * 修改店铺抽成比例
     * @param terraceRatio 平台分成比例
     * @param shopRetio  商家分成比例
     * @return
     */
    boolean modifyShare(BigDecimal terraceRatio, BigDecimal shopRetio);

    /**
     * 商店禁用
     * @param shopId
     * @param disableTime
     * @return
     */
    int disableShop(Long shopId ,  Date disableTime);
}

