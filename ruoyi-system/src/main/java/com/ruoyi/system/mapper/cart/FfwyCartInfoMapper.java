package com.ruoyi.system.mapper.cart;

import com.ruoyi.system.domain.cart.FfwyCartInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车 Mapper接口
 *
 * @author ruoyi
 * @date 2021-05-14
 */
public interface FfwyCartInfoMapper {
    /**
     * 查询购物车
     *
     * @param id 购物车 ID
     * @return 购物车
     */
    public FfwyCartInfo selectFfwyCartInfoById(Long id);

    /**
     * 查询购物车 列表
     *
     * @param ffwyCartInfo 购物车
     * @return 购物车 集合
     */
    public List<FfwyCartInfo> selectFfwyCartInfoList(FfwyCartInfo ffwyCartInfo);

    /**
     * 新增购物车
     *
     * @param ffwyCartInfo 购物车
     * @return 结果
     */
    public int insertFfwyCartInfo(FfwyCartInfo ffwyCartInfo);

    /**
     * 修改购物车
     *
     * @param ffwyCartInfo 购物车
     * @return 结果
     */
    public int updateFfwyCartInfo(FfwyCartInfo ffwyCartInfo);

    /**
     * 删除购物车
     *
     * @param id 购物车 ID
     * @return 结果
     */
    public int deleteFfwyCartInfoById(Long id);

    /**
     * 批量删除购物车
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyCartInfoByIds(String[] ids);

    FfwyCartInfo selectFfwyCartInfoisCheckedById(@Param("id") String ids, @Param("userId") Long userId);

    FfwyCartInfo selectFfwyCartInfoSkuId(@Param("skuId") Long skuId);

    int updateFfwyCartInfoId( String[] id);
    int updateFfwyCartInfoisCheckedId( String[] id);

    int deleteFfwyCartInfoSkuId(Long skuId);

}
