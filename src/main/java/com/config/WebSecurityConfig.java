package com.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author : Olivier Barais
 * @created : 20-10-2023
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(registry -> {
                    try {
                        registry.requestMatchers("/").permitAll()
                                .requestMatchers("/index").hasRole("USER")
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/questions").hasRole("USER")
                                .anyRequest().permitAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2Configurer -> oauth2Configurer
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
                            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
                            Collection<String> roles = realmAccess.get("roles");
                            List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
                            grantedAuthorities.forEach(e -> {
                                System.err.println(e.getAuthority());

                            });
                            return new JwtAuthenticationToken(jwt, grantedAuthorities);
                        })));
        return httpSecurity.build();
    }
}