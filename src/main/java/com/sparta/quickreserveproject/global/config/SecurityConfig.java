package com.sparta.quickreserveproject.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // 모든 요청 허용 // TODO: 로그인해야지 이용가능하도록 바꾸기
                )
                .formLogin(form -> form.disable()) // 폼 기반 로그인 비활성화
                .httpBasic(basic -> basic.disable()) // HTTP Basic 인증 비활성화
                .build();
    }
}