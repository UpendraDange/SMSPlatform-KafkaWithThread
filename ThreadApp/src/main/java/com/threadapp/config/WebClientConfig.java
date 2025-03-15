package com.threadapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return WebClient.builder()
                .baseUrl(AppConstants.WEB_CLIENT_IP) 
                .build();
	}
}
