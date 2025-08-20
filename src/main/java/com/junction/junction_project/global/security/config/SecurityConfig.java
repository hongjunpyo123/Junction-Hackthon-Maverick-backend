package com.junction.junction_project.global.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.junction_project.global.common.dto.response.ErrorResponse;
import com.junction.junction_project.global.exception.ErrorCode;
import com.junction.junction_project.global.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;

  /**
   * 예외처리 응답을 위해 사용되는 객체입니다.
   */
  private final ObjectMapper objectMapper;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    // 인증 실패 시 반환할 JSON 응답
    String invalidAuthenticationResponse = objectMapper
        .writeValueAsString(ErrorResponse.of(ErrorCode.TOKEN_INVALID));

    // 인가 실패 시 반환할 JSON 응답
    String invalidAuthorizationResponse = objectMapper
        .writeValueAsString(ErrorResponse.of(ErrorCode.ACCESS_DENIED));

    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/test/**",
                "/admin/**",
                "/api/v1/auth/social"
            ).permitAll()
            .anyRequest().authenticated())
        .exceptionHandling(e -> e
            //인증 실패 시 응답 핸들링
            .authenticationEntryPoint(((request, response, authException) -> {
              response.setStatus(ErrorCode.TOKEN_INVALID.getHttpStatus().value());
              response.setContentType("application/json");
              response.getWriter().write(invalidAuthenticationResponse);
            }
            ))
            //인가 실패 시 응답 핸들링
            .accessDeniedHandler((request, response, authException) -> {
              response.setStatus(ErrorCode.TOKEN_INVALID.getHttpStatus().value());
              response.setContentType("application/json");
              response.getWriter().write(invalidAuthorizationResponse);
            }))
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }


}
