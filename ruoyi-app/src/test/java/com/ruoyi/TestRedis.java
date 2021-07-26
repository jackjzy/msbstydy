package com.ruoyi;

import com.ruoyi.back.constant.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test1(){
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(Comment.USER_LIKE+1,"2");
        set.add(Comment.USER_LIKE+1,"3");
        Boolean aBoolean = set.isMember(Comment.USER_LIKE + 1, "2");
        Boolean member = set.isMember(Comment.USER_LIKE + 1, "4");

        System.err.println(aBoolean);
        System.err.println(member);

    }

    @Test
    public void test2(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        ops.set(Comment.VIDEO_LIKE+1,"1");
        ops.increment(Comment.VIDEO_LIKE+1);
        String s = ops.get(Comment.VIDEO_LIKE + 1);
        System.err.println(s);
    }

}
