package com.leo.es_project_in_actions.utils;

import com.alibaba.fastjson.JSON;
import com.leo.es_project_in_actions.entity.Goods;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerUtil {
    private final static String url_temp = "https://search.jd.com/Search?keyword=%s&enc=utf-8";
    public static String USER_AGENT = "User-Agent";
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";

    public static List<Goods> searchJd(String key){
        String url = String.format(url_temp,key);
        List<Goods> goodsList = new ArrayList<>();
        try {
            Connection connect = Jsoup.connect(url);
            System.out.println(url);
            connect.header(USER_AGENT,USER_AGENT_VALUE);
            Document document = connect.get();
            Element j_goodsList = document.getElementById("J_goodsList");
            Elements elements = j_goodsList.getElementsByTag("li");
            for (Element e: elements) {
                String img = e.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String price = e.getElementsByClass("p-price").eq(0).text();
                String name = e.getElementsByClass("p-name").eq(0).text();
                goodsList.add(new Goods(name,price,img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("size:"+goodsList.size());
        return goodsList;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(searchJd("linux")));
    }
}
