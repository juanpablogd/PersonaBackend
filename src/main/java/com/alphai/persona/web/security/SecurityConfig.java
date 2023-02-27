package com.alphai.persona.web.security;

import com.alphai.persona.dominio.servicio.AiUserDetailsService;
import com.alphai.persona.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private AiUserDetailsService aiDetailsService;
    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(aiDetailsService)
                .and()
                .build();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
            return         http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest()
                .authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
    }

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.aiDetailsService(aiDetailsService);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic().and().authenticationManager(authenticationManager);

        return http.build();
    }*/

}