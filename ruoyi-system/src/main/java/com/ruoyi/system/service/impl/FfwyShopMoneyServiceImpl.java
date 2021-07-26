package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.vo.FfwyShopVo;
import com.ruoyi.system.domain.vo.ShopVo;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMoneyMapper;
import com.ruoyi.system.service.IFfwyShopMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 店铺信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyShopMoneyServiceImpl implements IFfwyShopMoneyService
{
    @Autowired
    private FfwyShopMoneyMapper ffwyShopMoneyMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    /**
     * 查询店铺信息根据id
     * 
     * @param shopId 店铺信息ID
     * @return 店铺信息
     */
    @Override
    public FfwyShop selectFfwyShopById(Long shopId)
    {
        return ffwyShopMoneyMapper.selectFfwyShopById(shopId);
    }

    /**
     * 查询店铺信息列表
     * 
     * @param ffwyShopVo 店铺信息
     * @return 店铺信息
     */
    @Override
    public List<ShopVo> selectFfwyShopListBydetail(FfwyShopVo ffwyShopVo)
    {
        List<ShopVo> ffwyShops = null;
        String phone = ffwyShopVo.getPhone();
        String shopName = ffwyShopVo.getShopName();
        String searchStr = ffwyShopVo.getSearchStr();
        if(phone != null || shopName != null || searchStr !=null){
            ffwyShops = ffwyShopMoneyMapper.selectFfwyShopListBydetail(ffwyShopVo);
        }
        if(phone == null && shopName == null && searchStr == null){
            ffwyShops = ffwyShopMoneyMapper.selectFfwyShopListAll();
        }
        return ffwyShops;
    }

    /**
     * 新增店铺信息
     * 
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    @Override
    public int insertFfwyShop(FfwyShop ffwyShop)
    {
        ffwyShop.setCreateTime(DateUtils.getNowDate());
        return ffwyShopMoneyMapper.insertFfwyShop(ffwyShop);
    }

    /**
     * 修改店铺分成比例
     * 
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    @Override
    public int updateFfwyShop(FfwyShop ffwyShop)
    {
        ffwyShop.setUpdateTime(DateUtils.getNowDate());
        return ffwyShopMoneyMapper.updateFfwyShop(ffwyShop);
    }

    /**
     * 批量删除店铺信息
     * 
     * @param shopIds 需要删除的店铺信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopByIds(Long[] shopIds)
    {
        return ffwyShopMoneyMapper.deleteFfwyShopByIds(shopIds);
    }

    /**
     * 删除店铺信息信息
     * 
     * @param shopId 店铺信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopById(Long shopId)
    {
        return ffwyShopMoneyMapper.deleteFfwyShopById(shopId);
    }
}
