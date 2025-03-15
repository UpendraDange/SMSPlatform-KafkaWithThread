package com.kafkaconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaRequestDto {

	private String ackId;
	private Integer acccountId;
	private Long mobile;
	private String message;
}
