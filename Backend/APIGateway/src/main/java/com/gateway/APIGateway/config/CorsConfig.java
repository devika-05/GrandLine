package com.gateway.APIGateway.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsWebFilter;

import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration

public class CorsConfig {

	

	@Bean

	public CorsWebFilter corsWebFilter() {

		CorsConfiguration corsConfig = new CorsConfiguration();

		corsConfig.addAllowedOrigin("http://localhost:4200"); // Allow all origins

		corsConfig.addAllowedHeader("*"); // Allow all headers

		corsConfig.addAllowedMethod("*"); // Allow all methods

		corsConfig.setAllowCredentials(true); // Allow credentials

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);

	}

}

