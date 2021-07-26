package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.order.FfwyConsigneeAddrMapper;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.service.IFfwyConsigneeAddrService;

/**
 * 收货地址Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class FfwyConsigneeAddrServiceImpl implements IFfwyConsigneeAddrService {
    @Autowired
    private FfwyConsigneeAddrMapper ffwyConsigneeAddrMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;
    @Autowired
    private FfwyShopMapper ffwyShopMapper;

    /**
     * 查询收货地址
     *
     * @param consigneeAddrid 收货地址ID
     * @return 收货地址
     */
    @Override
    public FfwyConsigneeAddr selectFfwyConsigneeAddrById(Long consigneeAddrid) {
        return ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrById(consigneeAddrid);
    }

    /**
     * 查询收货地址列表
     *
     * @param ffwyConsigneeAddr 收货地址
     * @return 收货地址
     */
    @Override
    public List<FfwyConsigneeAddr> selectFfwyConsigneeAddrList(FfwyConsigneeAddr ffwyConsigneeAddr) {

        if (null != ffwyConsigneeAddr.getUserId()) {
            FfwyUser user = ffwyUserMapper.selectFfwyUserById(ffwyConsigneeAddr.getUserId());
            List<FfwyConsigneeAddr> ffwyConsigneeAddrs = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrList(ffwyConsigneeAddr);
            //判断是否有收货地址
            if (null != ffwyConsigneeAddrs) {
                //判断是否有默认收货地址
                if (null != user.getDefaultAddr()) {
                    for (FfwyConsigneeAddr consigneeAddr : ffwyConsigneeAddrs) {
                        if (user.getDefaultAddr().equals(consigneeAddr.getConsigneeAddrid())) {
                            consigneeAddr.setAddressStatus("1");

                        } else {
                            consigneeAddr.setAddressStatus("0");
                        }
                    }
                }
            }


            return ffwyConsigneeAddrs;

        } else {
            return ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrList(ffwyConsigneeAddr);
        }

    }

    @Override
    public List<FfwyConsigneeAddr> selectShopAddrList(FfwyConsigneeAddr ffwyConsigneeAddr) {
        if (null != ffwyConsigneeAddr.getShopId()) {
            FfwyShop ffwyShop = ffwyShopMapper.selectFfwyShopById(ffwyConsigneeAddr.getShopId());
            List<FfwyConsigneeAddr> ffwyConsigneeAddrs = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrList(ffwyConsigneeAddr);
            //判断是否有收货地址
            if (null != ffwyConsigneeAddrs) {
                //判断是否有默认收货地址
                if (null != ffwyShop.getShopDefaultAddr()) {
                    for (FfwyConsigneeAddr consigneeAddr : ffwyConsigneeAddrs) {
                        if (ffwyShop.getShopDefaultAddr().equals(consigneeAddr.getConsigneeAddrid())) {
                            consigneeAddr.setAddressStatus("1");

                        } else {
                            consigneeAddr.setAddressStatus("0");
                        }
                    }
                }
            }


            return ffwyConsigneeAddrs;

        } else {
            return ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrList(ffwyConsigneeAddr);
        }
    }

    /**
     * 新增收货地址
     *
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    @Override
    public AjaxResult insertFfwyConsigneeAddr(FfwyConsigneeAddr ffwyConsigneeAddr) {
        FfwyUser user = ffwyUserMapper.selectFfwyUserById(ffwyConsigneeAddr.getUserId());

        ffwyConsigneeAddr.setCreateTime(DateUtils.getNowDate());

        int i = ffwyConsigneeAddrMapper.insertFfwyConsigneeAddr(ffwyConsigneeAddr);
        FfwyConsigneeAddr ffwyConsigneeAddr1 = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrById(ffwyConsigneeAddr.getConsigneeAddrid());

        //判断是否为第一次添加地址，如果是第一次添加地址则设为默认地址
        if (null == user.getDefaultAddr()) {
            user.setDefaultAddr(ffwyConsigneeAddr.getConsigneeAddrid());
            int i1 = ffwyUserMapper.updateFfwyUser(user);
            i = i + i1;
        }
        return AjaxResult.success("添加之后返回数据",ffwyConsigneeAddr1);
    } /**
     * 新增收货地址
     *
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    @Override
    public int insertFfwyConsigneeAddrs(FfwyConsigneeAddr ffwyConsigneeAddr) {
        FfwyUser user = ffwyUserMapper.selectFfwyUserById(ffwyConsigneeAddr.getUserId());

        ffwyConsigneeAddr.setCreateTime(DateUtils.getNowDate());

        int i = ffwyConsigneeAddrMapper.insertFfwyConsigneeAddr(ffwyConsigneeAddr);
        FfwyConsigneeAddr ffwyConsigneeAddr1 = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrById(ffwyConsigneeAddr.getConsigneeAddrid());

        //判断是否为第一次添加地址，如果是第一次添加地址则设为默认地址
        if (null == user.getDefaultAddr()) {
            user.setDefaultAddr(ffwyConsigneeAddr.getConsigneeAddrid());
            int i1 = ffwyUserMapper.updateFfwyUser(user);
            i = i + i1;
        }
        return i;
    }

    /**
     * 新增店铺收货地址
     *
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    @Override
    public int insertShopAddr(FfwyConsigneeAddr ffwyConsigneeAddr) {
        FfwyShop ffwyShop = ffwyShopMapper.selectFfwyShopById(ffwyConsigneeAddr.getShopId());
        ffwyConsigneeAddr.setCreateTime(DateUtils.getNowDate());

        int i = ffwyConsigneeAddrMapper.insertFfwyConsigneeAddr(ffwyConsigneeAddr);
        if (null == ffwyShop.getShopDefaultAddr()) {
            ffwyShop.setShopDefaultAddr(ffwyConsigneeAddr.getConsigneeAddrid());


            int i1 = ffwyShopMapper.updateFfwyShop(ffwyShop);
            i = i + i1;
        }

        return i;
    }

    /**
     * 修改收货地址
     *
     * @param ffwyConsigneeAddr 收货地址
     * @return 结果
     */
    @Override
    public int updateFfwyConsigneeAddr(FfwyConsigneeAddr ffwyConsigneeAddr) {
        ffwyConsigneeAddr.setUpdateTime(DateUtils.getNowDate());
        return ffwyConsigneeAddrMapper.updateFfwyConsigneeAddr(ffwyConsigneeAddr);
    }

    /**
     * 批量删除收货地址
     *
     * @param consigneeAddrids 需要删除的收货地址ID
     * @return 结果
     */
    @Override
    public int deleteFfwyConsigneeAddrByIds(Long[] consigneeAddrids) {
        return ffwyConsigneeAddrMapper.deleteFfwyConsigneeAddrByIds(consigneeAddrids);
    }

    /**
     * 删除收货地址信息
     *
     * @param consigneeAddrid 收货地址ID
     * @return 结果
     */
    @Override
    public int deleteFfwyConsigneeAddrById(Long consigneeAddrid) {
        int n = 0;
        FfwyConsigneeAddr ffwyConsigneeAddr = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrById(consigneeAddrid);
        FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(ffwyConsigneeAddr.getUserId());
        //删除当前地址
        int i1 = ffwyConsigneeAddrMapper.deleteFfwyConsigneeAddrById(ffwyConsigneeAddr.getConsigneeAddrid());
        n = n + i1;
        //判断当前地址是否为店铺的默认收货地址
        if (ffwyConsigneeAddr.getConsigneeAddrid().equals(ffwyUser.getDefaultAddr())) {
            //默认地址则查询出当前店铺的所有地址设置查询出来的第一个地址为默认收货地址
            FfwyConsigneeAddr ffwyConsigneeAddr1 = new FfwyConsigneeAddr();
            ffwyConsigneeAddr1.setUserId(ffwyConsigneeAddr.getUserId());
            List<FfwyConsigneeAddr> ffwyConsigneeAddrs = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrList(ffwyConsigneeAddr1);
            //判断是否还有其他地址
            if (null != ffwyConsigneeAddrs && ffwyConsigneeAddrs.size() > 0) {
                //有其他地址设置为默认地址
                ffwyUser.setDefaultAddr(ffwyConsigneeAddrs.get(0).getConsigneeAddrid());
                int i = ffwyUserMapper.updateFfwyUser(ffwyUser);
                n = n + i;
            } else {
                //没有默认地址将店铺默认地址设置为空
                int i = ffwyUserMapper.updateFfwyUserDefaultAddrIsNull(ffwyUser);
                n = n + i;
            }

        }


        return n;
    }

    /**
     * 删除店铺收货地址信息
     *
     * @param ffwyConsigneeAddr 收货地址ID
     * @return 结果
     */
    @Override
    public int deleteShopAddrById(FfwyConsigneeAddr ffwyConsigneeAddr) {

        int n = 0;
        FfwyShop ffwyShop = ffwyShopMapper.selectFfwyShopById(ffwyConsigneeAddr.getShopId());
        //删除当前地址
        int i1 = ffwyConsigneeAddrMapper.deleteFfwyConsigneeAddrById(ffwyConsigneeAddr.getConsigneeAddrid());
        n = n + i1;
        //判断当前地址是否为店铺的默认收货地址
        if (ffwyConsigneeAddr.getConsigneeAddrid().equals(ffwyShop.getShopDefaultAddr())) {
            //默认地址则查询出当前店铺的所有地址设置查询出来的第一个地址为默认收货地址
            FfwyConsigneeAddr ffwyConsigneeAddr1 = new FfwyConsigneeAddr();
            ffwyConsigneeAddr1.setShopId(ffwyConsigneeAddr.getShopId());
            List<FfwyConsigneeAddr> ffwyConsigneeAddrs = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrList(ffwyConsigneeAddr1);
            //判断是否还有其他地址
            if (null != ffwyConsigneeAddrs && ffwyConsigneeAddrs.size() > 0) {
                //有其他地址设置为默认地址
                ffwyShop.setShopDefaultAddr(ffwyConsigneeAddrs.get(0).getConsigneeAddrid());
                int i = ffwyShopMapper.updateFfwyShop(ffwyShop);
                n = n + i;
            } else {
                //没有默认地址将店铺默认地址设置为空
                int i = ffwyShopMapper.updateShopDefaultAdd(ffwyShop);
                n = n + i;
            }

        }


        return n;
    }

    @Override
    public List<FfwyConsigneeAddr> selectFfwyConsigneeAddressStatus(FfwyConsigneeAddr ffwyConsigneeAddr) {
        List<FfwyConsigneeAddr> ffwyConsigneeAddrs = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrDefaultList(ffwyConsigneeAddr);
        if (ffwyConsigneeAddrs != null) {

            return ffwyConsigneeAddrs;
        }
        return null;

    }
}
