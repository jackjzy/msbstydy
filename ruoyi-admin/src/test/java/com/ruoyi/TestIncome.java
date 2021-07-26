package com.ruoyi;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.order.FfwyIncome;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.service.IFfwyIncomeService;
import com.ruoyi.system.service.IFfwyShopUserService;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestIncome {
    @Autowired
    private IFfwyIncomeService iFfwyIncomeService;
    @Autowired
    private IFfwyShopUserService iFfwyShopUserService;
    @Test
    public void test1(){
//        FfwyIncome ffwyIncome1 = new FfwyIncome();
//        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
//        ffwyOrderDetails.setShopId((long) 4);
//        ffwyIncome1.setOrderDetails(ffwyOrderDetails);
//        ffwyIncome1.setDay(7);
//        List<FfwyIncome> ffwyIncomes = iFfwyShopUserService.detailedByshop(ffwyIncome1);
//        ffwyIncomes.forEach(ffwyIncome -> {
//            System.err.println(ffwyIncome);
//            FfwyOrderDetails orderDetails = ffwyIncome.getOrderDetails();
//            System.err.println(orderDetails);
//        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");


        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH)-12 );
        Date day =  c.getTime();


        Date day30 = DateUtils.getDatePoor(30);
        Date day7 = DateUtils.getDatePoor(7);
        Date day120 = DateUtils.getDatePoor(120);



        String format = sdf.format(day7);
        String format2 = sdf.format(day);
        String format3 = sdf.format(day120);

        System.out.println("day7:"+format);
        System.out.println("day30:"+format2);
        System.out.println("day120:"+format3);

    }
}
