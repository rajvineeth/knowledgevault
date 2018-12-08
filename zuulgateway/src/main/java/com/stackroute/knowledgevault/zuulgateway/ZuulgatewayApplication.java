package com.stackroute.knowledgevault.zuulgateway;

import filters.ErrorFilter;
import filters.PostFilter;
import filters.PreFilter;
import filters.RouteFilter;
import filters.CORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class ZuulgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulgatewayApplication.class, args);
	}
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

	@Bean 
	public CORSFilter corsFilter() {
		return new CORSFilter();
	}

	// @Bean
	// public CorsFilter corsFilter() {
	// 	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	// 	final CorsConfiguration config = new CorsConfiguration();
	// 	config.setAllowCredentials(true);
	// 	config.setAllowedOrigins(Collections.singletonList("*"));
	// 	config.setAllowedHeaders(Collections.singletonList("*"));
	// 	config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
	// 	source.registerCorsConfiguration("/**", config);
	// 	return new CorsFilter(source);
	// }

}
