package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.order.FfwyIncome;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.FfwyPaymentMapper;
import com.ruoyi.system.mapper.order.FfwyIncomeMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.ruoyi.system.service.IFfwyOrderService;
import com.ruoyi.system.service.IFfwyShopUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author 赵字豪
 * @Title: 商铺管理Service业务层处理
 * @date 2021/4/20
 */
@Service
public class IFfwyShopUserServiceImpl implements IFfwyShopUserService {

    @Autowired
    private FfwyIncomeMapper ffwyIncomeMapper;

    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;

    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;

    @Autowired
    private FfwyShopMapper ffwyShopMapper;

    @Autowired
    private FfwyPaymentMapper ffwyPaymentMapper;

    @Autowired
    private IFfwyOrderService ffwyOrderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public StatisticsOrderVo statisticsOrder(Integer userId) {
        BigDecimal moneyCount = new BigDecimal("0") ;    // 金额累加器
        Integer pieceCount = 0;      // 件数累加器
        Integer browseCount = 0;     // 浏览量累加器
        Long shopId =0L;

        //  一个订单可能因为限额分为多次支付，放入set中去重
        Set<Integer> set = new HashSet<>();

        StatisticsOrderVo statisticsOrderVo = new StatisticsOrderVo();
        //1. 今日付款金额


        List<FfwyIncome> ffwyIncomes = ffwyIncomeMapper.selectFfwyIncomeList(new FfwyIncome(Long.valueOf(userId), "0", DateUtils.getDatePoor(1)));
        for (FfwyIncome ffwyIncome : ffwyIncomes) {
            set.add(ffwyIncome.getOrderId().intValue());  //  今日订单数

            BigDecimal money = ffwyIncome.getMoney();      // 今日付款金额
            moneyCount = moneyCount.add(money);

            // 今日件数
            FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsById(ffwyIncome.getOrderId());
            if (null!=orderDetails){
                pieceCount += orderDetails.getNumber();
                shopId = orderDetails.getShopId();
            }else {
                pieceCount +=0;
            }

        }

        // 今日浏览量
        String s = stringRedisTemplate.opsForValue().get(Constants.VIEWS + shopId);
        if (!StringUtils.isEmpty(s)){
            browseCount = Integer.valueOf(s);
        }

        statisticsOrderVo.setStatisticsMoney(moneyCount);
        statisticsOrderVo.setStatisticsCount(pieceCount);
        statisticsOrderVo.setStatisticsOrder(set.size());
        statisticsOrderVo.setQueryCount(browseCount);

        return statisticsOrderVo;
    }

    @Override
    public DetailedVo flowingWater(ProductByUserVo productByUserVo) {
        Double moneyCount = 0.0 ;    // 金额累加器

        DetailedVo detailedVo = new DetailedVo();
        FfwyShop ffwyShop = ffwyShopMapper.selectFfwyShopById(productByUserVo.getShopId());
        // 1.店铺余额
        detailedVo.setBalance(ffwyShop.getBalance().toString());

        // 2.7天内营收
        List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderList(new FfwyOrder(productByUserVo.getUserId(), productByUserVo.getShopId()));
        for (FfwyOrder ffwyOrder : ffwyOrders) {
            List<FfwyIncome> ffwyIncomes = ffwyIncomeMapper.selectFfwyIncomeList(new FfwyIncome(ffwyOrder.getOrderId(),productByUserVo.getUserId(),  DateUtils.getDatePoor(7)));
            for (FfwyIncome ffwyIncome : ffwyIncomes) {
                BigDecimal money;
                if ("0".equals(ffwyIncome.getIncomePay())){  //收入
                    money=ffwyIncome.getMoney();
                    moneyCount += Double.valueOf(money.toString());
                }else if ("1".equals(ffwyIncome.getIncomePay())){   //支出
                    money=ffwyIncome.getMoney();
                    moneyCount -= Double.valueOf(money.toString());
                }
            }
        }
        detailedVo.setIncome(moneyCount);

        return detailedVo;
    }

    @Override
    public List<IncomeVo> detailed(ProductByUserVo productByUserVo) {
        List<IncomeVo> list = new ArrayList<>();

        List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderList(new FfwyOrder(productByUserVo.getUserId(), productByUserVo.getShopId()));
        for (FfwyOrder ffwyOrder : ffwyOrders) {
            List<FfwyIncome> ffwyIncomes = ffwyIncomeMapper.selectFfwyIncomeList(new FfwyIncome(ffwyOrder.getOrderId(),productByUserVo.getUserId(),  DateUtils.getDatePoor(7)));
            for (FfwyIncome ffwyIncome : ffwyIncomes) {
                list.add(
                        new IncomeVo(
                               ffwyIncome.getIncomePay(),
                               ffwyIncome.getMoney().toString(),
                               ffwyPaymentMapper.selectpatmentTypeById(ffwyIncome.getPaymentId()),
                               ffwyIncome.getOrderId().toString(),
                               ffwyOrder.getOrderNumber()
                        ));
            }

        }

        return list;
    }

    @Override
    public List<FfwyIncome> detailedByshop(FfwyIncome ffwyIncome) {
        if (null!=ffwyIncome&&ffwyIncome.getDay()!=null){
            Date datePoor=null;
            Calendar c = Calendar.getInstance();
            if(ffwyIncome.getDay()<=7){
                datePoor= DateUtils.getDatePoor(ffwyIncome.getDay());
            }else if (ffwyIncome.getDay()<=30){
                c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1 );
                datePoor=c.getTime();
            }else if (ffwyIncome.getDay()<=120){
                c.set(Calendar.MONTH, c.get(Calendar.MONTH)-4 );
                datePoor=c.getTime();
            }else {
                c.set(Calendar.MONTH, c.get(Calendar.MONTH)-12 );
                datePoor=c.getTime();
            }

            ffwyIncome.setDealTime(datePoor);
        }


        return ffwyIncomeMapper.selectIncomeByshopId(ffwyIncome);
    }

    @Override
    public OrderVo flowingWaterDetails(ProductByUserVo productByUserVo) {

        return ffwyOrderService.selectFfwyOrderByOrderNumber(productByUserVo.getUserId(),productByUserVo.getOrderNumber());
    }

}
