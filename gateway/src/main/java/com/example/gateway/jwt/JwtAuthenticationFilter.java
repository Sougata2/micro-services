package com.example.gateway.jwt;

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
        ServerHttpRequest mutatedRequest = request.mutate()
                .header("X-Username", "sougata")
                .build();

        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

        // skip authentication for these paths
        if (request.getURI().getPath().contains("/auth")) {
            return chain.filter(exchange);
        }

        // check for Auth headers
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//            throw new UnAuthorizedAccessException("Authorization Headers are missing");
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new UnAuthorizedAccessException("Bearer Token is missing");
        }

//        String token = authHeader.substring(7);
        try {
//            System.out.println("Token Validated : " + jwtService.validateToken(token));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
