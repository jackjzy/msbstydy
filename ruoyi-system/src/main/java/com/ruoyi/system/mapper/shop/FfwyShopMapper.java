package com.ruoyi.system.mapper.shop;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.querys.QueryShop;
import com.ruoyi.system.domain.shop.FfwyShop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 店铺Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
@Repository
public interface FfwyShopMapper 
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
    public FfwyShop selectStroeShopByuserIdAndShopId(@Param("shopId") Long shopId,@Param("userId") Long userId);

    /**
     * 查询店铺列表
     * 
     * @param ffwyShop 店铺
     * @return 店铺集合
     */
    public List<FfwyShop> selectFfwyShopList(FfwyShop ffwyShop);

    /**
     * 分页查询
     * @param num
     * @param size
     * @return
     */
    public List<FfwyShop> selectShopListLimit(@Param("num")Integer num,@Param("size")Integer size);

    public int sCount();

    public List<FfwyShop> selectFfwyShopIdList(Long shopId);

    public  FfwyShop selectFfwyStoreShopCount(Long shopId);

    /**
     * 查询店铺审核列表
     *
     * @param queryShop 店铺
     * @return 店铺集合
     */
    public List<FfwyShop> selectByShopAdudtiStatus(QueryShop queryShop);
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
    public int insertStroeShop(@Param("shopId") Long shopId,@Param("userId") Long userId);

    /**
     * 查询收藏店铺列表
     *
     * @param  userId 用户id
     *
     * @return 店铺集合
     */
    public List<FfwyShop> selectStroeShop(Long userId);

    public int updateShopDisable(@Param("shopId")Long shopId , @Param("disableTime") Date disableTime, @Param("shopStatus") Integer shopStatus);

    /**
     * 修改店铺
     * 
     * @param ffwyShop 店铺
     * @return 结果
     */
    public int updateFfwyShop(FfwyShop ffwyShop);

    public int updateShop(@Param("shopId") Long shopId,@Param("terraceRatio") BigDecimal
            terraceRatio,@Param("shopRetio") BigDecimal shopRetio);

    /**
     * 修改店铺
     *
     * @param ffwyShop 店铺
     * @return 结果
     */
    public int updateShopDefaultAdd(FfwyShop ffwyShop);

    /**
     * 删除店铺
     * 
     * @param shopId 店铺ID
     * @return 结果
     */
    public int deleteFfwyShopById(Long shopId);

    /**
     * 批量删除店铺
     * 
     * @param shopIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyShopByIds(Long[] shopIds);

    /**
     * 删除收藏店铺
     *
     * @param shopId 店铺
     * @param userId 用户id
     * @return 结果
     */
    public int deleteStroeShop(@Param("shopId") Long shopId,@Param("userId") Long userId);


    FfwyShop selectFfwyStoreShopList(FfwyShop ffwyShop1);
}
