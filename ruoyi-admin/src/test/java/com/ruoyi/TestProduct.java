package com.ruoyi;

import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.service.IFfwyProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestProduct {
    @Autowired
    private IFfwyProductService iFfwyProductService;


    @Test
    public void test1(){

    }

}
