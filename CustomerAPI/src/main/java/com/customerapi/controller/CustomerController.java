package com.customerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerapi.dto.ApiResponse;
import com.customerapi.dto.SendMsgDto;
import com.customerapi.service.CustomerService;

@RestController
@RequestMapping("/telco")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/sendmsg")
	public ApiResponse sendMassage(@RequestBody SendMsgDto sendMsgDto) {

		if (sendMsgDto.getMobile() == null || sendMsgDto.getMobile().toString().length() != 10) {
			// Note : Integer Data Range -2147483648 to 2147483647
			return new ApiResponse(HttpStatus.BAD_REQUEST, "mobile number is empty or size is not equals 10.");
		}

		if (sendMsgDto.getMessage().isEmpty()) {
			return new ApiResponse(HttpStatus.BAD_REQUEST, "message parameter is empty.");
		}

		return customerService.sendMassage(sendMsgDto);
	}
}
