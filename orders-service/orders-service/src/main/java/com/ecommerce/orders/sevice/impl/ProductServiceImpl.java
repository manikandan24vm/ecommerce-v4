package com.ecommerce.orders.sevice.impl;

import com.ecommerce.orders.sevice.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private WebClient webClient;
    /*
    @Override
    public Flux<ProductDTO> getAllProducts() {
      return webClient.get().retrieve()
              .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Client Error")))
              .onStatus(HttpStatusCode::is5xxServerError,clientResponse -> Mono.error((new RuntimeException("Server Error"))))
              .bodyToFlux(ProductDTO.class)
              .onErrorResume(WebClientResponseException.class, e -> Mono.error(new RuntimeException("Error: " + e.getResponseBodyAsString())));
    }
}
*/
}