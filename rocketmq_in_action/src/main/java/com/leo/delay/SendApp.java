package com.leo.delay;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class SendApp {
    public static void main(String[] args) throws Exception {
        //初始化生产者
        DefaultMQProducer producer = new DefaultMQProducer("group");
        //启动生产者
        producer.start();
        for (int i=0;i<10;i++){
            Message message = new Message("TestTopic", ("Hello scheduled delay message " + i).getBytes());
            // 设置延时等级3,这个消息将在10s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
            message.setDelayTimeLevel(3);
            producer.send(message);
        }
        producer.shutdown();
    }
}
