package com.leo.springbootmongodb.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@Configuration
//@EnableReactiveMongoRepositories
public class MongoTemplateConfig extends AbstractReactiveMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "model";
    }

//    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }

//    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, "model");
    }
}
