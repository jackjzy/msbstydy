package com.ruoyi;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.util.em.Entitie;
import com.ruoyi.app.util.em.HXUtil;
import com.ruoyi.common.utils.jersey.Result;
import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.service.IFfwyAfterSaleService;
import com.ruoyi.system.service.IFfwyPayRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.parseObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestOrder {

    @Resource
    private IFfwyAfterSaleService ffwyAfterSaleService;

    @Autowired
    private IFfwyPayRecordService iFfwyPayRecordService;


    @Test
    public void testgetToken() {

        FfwyPayRecord ffwyPayRecord = iFfwyPayRecordService.selectFfwyPayRecordById((long) 11);
        System.err.println(ffwyPayRecord);
        iFfwyPayRecordService.insertFfwyPayRecord(ffwyPayRecord);
    }

}
