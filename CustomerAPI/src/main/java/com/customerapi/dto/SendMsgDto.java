package com.customerapi.dto;

import lombok.Data;

@Data
public class SendMsgDto {

	private String username;
	private String password;
	private Long mobile;
	private String message;

}
