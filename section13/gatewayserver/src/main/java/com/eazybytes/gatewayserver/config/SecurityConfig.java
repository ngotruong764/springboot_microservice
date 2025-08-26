package com.eazybytes.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
// Since Spring Cloud Gateway build based on the Spring Reactive module,
// we need to make sure to use this annotation
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        // All the requests that are coming towards our gateway server have to be authenticated
        // serverHttpSecurity.authorizeExchange(exchange -> exchange.anyExchange().authenticated());

        // For all the APIs that support GET method, we permit all
        serverHttpSecurity.authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.GET).permitAll()
                // APIs path that we want to secure or permit all
                .pathMatchers("/eazybank/accounts/**").hasRole("ACCOUNTS")
                .pathMatchers("/eazybank/cards/**").hasRole("CARDS")
                .pathMatchers("/eazybank/loans/**").hasRole("LOANS"))
                // Convert Gateway server as an OAuth2 resource server
                .oauth2ResourceServer(oAuth2ResourceServerSpec
                        // Use the custom configurations related to the JWT tokens
                        -> oAuth2ResourceServerSpec.jwt(
                                jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthorityExtractor())
                ));

        // Disable CSRF because CSRF is only needed when browsers are involved
        // Since in our scenario, there are no browsers involved, we can disable CSRF
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthorityExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
