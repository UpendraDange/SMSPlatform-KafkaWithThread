package com.internaldb.dto;

import lombok.Data;

@Data
public class SendMsgDto {

	private String username;
	private String password;
	private Long mobile;
	private String message;

	public SendMsgDto(String username, String password2, Long mobile2, String message2) {
		this.username = username;
		this.password = password2;
		this.mobile = mobile2;
		this.message = message2;

	}

}
