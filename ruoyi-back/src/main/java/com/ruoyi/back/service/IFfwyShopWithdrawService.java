package com.ruoyi.back.service;

import com.ruoyi.back.domain.FfwyShopWithdraw;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提现申请管理Service接口
 *
 * @author Yapeng Guo
 * @date 2021-05-25
 */
public interface IFfwyShopWithdrawService
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
     * 拒绝提现申请
     *
     * @param ffwyShopWithdraw 提现申请管理
     * @return 结果
     */
    public int rejectShopWithdraw(FfwyShopWithdraw ffwyShopWithdraw);

    /**
     * 同意提现申请管理
     *
     * @param
     * @return 结果
     */
    public int updateFfwyShopWithdraw(
            Long withdrawId,
            String applyStatus,
            Long shopId,
            BigDecimal withdrawNum,
            MultipartFile file);

    /**
     * 批量删除提现申请管理
     *
     * @param withdrawIds 需要删除的提现申请管理ID
     * @return 结果
     */
    public int deleteFfwyShopWithdrawByIds(Long[] withdrawIds);

    /**
     * 删除提现申请管理信息
     *
     * @param withdrawId 提现申请管理ID
     * @return 结果
     */
    public int deleteFfwyShopWithdrawById(Long withdrawId);

}
