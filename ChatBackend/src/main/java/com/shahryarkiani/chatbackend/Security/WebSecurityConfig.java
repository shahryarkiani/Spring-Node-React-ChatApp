package com.shahryarkiani.chatbackend.Security;

import com.shahryarkiani.chatbackend.User.UserRepository;
import com.shahryarkiani.chatbackend.User.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin()
                 .loginPage("/index.html")
                 .loginProcessingUrl("/api/users/login")
                 .permitAll()
                 .and()
                .logout()
                 .logoutUrl("api/users/logout")
                 .permitAll()
                 .and()
                .authorizeHttpRequests((req) -> req
                        .requestMatchers("/", "/index.html").permitAll()
                        .anyRequest().authenticated()
                );


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserService(userRepository);
    }


}
