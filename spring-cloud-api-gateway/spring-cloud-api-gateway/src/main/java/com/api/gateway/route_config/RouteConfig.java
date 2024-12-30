package com.api.gateway.route_config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;


@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p
                        .path("/ecommerce/user/**")
                        .filters( f -> f.rewritePath("/ecommerce/user/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(cb-> cb.setName("userCircuitBreaker")
                                        .setFallbackUri("forward:/users/**")))

                        .uri("lb://USER"))
                .route(p -> p
                        .path("/ecommerce/product/**")
                        .filters( f -> f.rewritePath("/ecommerce/product/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET).setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2,true)
                                ))
                        .uri("lb://PRODUCT"))
                .route(p -> p
                        .path("/ecommerce/orders/**")
                        .filters( f -> f.rewritePath("/ecommerce/orders/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()) )// pre-defined filter
                        .uri("lb://ORDERS")).build();
    }
}
/**
 * The `setBackoff` method in the `RetryConfig` class of Spring Cloud Gateway's `RetryGatewayFilterFactory` is used to configure the backoff strategy for retrying failed requests. This configuration determines how long the system waits between retry attempts, and the retry behavior in terms of duration and growth.
 *
 * Let's break down each of the parameters:
 *
 * ### Parameters of `setBackoff`:
 *
 * 1. **`firstBackoff` (`java.time.Duration`)**:
 *    - This specifies the initial delay before the first retry attempt.
 *    - The delay is represented by a `Duration`, which can be any valid time span (e.g., 5 seconds, 1 minute, etc.).
 *    - For example, if you set `firstBackoff` to 10 seconds, the system will wait 10 seconds before making the first retry attempt after a failure.
 *
 *
 *
 * 2. **`maxBackoff` (`java.time.Duration`)**:
 *    - This sets the maximum allowed duration for the backoff.
 *    - This duration represents the longest possible time between retries.
 *    - For instance, if you set `maxBackoff` to 1 minute, the backoff time will not grow beyond 1 minute, even if the retries keep increasing.
 *
 * 3. **`factor` (`int`)**:
 *    - The `factor` defines how the backoff interval increases with each retry.
 *    - It is typically a multiplier that is applied   the previous backoff time to calculate the next retry delay.
 *    - For example, if the first retry happens after 10 seconds (`firstBackoff = 10 seconds`), and the factor is set to 2, the second retry will happen after 20 seconds, the third retry after 40 seconds, and so on.
 *    - This behavior is often referred to as "exponential backoff" because the time between retries grows exponentially.
 *
 * 4. **`basedOnPreviousValue` (`boolean`)**:
 *    - This flag determines whether the backoff calculation should be based on the previous retry's value.
 *    - If set to `true`, each backoff interval will be calculated based on the previous retry's delay, so the backoff will grow exponentially (i.e., first retry delay × factor, second retry delay × factor, etc.).
 *    - If set to `false`, the backoff duration will always be calculated based on the initial backoff (`firstBackoff`), regardless of the number of retries.
 *
 * ### Summary of Behavior:
 *
 * - **Backoff Growth**: The backoff duration increases with each retry attempt based on the `factor` and the `basedOnPreviousValue` flag.
 * - **Limit on Growth**: The backoff duration will not exceed `maxBackoff` even if the growth factor suggests a longer delay.
 * - **Initial Delay**: The first retry will always be delayed by the `firstBackoff` value.
 *
 * ### Example:
 *
 * Assume we configure the backoff with:
 *
 * - `firstBackoff` = 5 seconds
 * - `maxBackoff` = 1 minute
 * - `factor` = 2
 * - `basedOnPreviousValue` = true
 *
 * Here’s what will happen:
 *
 * 1. **First retry**: After 5 seconds (as defined by `firstBackoff`).
 * 2. **Second retry**: After 10 seconds (5 seconds × 2 due to `factor`).
 * 3. **Third retry**: After 20 seconds (10 seconds × 2).
 * 4. **Subsequent retries**: The backoff continues to double (40 seconds, 80 seconds, etc.) until it reaches the `maxBackoff` value of 1 minute, and the delay will remain 1 minute for any retries beyond this point.
 *
 * If `basedOnPreviousValue` was set to `false`, the backoff would always reset to the `firstBackoff` value (5 seconds) for every retry, and would not increase based on previous retry times.
 *
 * This method helps in controlling the retry behavior in case of failures, ensuring that retries are spaced out intelligently without overloading the system or causing immediate repeated failures.
 */