package com.ruoyi.app.config;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class RabbitCallBack implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    //交换机确认收到消息
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
     String id= correlationData!=null?correlationData.getId():"";
     if (ack){
      log.info("交换机收到的id为：{}的消息",id);

     }else {
         log.info("交换机未收到的id为：{}的消息，由于原因：{}",id,s);
     }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        log.error("消息{}，被交换机{}回退，退回原因{}，路由key:{}",new String(message.getBody()),exchange,replyText,routingKey);

    }


    //队列确认收到消息

}
