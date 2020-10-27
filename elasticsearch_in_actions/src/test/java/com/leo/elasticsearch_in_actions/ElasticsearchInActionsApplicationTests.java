package com.leo.elasticsearch_in_actions;

import com.alibaba.fastjson.JSON;
import com.leo.elasticsearch_in_actions.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.support.single.instance.InstanceShardOperationRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.cluster.metadata.MetaData;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ElasticsearchInActionsApplicationTests {
    //注入时，需要注入类型以及名字对应，否则没办法找到
    @Autowired
    @Qualifier("restHighLevelClient") //注入bean的方法名
    private RestHighLevelClient client;

    @Test
    void createIndex() {
        //创建索引
        //不需要/ 等符号 must be lowercase 驼峰式
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("javatest");
        CreateIndexResponse response = null;
        try {
            response =  client.indices().create(
                    createIndexRequest, RequestOptions.DEFAULT
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    @Test
    void getIndex() {
        GetIndexRequest getIndexRequest = new GetIndexRequest("javatest");
        try {
            boolean exists = client.indices().exists(getIndexRequest,RequestOptions.DEFAULT);
            System.out.println("是否存在："+exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteIndex() {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("javatest");
        try {
            AcknowledgedResponse response = client.indices().delete(deleteIndexRequest,RequestOptions.DEFAULT);
            System.out.println("是否删除："+response.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //文档

    @Test
    void addDoc() {
        //json请求体
        User user = new User("mwq",12);
        //索引链接
        IndexRequest indexRequest = new IndexRequest("javatest");
        //规则 put /xxx/xxx/ {}
        indexRequest.id("1");
        indexRequest.timeout("1s");
        //将json数据放入
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);

        //客户端发送请求
        try {
            IndexResponse response = client.index(indexRequest,RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void existsDoc() {
        GetRequest getRequest = new GetRequest(
                "javatest",
                "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        try {
            boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
            System.out.println("是否存在："+exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getDoc() {
        GetRequest getRequest = new GetRequest(
                "javatest",
                "1");
        try {
            GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println(response.getSourceAsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateDoc() {
        UpdateRequest request = new UpdateRequest("javatest", "1");
        User user = new User("mwq",100);
        //对doc需要设置contentType
        request.doc(JSON.toJSONString(user),XContentType.JSON);
        try {
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteDoc() {
        DeleteRequest request = new DeleteRequest("javatest", "1");
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println(response.toString());
            System.out.println(response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量插入，bulk
    @Test
    void bulkDoc() {
        BulkRequest request = new BulkRequest();
        List<User> users = new ArrayList<>();
        users.add(new User("songing",12));
        users.add(new User("ssw",13));
        users.add(new User("fys",16));
        int index = 2;
        for (User user : users){
            request.add(new IndexRequest("javatest").id(index+"").source(JSON.toJSONString(user),XContentType.JSON));
            index++;
        }
        try {
            //批量操作就在这里
            //不加id的话就会生成一个随机id
            BulkResponse response = client.bulk(request,RequestOptions.DEFAULT);
            System.out.println("是否循环插入成功："+response.hasFailures());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //索引使用静态变量去存储
    //条件构造
    @Test
    void queryDoc() {
        SearchRequest request = new SearchRequest("javatest");
        //条件内容
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.query(QueryBuilders.matchQuery("name","mwq"));
//        sourceBuilder.query(QueryBuilders.);
        //封装条件

        //高亮
//        sourceBuilder.highlighter(HighlightBuilder);
        sourceBuilder.query(QueryBuilders.termQuery("name","mwq")); //精确查询，使用倒排索引
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高亮
        request.source(sourceBuilder);
        try {
            SearchResponse search = client.search(request, RequestOptions.DEFAULT);
            System.out.println(JSON.toJSONString(search.getHits()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
