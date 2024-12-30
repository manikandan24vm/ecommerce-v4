package com.ecommerce.orders.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient createWebClient(WebClient.Builder builder){
       return builder.baseUrl("http://localhost:9092").build();
    }
}
