package com.manish.gateway.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;


@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user-service", r -> r
                        .path("/api/user/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://UserServiceMS/user/"))
                .route("product-service", r -> r
                        .path("/api/product/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://ProductServiceMS/product/"))
                .route("cart-service", r -> r
                        .path("/api/cart/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://CartServiceMS/cart/"))
                .route("recipe-service", r -> r
                        .path("/api/recipe/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://RecipeServiceMS/recipe/"))
                .build();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }

}
