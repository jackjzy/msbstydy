package com.ruoyi;

import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhaseMsg;
import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhasePhoto;
import com.ruoyi.system.service.IFfwyPhaseMsgService;
import com.ruoyi.system.service.IFfwyPhasePhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestPhaseMsg {

    @Autowired
    private IFfwyPhaseMsgService iFfwyPhaseMsgService;
    @Autowired
    private IFfwyPhasePhotoService iFfwyPhasePhotoService;

    @Test
    public void test1(){
        FfwyPhaseMsg ffwyPhaseMsg1 = new FfwyPhaseMsg();
        ffwyPhaseMsg1.setPhaseMsgId((long) 1);
        List<FfwyPhaseMsg> ffwyPhaseMsgs = iFfwyPhaseMsgService.selectFfwyPhaseMsgAndPhoto(ffwyPhaseMsg1);
        ffwyPhaseMsgs.forEach(ffwyPhaseMsg -> {
            System.err.println(ffwyPhaseMsg);
            List<FfwyPhasePhoto> ffwyPhasePhotos = ffwyPhaseMsg.getFfwyPhasePhotos();
            ffwyPhasePhotos.forEach(ffwyPhasePhoto -> {
                System.err.println(ffwyPhasePhoto);
            });

        });

    }
}
