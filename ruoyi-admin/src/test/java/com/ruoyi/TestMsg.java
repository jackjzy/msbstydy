package com.ruoyi;

import com.ruoyi.back.domain.FfwyMsg;
import com.ruoyi.back.domain.FfwyMsgType;
import com.ruoyi.back.domain.queryentity.QueryMsg;
import com.ruoyi.back.service.IFfwyMsgService;
import com.ruoyi.back.service.IFfwyMsgTypeService;
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
public class TestMsg {
    @Autowired
    IFfwyMsgService iFfwyMsgService;
    @Autowired
    IFfwyMsgTypeService iFfwyMsgTypeService;

    @Test
    public void test1() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date BeginTime = sdf.parse("2021-04-08");
        Date endTime = sdf.parse("2021-04-14");


        QueryMsg queryMsg = new QueryMsg();
        queryMsg.setBeginTime(BeginTime);
        queryMsg.setEndTime(endTime);
        List<FfwyMsg> ffwyMsgs = iFfwyMsgService.selectFfwyMsgList(queryMsg);
        ffwyMsgs.forEach(ffwyMsg -> {
            System.err.println(ffwyMsg);
        });

    }

}
