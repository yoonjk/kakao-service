package com.demo.microservices.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.demo.microservices.service.KakaoOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final KakaoOAuth2UserService kakaoOAuth2UserService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login().userInfoEndpoint().userService(kakaoOAuth2UserService); 
    }
}
