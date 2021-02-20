package com.will.webflux_in_action.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 5).mapToObj(i -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                    }
                    return "flux data--" + i;
                }));
        return result;
    }
}
