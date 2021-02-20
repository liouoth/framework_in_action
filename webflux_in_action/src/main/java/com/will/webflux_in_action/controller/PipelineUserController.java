package com.will.webflux_in_action.controller;

import com.will.webflux_in_action.dao.PipelineUserRepository;
import com.will.webflux_in_action.entity.PipelineUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pipelineUser")
public class PipelineUserController {


    @Autowired
    private PipelineUserRepository pipelineUserRepository;



    @PostMapping("")
    public Mono<Void> saveUser(@RequestBody Flux<PipelineUser> userFlux) {
        return pipelineUserRepository.insert(userFlux).then();
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<PipelineUser> getUser() {
        return pipelineUserRepository.findBy();
    }
}