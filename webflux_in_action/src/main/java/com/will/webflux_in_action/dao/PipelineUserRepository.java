package com.will.webflux_in_action.dao;

import com.will.webflux_in_action.entity.PipelineUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface PipelineUserRepository extends ReactiveMongoRepository<PipelineUser,Long> {

    @Tailable
    Flux<PipelineUser> findBy();
}
