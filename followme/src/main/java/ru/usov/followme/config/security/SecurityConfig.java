package ru.usov.followme.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import ru.usov.followme.enums.Role;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static ru.usov.followme.config.security.AuthorizationConstants.*;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${spring.security.oauth2.resource-server.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/api/**").permitAll()
                .anyRequest().permitAll()
            );
//            .oauth2ResourceServer(resourceServerConfigurer -> resourceServerConfigurer
//                .jwt(jwtConfigurer -> jwtConfigurer
//                    .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//            );
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
        return (JwtConverter) (Jwt jwt) -> {
            var delegate = new JwtGrantedAuthoritiesConverter();
            var grantedAuthorities = delegate.convert(jwt);

            Map<String, List<String>> realmAccess = jwt.getClaim(REALM_ACCESS);

            if (realmAccess == null || realmAccess.get(ROLES) == null) {
                return grantedAuthorities;
            }

            var authorities = realmAccess.get(ROLES);
            var keycloakAuthorities = authorities.stream()
                .map(auth -> new SimpleGrantedAuthority(
                    Role.getAllRoles().contains(auth.toLowerCase()) ? (ROLE_PREFIX + auth) : auth))
                .toList();

            grantedAuthorities.addAll(keycloakAuthorities);

            return grantedAuthorities;
        };
    }
}