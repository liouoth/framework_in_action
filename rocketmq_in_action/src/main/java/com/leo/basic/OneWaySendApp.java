package com.leo.basic;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OneWaySendApp {
    public static void main(String[] args) throws Exception {
        //实例化生产者,指定生产者的组
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //指定nameServer
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.123:9876");
        //启动producer
        producer.start();
        for (int i=0;i<10;i++){
            //创建消息，并指定Topic，Tag和消息体
            Message message = new Message(
                    "TopicTest","TagA",
                    ("Hello Rocket mq test one way "+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //没有返回结果
            producer.sendOneway(message);
        }
        producer.shutdown();
    }
}
