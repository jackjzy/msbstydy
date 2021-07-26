package com.ruoyi;

import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.domain.FfwyVideoHot;
import com.ruoyi.back.domain.FfwyVideoHots;
import com.ruoyi.back.service.IFfwyVideoHotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestVideoHot {

    @Autowired
    private IFfwyVideoHotService iFfwyVideoHotService;

    @Test
    public void test(){
        List<FfwyVideoHot> ffwyVideoHots = iFfwyVideoHotService.selectFfwyVideoHotList(null);
        ffwyVideoHots.forEach(ffwyVideoHot -> {
            System.err.println(ffwyVideoHot);
            List<FfwyVideoHots> videos = ffwyVideoHot.getVideos();
            videos.forEach(ffwyVideo -> {
                System.err.println(ffwyVideo.getVideoName());
            });
        });

    }
}
