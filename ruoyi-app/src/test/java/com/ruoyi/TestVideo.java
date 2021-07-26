package com.ruoyi;

import com.ruoyi.back.constant.Comment;
import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.mapper.FfwyVideoMapper;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.FfwyTag;
import com.ruoyi.system.domain.video.FfwyVideoComment;
import com.ruoyi.system.service.IFfwyVideoCommentService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestVideo {
    @Autowired
    private IFfwyVideoService iFfwyVideoService;

    @Autowired
    private IFfwyVideoCommentService iFfwyVideoCommentService;
    @Autowired
    private FfwyVideoMapper ffwyVideoMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test4(){
        FfwyVideo ffwyVideo1 = new FfwyVideo();
        ffwyVideo1.setVideoId((long) 112);
        System.err.println(1111111);
        FfwyVideo ffwyVideo = iFfwyVideoService.selectFfwyVideoById((long) 112);

        List<FfwyTag> tagList = ffwyVideo.getTagList();

        for (FfwyTag ffwyTag : tagList) {
            System.err.println(ffwyTag);
        }
    }



    @Test
    public void test2(){
                    ValueOperations<String, String> ops = redisTemplate.opsForValue();
//        FfwyVideo ffwyVideo1 = new FfwyVideo();
//        ffwyVideo1.setVideoStatus("0");

        List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoByUserId((long) 4);
        ffwyVideos.forEach(ffwyVideo -> {
            System.err.println(ffwyVideo);

           //ops.set(Comment.VIDEO_LIKE + ffwyVideo.getVideoId(), RandomStringUtils.randomNumeric(5));
        });
    }

    @Test
    public void test1(){
        iFfwyVideoService.insertFfwyPirvateVideo((long)11,(long)4);
        List<FfwyVideo> ffwyVideos = iFfwyVideoService.selectFfwyVideoByUserId((long) 4);
        for (FfwyVideo ffwyVideo : ffwyVideos) {
            System.err.println(ffwyVideo);

        }
    }
}
