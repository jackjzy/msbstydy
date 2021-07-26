package com.ruoyi.system.service;

import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.combomealorders.Materials;
import com.ruoyi.system.domain.combomealorders.Phases;
import com.ruoyi.system.domain.vo.FfwyOrderCombomealVo;
import com.ruoyi.system.domain.vo.WareSkuLockVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-05-14
 */
public interface IFfwyOrderCombomealService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param orderId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyOrderCombomeal selectFfwyOrderCombomealById(Long orderId);



    public FfwyOrderCombomeal selectFfwyOrderCombomealByOrderNumber(String orderNumber);


    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyOrderCombomeal> selectFfwyOrderCombomealList(FfwyOrderCombomeal ffwyOrderCombomeal);

    /**
     * 查询【所有订单】列表PC
     *
     * @param ffwyOrderCombomealVo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyOrderCombomeal> selectFfwyOrderCombomealListPC(FfwyOrderCombomealVo ffwyOrderCombomealVo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyOrderCombomeal(FfwyOrderCombomeal ffwyOrderCombomeal);
    /**
     * 新增【套餐订单、装修材料、子订单（节点）】
     *

     * @param materials 【装修材料】

     * @return 结果
     */
    public int insertFfwyOrder(Materials materials);


    /**
     * 新增【套餐订单】
     *

     * @param

     * @return 结果
     */
    public int createOrder(Long orderId,
                           String designerName,
                           MultipartFile photo,
                           List<MultipartFile> phtotos,
                           String phaseStr,
                           MultipartFile doc);

    /**
     * 新增【添加设计图】
     *

     * @param

     * @return 结果
     */
    public int addDesignerPhotos(Long orderId,List<MultipartFile> files);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyOrderCombomeal(FfwyOrderCombomeal ffwyOrderCombomeal);

    /**
     * 修改【分配工长】
     *
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    public Map<String, Object> updateFfwyOrderCombomealAllocation(FfwyOrderCombomeal ffwyOrderCombomeal);


    /**
     * 修改【分配工长】
     *
     * @param ffwyOrderCombomeal 【请填写功能名称】
     * @return 结果
     */
    public int allocationWorker(FfwyOrderCombomeal ffwyOrderCombomeal);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param orderIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealByIds(Long[] orderIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param orderId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyOrderCombomealById(Long orderId);

    List<FfwyOrderCombomeal> selectFfwyOrderCombomealByorderType();
}
