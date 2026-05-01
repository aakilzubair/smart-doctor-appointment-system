package com.auth_service.config;

import com.auth_service.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // ✅ Public APIs
                        .requestMatchers("/auth/**").permitAll()

                        // ✅ DOCTOR APIs
                        .requestMatchers("/doctors/**").hasRole("DOCTOR")

                        // ✅ PATIENT APIs
                        .requestMatchers("/patients/**").hasRole("PATIENT")

                        // ✅ APPOINTMENT (both allowed)
                        .requestMatchers("/appointments/**").hasAnyRole("DOCTOR", "PATIENT")

                        // ✅ Location APIs (public or restrict if needed)
                        .requestMatchers("/states/**", "/cities/**", "/areas/**").permitAll()

                        // बाकी सब authenticated
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}