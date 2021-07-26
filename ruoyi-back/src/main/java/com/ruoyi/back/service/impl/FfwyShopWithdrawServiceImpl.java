package com.ruoyi.back.service.impl;

import com.ruoyi.back.domain.FfwyShopWithdraw;
import com.ruoyi.back.mapper.FfwyShopWithdrawMapper;
import com.ruoyi.back.service.IFfwyShopWithdrawService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 提现申请管理Service业务层处理
 *
 * @author Yapeng Guo
 * @date 2021-05-25
 */
@Service
public class FfwyShopWithdrawServiceImpl implements IFfwyShopWithdrawService
{
    @Autowired
    private FfwyShopWithdrawMapper ffwyShopWithdrawMapper;
    @Autowired
    private FfwyShopMapper ffwyShopMapper;

    /**
     * 查询提现申请管理
     *
     * @param withdrawId 提现申请管理ID
     * @return 提现申请管理
     */
    @Override
    public FfwyShopWithdraw selectFfwyShopWithdrawById(Long withdrawId)
    {
        return ffwyShopWithdrawMapper.selectFfwyShopWithdrawById(withdrawId);
    }

    /**
     * 查询提现申请管理列表
     *
     * @param ffwyShopWithdraw 提现申请管理
     * @return 提现申请管理
     */
    @Override
    public List<FfwyShopWithdraw> selectFfwyShopWithdrawList(FfwyShopWithdraw ffwyShopWithdraw)
    {
        return ffwyShopWithdrawMapper.selectFfwyShopWithdrawList(ffwyShopWithdraw);
    }

    /**
     * 新增提现申请管理
     *
     * @param ffwyShopWithdraw 提现申请管理
     * @return 结果
     */
    @Override
    public int insertFfwyShopWithdraw(FfwyShopWithdraw ffwyShopWithdraw)
    {
        ffwyShopWithdraw.setApplyStatus("0");
        FfwyShop ffwyShop = ffwyShopMapper.selectFfwyShopById(ffwyShopWithdraw.getShopId());
        ffwyShopWithdraw.setBankCard(ffwyShop.getBankCard());
        BigDecimal oldBalance = ffwyShop.getBalance();
        BigDecimal withdrawNum = ffwyShopWithdraw.getWithdrawNum();
        BigDecimal newBalance = oldBalance.subtract(withdrawNum);
        double v = newBalance.doubleValue();
        if (v<0){
            return 0;
        }
        ffwyShop.setBalance(newBalance);
        ffwyShop.setUpdateTime(DateUtils.getNowDate());
        ffwyShopMapper.updateFfwyShop(ffwyShop);

        ffwyShopWithdraw.setCreateTime(DateUtils.getNowDate());
        return ffwyShopWithdrawMapper.insertFfwyShopWithdraw(ffwyShopWithdraw);
    }

    @Override
    public int rejectShopWithdraw(FfwyShopWithdraw ffwyShopWithdraw) {
        if ("2".equals(ffwyShopWithdraw.getApplyStatus())){

            FfwyShop ffwyShop = ffwyShopMapper.selectFfwyShopById(ffwyShopWithdraw.getShopId());
            BigDecimal balance = ffwyShop.getBalance();
            BigDecimal withdrawNum = ffwyShopWithdraw.getWithdrawNum();
            BigDecimal addBalance = balance.add(withdrawNum);
            ffwyShop.setBalance(addBalance);
            ffwyShop.setUpdateTime(DateUtils.getNowDate());
            ffwyShopMapper.updateFfwyShop(ffwyShop);
        }
        ffwyShopWithdraw.setUpdateTime(DateUtils.getNowDate());
        return ffwyShopWithdrawMapper.updateFfwyShopWithdraw(ffwyShopWithdraw);
    }

    /**
     * 修改提现申请管理
     *
     * @param
     * @return 结果
     */
    @Override
    public int updateFfwyShopWithdraw( Long withdrawId,String applyStatus,Long shopId,BigDecimal withdrawNum, MultipartFile file)
    {
        FfwyShopWithdraw ffwyShopWithdraw = new FfwyShopWithdraw();
        ffwyShopWithdraw.setWithdrawId(withdrawId);
        ffwyShopWithdraw.setApplyStatus(applyStatus);
        ffwyShopWithdraw.setShopId(shopId);
        ffwyShopWithdraw.setWithdrawNum(withdrawNum);

        if ("1".equals(applyStatus)){
            if (!file.isEmpty()){
                String path = CosUtil.CosUpload(file);
                ffwyShopWithdraw.setTransferAccount(path);
            }else {
                return 0;
            }

        }
        ffwyShopWithdraw.setUpdateTime(DateUtils.getNowDate());
        return ffwyShopWithdrawMapper.updateFfwyShopWithdraw(ffwyShopWithdraw);
    }

    /**
     * 批量删除提现申请管理
     *
     * @param withdrawIds 需要删除的提现申请管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopWithdrawByIds(Long[] withdrawIds)
    {
        return ffwyShopWithdrawMapper.deleteFfwyShopWithdrawByIds(withdrawIds);
    }

    /**
     * 删除提现申请管理信息
     *
     * @param withdrawId 提现申请管理ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopWithdrawById(Long withdrawId)
    {
        return ffwyShopWithdrawMapper.deleteFfwyShopWithdrawById(withdrawId);
    }

}
