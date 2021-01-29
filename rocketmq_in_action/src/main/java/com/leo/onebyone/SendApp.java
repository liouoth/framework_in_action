package com.leo.onebyone;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendApp {
    public static void main(String[] args) throws Exception{
        //实例化生产者,指定生产者的组
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //指定nameServer
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.123:9876");
        //启动producer
        producer.start();
        //模拟订单
        List<OrderStep> orderSteps = new SendApp().buildOrders();

        String[] tags = new String[]{"TagA", "TagC", "TagD"};
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(date);
        for (int i=0;i<orderSteps.size();i++){
            //添加时间前缀
            String body = dateStr+" Hello RocketMQ "+orderSteps.get(i);
            Message msg = new Message("TopicTest", tags[i % tags.length], "KEY" + i, body.getBytes());
            //选择器，通过订单的id保证
            SendResult result = producer.send(msg, new MessageQueueSelector() {
                //list 队列集合,id % list.size(),取模，保证对应的队列
                //消息对象
                //业务标识的参数，也就是后续传入的订单id
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                    Long id = (Long) arg;  //根据订单id选择发送queue
                    long index = id % list.size();
                    return list.get((int) index);
                }
            },orderSteps.get(i).getOrderId());
            System.out.println(String.format("SendResult status:%s, queueId:%d, body:%s",
                    result.getSendStatus(),
                    result.getMessageQueue().getQueueId(),
                    body));
        }
        producer.shutdown();
    }

    //订单信息
    private static class OrderStep {
        private long orderId;
        private String desc;

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "OrderStep{" +
                    "orderId=" + orderId +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    //模拟订单信息
    private List<OrderStep> buildOrders() {
        List<OrderStep> orderList = new ArrayList<OrderStep>();

        OrderStep orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("创建");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("付款");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111065L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("推送");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103117235L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(15103111039L);
        orderDemo.setDesc("完成");
        orderList.add(orderDemo);

        return orderList;
    }
}
