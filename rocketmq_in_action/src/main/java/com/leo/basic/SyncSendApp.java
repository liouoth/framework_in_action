package com.leo.basic;

import com.sun.org.glassfish.gmbal.NameValue;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.omg.DynamicAny.NameValuePair;

public class SyncSendApp {
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
                    ("Hello Rocket mq test"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult result = producer.send(message);
            System.out.println(result);
        }
        producer.shutdown();

    }
}
