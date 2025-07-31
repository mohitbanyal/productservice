package com.mb.springcloud.productservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

        @Bean
        BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Autowired
        UserDetailsService userDetailsService;

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.httpBasic(Customizer.withDefaults());
            http.authorizeHttpRequests(authorize ->
                    authorize.requestMatchers("/productapi/products/{id:^\\d+$}")
                            .hasAnyRole("ADMIN","USER")
                            .requestMatchers("/productapi/products")
                            .hasRole("ADMIN")
            );
            http.csrf(AbstractHttpConfigurer::disable);
            return http.build();
        }
}
