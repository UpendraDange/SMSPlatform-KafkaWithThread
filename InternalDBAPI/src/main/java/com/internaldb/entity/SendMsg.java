package com.internaldb.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.internaldb.dto.KafkaRequestDto;

import lombok.Data;

@Entity
@Table(name = "send_msg")
@Data
public class SendMsg {

	public SendMsg(KafkaRequestDto kafkaRequestDto) {
		this.mobile = kafkaRequestDto.getMobile();
		this.message = kafkaRequestDto.getMessage();
		this.status = "NEW";
		this.accountId = kafkaRequestDto.getAcccountId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long mobile;
	private String message;
	private String status;
	private LocalDateTime receivedTs;
	private LocalDateTime sendTs;
	private Integer accountId;
	private String telcoResponse;

	@PrePersist
	protected void onCreate() {
		receivedTs = LocalDateTime.now();
	}
}
