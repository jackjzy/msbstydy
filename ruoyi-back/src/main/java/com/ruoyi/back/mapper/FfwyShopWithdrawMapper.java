package com.ruoyi.back.mapper;

import com.ruoyi.back.domain.FfwyShopWithdraw;

import java.util.List;

/**
 * 提现申请管理Mapper接口
 *
 * @author Yapeng Guo
 * @date 2021-05-25
 */
public interface FfwyShopWithdrawMapper
{
    /**
     * 查询提现申请管理
     *
     * @param withdrawId 提现申请管理ID
     * @return 提现申请管理
     */
    public FfwyShopWithdraw selectFfwyShopWithdrawById(Long withdrawId);

    /**
     * 查询提现申请管理列表
     *
     * @param ffwyShopWithdraw 提现申请管理
     * @return 提现申请管理集合
     */
    public List<FfwyShopWithdraw> selectFfwyShopWithdrawList(FfwyShopWithdraw ffwyShopWithdraw);

    /**
     * 新增提现申请管理
     *
     * @param ffwyShopWithdraw 提现申请管理
     * @return 结果
     */
    public int insertFfwyShopWithdraw(FfwyShopWithdraw ffwyShopWithdraw);

    /**
     * 修改提现申请管理
     *
     * @param ffwyShopWithdraw 提现申请管理
     * @return 结果
     */
    public int updateFfwyShopWithdraw(FfwyShopWithdraw ffwyShopWithdraw);

    /**
     * 删除提现申请管理
     *
     * @param withdrawId 提现申请管理ID
     * @return 结果
     */
    public int deleteFfwyShopWithdrawById(Long withdrawId);

    /**
     * 批量删除提现申请管理
     *
     * @param withdrawIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyShopWithdrawByIds(Long[] withdrawIds);

}
