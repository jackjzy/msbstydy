package com.ruoyi.system.mapper.product;

import java.util.List;
import com.ruoyi.system.domain.product.FfwyProductDesc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Repository
public interface FfwyProductDescMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param descId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyProductDesc selectFfwyProductDescById(Long descId);

    /**
     * @param productId 商品
     * @return 商品规格
     */
    public List<FfwyProductDesc> selectSpecificationById(long productId);

    /**
     * @param productId 商品
     * @return 商品规格
     */
    public List<FfwyProductDesc> selectFfwySpecificationById(long productId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyProductDesc 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyProductDesc> selectFfwyProductDescList(FfwyProductDesc ffwyProductDesc);

    /**
     *
     * @param productId
     * @param descSort
     * @param descType
     * @param descStatus
     * @return
     */
    public List<FfwyProductDesc> selectProductDescListBySort(@Param("productId") Long productId,@Param("descSort")Long descSort, @Param("descType")String descType, @Param("descStatus")Boolean descStatus);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyProductDesc 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyProductDesc(FfwyProductDesc ffwyProductDesc);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyProductDesc 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyProductDesc(FfwyProductDesc ffwyProductDesc);

    /**
     * 删除【请填写功能名称】
     * 
     * @param descId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyProductDescById(Long descId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param descIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProductDescByIds(Long[] descIds);

    public int  deleteFfwyProductDescByProductId(Long productId);
}
