package com.leo.basic;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

public class AsyncSendApp {
    public static void main(String[] args) throws Exception {
        //实例化生产者,指定生产者的组
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //指定nameServer
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.123:9876");
        //启动producer
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        final CountDownLatch2 countLatch = new CountDownLatch2(2);
        for (int i=0;i<10;i++){
            final int index = i;
            //创建消息，并指定Topic，Tag和消息体
            Message message = new Message(
                    "TopicTest","TagA",
                    ("Hello Rocket mq test"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("[%-10d]发送成功啦！！%s %n",index,sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.printf("[%-10d]发送成功啦！！%s %n",index,throwable.getMessage());
                }
            });
        }
        countLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }
}
