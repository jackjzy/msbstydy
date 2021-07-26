package com.ruoyi.common.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/19
 **/
@Configuration
public class MyRabbitConfig {
    //普通交换机名称
    public static final String X_EXCHANGE="X";
    //死信交换机名称
    public static final String Y_DEAD_LETTER_EXCHANGE="Y";
    //普通队列
    public static final String QUEUE_A="QA";

    public static final String QUEUE_B="QB";

    public static final String QUEUE_C="QC";
    //死信队列名称
    public static final String DEAD_LETTER_QUEUE="QD";

    //队列
    public static  final String DELAYED_QUEUE_NAME="delayed.queue.phase";
    //交换机
    public static  final String DELAYED_EXCHANGE_NAME="delayed.exchange.phase";
    //routingKey
    public static  final String DELAYED_ROUTING_KEY="delayed.routingkey.phase";

    /*
    Queue(String name,  队列名字
    boolean durable,  是否持久化
    boolean exclusive,  是否排他
    boolean autoDelete, 是否自动删除
    Map<String, Object> arguments) 属性
 */
    //声明自定义交换机
    @Bean
    public CustomExchange delayeExchange(){
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");


        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delayed-message",
                true,false,arguments);
    }

    //申明队列
    @Bean
    public Queue delayedQueue(){

        return new Queue(DELAYED_QUEUE_NAME);
    }


    //绑定
    @Bean
    public Binding delayedQueueBindingDelayedExchange(@Qualifier("delayedQueue") Queue queue,
                                                      @Qualifier("delayeExchange") CustomExchange customExchange){

        return BindingBuilder.bind(queue).to(customExchange).with(DELAYED_ROUTING_KEY).noargs();
    }





    //声明xEXCHANGE交换机
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }


    //声明yEXCHANGE交换机
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    //声明队列
    @Bean("queueA")
    public Queue queueA(){
        Map<String,Object> arguments= new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","YD");
        //设置TTL
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(arguments).build();
    }


    //声明队列
    @Bean("queueB")
    public Queue queueB(){
        Map<String,Object> arguments= new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","YD");
        //设置TTL
        arguments.put("x-message-ttl", 40000);
        return QueueBuilder.durable(QUEUE_B).withArguments(arguments).build();
    }
    //声明队列C
    @Bean("queuec")
    public Queue queueC(){
        Map<String,Object> arguments= new HashMap<>(3);
        //设置死信交换机
        arguments.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
        //设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key","YD");

        return QueueBuilder.durable(QUEUE_C).withArguments(arguments).build();
    }
    //声明队列
    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    //绑定
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }
    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }
    @Bean
    public Binding queueDBindingX(@Qualifier("queueD") Queue queueD,
                                  @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }


    /* 容器中的Queue、Exchange、Binding 会自动创建（在RabbitMQ）不存在的情况下 */

    /**
     * 死信队列
     *
     * @return
     */@Bean
    public Queue orderDelayQueue() {
        /*
            Queue(String name,  队列名字
            boolean durable,  是否持久化
            boolean exclusive,  是否排他
            boolean autoDelete, 是否自动删除
            Map<String, Object> arguments) 属性
         */
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "order-event-exchange");
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        arguments.put("x-message-ttl", 1800000); // 消息过期时间 1分钟
        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);

        return queue;
    }




    /**
     * 普通队列(中转)
     *
     * @return
     */
    @Bean
    public Queue orderReleaseQueue() {

        Queue queue = new Queue("order.release.order.queue", true, false, false);

        return queue;
    }

    /**
     * 套餐订单队列
     * @return
     */
    @Bean
    public Queue orderReleaseQueueCom() {
        return new Queue("order.release.order.queue.com", true, false, false);

    }

    /**
     * 商品订单队列
     * @return
     */
    @Bean
    public Queue orderReleaseQueuePro() {
        return new Queue("order.release.order.queue.pro", true, false, false);

    }

    /**
     * TopicExchange
     *
     * @return
     */
    @Bean
    public Exchange orderEventExchange() {
        /*
         *   String name,
         *   boolean durable,
         *   boolean autoDelete,
         *   Map<String, Object> arguments
         * */
        return new TopicExchange("order-event-exchange", true, false);

    }


    @Bean
    public Binding orderCreateBinding() {
        /*
         * String destination, 目的地（队列名或者交换机名字）
         * DestinationType destinationType, 目的地类型（Queue、Exhcange）
         * String exchange,
         * String routingKey,
         * Map<String, Object> arguments
         * */
        return new Binding("order.delay.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order",
                null);
    }

    @Bean
    public Binding orderReleaseBinding() {

        return new Binding("order.release.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order",
                null);
    }

    /**
     * 订单释放直接和库存释放进行绑定
     * @return
     */
    @Bean
    public Binding orderReleaseOtherBinding() {

        return new Binding("stock.release.stock.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.other.#",
                null);
    }

}
