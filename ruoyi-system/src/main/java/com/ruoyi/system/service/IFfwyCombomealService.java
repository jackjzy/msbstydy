package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Search;
import com.ruoyi.system.domain.combomeal.*;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductCategory;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.domain.to.CombomealGoodsTo;
import com.ruoyi.system.domain.to.CombomealTo;
import com.ruoyi.system.domain.to.SignInCategoryCombomeal;
import com.ruoyi.system.domain.video.FfwyVideoHotVo;
import com.ruoyi.system.domain.video.FfwyVideoHotsVo;
import com.ruoyi.system.domain.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyCombomealService 
{

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderVo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ProductVo> selectFfwyCombomealList(FfwyOrderVo ffwyOrderVo);
    

    /**
     * 套餐管理新增
     * 
     * @param ffwyCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyCombomeal(FfwyCombomeal ffwyCombomeal);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyCombomealCategory 【实体类】
     * @return 结果
     */
    public int updateFfwyCombomeal(FfwyCombomealCategory ffwyCombomealCategory);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param combomealIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealByIds(Long[] combomealIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param combomealId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyCombomealById(Long combomealId);

    public FfwyCombomeal selectFfwyCombomealById(Long combomealId);

    List<FfwyCombomeal> selectCombomeal(FfwyCombomeal ffwyCombomeal);

    List<Search> selectFfwyCombomealBycat(String token);

    List<FfwyVideoHotVo> selectFfwyCombomealBylists();

    Boolean deleteFfwyCombomealBytoken(String token);

    /**
     * 查询套餐分类下 风格列表
     * @param ffwyCombomeal  套餐分类
     * @return
     */
    SignInCategoryCombomeal getCombomealStyleList(Long categoryId,Integer currPage);

    /**
     * 获得套餐详情
     * @param combomealId 套餐Id
     * @return
     */
    AjaxResult getCombomealDetails(Long combomealId,Long userId);

    /**
     * 装修价格预估
     * @param combomealTo
     * @return
     */
    String getEstimatedPrice(CombomealTo combomealTo);

    /**
     * 生成订单
     * @param combomealTo
     * @return 订单编号
     */
    String generateCombomaealOrder(CombomealTo combomealTo);

    /**
     * 套餐商品添加
     * @param combomealGoodsTo
     * @return
     */
    AjaxResult combomaealGoodsAdd(CombomealGoodsTo combomealGoodsTo);

    /**
     *  过期的未付款套餐订单  关单、解锁库存
     * @param lockVo
     */
    void combomaealUnlockStock(WareSkuLockVo lockVo);

    /**
     *   修改套餐订单状态
     * @param orderCombomealId  套餐订单id
     * @param status  套餐状态
     * @return
     */
    int combomaealStatusUpdata(Long orderCombomealId,String status);




    Map selectfindByproductcat();

    List<String> selectFfwyfindByproductcat();

    List<FfwyProduct> selectFfwyfindByproductcatName(Long productId);

    List<FfwyProductCategory> selectFfwyfindByproductcatdesc(Long categoryId);

    List<FfwySpecification> selectFfwyfindByproductcatspe(Long parentId);

    List<FfwyProductCategory> selectFfwyfindBysoft();

    List<FfwyProductCategory> selectFfwyfindByarticles();

    List<FfwyProductSku> selectFfwyfindByproductcatsj(Long productId);

    List<String> selectFfwyfindByproductcatej(Long productCategoryId);

    int editFfwyCombomeal(FfwyCombomeal ffwyCombomeal);

    int deletePhopos(String path);

    List<FfwyVideoHotsVo> selectFfwyCombomealfindBylists(@Param("hotId") Long hotId);

}

