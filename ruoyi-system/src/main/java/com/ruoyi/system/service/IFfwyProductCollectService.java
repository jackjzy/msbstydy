package com.ruoyi.system.service;

import com.ruoyi.system.domain.product.FfwyProductCollect;

import java.util.List;

import com.ruoyi.system.domain.product.FfwyProductCollect;

/**
 * 商品收藏Service接口
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
public interface IFfwyProductCollectService 
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
     * 批量删除商品收藏
     * 
     * @param ids 需要删除的商品收藏ID
     * @return 结果
     */
    public int deleteFfwyProductCollectByIds(Long[] ids);

    /**
     * 删除商品收藏信息
     * 
     * @param id 商品收藏ID
     * @return 结果
     */
    public int deleteFfwyProductCollectById(Long id);
}
