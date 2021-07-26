package com.ruoyi.app.mq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.ruoyi.common.config.MyRabbitConfig;
import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import com.ruoyi.system.domain.vo.WareSkuLockVo;
import com.ruoyi.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/20
 **/
@Component
@Slf4j
public class OrderCloseListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IFfwyOrderCombomealService ffwyOrderCombomealService;

    @Autowired
    private IFfwyCombomealService iFfwyCombomealService;
    @Autowired
    private IFfwyProductService iFfwyProductService;
    @Autowired
    private IFfwyOrderService iFfwyOrderService;

    @Autowired
    private IFfwyPhaseService iFfwyPhaseService;

    @RabbitListener(queues = "QD")
    public void receiveD(Message message,Channel channel) throws Exception{
        String msg =new String(message.getBody());
        System.err.println("收到死信队列的消息："+msg);
        basic(channel, message);
    }
    @RabbitListener(queues = MyRabbitConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message,Channel channel) throws IOException {
        String s = new String(message.getBody());
        FfwyPhase ffwyPhase = new FfwyPhase();
        ffwyPhase.setPhaseId(Long.valueOf(s));
        ffwyPhase.setPhaseStatusUser("1");
        ffwyPhase.setPhaseStatus("2");
        iFfwyPhaseService.updateFfwyPhase(ffwyPhase);
        log.info("接收到的消息{}",s);
        log.info("接收消息:"+s);
        basic(channel, message);

    }
    @RabbitListener(queues = "order.release.order.queue")
    public void transfer(JSONObject json, Channel channel, Message message) throws IOException {
        log.info("收到过期的订单消息，准备关闭订单! {}",json.get("orderSn"));
        if (json.get("comLocks") != null){
            //给套餐订单队列发送消息
            rabbitTemplate.convertAndSend("order.release.order.queue.com", json);
        }else if (json.get("proLocks") != null){
            //给商品订单队列发送消息
            rabbitTemplate.convertAndSend("order.release.order.queue.pro", json);
        }

        basic(channel,message);

    }

    /**
     * 解锁库存，定时关单
     * @param json
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "order.release.order.queue.com")
    public void listenerCombomealOrder(JSONObject json, Channel channel, Message message) throws IOException {
        WareSkuLockVo lockVo = json.toJavaObject(WareSkuLockVo.class);
        log.info("收到过期的套餐订单消息，准备关闭订单! {}",lockVo.getOrderSn());
        iFfwyCombomealService.combomaealUnlockStock(lockVo);
        basic(channel,message);
    }

    @RabbitListener(queues = "order.release.order.queue.pro")
    public void listenerProductOrder(JSONObject json, Channel channel, Message message)throws  IOException{
        WareSkuLockVo lockVo = json.toJavaObject(WareSkuLockVo.class);
        log.info("收到过期的商品订单消息，准备关闭订单! {}",lockVo.getOrderSn());
        iFfwyOrderService.combomaealUnlockStock(lockVo);
        basic(channel,message);
    }

    /**
     * 消息收到确认
     * @param channel
     * @param message
     * @throws IOException
     */

    private void basic(Channel channel, Message message) throws IOException {
        log.info("没有应答",message.getMessageProperties().getDeliveryTag());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("进行应答",message.getMessageProperties().getDeliveryTag());
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
            log.info("重新应答",message.getMessageProperties().getDeliveryTag());
        }
    }

}
