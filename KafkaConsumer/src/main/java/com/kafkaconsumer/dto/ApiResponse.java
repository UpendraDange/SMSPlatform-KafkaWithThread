package com.kafkaconsumer.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
	private HttpStatus status;
	private Object data;
	private String error;

	public ApiResponse(HttpStatus status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.error = message;

	}

	public ApiResponse(HttpStatus status, String message) {
		this.status = status;
		this.error = message;
	}
}
