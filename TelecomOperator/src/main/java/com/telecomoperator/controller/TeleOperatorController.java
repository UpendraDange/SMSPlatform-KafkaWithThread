package com.telecomoperator.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecomoperator.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/telco")
@Slf4j
public class TeleOperatorController {

	@GetMapping("/smsc/{account_id}/{mobile}/{message}")
	public ApiResponse sendMassage(@PathVariable Integer account_id, @PathVariable Long mobile,
			@PathVariable String message) {
		log.info("Got API request");

		try {
			if (account_id.toString().isEmpty() || mobile.toString().length() < 10 || message.isEmpty()) {
				return new ApiResponse(HttpStatus.BAD_REQUEST, "FAILURE", "");
			}
			UUID uuid = UUID.randomUUID();
			return new ApiResponse(HttpStatus.OK, "SUCCESS" + "~~" + uuid, "");
		} catch (Exception e) {
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error");
		}
	}
}
