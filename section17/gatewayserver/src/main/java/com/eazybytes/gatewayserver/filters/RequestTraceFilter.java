package com.eazybytes.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1) // order of the filter
@Component
// Whenever we want our filters to be executed for all kinds of traffic that is going to be received by
// our gateway server, then we need to make sure we are implementing GlobalFilter interface
public class RequestTraceFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    /**
     * Whenever we implement GlobalFilter interface, we need to override the filter method
     *
     * @param exchange - We can access the request and the response associated with an exchange
     * @param chain - Inside Gateway, there can be multiple filters that we can configure. So all those filters will be executed
     *              in a chain manner
     * @return
     * */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Get the request headers from the exchange
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        // Check if there is any header already available
        // If true, then we are good
        // If false, then we need to generate one and set it to the exchange
        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("eazyBank-correlation-id found in RequestTraceFilter : {}", filterUtility.getCorrelationId(requestHeaders));

        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationID);
            logger.debug("eazyBank-correlation-id generated in RequestTraceFilter : {}", correlationID);
        }
        // When we done executing our own filter, we need to invoke the next filter inside the chain
        // with the help of chain.filter(exchange)
        // Since we are not returning anything specifically, we need to make sure we are mentioning these Mono<Void>
        // Void means we are not returning anything specifically from this method, we are just trying to invoke the next filter
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
