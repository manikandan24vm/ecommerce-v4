package com.api.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/users/**")
    public Mono<String> userFallback(){
        return Mono.just("An Error occurred with the user service.. \n please try after sometime..");
    }
}
