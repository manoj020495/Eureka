package com.spring.CloudGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayLoggingFilter {

    private static final Logger logger = LoggerFactory.getLogger(GatewayLoggingFilter.class);

    @Bean
    public GlobalFilter logRequestAndRouteId() {
        return (exchange, chain) -> {
            String method = exchange.getRequest().getMethod().name();
            String uri = exchange.getRequest().getURI().toString();

            logger.info("🌐 Incoming Request: {} {}", method, uri);

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                if (route != null) {
                    logger.info("🛣️ Routed to: {}", route.getId());
                } else {
                    logger.warn("⚠️ No route ID found for this request.");
                }
            }));
        };
    }
}
