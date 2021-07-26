package com.ruoyi;

import com.ruoyi.app.util.hz.EqbB2cUtil;
import com.ruoyi.system.domain.FfwyContract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class Testeqb {
    @Test
    public void test1(){
        FfwyContract contract = EqbB2cUtil.createContract(null);

    }

}
