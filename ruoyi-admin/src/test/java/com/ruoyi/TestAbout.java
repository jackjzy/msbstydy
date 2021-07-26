package com.ruoyi;

import com.ruoyi.back.domain.FfwyAbout;
import com.ruoyi.back.service.IFfwyAboutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestAbout {

    @Autowired
    private IFfwyAboutService iFfwyAboutService;

    @Test
    public void test1(){
        FfwyAbout ffwyAbout = iFfwyAboutService.selectFfwyAboutById((long) 1);
        System.err.println(ffwyAbout);
        iFfwyAboutService.updateFfwyAbout(ffwyAbout);
    }
}
