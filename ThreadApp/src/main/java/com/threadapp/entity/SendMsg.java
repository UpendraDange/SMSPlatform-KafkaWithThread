package com.threadapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "send_msg")
@Data
public class SendMsg {


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
