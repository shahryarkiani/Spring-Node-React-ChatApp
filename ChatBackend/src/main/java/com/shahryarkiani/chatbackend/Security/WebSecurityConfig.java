package com.shahryarkiani.chatbackend.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(10); }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable().cors().disable()
                .authorizeHttpRequests((req) -> req
                        .requestMatchers(HttpMethod.POST, "/api/users/login/**", "/api/users").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .rememberMe((remember) -> remember
                        .key("SecretKeyThatShouldNotBeExposed").alwaysRemember(true).tokenValiditySeconds(60 * 60 * 72)
                        .rememberMeCookieName("CHAT_APP_REMEMBER"))
                .formLogin().loginProcessingUrl("/api/users/login")
                .successHandler(((req, res, auth) -> res.setStatus(200)))
                .failureHandler((req, res, except) -> res.setStatus(403))
                .and().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint())
        ;
        return http.build();
    }

}
