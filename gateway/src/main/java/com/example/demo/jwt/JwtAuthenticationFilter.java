package com.example.demo.jwt;

import com.example.demo.errors.exceptions.UnAuthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // skip authentication for these paths
        if (request.getURI().getPath().contains("/auth")){
            return chain.filter(exchange);
        }

        // check for Auth headers
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
            throw new UnAuthorizedAccessException("Authorization Headers are missing");
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new UnAuthorizedAccessException("Bearer Token is missing");
        }

        String token = authHeader.substring(7);
        try {
            // validate the token here.
            System.out.println("validating token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
