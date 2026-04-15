package com.app.api_gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        //  If token present → validate + extract username (for ALL APIs)
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                jwtUtil.validateToken(token);

                String username = jwtUtil.extractUsername(token);

                exchange = exchange.mutate()
                        .request(exchange.getRequest().mutate()
                                .header("username", username)
                                .build())
                        .build();

            } catch (Exception e) {
                return onError(exchange, "Invalid Token", HttpStatus.UNAUTHORIZED);
            }
        }

        //  PROTECT HISTORY (must have token)
        if (path.contains("/api/v1/quantities/history")) {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Missing Token", HttpStatus.UNAUTHORIZED);
            }
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }
}