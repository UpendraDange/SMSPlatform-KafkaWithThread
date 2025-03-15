package com.internaldb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internaldb.dto.ApiResponse;
import com.internaldb.dto.KafkaRequestDto;
import com.internaldb.dto.SendMsgDto;
import com.internaldb.service.UserService;

@RestController
@RequestMapping("/internaldb")
public class InternaldbController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/validate/user")
	public ApiResponse createUser(@RequestBody SendMsgDto sendMsgDto) {

		return userService.validateUser(sendMsgDto);
	}
	
	@PostMapping("/store/msg")
	public ApiResponse storeMsg(@RequestBody KafkaRequestDto kafkaRequestDto) {

		return userService.storeMsg(kafkaRequestDto);
	}
}
