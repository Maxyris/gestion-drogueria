package com.pruebatecnica.gateway.filters;

import java.nio.charset.StandardCharsets;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class SessionFilter extends AbstractGatewayFilterFactory<SessionFilter.Config> {

    public SessionFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (request.getHeaders().get("Authorization") == null) {
                System.out.println("El usuario no está autenticado");
                DataBuffer buffer = exchange.getResponse().bufferFactory()
                        .wrap("El usuario no está autenticado".getBytes(StandardCharsets.UTF_8));
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().writeWith(Flux.just(buffer));
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
        
    }
}
