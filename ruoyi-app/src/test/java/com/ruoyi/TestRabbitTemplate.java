package com.ruoyi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestRabbitTemplate {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        System.err.println("发送消息");
        rabbitTemplate.convertAndSend("X","XA","测试消息");
    }
}
