package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.product.FfwySpecification;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单规格Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Repository
public interface FfwySpecificationMapper {
    /**
     * 查询订单规格
     *
     * @param specificationId 订单规格ID
     * @return 订单规格
     */
    public FfwySpecification selectFfwySpecificationById(Integer specificationId);

//    /**
//     * 根据规格名称查询订单规格
//     *
//     * @param specificationName 订单规格ID
//     * @return 订单规格
//     */
//    public FfwySpecification selectFfwySpecificationBySpecificationName(String specificationName);

    /**
     * 查询订单规格列表
     *
     * @param ffwySpecification 订单规格
     * @return 订单规格集合
     */
    public List<FfwySpecification> selectFfwySpecificationList(FfwySpecification ffwySpecification);

    /**
     * 新增订单规格
     *
     * @param ffwySpecification 订单规格
     * @return 结果
     */
    public int insertFfwySpecification(FfwySpecification ffwySpecification);

    /**
     * 修改订单规格
     *
     * @param ffwySpecification 订单规格
     * @return 结果
     */
    public int updateFfwySpecification(FfwySpecification ffwySpecification);

    /**
     * 删除订单规格
     *
     * @param specificationId 订单规格ID
     * @return 结果
     */
    public int deleteFfwySpecificationById(Integer specificationId);

    /**
     * 批量删除订单规格
     *
     * @param specificationIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwySpecificationByIds(Integer[] specificationIds);

    List<FfwySpecification> selectFfwySpecificationByproductId(Long productId);

    FfwySpecification selectSpecificationNameAndProductId(@Param("productId") Long productId, @Param("specificationName") String specificationName);

    List<FfwySpecification> selectByspecificationid(Long parentId);
}



