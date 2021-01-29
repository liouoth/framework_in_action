package com.leo.springbootmongodb.rocketmq;

import com.alibaba.fastjson.JSON;
import com.leo.springbootmongodb.dao.ModelRepository;
import com.leo.springbootmongodb.entity.Model;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelTransConsumer {
    @Autowired
    private ModelRepository modelRepository;

    private static int count = 0;
    private DefaultMQPushConsumer consumer;
    public static final String CONSUMER_GROUP = "modelTransGroup";

    public ModelTransConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr("192.168.0.116:9876;192.168.0.123:9876");
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe("MongoTopic", "*");
        //批量消费
        consumer.setPullBatchSize(60);
        consumer.setConsumeMessageBatchMaxSize(30);
        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                try {
                    int i = list.stream().map(t -> JSON.parseObject(new String(t.getBody()), Model.class))
                            .collect(Collectors.toList()).size();
                    count+=i;
                    System.out.println(new Date()+",消费:"+i+",消费总数:"+count);
                } catch (Exception e) {
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("消费者 启动成功=======");
    }

}
