package com.ruoyi;

import com.ruoyi.common.enums.PayType;
import com.ruoyi.system.service.IFfwyPayRefundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @program: ffwy
 * @description: 银联退款
 * @author: Zx
 * @create: 2021-07-07 14:04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestRefundforUnionPay {


    @Autowired
    private IFfwyPayRefundService ffwyPayRefundService;

    @Test
    public void test1(){
        StringBuilder orderNumber = new StringBuilder();
        orderNumber.append(System.currentTimeMillis()).append(new Random().nextInt(1000));
        System.err.println("退款订单号..."+orderNumber);
        ffwyPayRefundService.refundUnionPay("20210706212646853",orderNumber.toString(),
                "银联",1L,2L,"100", PayType.PAY_PHASE.getCode());
        //ffwyPayService.refund("20210706212947806",orderNumber.toString(),"1000");

    }
}
