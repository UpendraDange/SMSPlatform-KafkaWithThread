package com.customerapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.customerapi.config.AppConstants;
import com.customerapi.dto.KafkaRequestDto;

@Service
public class KafkaService {

	@Autowired
	private KafkaTemplate<String, KafkaRequestDto> kafkaTemplate;
	public boolean updateSms(KafkaRequestDto kafkaRequestDto) {
	
		System.out.println("Sending kafka massage::"+ kafkaRequestDto);
		this.kafkaTemplate.send(AppConstants.SMS_UPDATE_TOPIC,kafkaRequestDto);
		return true;
	}
}
