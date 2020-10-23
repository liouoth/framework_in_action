package com.leo.es_project_in_actions.service;

import com.alibaba.fastjson.JSON;
import com.leo.es_project_in_actions.constant.IndexConstant;
import com.leo.es_project_in_actions.entity.Goods;
import com.leo.es_project_in_actions.utils.CrawlerUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.profile.ProfileShardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class EsService {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    public void sycn(String key) throws IOException {
        List<Goods> goods = CrawlerUtil.searchJd(key);
//        IndexConstant.JD_GOODS;
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(new TimeValue(60, TimeUnit.SECONDS));
        for (Goods g : goods){
            bulkRequest.add(new IndexRequest(IndexConstant.JD_GOODS)
                    .source(JSON.toJSONString(g), XContentType.JSON));
        }
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.hasFailures());
    }

    public List<Map<String, Object>> search(String key, Integer index, Integer pageSize) throws IOException {
        SearchRequest request = new SearchRequest();
        request.source(
                new SearchSourceBuilder()
                        .query(
                                QueryBuilders.termQuery("name",key)
                        ).from(index).size(pageSize)
        );
        List<Map<String, Object>> list = new ArrayList<>();
        SearchResponse response = client.search(request,RequestOptions.DEFAULT);
        for(SearchHit hit : response.getHits().getHits()){
            list.add(hit.getSourceAsMap());
        }
        return list;
    }
}
