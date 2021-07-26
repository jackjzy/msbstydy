package com.ruoyi;

import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestVideo {
    @Autowired
    private IFfwyVideoService iFfwyVideoService;

    @Test
    public void test1(){
        List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoByLike((long) 4, (long) 2, (long) 9);
        ffwyVideos.forEach(ffwyVideo -> {
            System.err.println(ffwyVideo);
        });
        System.err.println(ffwyVideos);
    }
}
