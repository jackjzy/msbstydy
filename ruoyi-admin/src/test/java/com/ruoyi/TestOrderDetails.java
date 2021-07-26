package com.ruoyi;

import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.service.IFfwyOrderDetailsService;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestOrderDetails {

    @Autowired
    private IFfwyOrderDetailsService iFfwyOrderDetailsService;

    @Test
    public void test1() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date BeginTime = sdf.parse("2021-04-21");
        Date endTime = sdf.parse("2021-04-24");
        FfwyOrderDetails ffwyOrderDetails2 = new FfwyOrderDetails();
//        ffwyOrderDetails2.setShopId((long) 4);
//        ffwyOrderDetails2.setProductSkuName("小米");
        ffwyOrderDetails2.setBeginTime(BeginTime);
        ffwyOrderDetails2.setEndTime(endTime);


        List<FfwyOrderDetails> ffwyOrderDetails = iFfwyOrderDetailsService.selectFfwyOrderDetailsList(ffwyOrderDetails2);
        ffwyOrderDetails.forEach(ffwyOrderDetails1 -> {
            System.err.println(ffwyOrderDetails1);
        });
    }
    @Test
    public void test2(){
        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
        ffwyOrderDetails.setOrderDetailsId((long) 4);
        ffwyOrderDetails.setProductPhoto("1313");
        iFfwyOrderDetailsService.updateFfwyOrderDetails(ffwyOrderDetails);
    }
}
