package com.leo.springbootmongodb.controller;

import com.alibaba.fastjson.JSONObject;
import com.leo.springbootmongodb.dao.ModelRepository;
import com.leo.springbootmongodb.dto.ListSplitter;
import com.leo.springbootmongodb.entity.Model;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ScheduledExecutorService scheduledExecutorService;

    @RequestMapping("/put")
    public void addModel() {
        long before = System.currentTimeMillis();
//        System.out.println("进来了吗？");
//        JSONReader reader = null;
//
//        try {
//            reader = new JSONReader(new FileReader(new File("E:\\2020\\framework_in_action\\springboot-mongodb\\src\\main\\resources\\static\\model310.json")));
//            reader.startArray();
//            while (reader.hasNext()) {
//                Model model = reader.readObject(Model.class);
//                modelRepository.insert(model).subscribe();
//            }
//            reader.endArray();
//            reader.close();
//            reader = null;
//        } catch (FileNotFoundException e) {
//            System.out.println("出错！"+e.getMessage());
//            e.printStackTrace();
//        }
        String pStr = "{\"properties\":[{\"name\":\"parent\",\"value\":\"528\",\"category\":\"__parent__\",\"description\":\"0\"},{\"name\":\"底部约束\",\"value\":\"-2F（结构）\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"结构用途\",\"value\":\"其他\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"Z 轴偏移值\",\"value\":\"0.0\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"OmniClass 标题\",\"value\":\"Beams\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"Z 轴对正\",\"value\":\"顶\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"成本\",\"value\":\"0.0\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"结构材质\",\"value\":\"混凝土_C35\",\"category\":\"材质和装饰\",\"description\":\"0\"},{\"name\":\"长度\",\"value\":\"8400.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"终点标高偏移\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"剪切长度\",\"value\":\"7700.0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"族名称\",\"value\":\"矩形梁\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"类型名称\",\"value\":\"梁_矩形梁_300×800\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"图像\",\"value\":\"<无>\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"类别\",\"value\":\"结构框架\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"参照标高\",\"value\":\"2F（结构）\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"终点附着类型\",\"value\":\"端点高程\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"OmniClass 编号\",\"value\":\"23.25.30.11.14.14\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"抗渗等级\",\"value\":\"无要求\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 顶面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"instanceof_objid\",\"value\":\"528\",\"category\":\"__instanceof__\",\"description\":\"0\"},{\"name\":\"抗裂等级\",\"value\":\"二级\",\"category\":\"文字\",\"description\":\"0\"},{\"name\":\"viewable_in\",\"value\":\"3d\",\"category\":\"__viewable_in__\",\"description\":\"0\"},{\"name\":\"截面高度\",\"value\":\"800.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"Y 轴对正\",\"value\":\"原点\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"外加剂种类\",\"value\":\"无要求\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"族与类型\",\"value\":\"矩形梁: 梁_矩形梁_300×800\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"Y 轴偏移值\",\"value\":\"0.0\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"拆除的阶段\",\"value\":\"无\",\"category\":\"阶段化\",\"description\":\"0\"},{\"name\":\"name\",\"value\":\"梁_矩形梁_300×800 [806235]\",\"category\":\"__name__\",\"description\":\"0\"},{\"name\":\"类型 ID\",\"value\":\"576402\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"启用分析模型\",\"value\":\"0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"Level\",\"value\":\"2F（结构）\",\"category\":\"__internalref__\",\"description\":\"0\"},{\"name\":\"构件ID\",\"value\":\"0.0\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"child\",\"value\":\"529\",\"category\":\"__child__\",\"description\":\"0\"},{\"name\":\"工作平面\",\"value\":\"标高 : 2F（结构）\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"Category\",\"value\":\"Revit FamilyInstance\",\"category\":\"__category__\",\"description\":\"0\"},{\"name\":\"横截面旋转\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"族\",\"value\":\"矩形梁\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"横断面形状\",\"value\":\"0.0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"梁名称\",\"value\":\"2F（结构）_8#E~F\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"底部高程\",\"value\":\"5150.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 其他面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"创建的阶段\",\"value\":\"新构造\",\"category\":\"阶段化\",\"description\":\"0\"},{\"name\":\"b\",\"value\":\"300.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"截面宽度\",\"value\":\"300.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"顶部高程\",\"value\":\"5950.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"h\",\"value\":\"800.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"类型图像\",\"value\":\"<无>\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 底面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"element_id\",\"value\":\"806235\",\"category\":\"__revit-internal__\",\"description\":\"0\"},{\"name\":\"起点标高偏移\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"方向\",\"value\":\"标准\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"体积\",\"value\":\"1.848\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"YZ 轴对正\",\"value\":\"统一\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"node_flags\",\"value\":\"4\",\"category\":\"__node_flags__\",\"description\":\"0\"},{\"name\":\"起点附着类型\",\"value\":\"端点高程\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"类型\",\"value\":\"梁_矩形梁_300×800\",\"category\":\"其他\",\"description\":\"0\"}]}";
        Model modelTemp = JSONObject.parseObject(pStr, Model.class);
        for (int j = 0; j < 600; j++) {
            List<Model> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Model model = new Model();
                model.setEid(UUID.randomUUID().toString());
                model.setModelId(310);
                model.setModelId(1);
                model.setSF36Unicode(UUID.randomUUID().toString());
                model.setVname(UUID.randomUUID().toString());
                model.setId(null);
                model.setProperties(modelTemp.getProperties());
                list.add(model);
            }
            //控制消费速度,能够控制保持数据库连接数活跃数
            scheduledExecutorService.schedule(
                    () -> {
                        modelRepository.insert(list).subscribe();
                    },
                    j+j/100, //延迟的时间，避免高峰
                    TimeUnit.SECONDS
            );
        }
        long after = System.currentTimeMillis();
        System.out.println("插入时间：" + (after - before));
    }

    @RequestMapping("/puts")
    public void testModel() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("modelTransGroup");
        //指定nameServer
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.123:9876");
        long before = System.currentTimeMillis();
        producer.start();
        String pStr = "{\"properties\":[{\"name\":\"parent\",\"value\":\"528\",\"category\":\"__parent__\",\"description\":\"0\"},{\"name\":\"底部约束\",\"value\":\"-2F（结构）\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"结构用途\",\"value\":\"其他\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"Z 轴偏移值\",\"value\":\"0.0\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"OmniClass 标题\",\"value\":\"Beams\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"Z 轴对正\",\"value\":\"顶\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"成本\",\"value\":\"0.0\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"结构材质\",\"value\":\"混凝土_C35\",\"category\":\"材质和装饰\",\"description\":\"0\"},{\"name\":\"长度\",\"value\":\"8400.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"终点标高偏移\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"剪切长度\",\"value\":\"7700.0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"族名称\",\"value\":\"矩形梁\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"类型名称\",\"value\":\"梁_矩形梁_300×800\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"图像\",\"value\":\"<无>\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"类别\",\"value\":\"结构框架\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"参照标高\",\"value\":\"2F（结构）\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"终点附着类型\",\"value\":\"端点高程\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"OmniClass 编号\",\"value\":\"23.25.30.11.14.14\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"抗渗等级\",\"value\":\"无要求\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 顶面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"instanceof_objid\",\"value\":\"528\",\"category\":\"__instanceof__\",\"description\":\"0\"},{\"name\":\"抗裂等级\",\"value\":\"二级\",\"category\":\"文字\",\"description\":\"0\"},{\"name\":\"viewable_in\",\"value\":\"3d\",\"category\":\"__viewable_in__\",\"description\":\"0\"},{\"name\":\"截面高度\",\"value\":\"800.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"Y 轴对正\",\"value\":\"原点\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"外加剂种类\",\"value\":\"无要求\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"族与类型\",\"value\":\"矩形梁: 梁_矩形梁_300×800\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"Y 轴偏移值\",\"value\":\"0.0\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"拆除的阶段\",\"value\":\"无\",\"category\":\"阶段化\",\"description\":\"0\"},{\"name\":\"name\",\"value\":\"梁_矩形梁_300×800 [806235]\",\"category\":\"__name__\",\"description\":\"0\"},{\"name\":\"类型 ID\",\"value\":\"576402\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"启用分析模型\",\"value\":\"0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"Level\",\"value\":\"2F（结构）\",\"category\":\"__internalref__\",\"description\":\"0\"},{\"name\":\"构件ID\",\"value\":\"0.0\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"child\",\"value\":\"529\",\"category\":\"__child__\",\"description\":\"0\"},{\"name\":\"工作平面\",\"value\":\"标高 : 2F（结构）\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"Category\",\"value\":\"Revit FamilyInstance\",\"category\":\"__category__\",\"description\":\"0\"},{\"name\":\"横截面旋转\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"族\",\"value\":\"矩形梁\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"横断面形状\",\"value\":\"0.0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"梁名称\",\"value\":\"2F（结构）_8#E~F\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"底部高程\",\"value\":\"5150.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 其他面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"创建的阶段\",\"value\":\"新构造\",\"category\":\"阶段化\",\"description\":\"0\"},{\"name\":\"b\",\"value\":\"300.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"截面宽度\",\"value\":\"300.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"顶部高程\",\"value\":\"5950.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"h\",\"value\":\"800.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"类型图像\",\"value\":\"<无>\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 底面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"element_id\",\"value\":\"806235\",\"category\":\"__revit-internal__\",\"description\":\"0\"},{\"name\":\"起点标高偏移\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"方向\",\"value\":\"标准\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"体积\",\"value\":\"1.848\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"YZ 轴对正\",\"value\":\"统一\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"node_flags\",\"value\":\"4\",\"category\":\"__node_flags__\",\"description\":\"0\"},{\"name\":\"起点附着类型\",\"value\":\"端点高程\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"类型\",\"value\":\"梁_矩形梁_300×800\",\"category\":\"其他\",\"description\":\"0\"}]}";
        Model modelTemp = JSONObject.parseObject(pStr, Model.class);
        for (int j = 0; j < 600; j++) {
//            List<Message> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Model model = new Model();
                model.setEid(UUID.randomUUID().toString());
                model.setModelId(310);
                model.setModelId(1);
                model.setSF36Unicode(UUID.randomUUID().toString());
                model.setVname(UUID.randomUUID().toString());
                model.setId(null);
                model.setProperties(modelTemp.getProperties());
                Message m = new Message("MongoTopic",JSONObject.toJSONBytes(model));
                m.setDelayTimeLevel(j/60+1);
//                list.add(m);
                producer.send(m);
            }
        }
        // 关闭生产者
        producer.shutdown();
        long after = System.currentTimeMillis();
        System.out.println("插入时间：" + (after - before));
    }

    @GetMapping()
    public @ResponseBody
    Mono<Long> getAllKayaks() {
        return modelRepository.count();
    }

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("modelTransGroup");
        //指定nameServer
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.123:9876");
        long before = System.currentTimeMillis();
        producer.start();
        String pStr = "{\"properties\":[{\"name\":\"parent\",\"value\":\"528\",\"category\":\"__parent__\",\"description\":\"0\"},{\"name\":\"底部约束\",\"value\":\"-2F（结构）\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"结构用途\",\"value\":\"其他\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"Z 轴偏移值\",\"value\":\"0.0\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"OmniClass 标题\",\"value\":\"Beams\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"Z 轴对正\",\"value\":\"顶\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"成本\",\"value\":\"0.0\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"结构材质\",\"value\":\"混凝土_C35\",\"category\":\"材质和装饰\",\"description\":\"0\"},{\"name\":\"长度\",\"value\":\"8400.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"终点标高偏移\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"剪切长度\",\"value\":\"7700.0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"族名称\",\"value\":\"矩形梁\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"类型名称\",\"value\":\"梁_矩形梁_300×800\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"图像\",\"value\":\"<无>\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"类别\",\"value\":\"结构框架\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"参照标高\",\"value\":\"2F（结构）\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"终点附着类型\",\"value\":\"端点高程\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"OmniClass 编号\",\"value\":\"23.25.30.11.14.14\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"抗渗等级\",\"value\":\"无要求\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 顶面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"instanceof_objid\",\"value\":\"528\",\"category\":\"__instanceof__\",\"description\":\"0\"},{\"name\":\"抗裂等级\",\"value\":\"二级\",\"category\":\"文字\",\"description\":\"0\"},{\"name\":\"viewable_in\",\"value\":\"3d\",\"category\":\"__viewable_in__\",\"description\":\"0\"},{\"name\":\"截面高度\",\"value\":\"800.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"Y 轴对正\",\"value\":\"原点\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"外加剂种类\",\"value\":\"无要求\",\"category\":\"数据\",\"description\":\"0\"},{\"name\":\"族与类型\",\"value\":\"矩形梁: 梁_矩形梁_300×800\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"Y 轴偏移值\",\"value\":\"0.0\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"拆除的阶段\",\"value\":\"无\",\"category\":\"阶段化\",\"description\":\"0\"},{\"name\":\"name\",\"value\":\"梁_矩形梁_300×800 [806235]\",\"category\":\"__name__\",\"description\":\"0\"},{\"name\":\"类型 ID\",\"value\":\"576402\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"启用分析模型\",\"value\":\"0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"Level\",\"value\":\"2F（结构）\",\"category\":\"__internalref__\",\"description\":\"0\"},{\"name\":\"构件ID\",\"value\":\"0.0\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"child\",\"value\":\"529\",\"category\":\"__child__\",\"description\":\"0\"},{\"name\":\"工作平面\",\"value\":\"标高 : 2F（结构）\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"Category\",\"value\":\"Revit FamilyInstance\",\"category\":\"__category__\",\"description\":\"0\"},{\"name\":\"横截面旋转\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"族\",\"value\":\"矩形梁\",\"category\":\"其他\",\"description\":\"0\"},{\"name\":\"横断面形状\",\"value\":\"0.0\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"梁名称\",\"value\":\"2F（结构）_8#E~F\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"底部高程\",\"value\":\"5150.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 其他面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"创建的阶段\",\"value\":\"新构造\",\"category\":\"阶段化\",\"description\":\"0\"},{\"name\":\"b\",\"value\":\"300.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"截面宽度\",\"value\":\"300.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"顶部高程\",\"value\":\"5950.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"h\",\"value\":\"800.0\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"类型图像\",\"value\":\"<无>\",\"category\":\"标识数据\",\"description\":\"0\"},{\"name\":\"钢筋保护层 - 底面\",\"value\":\"I，(梁、柱、钢筋)，≥C30 <20 mm>\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"element_id\",\"value\":\"806235\",\"category\":\"__revit-internal__\",\"description\":\"0\"},{\"name\":\"起点标高偏移\",\"value\":\"0.0\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"方向\",\"value\":\"标准\",\"category\":\"约束\",\"description\":\"0\"},{\"name\":\"体积\",\"value\":\"1.848\",\"category\":\"尺寸标注\",\"description\":\"0\"},{\"name\":\"YZ 轴对正\",\"value\":\"统一\",\"category\":\"几何图形位置\",\"description\":\"0\"},{\"name\":\"node_flags\",\"value\":\"4\",\"category\":\"__node_flags__\",\"description\":\"0\"},{\"name\":\"起点附着类型\",\"value\":\"端点高程\",\"category\":\"结构\",\"description\":\"0\"},{\"name\":\"类型\",\"value\":\"梁_矩形梁_300×800\",\"category\":\"其他\",\"description\":\"0\"}]}";
        Model modelTemp = JSONObject.parseObject(pStr, Model.class);
        for (int j = 0; j < 60; j++) {
            for (int i = 0; i < 20; i++) {
                Model model = new Model();
                model.setEid(UUID.randomUUID().toString());
                model.setModelId(310);
                model.setModelId(1);
                model.setSF36Unicode(UUID.randomUUID().toString());
                model.setVname(UUID.randomUUID().toString());
                model.setId(null);
                model.setProperties(modelTemp.getProperties());
                Message m = new Message("MongoTopic",JSONObject.toJSONBytes(model));
                m.setDelayTimeLevel(j/60+1);
                producer.send(m);
            }
        }
        producer.shutdown();
        long after = System.currentTimeMillis();
        System.out.println("插入时间：" + (after - before));
    }
}
