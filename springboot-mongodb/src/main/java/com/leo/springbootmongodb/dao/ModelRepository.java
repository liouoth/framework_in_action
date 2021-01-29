package com.leo.springbootmongodb.dao;

import com.leo.springbootmongodb.entity.Model;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ModelRepository extends ReactiveMongoRepository<Model, String>{
}
