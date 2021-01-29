package com.leo.basic;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class ConsumeApp {
    public static void main(String[] args) throws Exception{
        //创建消费者,推的方式
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //设置NameServer的地址
        consumer.setNamesrvAddr("192.168.0.116:9876;192.168.0.123:9876");
        //订阅一个或者多个Topic，还可以使用Tag来过滤需要消费的信息
        consumer.subscribe("TopicTest","*");
        consumer.setMessageModel(MessageModel.BROADCASTING);
//        consumer.setMessageModel(MessageModel.CLUSTERING);
        //注册回调实现类来处理broker拉取回来的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //启动消费实例
        consumer.start();
        System.out.println("consumer start");
    }
}
