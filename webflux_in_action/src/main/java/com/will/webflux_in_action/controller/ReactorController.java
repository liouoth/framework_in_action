package com.will.webflux_in_action.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/t")
public class ReactorController {

    @GetMapping("/helloWorld")
    public Mono<String> helloWorld(){
        return Mono.just("hello world");
    }
}
