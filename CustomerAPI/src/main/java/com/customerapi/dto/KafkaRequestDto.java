package com.customerapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KafkaRequestDto {

	public KafkaRequestDto(String uuid, Integer string, Long mobile2, String message2) {
		this.ackId = uuid;
		this.acccountId = string;
		this.mobile = mobile2;
		this.message = message2;
	}
	private String ackId;
	private Integer acccountId;
	private Long mobile;
	private String message;
}
