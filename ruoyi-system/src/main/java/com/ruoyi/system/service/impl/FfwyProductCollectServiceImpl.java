package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.product.FfwyProductCollect;
import com.ruoyi.system.mapper.product.FfwyProductCollectMapper;
import com.ruoyi.system.domain.product.FfwyProductCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;


import com.ruoyi.system.service.IFfwyProductCollectService;

/**
 * 商品收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-11
 */
@Service
public class FfwyProductCollectServiceImpl implements IFfwyProductCollectService 
{
    @Autowired
    private FfwyProductCollectMapper ffwyProductCollectMapper;

    /**
     * 查询商品收藏
     * 
     * @param id 商品收藏ID
     * @return 商品收藏
     */
    @Override
    public FfwyProductCollect selectFfwyProductCollectById(Long id)
    {
        return ffwyProductCollectMapper.selectFfwyProductCollectById(id);
    }

    /**
     * 查询商品收藏列表
     * 
     * @param ffwyProductCollect 商品收藏
     * @return 商品收藏
     */
    @Override
    public List<FfwyProductCollect> selectFfwyProductCollectList(FfwyProductCollect ffwyProductCollect)
    {
        return ffwyProductCollectMapper.selectFfwyProductCollectList(ffwyProductCollect);
    }

    /**
     * 新增商品收藏
     * 
     * @param ffwyProductCollect 商品收藏
     * @return 结果
     */
    @Override
    public int insertFfwyProductCollect(FfwyProductCollect ffwyProductCollect)
    {
        ffwyProductCollect.setCreateTime(DateUtils.getNowDate());
        return ffwyProductCollectMapper.insertFfwyProductCollect(ffwyProductCollect);
    }

    /**
     * 修改商品收藏
     * 
     * @param ffwyProductCollect 商品收藏
     * @return 结果
     */
    @Override
    public int updateFfwyProductCollect(FfwyProductCollect ffwyProductCollect)
    {
        ffwyProductCollect.setUpdateTime(DateUtils.getNowDate());
        return ffwyProductCollectMapper.updateFfwyProductCollect(ffwyProductCollect);
    }

    /**
     * 批量删除商品收藏
     * 
     * @param ids 需要删除的商品收藏ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCollectByIds(Long[] ids)
    {
        return ffwyProductCollectMapper.deleteFfwyProductCollectByIds(ids);
    }

    /**
     * 删除商品收藏信息
     * 
     * @param id 商品收藏ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCollectById(Long id)
    {
        return ffwyProductCollectMapper.deleteFfwyProductCollectById(id);
    }
}
