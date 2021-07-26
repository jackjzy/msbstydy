package com.ruoyi.system.mapper.product;

import com.ruoyi.system.domain.product.FfwyProductCollect;

import java.util.List;


/**
 * 商品收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
public interface FfwyProductCollectMapper 
{
    /**
     * 查询商品收藏
     * 
     * @param id 商品收藏ID
     * @return 商品收藏
     */
    public FfwyProductCollect selectFfwyProductCollectById(Long id);

    /**
     * 查询商品收藏列表
     * 
     * @param ffwyProductCollect 商品收藏
     * @return 商品收藏集合
     */
    public List<FfwyProductCollect> selectFfwyProductCollectList(FfwyProductCollect ffwyProductCollect);

    /**
     * 新增商品收藏
     * 
     * @param ffwyProductCollect 商品收藏
     * @return 结果
     */
    public int insertFfwyProductCollect(FfwyProductCollect ffwyProductCollect);

    /**
     * 修改商品收藏
     * 
     * @param ffwyProductCollect 商品收藏
     * @return 结果
     */
    public int updateFfwyProductCollect(FfwyProductCollect ffwyProductCollect);

    /**
     * 删除商品收藏
     * 
     * @param id 商品收藏ID
     * @return 结果
     */
    public int deleteFfwyProductCollectById(Long id);

    /**
     * 批量删除商品收藏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProductCollectByIds(Long[] ids);
}
